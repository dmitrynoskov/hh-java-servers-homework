import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseEntity {
  public final String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
  public final int value = Counter.get();
}
