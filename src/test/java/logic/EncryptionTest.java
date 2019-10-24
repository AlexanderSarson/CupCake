package logic;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rando
 */
public class EncryptionTest {

    /**
     * Test of encryptPsw method, of class Encryption.
     */
    @Test
    public void testEncryptPsw() {
        //Arrange
        String password = "ASDF";
        String expResult = "62ffff9e4af92f86161d09e682a7b015c579fd1ac72ca80452da198efd8909818667056a66a06a19c6f2ec240e95b68214414cb20242ecf29343458abe0f87cf";
        //Act
        String result = Encryption.encryptPsw(password);
        //Assert
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEncryptPsw_HashLength(){
        //Arrange
        String password = "QWERTY";
        int expResult = 128;
        //Act
        String result = Encryption.encryptPsw(password);
        //Assert
        assertEquals(expResult, result.length());
    }
}
