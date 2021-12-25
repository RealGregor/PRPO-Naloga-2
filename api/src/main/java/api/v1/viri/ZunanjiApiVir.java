package api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;


@RequestScoped
@Path("covidData")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")

public class ZunanjiApiVir {

    private Logger logger = Logger.getLogger(UporabnikiVir.class.getName());

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response vrniUporabnike(){
        HttpResponse<String> response;
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://covid-19-data.p.rapidapi.com/country/code?code=si&format=json"))
                    .header("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
                    .header("x-rapidapi-key", "5079ab9f5cmshc1f4e94f23a69b4p1068f3jsne20867807d82")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }catch (WebApplicationException | ProcessingException | IOException | InterruptedException e){
            logger.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return Response.status(Response.Status.CREATED).entity(response.body()).build();
    }
}

