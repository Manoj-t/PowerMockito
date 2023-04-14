import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manoj.ClassWithFinalMethods;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.manoj.*")
@PowerMockIgnore({"javax.management.", "com.sun.org.apache.xerces.",
        "javax.xml.", "org.xml.", "org.w3c.dom.",
        "com.sun.org.apache.xalan.", "javax.activation.*", "jdk.internal.reflect.*"})
public class PowerMockFinalMethodTest {

    @Test
    public void testClassWithFinalMethods_printMessage_finalMethod() throws Exception {

        // 1. Define a generic string message
        String message = "Hello PowerMockito!";

        // 2. Create a mock instance of the system under test
        ClassWithFinalMethods mockObject = PowerMockito.mock(ClassWithFinalMethods.class);

        /*3. whenNew() method makes sure whenever an instance of this class is made using the new keyword by invoking
        a no argument constructor, this mock instance is returned instead of the real object */
        PowerMockito.whenNew(ClassWithFinalMethods.class)
                .withNoArguments()
                .thenReturn(mockObject);

        // 4. Create an instance of the class under test with no arguments
        ClassWithFinalMethods object = new ClassWithFinalMethods();

        // 5. We verify that the no argument constructor was actually involved during the last step.
        PowerMockito.verifyNew(ClassWithFinalMethods.class).withNoArguments();

        // 6. We set an expected String when the final method is called, using the String we defined in Step 1.
        PowerMockito.when(object.printMessage(message)).thenReturn(message);

        // 7. The final method printMessage(…) is invoked.
        String helloPowerMockito = object.printMessage(message);

        // 8. We verify that the final method was actually called.
        Mockito.verify(object).printMessage(message);

        // 9. Finally, we assert our expectations to the actual String returned to us.
        Assert.assertEquals(message, helloPowerMockito);
    }

}
