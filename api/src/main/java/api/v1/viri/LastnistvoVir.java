package api.v1.viri;

import Entitete.Lastnistvo;
import Zrno.LastnistvoZrno;
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

    @GET
    public Response vrniLastnistva(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Lastnistvo> lastnistva = (List<Lastnistvo>) lastnistvoZrno.pridobiLastnistva();
        return Response.status(Response.Status.OK).entity(lastnistva).build();
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
    /*
    Za CDI zrna s CRUD operacijami, ki ste jih implementirali v prejšnji nalogi,
    dodajte vire REST (npr. UporabnikiVir), v katerih njihove metode izpostavite kot storitve REST.
    Storitve naj sprejemajo in vračajo JPA entitete.
    */
    @POST
    public Response dodajLastnistvo(Lastnistvo lastnistvo) {
        Lastnistvo novo = lastnistvoZrno.dodajLastnistvo((lastnistvo));
        if(novo != null){
            return Response.status(Response.Status.CREATED).entity(novo).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
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
