package api.v1.viri;

import Entitete.Uporabnik;
import Zrno.LastnistvoZrno;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class UporabnikiVir {

    private Logger logger = Logger.getLogger(UporabnikZrno.class.getName());
    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private UpravljanjePolnilnicZrno upravljanjePolnilnicZrno;

   @GET
    public Response vrniUporabnike(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = (List<Uporabnik>) uporabnikZrno.pridobiUporabnike();
        return Response.status(Response.Status.OK).entity(uporabniki).build();
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


}
