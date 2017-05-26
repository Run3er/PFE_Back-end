package projMngmntSaaS.domain.utils;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * testing {@link PasswordHashing} functions behavior.
 */
public class PasswordHashingTest
{
    private static final int HASH_LENGTH;
    private static final String PASSWORD;
    private static final byte[] SALT;
    private static final byte[] PASSWORD_HASH;

    static {
        HASH_LENGTH = 32;
        PASSWORD = "random0password";
        SALT = new byte[] {-31, -26, 102, -47, 117, -77, -119, -107, -110, 68, -88, 5, 89, -128, 75, 21};
        PASSWORD_HASH = PasswordHashing.hash(PASSWORD, SALT);
    }

    /**
     * Testing {@link PasswordHashing#getNextSalt()}
     */
    @Test
    public void consecutiveSaltsShouldBeDistinct() {
        byte[] salt1 = PasswordHashing.getNextSalt();
        byte[] salt2 = PasswordHashing.getNextSalt();
        assertFalse("Two consecutively generated salts should be different.",
                Arrays.equals(salt1, salt2));
    }

    /**
     * Testing {@link PasswordHashing#hash(String, byte[])}
     */
    @Test(expected = NullPointerException.class)
    public void hashingNullPasswordShouldThrowNPE() {
        PasswordHashing.hash(null, new byte[1]);
    }

    /**
     * Testing {@link PasswordHashing#hash(String, byte[])}
     */
    @Test(expected = NullPointerException.class)
    public void hashingWithNullSaltShouldThrowNPE() {
        PasswordHashing.hash("", null);
    }

    /**
     * Testing {@link PasswordHashing#hash(String, byte[])}
     */
    @Test
    public void passwordHashShouldBeDifferentFromPassword() throws UnsupportedEncodingException {
        String passwordHash = new String(PASSWORD_HASH, "UTF-8");
        assertFalse("Password hash shouldn't be the same as the password itself.",
                passwordHash.equals(PASSWORD));
    }

    /**
     * Testing {@link PasswordHashing#hash(String, byte[])}
     */
    @Test
    public void passwordHashLengthShouldBeFixed() {
        assertTrue("Password hash's length [" + PASSWORD_HASH.length + "] should be equal to " + HASH_LENGTH + ".",
                PASSWORD_HASH.length == HASH_LENGTH);
    }

    /**
     * Testing {@link PasswordHashing#hash(String, byte[])}
     */
    @Test
    public void differentSaltsForHashingSamePasswordGenerateDifferentHashes() throws Exception {
        byte[] saltNew = new byte[] {-16, 49, -50, 37, 93, -61, -119, 107, -57, -120, -75, 98, 51, -113, -6, -117};
        byte[] passwordNewHash = PasswordHashing.hash(PASSWORD, saltNew);
        assertFalse("A password hashed with different salts should return different hashes.",
                Arrays.equals(PASSWORD_HASH, passwordNewHash));
    }

    /**
     * Testing {@link PasswordHashing#isHashOfPassword(byte[], byte[], String)}
     */
    @Test(expected = NullPointerException.class)
    public void checkingAgainstNullExpectedHashShouldThrowNPE() {
        PasswordHashing.isHashOfPassword(null, new byte[1], "");
    }

    /**
     * Testing {@link PasswordHashing#isHashOfPassword(byte[], byte[], String)}
     */
    @Test(expected = NullPointerException.class)
    public void checkingHashAgainstNullSaltShouldThrowNPE() {
        PasswordHashing.isHashOfPassword(new byte[1], null, "");
    }

    /**
     * Testing {@link PasswordHashing#isHashOfPassword(byte[], byte[], String)}
     */
    @Test(expected = NullPointerException.class)
    public void checkingHashAgainstNullPasswordShouldThrowNPE() {
        PasswordHashing.isHashOfPassword(new byte[1], new byte[1], null);
    }

    /**
     * Testing {@link PasswordHashing#isHashOfPassword(byte[], byte[], String)}
     */
    @Test
    public void hashResultWithSameParametersShouldBeTheSame() {
        assertTrue("A password's hash should be equal to the result of the hashing function checked against.",
                PasswordHashing.isHashOfPassword(PasswordHashing.hash(PASSWORD, SALT), SALT, PASSWORD));
    }
}