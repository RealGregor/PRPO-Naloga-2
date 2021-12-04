package api.v1.viri;

import DTO.DodajLastnistvoDTO;
import Entitete.Lastnistvo;
import Zrno.LastnistvoZrno;
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
import java.util.logging.Logger;

@ApplicationScoped
@Path("lastnistva")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class LastnistvoVir {

    private Logger logger = Logger.getLogger(LastnistvoVir.class.getName());
    @Context
    protected UriInfo uriInfo;

    @Inject
    private LastnistvoZrno lastnistvoZrno;

    @Inject
    private UpravljanjePolnilnicZrno upravljanjePolnilnicZrno;

    @Operation(description = "Vrne seznam lastnistev.", summary = "Seznam lastnistev")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Seznam lastnistev.",
                    content = @Content(
                            schema = @Schema(implementation = Lastnistvo.class)),
                    headers = {@Header(name = "X-Total-Count", description = "Število vrnjenih lastnistev.")}
            ),
            @APIResponse(responseCode = "404", description = "Lastnistvo not found")
    })
    @GET
    public Response vrniLastnistva(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Lastnistvo> lastnistva = (List<Lastnistvo>) lastnistvoZrno.pridobiLastnistva(query);
        Long steviloLastnistev = lastnistvoZrno.pridobiLastnistvaCount(query);
        return Response.status(Response.Status.OK).entity(lastnistva).header("X-Total-Count", steviloLastnistev).build();
    }

    @Operation(description = "Vrne podrobnosti lastnistva.", summary = "Podrobnosti lastnistva")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Podrobnosti lastnistva.",
                    content = @Content(
                            schema = @Schema(implementation = Lastnistvo.class))
            ),
            @APIResponse(responseCode = "404", description = "Lastnistvo ne obstaja")
    })
    @GET
    @Path("{id}")
    public Response vrniLastnistvo(@Parameter(
            description = "Identifikator lastnistva.",
            required = true) @PathParam("id") int id){
        Lastnistvo lastnistvo = lastnistvoZrno.pridobiLastnistvo(id);
        if(lastnistvo != null){
            return Response.status(Response.Status.OK).entity(lastnistvo).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Dodaj lastnistvo.", summary = "Dodajanje lastnistva")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Lastnistvo uspešno dodan."
            ),
            @APIResponse(responseCode = "405", description = "Validacijska napaka."),
    })
    @POST
    public Response dodajLastnistvo(@RequestBody(
            description = "DTO objekt za dodajanje lastnistva.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Lastnistvo.class))) DodajLastnistvoDTO dodajLastnistvoDTO) {
        Lastnistvo novo = upravljanjePolnilnicZrno.dodajLastnistvo(dodajLastnistvoDTO);
        if(novo != null){
            return Response.status(Response.Status.CREATED).entity(novo).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Posodobi lastnistvo.", summary = "Posodabljanje lastnistva")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Lasnistvo uspešno posodobljen."),
            @APIResponse(responseCode = "404", description = "Lasnistvo ne obstaja")
    })
    @PUT
    @Path("{id}")
    public Response posodobiLastnistvo(@Parameter(
            description = "Identifikator lastnistva.",
            required = true) @PathParam("id") int id, @RequestBody(
            description = "Objekt za posodobitev lastnistva.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Lastnistvo.class))) Lastnistvo lastnistvo) {
        var lastnistvoPosodobljeno = lastnistvoZrno.posodobiLastnistvo(id, lastnistvo);
        if (lastnistvoPosodobljeno != null) {
            return Response.status(Response.Status.OK).entity(lastnistvoPosodobljeno).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Izbriši lastnistvo.", summary = "Brisanje lastnistva")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Lastnistvo uspešno izbrisano."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Lastnistvo ne obstaja.")
    })
    @DELETE
    @Path("{id}")
    public Response odstraniLastnistvo(@Parameter(
            description = "Identifikator lastnistva.",
            required = true) @PathParam("id") int id) {
        var success = lastnistvoZrno.odstraniLastnistvo(id);
        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
