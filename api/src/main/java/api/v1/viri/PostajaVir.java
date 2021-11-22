package api.v1.viri;

import Entitete.Postaja;

import Zrno.PostajaZrno;
import Zrno.UpravljanjePolnilnicZrno;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("postaje")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class PostajaVir {

    @Inject
    private PostajaZrno postajaZrno;

    @Inject
    private UpravljanjePolnilnicZrno upravljanjePolnilnicZrno;

    @POST
    public Response dodajPostajo(Postaja postaja) {
        if (postaja != null) {
            postajaZrno.dodajPostajo(postaja);
            return Response.status(Response.Status.CREATED).entity(postaja).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    public Response vrniPostaje() {

        List<Postaja> postaje = (List<Postaja>) postajaZrno.pridobiPostaje();
        return Response.status(Response.Status.OK).entity(postaje).build();
    }

    @GET
    @Path("{id}")
    public Response vrniPostajo(@PathParam("id") int id) {
        Postaja postaja = postajaZrno.pridobiPostajo(id);
        if (postaja != null) {
            return Response.status(Response.Status.OK).entity(postaja).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response posodobiPostajo(@PathParam("id") int id, Postaja postaja) {
        var updatedPostaja = postajaZrno.posodobiPostajo(id, postaja);

        if (updatedPostaja != null) {
            return Response.status(Response.Status.OK).entity(postaja).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response odstraniPostajo(@PathParam("id") int id) {
        var success = postajaZrno.odstraniPostajo(id);

        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
