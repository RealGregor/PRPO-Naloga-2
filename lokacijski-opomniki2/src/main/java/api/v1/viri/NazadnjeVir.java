package api.v1.viri;

import api.v1.dtos.Postaja;
import com.kumuluz.ee.security.annotations.Secure;
import zrna.PostajeZadnjeZrno;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("zadnje_postaje")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@Secure
public class NazadnjeVir {

    @Inject
    protected PostajeZadnjeZrno postajaZadnjeZrno;

    @GET
    //@RolesAllowed("user")
    public Response vrniZadnjeTri(){

        return Response.status(Response.Status.OK).entity(postajaZadnjeZrno.vrniZadnjeRezerviranePostaje()).build();
    }

    @POST
    // @PermitAll
    public Response vstaviZadnjoRezervacijo(Postaja postaja){

        postajaZadnjeZrno.dodajNovoRezerviranoPostajo(postaja);
        //vrne posodoboljene zadnje 3 rezervirane postaje
        return Response.status(Response.Status.CREATED).entity(postajaZadnjeZrno.vrniZadnjeRezerviranePostaje()).build();
    }
}
