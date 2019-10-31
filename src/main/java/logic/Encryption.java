package logic;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Encryption of password, using SHA-512.
 * @author rando
 */
public class Encryption {

    public static String encryptPsw(String password) {
        try {
            byte[] messageDirect = getSHA(password);
            return toHexString(messageDirect);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("Encryption Failed");
        }
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        //Static getInstance methos is called with hashing SHA-512
        /*
        Message digests are secure one-way hash functions that take an input of
        data and in return output a fixed-length hash value.
        */
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        /*
        Digest() method is called to calculate the message digest of our string 
        input, and return it as a array of byte
        */
        //md.update(generateSalt());
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }

    public static String toHexString(byte[] hash) {
        //Convert byte array into signum representation(Negative -1, neutral 0, positive 1)
        BigInteger number = new BigInteger(1, hash);
        /*
        Convert the big integer to Hex value by using the java.math.BigInteger.toString(int radix) method
        When using a radix that is outside the range of Character.MIN_RADIX to Character.MAX_RADIX
        then the radix is set to 10 as default.
        When the Radix is 16 then the function will first convert BigInteger
        to hexadecimal from then it will return the String representation of that hexadecimal number.
        */
        StringBuilder hexString = new StringBuilder(number.toString(16));
        //Pad with leading zeroes
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
