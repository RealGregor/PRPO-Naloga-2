package api.v1.viri;

import Entitete.Rezervacija;

import Zrno.RezervacijaZrno;
import Zrno.UpravljanjePolnilnicZrno;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import DTO.RezervacijaDTO;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("rezervacije")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class RezervacijaVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private RezervacijaZrno rezervacijaZrno;

    @Inject
    private UpravljanjePolnilnicZrno upravljanjePolnilnicZrno;

    @Operation(description = "Dodaj rezervacijo.", summary = "Dodajanje rezervacije")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Rezervacija uspešno dodana."
            ),
            @APIResponse(responseCode = "405", description = "Validacijska napaka."),
    })
    @POST
    public Response dodajRezervacijo(@RequestBody(
            description = "DTO objekt za dodajanje rezervacije.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Rezervacija.class))) RezervacijaDTO rezervacijaDTO) {
        Rezervacija rezervacija = upravljanjePolnilnicZrno.rezervacijaPolnilnice(rezervacijaDTO);

        if (rezervacija != null) {
            return Response.status(Response.Status.CREATED).entity(rezervacija).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(description = "Vrne seznam rezervacij.", summary = "Seznam rezervacij")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Seznam rezervacij.",
                    content = @Content(
                            schema = @Schema(implementation = Rezervacija.class)),
                    headers = {@Header(name = "X-Total-Count", description = "Število vrnjenih rezervacij.")}
            ),
            @APIResponse(responseCode = "404", description = "Rezervacije not found")
    })
    @GET
    public Response vrniRezervacije() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Rezervacija> rezervacije = (List<Rezervacija>) rezervacijaZrno.pridobiRezervacije(query);
        Long steviloRezervacij = rezervacijaZrno.pridobiRezervacijeCount(query);
        return Response.status(Response.Status.OK).entity(rezervacije).header("X-Total-Count", steviloRezervacij).build();
    }

    @Operation(description = "Vrne podrobnosti rezervacije.", summary = "Podrobnosti rezervacije")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Podrobnosti rezervacije.",
                    content = @Content(
                            schema = @Schema(implementation = Rezervacija.class))
            ),
            @APIResponse(responseCode = "404", description = "Rezervacija ne obstaja")
    })
    @GET
    @Path("{id}")
    public Response vrniRezervacijo(@Parameter(
            description = "Identifikator rezervacije.",
            required = true) @PathParam("id") int id) {
        Rezervacija rezervacija = rezervacijaZrno.pridobiRezervacijo(id);
        if (rezervacija != null) {
            return Response.status(Response.Status.OK).entity(rezervacija).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Posodobi rezervacijo.", summary = "Posodabljanje rezervacije")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Rezervacija uspešno posodobljena."),
            @APIResponse(responseCode = "404", description = "Rezervacija ne obstaja")
    })
    @PUT
    @Path("{id}")
    public Response posodobiRezervacijo(@Parameter(
            description = "Identifikator rezervacije.",
            required = true) @PathParam("id") int id, @RequestBody(
            description = "Objekt za posodobitev rezervacije.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Rezervacija.class))) Rezervacija rezervacija) {
        var updatedRezervacija = rezervacijaZrno.posodobiRezervacijo(id, rezervacija);

        if (updatedRezervacija != null) {
            return Response.status(Response.Status.OK).entity(rezervacija).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Izbriši rezervacijo.", summary = "Brisanje rezervacije")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Rezervacija uspešno izbrisana."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Rezervacija ne obstaja.")
    })
    @DELETE
    @Path("{id}")
    public Response odstraniRezervacijo(@Parameter(
            description = "Identifikator rezervacije.",
            required = true) @PathParam("id") int id) {
        var success = rezervacijaZrno.odstraniRezervacijo(id);

        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
