package persistence;

import logic.Account;
import logic.Role;
import logic.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {
    @InjectMocks
    private UserMapper mapper;

    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet resSet;
    @Mock
    private SQLConnection connection;
    @Mock
    private Connection sqlConnection;

    @Before
    public void setup() throws Exception {
    }

    @Test

    public void test_getAllUsers() throws SQLException, UserException {
        // First and second time next() is called, it returns true, then it returns false.
        when(resSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resSet.getInt("user_id")).thenReturn(1);
        when(resSet.getString("user_name")).thenReturn("Peter Larsen");
        when(resSet.getString("user_role")).thenReturn("CUSTOMER");
        when(resSet.getString("login_mail")).thenReturn("peter@example.com");
        when(resSet.getInt("account_id")).thenReturn(1);
        when(resSet.getInt("user_balance")).thenReturn(1000);

        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);

        ArrayList<User> users = mapper.getAllUser();
        User user = users.get(0);
        assertEquals(1,user.getID());
        assertEquals("Peter Larsen",user.getName());
        assertEquals(Role.CUSTOMER, user.getRole());
        assertEquals("peter@example.com",user.getMail());
        assertEquals(1,user.getAccount().getId());
        assertEquals(1000,user.getAccount().getBalance());
        assertEquals(2, users.size());
    }

    @Test(expected = UserException.class)
    public void test_createUser_already_exists() throws SQLException, UserException {
        Account acc = new Account(10000);
        User user = new User("Peter Larsen", "peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);

        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);

        mapper.createUser(user,acc,"test");
    }

    @Test(expected = UserException.class)
    public void test_createUser() throws SQLException, UserException {
        Account acc = new Account(10000);
        User user = new User("Peter Larsen", "peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(false).thenReturn(true);

        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);
        when(connection.executeQuery(ps)).thenReturn(true).thenReturn(false);

        mapper.createUser(user,acc,"test");
    }

    @Test (expected = UserException.class)
    public void test_delete_user_when_delete_logins_fails() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);

        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);
        when(connection.executeQuery(ps)).thenReturn(false);

        mapper.deleteUser(user);
    }
    @Test (expected = UserException.class)
    public void test_delete_user_when_delete_accounts_fails() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);

        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);
        when(connection.executeQuery(ps)).thenReturn(true).thenReturn(false);

        mapper.deleteUser(user);
    }

    @Test (expected = UserException.class)
    public void test_delete_user_when_delete_user_fails() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);

        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);
        when(connection.executeQuery(ps)).thenReturn(true).thenReturn(true).thenReturn(false);

        mapper.deleteUser(user);
    }

    @Test (expected = UserException.class)
    public void test_delete_user_when_user_does_not_exist() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(false);

        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);

        mapper.deleteUser(user);
    }

    @Test
    public void test_delete_user() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);

        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);
        when(connection.executeQuery(ps)).thenReturn(true).thenReturn(true).thenReturn(true);

        mapper.deleteUser(user);
    }
}
