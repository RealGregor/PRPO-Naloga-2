package api.v1.viri;

import Entitete.Uporabnik;
import Zrno.UporabnikZrno;
import Zrno.UpravljanjePolnilnicZrno;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(description = "Vrne seznam uporabnikov.", summary = "Seznam Uporabnikov",
            tags = "uporabniki")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Seznam uporabnikov.",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Uporabnik.class))),
                    headers = {@Header(name = "X-Total-Count", description = "Število vrnjenih uporabnikov.")}
            ),
            @ApiResponse(responseCode = "404", description = "Uporabniki not found")
    })
   @GET
    public Response vrniUporabnike(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = (List<Uporabnik>) uporabnikZrno.pridobiUporabnike(query);
        Long steviloUporabnikov = uporabnikZrno.pridobiUporabnikeCount(query);
        return Response.status(Response.Status.OK).entity(uporabniki).header("X-Total-Count", steviloUporabnikov).build();
    }

    @Operation(description = "Vrne podrobnosti uporabnika.", summary = "Podrobnosti uporabnika",
            tags = "uporabniki")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Podrobnosti uporabnika.",
                    content = @Content(
                            schema = @Schema(implementation = Uporabnik.class))
            )
    })
    @GET
    @Path("{id}")
    public Response vrniUporabnika(@Parameter(
            description = "Identifikator uporabnika.",
            required = true) @PathParam("id") int id){
       Uporabnik uporabnik = uporabnikZrno.pridobiUporabnika(id);
       if(uporabnik != null){
           return Response.status(Response.Status.OK).entity(uporabnik).build();
       }else{
           return Response.status(Response.Status.NOT_FOUND).build();
       }
    }

    @Operation(description = "Dodaj uporabnika.", summary = "Dodajanje uporabnika",
            tags = "uporabniki")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Uporabnik uspešno dodan."
            ),
            @ApiResponse(responseCode = "405", description = "Validacijska napaka.")
    })
    @POST
    public Response dodajUporabnika(@RequestBody(
            description = "Objekt za dodajanje uporabnika.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Uporabnik.class))) Uporabnik uporabnik) {
        Uporabnik u = uporabnikZrno.dodajUporabnika((uporabnik));
        if(uporabnik != null){
            return Response.status(Response.Status.CREATED).entity(u).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @Operation(description = "Posodobi uporabnika.", summary = "Posodabljanje uporabnika",
            tags = "uporabniki")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "uporabniki uspešno posodobljen.")
    })
    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@Parameter(
            description = "Identifikator uporabnika za posodobitev.",
            required = true) @PathParam("id") int id, @RequestBody(
            description = "Objekt za posodabljanje uporabnika.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Uporabnik.class)
            )
    ) Uporabnik uporabnik) {
        var uporabnikPosodobljen = uporabnikZrno.posodobiUporabnika(id, uporabnik);
        if (uporabnikPosodobljen != null) {
            return Response.status(Response.Status.OK).entity(uporabnikPosodobljen).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Izbriši uporabnika.", summary = "Brisanje uporabnika",
            tags = "uporabniki")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Uporabnik uspešno izbrisan."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Uporabnik ne obstaja.")
    })
    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@Parameter(
            description = "Identifikator uporabnika za brisanje.",
            required = true) @PathParam("id") int id) {
        var success = uporabnikZrno.odstraniUporabnika(id);
        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
