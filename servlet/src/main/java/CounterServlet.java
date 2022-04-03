import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {
  private final String SUBTRACTION_HEADER = "Subtraction-Value";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();
    writer.println(Counter.get());
    writer.flush();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Counter.increment();
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String subtractionString = req.getHeader(SUBTRACTION_HEADER);
    if (subtractionString != null) {
      try {
        int subtractionInteger = Integer.parseInt(subtractionString);
        Counter.subtract(subtractionInteger);
      } catch (NumberFormatException e) {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Subtraction-Value should be an integer!");
      }
    } else {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Subtraction-Value header not found!");
    }
  }
}
