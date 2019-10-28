package logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Benjamin Paepke
 * @version 1.0
 */
public class UserTest {
    private User user;
    private Account account;

    @Before
    public void setup() {
        account = new Account(1);
        user = new User(1,"peter", "peter@example.com", Role.CUSTOMER,account);
    }

    @Test
    public void test_getID() {
        long id = 1;
        assertEquals(id,user.getID());
    }

    @Test
    public void test_setID() {
        int id = 100;
        user.setID(id);
        assertEquals(id,user.getID());
    }

    @Test
    public void test_getName() {
        String name = "peter";
        assertEquals(name,user.getName());
    }

    @Test
    public void test_setName() {
        String name ="peterLarsen";
        user.setName(name);
        assertEquals(name,user.getName());
    }

    @Test
    public void test_getMail() {
        String mail = "peter@example.com";
        assertEquals(mail,user.getMail());
    }

    @Test
    public void test_setMail() {
        String mail = "larsen@example.com";
        user.setMail(mail);
        assertEquals(mail,user.getMail());
    }

    @Test
    public void getRole() {
        Role role = Role.CUSTOMER;
        assertEquals(role,user.getRole());
    }

    @Test
    public void setRole() {
        Role role = Role.ADMIN;
        user.setRole(role);
        assertEquals(role,user.getRole());
    }

    @Test
    public void getAccount() {
        assertEquals(account, user.getAccount());
    }

    @Test
    public void setAccount() {
        Account newAccount = new Account(200);
        user.setAccount(newAccount);
        assertEquals(newAccount,user.getAccount());
    }
}