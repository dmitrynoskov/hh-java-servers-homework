import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
  private static final AtomicInteger counter = new AtomicInteger(0);

  public static int get() {
    return counter.get();
  }

  public static void subtract(int delta) {
    counter.addAndGet(-1 * delta);
  }

  public static void increment() {
    counter.incrementAndGet();
  }

  public static void clear() {
    counter.set(0);
  }
}
