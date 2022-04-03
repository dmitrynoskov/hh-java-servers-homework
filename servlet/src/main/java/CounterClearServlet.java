import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CounterClearServlet extends HttpServlet {
  private final String COOKIE_NAME = "hh-auth";
  private final int MIN_COOKIE_LENGTH = 11;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (checkCookie(req)) {
      Counter.clear();
    } else {
      resp.sendError(HttpServletResponse.SC_FORBIDDEN, "hh-auth cookie not found or has an inappropriate format!");
    }
  }

  private boolean checkCookie(HttpServletRequest req) {
    return req.getCookies() != null && Arrays.stream(req.getCookies()).
            filter(cookie -> cookie.getName().equals(COOKIE_NAME))
            .anyMatch(cookie -> cookie.getValue().length() >= MIN_COOKIE_LENGTH);
  }
}
