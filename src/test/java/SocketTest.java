import org.junit.*;

import java.net.SocketException;

import static junit.framework.TestCase.assertFalse;

public class SocketTest {

    @Test
    public void testSocket() throws SocketException {
        Coffee coffee = new Coffee(false,false);
        Coffee coffee2 = new Coffee(false,false);
        assertFalse(coffee.both());

    }
}
