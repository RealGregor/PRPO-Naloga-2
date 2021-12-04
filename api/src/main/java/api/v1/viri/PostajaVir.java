package api.v1.viri;

import Entitete.Postaja;

import Entitete.Uporabnik;
import Zrno.PostajaZrno;
import Zrno.UpravljanjePolnilnicZrno;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
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
@Path("postaje")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class PostajaVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private PostajaZrno postajaZrno;

    @Inject
    private UpravljanjePolnilnicZrno upravljanjePolnilnicZrno;

    @Operation(description = "Dodaj postajo.", summary = "Dodajanje postaje")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Postaja uspešno dodana."
            ),
            @APIResponse(responseCode = "405", description = "Validacijska napaka.")
    })
    @POST
    public Response dodajPostajo(@RequestBody(
            description = "Objekt za dodajanje postaje.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Postaja.class))) Postaja postaja) {
        if (postaja != null) {
            postajaZrno.dodajPostajo(postaja);
            return Response.status(Response.Status.CREATED).entity(postaja).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(description = "Vrne seznam postaj.", summary = "Seznam postaj")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Seznam postaj.",
                    content = @Content(
                            schema = @Schema(implementation = Postaja.class)),
                    headers = {@Header(name = "X-Total-Count", description = "Število vrnjenih postaj.")}
            ),
            @APIResponse(responseCode = "404", description = "Postaje not found")
    })
    @GET
    public Response vrniPostaje() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Postaja> postaje = (List<Postaja>) postajaZrno.pridobiPostaje(query);
        Long steviloPostaj = postajaZrno.pridobiPostajeCount(query);
        return Response.status(Response.Status.OK).entity(postaje).header("X-Total-Count", steviloPostaj).build();
    }

    @Operation(description = "Vrne podrobnosti postaje.", summary = "Podrobnosti postaje")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Podrobnosti postaje.",
                    content = @Content(
                            schema = @Schema(implementation = Postaja.class))
            ),
            @APIResponse(responseCode = "404", description = "Postaja ne obstaja")
    })
    @GET
    @Path("{id}")
    public Response vrniPostajo(@Parameter(
            description = "Identifikator postaje.",
            required = true) @PathParam("id") int id) {
        Postaja postaja = postajaZrno.pridobiPostajo(id);
        if (postaja != null) {
            return Response.status(Response.Status.OK).entity(postaja).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Posodobi postajo.", summary = "Posodabljanje postaje")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Postaja uspešno posodobljena."),
            @APIResponse(responseCode = "404", description = "Postaja ne obstaja")
    })
    @PUT
    @Path("{id}")
    public Response posodobiPostajo(@Parameter(
            description = "Identifikator postaje za posodobitev.",
            required = true) @PathParam("id") int id, @RequestBody(
            description = "Objekt za posodabljanje postaje.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Uporabnik.class)
            )
    ) Postaja postaja) {
        var updatedPostaja = postajaZrno.posodobiPostajo(id, postaja);

        if (updatedPostaja != null) {
            return Response.status(Response.Status.OK).entity(postaja).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Izbriši postajo.", summary = "Brisanje postaje")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Postaja uspešno izbrisana."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Postaja ne obstaja.")
    })
    @DELETE
    @Path("{id}")
    public Response odstraniPostajo(@Parameter(
            description = "Identifikator postaje za brisanje.",
            required = true) @PathParam("id") int id) {
        var success = postajaZrno.odstraniPostajo(id);

        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
