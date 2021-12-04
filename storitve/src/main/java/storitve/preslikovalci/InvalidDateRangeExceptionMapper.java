package storitve.preslikovalci;

/**
 * InvalidDateRangeExceptionMapper
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import storitve.izjeme.InvalidDateRangeException;
@Provider
public class InvalidDateRangeExceptionMapper implements ExceptionMapper<InvalidDateRangeException>{

    @Override
    public Response toResponse(InvalidDateRangeException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"napaka\": \"" + e.getMessage() + "\"}")
                .build();
    }
}