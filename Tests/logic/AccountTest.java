package logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Benjamin Paepke
 * @version 1.0
 */
public class AccountTest {
    private Account account;

    @Before
    public void setup() {
        account = new Account(1,1000);
    }

    @Test
    public void test_getBalance() {
        int balance = 1000;
        assertEquals(balance,account.getBalance());
    }

    @Test
    public void test_setBalance() {
        int balance = 2000;
        account.setBalance(balance);
        assertEquals(balance,account.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_setBalance_negative_value() {
        int balance = -2000;
        account.setBalance(balance);
    }

    @Test
    public void test_reduceBalance() {
        int reduction = 10000;
        account.reduceBalance(reduction);
        assertEquals(0, account.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_reduceBalance_with_negative_reduction(){
        int reduction = -1000;
        account.reduceBalance(reduction);
    }
}