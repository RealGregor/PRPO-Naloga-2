package api.v1.viri;

import Entitete.Najem;
import Zrno.NajemZrno;
import Zrno.UpravljanjePolnilnicZrno;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import DTO.NajemDTO;
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
@Path("najemi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class NajemVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private NajemZrno najemZrno;

    @Inject
    private UpravljanjePolnilnicZrno upravljanjePolnilnicZrno;

    @Operation(description = "Dodaj najem.", summary = "Dodajanje najema")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Najem uspešno dodan."
            ),
            @APIResponse(responseCode = "405", description = "Validacijska napaka."),
    })
    @POST
    public Response dodajNajem(@RequestBody(
            description = "DTO objekt za dodajanje najema.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Najem.class))) NajemDTO najemDTO) {
        Najem najem = upravljanjePolnilnicZrno.najemPolnilnice(najemDTO);

        if (najem != null) {
            return Response.status(Response.Status.CREATED).entity(najem).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(description = "Vrne seznam najemov.", summary = "Seznam najemov")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Seznam najemov.",
                    content = @Content(
                            schema = @Schema(implementation = Najem.class)),
                    headers = {@Header(name = "X-Total-Count", description = "Število vrnjenih najemov.")}
            ),
            @APIResponse(responseCode = "404", description = "Najemi not found")
    })
    @GET
    public Response vrniNajeme() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Najem> postaje = (List<Najem>) najemZrno.pridobiNajeme(query);
        Long steviloNajemov = najemZrno.pridobiNajemeCount(query);
        return Response.status(Response.Status.OK).entity(postaje).header("X-Total-Count", steviloNajemov).build();
    }

    @Operation(description = "Vrne podrobnosti najema.", summary = "Podrobnosti najema")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Podrobnosti najema.",
                    content = @Content(
                            schema = @Schema(implementation = Najem.class))
            ),
            @APIResponse(responseCode = "404", description = "Najem ne obstaja")
    })
    @GET
    @Path("{id}")
    public Response vrniNajem(@Parameter(
            description = "Identifikator najema.",
            required = true) @PathParam("id") int id) {

        Najem najem = najemZrno.pridobiNajem(id);
        if (najem != null) {
            return Response.status(Response.Status.OK).entity(najem).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Posodobi najem.", summary = "Posodabljanje najema")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Najem uspešno posodobljen."),
            @APIResponse(responseCode = "404", description = "Najem ne obstaja")
    })
    @PUT
    @Path("{id}")
    public Response posodobiNajem(@Parameter(
            description = "Identifikator najema.",
            required = true) @PathParam("id") int id, @RequestBody(
            description = "Objekt za dodajanje najema.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Najem.class))) Najem najem) {
        var updatednajem = najemZrno.posodobiNajem(id, najem);

        if (updatednajem != null) {
            return Response.status(Response.Status.OK).entity(najem).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Izbriši najem.", summary = "Brisanje najema")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Najem uspešno izbrisan."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Najem ne obstaja.")
    })
    @DELETE
    @Path("{id}")
    public Response odstraniNajem(@Parameter(
            description = "Identifikator najema.",
            required = true) @PathParam("id") int id) {
        var success = najemZrno.odstraniNajem(id);

        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
