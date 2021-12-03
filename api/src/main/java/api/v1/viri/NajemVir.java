package api.v1.viri;

import Entitete.Najem;
import Zrno.NajemZrno;
import Zrno.UpravljanjePolnilnicZrno;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import DTO.NajemDTO;
import com.kumuluz.ee.rest.beans.QueryParameters;

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

    @POST
    public Response dodajNajem(NajemDTO najemDTO) {
        Najem najem = upravljanjePolnilnicZrno.najemPolnilnice(najemDTO);

        if (najem != null) {
            return Response.status(Response.Status.CREATED).entity(najem).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    public Response vrniNajeme() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Najem> postaje = (List<Najem>) najemZrno.pridobiNajeme(query);
        Long steviloNajemov = najemZrno.pridobiNajemeCount(query);
        return Response.status(Response.Status.OK).entity(postaje).header("X-Total-Count", steviloNajemov).build();
    }

    @GET
    @Path("{id}")
    public Response vrniNajem(@PathParam("id") int id) {

        Najem najem = najemZrno.pridobiNajem(id);
        if (najem != null) {
            return Response.status(Response.Status.OK).entity(najem).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response posodobiNajem(@PathParam("id") int id, Najem najem) {
        var updatednajem = najemZrno.posodobiNajem(id, najem);

        if (updatednajem != null) {
            return Response.status(Response.Status.OK).entity(najem).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response odstraniNajem(@PathParam("id") int id) {
        var success = najemZrno.odstraniNajem(id);

        if (success) {
            return Response.status(Response.Status.OK).entity(success).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
