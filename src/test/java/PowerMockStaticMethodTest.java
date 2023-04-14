import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manoj.ClassWithStaticMethod;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.manoj.*")
public class PowerMockStaticMethodTest {

    @Test
    public void testClassWithStaticMethod_printMessage_staticMethod(){

        // 1. Define a generic string message which will be using as parameter
        String message = "Hello PowerMockito";

        // 2. Another generic String message, to be used as an expectation.
        String expectation = "Expectation";

        // 3. Prepare ClassWithStaticMethod for static method test.
        PowerMockito.mockStatic(ClassWithStaticMethod.class);

        // 4. Preparing expectations when the static method will be invoked.
        PowerMockito.when(ClassWithStaticMethod.printMessage(message))
                .thenReturn(expectation);

        // 5. Invoking the static method.
        String actual = ClassWithStaticMethod.printMessage(message);

        // 6. Verifying the expected and actual result.
        Assert.assertEquals(expectation, actual);

    }
}
