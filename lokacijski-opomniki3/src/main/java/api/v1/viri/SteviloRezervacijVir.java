package api.v1.viri;
import zrna.PostajeRezervacijeZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("rezervacije")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SteviloRezervacijVir {
    @Inject
    protected PostajeRezervacijeZrno postajeRezervacijeZrno;

    @GET
    public Response vrniRezervacijePostaj(){
        return Response.status(Response.Status.OK).entity(postajeRezervacijeZrno.vrniSteviloRezervacijPostaj()).build();
    }

    @GET
    @Path("{id}")
    public Response vrniRezervacijePostaje(@PathParam("id") Integer postaja_id){
        return Response.status(Response.Status.OK).entity(postajeRezervacijeZrno.vrniSteviloRezervacijPostaje(postaja_id)).build();
    }

    @POST
    @Path("{id}")
    public Response povecajSteviloRezervacij(@PathParam("id") Integer postaja_id){
        return Response.status(Response.Status.OK).entity(postajeRezervacijeZrno.povecajSteviloRezervacijPostaje(postaja_id)).build();
    }
}
