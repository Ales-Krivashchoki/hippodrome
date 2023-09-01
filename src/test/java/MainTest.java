import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    @Disabled
    @Test
    @Timeout(22)
    void shouldTimeoutMain() throws Exception {
        Main.main(null);
    }
}
