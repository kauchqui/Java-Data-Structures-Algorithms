package utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
    @Test
    void getMiddleIndexTest()
    {
        assertEquals(Utils.getMiddleIndex(0, 10), 5);
    }
}
