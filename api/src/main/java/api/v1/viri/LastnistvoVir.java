package api.v1.viri;

import DTO.DodajLastnistvoDTO;
import Entitete.Lastnistvo;
import Zrno.LastnistvoZrno;
import Zrno.UpravljanjePolnilnicZrno;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("lastnistva")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class LastnistvoVir {

    private Logger logger = Logger.getLogger(LastnistvoVir.class.getName());
    @Context
    protected UriInfo uriInfo;

    @Inject
    private LastnistvoZrno lastnistvoZrno;

    @Inject
    private UpravljanjePolnilnicZrno upravljanjePolnilnicZrno;

    @GET
    public Response vrniLastnistva(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Lastnistvo> lastnistva = (List<Lastnistvo>) lastnistvoZrno.pridobiLastnistva(query);
        Long steviloLastnistev = lastnistvoZrno.pridobiLastnistvaCount(query);
        return Response.status(Response.Status.OK).entity(lastnistva).header("X-Total-Count", steviloLastnistev).build();
    }
    @GET
    @Path("{id}")
    public Response vrniLastnistvo(@PathParam("id") int id){
        Lastnistvo lastnistvo = lastnistvoZrno.pridobiLastnistvo(id);
        if(lastnistvo != null){
            return Response.status(Response.Status.OK).entity(lastnistvo).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajLastnistvo(DodajLastnistvoDTO dodajLastnistvoDTO) {
        Lastnistvo novo = upravljanjePolnilnicZrno.dodajLastnistvo(dodajLastnistvoDTO);
        if(novo != null){
            return Response.status(Response.Status.CREATED).entity(novo).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response posodobiLastnistvo(@PathParam("id") int id, Lastnistvo lastnistvo) {
        var lastnistvoPosodobljeno = lastnistvoZrno.posodobiLastnistvo(id, lastnistvo);
        if (lastnistvoPosodobljeno != null) {
            return Response.status(Response.Status.OK).entity(lastnistvoPosodobljeno).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response odstraniLastnistvo(@PathParam("id") int id) {
        var success = lastnistvoZrno.odstraniLastnistvo(id);
        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
