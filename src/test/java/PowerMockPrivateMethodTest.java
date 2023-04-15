import org.junit.Test;
import org.junit.runner.RunWith;
import org.manoj.ClassWithPrivateMethods;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.manoj.*")
public class PowerMockPrivateMethodTest {

    @Test
    public void testClassWithPrivateMethods_printMessage_privateMethod() throws Exception {

        // 1. Create a generic message string
        String message = "Hello PowerMockito";

        // 2. Create a generic string expectation
        String expectation = "Expectation";


        // 3. Create a mock object of the class under test using spy(..) method
        ClassWithPrivateMethods mock = PowerMockito.spy(new ClassWithPrivateMethods());

        // 4. We set the expectation on the mocked object's private method using Java Reflection API by providing method name as a String paramter to when() method
        PowerMockito.doReturn(expectation).when(mock, "printMessage", message);

        // 5. Invoke the public method which in turn invokes the private method
        String actual = mock.privateCall(message);

        assertEquals(actual, expectation);

    }

}
