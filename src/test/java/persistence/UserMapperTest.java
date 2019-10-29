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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {
    @InjectMocks
    private UserMapper mapper;

    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resSet;
    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;

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

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);

        List<User> users = mapper.getAllUser();
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
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);

        mapper.createUser(user,acc,"test");
    }

    @Test(expected = UserException.class)
    public void test_createUser() throws SQLException, UserException {
        Account acc = new Account(10000);
        User user = new User("Peter Larsen", "peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1).thenReturn(0);

        mapper.createUser(user,acc,"test");
    }

    @Test (expected = UserException.class)
    public void test_delete_user_when_delete_logins_fails() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(0);

        mapper.deleteUser(user);
    }
    @Test (expected = UserException.class)
    public void test_delete_user_when_delete_accounts_fails() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1).thenReturn(0);

        mapper.deleteUser(user);
    }

    @Test (expected = UserException.class)
    public void test_delete_user_when_delete_user_fails() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1).thenReturn(1).thenReturn(0);

        mapper.deleteUser(user);
    }

    @Test (expected = UserException.class)
    public void test_delete_user_when_user_does_not_exist() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);

        mapper.deleteUser(user);
    }

    @Test
    public void test_delete_user() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1).thenReturn(1).thenReturn(1);

        mapper.deleteUser(user);
    }

    @Test (expected = UserException.class)
    public void test_update_user_when_update_mail_fails() throws SQLException, UserException{
    Account acc = new Account (10000);
    User user = new User ("Peter Larsen", "peter@example.com", Role.CUSTOMER, acc);
    when(resSet.next()).thenReturn(true).thenReturn(false);
    when(dataSource.getConnection()).thenReturn(connection);
    when(connection.prepareStatement(any(String.class))).thenReturn(statement);
    when(statement.executeQuery()).thenReturn(resSet);
    when(statement.executeUpdate()).thenReturn(0);

    mapper.updateUser(user);
    }

    @Test (expected = UserException.class)
    public void test_update_user_when_update_user_name_fails() throws SQLException, UserException{
        Account acc = new Account (10000);
        User user = new User ("Peter Larsen", "peter@example.com", Role.CUSTOMER, acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1).thenReturn(0);

        mapper.updateUser(user);
    }

    @Test (expected = UserException.class)
    public void test_update_user_when_user_does_not_exist() throws SQLException, UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);

        mapper.updateUser(user);

    }

    @Test
    public void test_update_user() throws SQLException,UserException{
        Account acc = new Account(10000);
        User user = new User("Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        when(resSet.next()).thenReturn(true).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1).thenReturn(1);

        mapper.updateUser(user);

    }
}
