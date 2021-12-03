package api.v1.viri;

import Entitete.Uporabnik;
import Zrno.UporabnikZrno;
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
@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class UporabnikiVir {

    private Logger logger = Logger.getLogger(UporabnikiVir.class.getName());

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private UpravljanjePolnilnicZrno upravljanjePolnilnicZrno;

   @GET
    public Response vrniUporabnike(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = (List<Uporabnik>) uporabnikZrno.pridobiUporabnike(query);
        Long steviloUporabnikov = uporabnikZrno.pridobiUporabnikeCount(query);
        return Response.status(Response.Status.OK).entity(uporabniki).header("X-Total-Count", steviloUporabnikov).build();
    }
    @GET
    @Path("{id}")
    public Response vrniUporabnika(@PathParam("id") int id){
       Uporabnik uporabnik = uporabnikZrno.pridobiUporabnika(id);
       if(uporabnik != null){
           return Response.status(Response.Status.OK).entity(uporabnik).build();
       }else{
           return Response.status(Response.Status.NOT_FOUND).build();
       }
    }

    @POST
    public Response dodajUporabnika(Uporabnik uporabnik) {
        Uporabnik u = uporabnikZrno.dodajUporabnika((uporabnik));
        if(uporabnik != null){
            return Response.status(Response.Status.CREATED).entity(u).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@PathParam("id") int id, Uporabnik uporabnik) {
        var uporabnikPosodobljen = uporabnikZrno.posodobiUporabnika(id, uporabnik);
        if (uporabnikPosodobljen != null) {
            return Response.status(Response.Status.OK).entity(uporabnikPosodobljen).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@PathParam("id") int id) {
        var success = uporabnikZrno.odstraniUporabnika(id);
        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
