import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/counter")
public class CounterServlet extends HttpServlet {
  private final String SUBTRACTION_HEADER = "Subtraction-Value";
  private final String COOKIE_NAME = "hh-auth";
  private final int MIN_COOKIE_LENGTH = 11;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response get() {
    return Response.ok().entity(new ResponseEntity()).build();
  }

  @POST
  public Response post() {
    Counter.increment();
    return Response.ok().build();
  }

  @DELETE
  public Response delete(@HeaderParam(SUBTRACTION_HEADER) String subtractionString) {
    if (subtractionString != null) {
      try {
        int subtractionInteger = Integer.parseInt(subtractionString);
        Counter.subtract(subtractionInteger);
        return Response.ok().build();
      } catch (NumberFormatException e) {
        return Response.status(404, "Subtraction-Value should be an integer!").build();
      }
    } else {
      return Response.status(404, "Subtraction-Value header not found!").build();
    }
  }

  @POST
  @Path("/clear")
  public Response postClear(@CookieParam(COOKIE_NAME) String cookie) {
    if (cookie != null && cookie.length() >= MIN_COOKIE_LENGTH) {
      Counter.clear();
      return Response.ok().build();
    } else {
      return Response.status(403, "hh-auth cookie not found or has an inappropriate format!").build();
    }
  }
}
