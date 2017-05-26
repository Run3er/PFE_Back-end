package projMngmntSaaS.domain;

import org.junit.Test;
import projMngmntSaaS.domain.entities.User;

import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;

/**
 * Testing {@link User} password storage related permissible behavior.
 * Salt should only be set internally, along its hash counterpart.
 * Hash should only be set internally, along its salt counterpart.
 * Password should never be stored in plain text (checking against field).
 */
public class UserTest
{
    @Test
    public void passwordSaltSetterShouldNotExist() {
        Method[] methods = User.class.getDeclaredMethods();
        for (Method method : methods) {
            assertFalse("Setter for password salt should not exist.",
                    method.getName().equals("setPasswordSalt"));
        }
    }

    @Test(expected = NoSuchMethodException.class)
    public void passwordHashRawSetterShouldNotExist() throws NoSuchMethodException {
        User.class.getDeclaredMethod("setPasswordHash", byte[].class);
    }

    @Test(expected = NoSuchFieldException .class)
    public void passwordShouldNotBeKeptButOnlyItsHash() throws NoSuchFieldException {
        User.class.getField("password");
    }
}
