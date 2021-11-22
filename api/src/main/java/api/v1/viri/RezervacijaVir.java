package api.v1.viri;

import Entitete.Rezervacija;

import Zrno.RezervacijaZrno;
import Zrno.UpravljanjePolnilnicZrno;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import DTO.RezervacijaDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("rezervacije")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class RezervacijaVir {

    @Inject
    private RezervacijaZrno rezervacijaZrno;

    @Inject
    private UpravljanjePolnilnicZrno upravljanjePolnilnicZrno;

    @POST
    public Response dodajRezervacijo(RezervacijaDTO rezervacijaDTO) {
        Rezervacija rezervacija = upravljanjePolnilnicZrno.rezervacijaPolnilnice(rezervacijaDTO);

        if (rezervacija != null) {
            return Response.status(Response.Status.CREATED).entity(rezervacija).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    public Response vrniRezervacije() {

        List<Rezervacija> rezervacije = (List<Rezervacija>) rezervacijaZrno.pridobiRezervacije();
        return Response.status(Response.Status.OK).entity(rezervacije).build();
    }

    @GET
    @Path("{id}")
    public Response vrniRezervacijo(@PathParam("id") int id) {
        Rezervacija rezervacija = rezervacijaZrno.pridobiRezervacijo(id);
        if (rezervacija != null) {
            return Response.status(Response.Status.OK).entity(rezervacija).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response posodobiRezervacijo(@PathParam("id") int id, Rezervacija rezervacija) {
        var updatedRezervacija = rezervacijaZrno.posodobiRezervacijo(id, rezervacija);

        if (updatedRezervacija != null) {
            return Response.status(Response.Status.OK).entity(rezervacija).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response odstraniRezervacijo(@PathParam("id") int id) {
        var success = rezervacijaZrno.odstraniRezervacijo(id);

        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
