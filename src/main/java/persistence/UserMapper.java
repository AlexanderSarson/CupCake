package persistence;

import logic.Account;
import logic.Role;
import logic.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of the class is to map user objects to dabase information, and database information back to User Objects.
 * The holds all CRUD operations regarding Users.
 * @author Oscar, Benjamin
 * version 1.0
 */
public class UserMapper {
    private SQLConnection connection;
    public UserMapper(SQLConnection connection) {
        this.connection = connection;
    }

    /**
     * Validates the users, is a registered user, using mail and password.
     * @param mail The mail of the user
     * @param password The password for the user.
     * @return If the user is able to login, the user object is returned. Returns null if the information is associated with a user.
     * @throws UserException if a user could not be found, or if there was an error when validating the user.
     */
    public User login(String mail, String password) throws UserException {
        User user = null;
        String sql = "select user_id from logins where login_mail = ? and where login_password = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1,mail);
            ps.setString(2, password);
            ResultSet rs = connection.selectQuery(ps);
            while(rs.next()){
                int id = rs.getInt("user_id");

            }
            sql = "select * from Users join Accounts on Users.user_id = Accounts.user_id join Logins on Users.user_id = Logins.user_id";
            ps = connection.getConnection().prepareStatement(sql);
            rs = connection.selectQuery(ps);
            while(rs.next()){
                user = findUserFromResultSet(rs);
            }

        }catch (SQLException e){
            throw new UserException("Error validating user");
        }
        if(user == null)
            throw new UserException("User could not be validated");
        else
            return user;
    }

    /**
     * Gets all registered users.
     * @return A list of all Users, as user objects.
     * @throws UserException if an error occurs when fetching all users.
     */
    public ArrayList<User> getAllUser() throws UserException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from Users join Accounts on Users.user_id = Accounts.user_id join Logins on Users.user_id = Logins.user_id";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet rs = connection.selectQuery(ps);
            while(rs.next()) {
                User user = findUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new UserException("Error fetching users");
        }
        return users;
    }

    /**
     * Deletes a given user from the database.
     * @param user The user to be deleted.
     * @throws IllegalArgumentException If the user does not exist in the database.
     */
    public void deleteUser(User user) throws UserException {
        String sql = "select * from Users WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setLong(1, user.getID());
            ResultSet rs = connection.selectQuery(ps);
            if (!rs.next()) {
                throw new UserException("User doesn't exist in database");
            } else {
                connection.getConnection().setAutoCommit(false);
                try {
                    //Delete from logins
                    String deleteLogin = "DELETE * from logins WHERE user_id = ?";
                    PreparedStatement loginPS = connection.getConnection().prepareStatement(deleteLogin);
                    loginPS.setLong(1, user.getID());
                    if (!connection.executeQuery(loginPS)) {
                        throw new SQLException("User login could not be deleted");

                    }
                    //Delete from accounts
                    String deleteAccount = "DELETE * from accounts where user_id = ?";
                    PreparedStatement accountPS = connection.getConnection().prepareStatement(deleteAccount);
                    accountPS.setLong(1, user.getID());
                    if (!connection.executeQuery(accountPS)) {
                        throw new SQLException("User account could not be deleted");
                    }
                    //Delete from users
                    String deleteUser = "DELETE * from users WHERE user_ID = ?";
                    PreparedStatement userPS = connection.getConnection().prepareStatement(deleteUser);
                    userPS.setLong(1, user.getID());
                    if (!connection.executeQuery(userPS)) {
                        throw new SQLException("User could not be deleted");
                    }
                    //Commit all transactions
                    connection.getConnection().commit();
                } catch (SQLException ex) {
                    connection.getConnection().rollback();
                    throw new UserException("User could not be deleted");
                } finally {
                    connection.getConnection().setAutoCommit(true);
                }
            }
        }catch (SQLException e){
            throw new UserException("User could not be deleted");
        }
    }

    /**
     * Creates a user, using transactions such that if anything goes wrong nothing is committed.
     * @param user The user to be created.
     * @param account The account associated with the given user.
     * @param password The password associated with the user & account.
     * @throws UserException If anything goes wrong during creation, a UserException will be thrown.
     */
    public void createUser(User user, Account account, String password) throws UserException {
        String sql = "select * from logins where login_mail = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1,user.getMail());
            ResultSet rs = connection.selectQuery(ps);
            if(rs.next()) {
                // If we already have a user with the given mail address.
                throw new UserException("User mail already exists in database");
            } else {
                // Begin transaction.
                connection.getConnection().setAutoCommit(false);
                try {
                    // Insert into Users
                    String userPrepare = "INSERT INTO Users(user_name,user_role) values(?,?)";
                    PreparedStatement userPS = connection.getConnection().prepareStatement(userPrepare);
                    userPS.setString(1, user.getName());
                    userPS.setString(2, user.getRole().name());
                    if(!connection.executeQuery(userPS)) {
                        throw new SQLException("Could not insert into users");
                    }
                    else {
                        user.setID(lastID());
                    }
                    // Insert into Logins
                    String loginPrepare = "INSERT INTO Logins(user_id, login_mail, login_password, login_salt) values (?,?,?,?)";
                    PreparedStatement loginPS = connection.getConnection().prepareStatement(loginPrepare);
                    loginPS.setInt(1,user.getID());
                    loginPS.setString(2,user.getMail());
                    loginPS.setString(3, password);
                    loginPS.setInt(4,1000); // TODO(Benjamin) Add the correct salt at some point.
                    if(!connection.executeQuery(loginPS)) {
                        throw new SQLException("Could not insert into login");
                    }

                    // Insert into Accounts
                    String accountPrepare = "INSERT INTO Accounts(user_id,user_balance) values (?,?)";
                    PreparedStatement accPS = connection.getConnection().prepareStatement(accountPrepare);
                    accPS.setInt(1,user.getID());
                    accPS.setInt(2,account.getBalance());
                    if(!connection.executeQuery(accPS)) {
                        throw new SQLException("Could not insert into account");
                    } else {
                        account.setId(lastID());
                    }
                    // Commit all transactions
                    connection.getConnection().commit();
                } catch (SQLException ex) {
                    // If anything goes wrong - rollback.
                    connection.getConnection().rollback();
                    throw new UserException("User creation failed");
                }
                finally {
                    // What ever happens set auto commit back to true.
                    connection.getConnection().setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            throw new UserException("User creation failed");
        }
    }

    /**
     * Returns the last created ID.
     * @return The id of the last created entry.
     * @throws SQLException If anything goes wrong when trying to get the last created ID.
     */
    private int lastID() throws SQLException {
        PreparedStatement userIdPS = connection.getConnection().prepareStatement("select last_insert_id() as id");
        ResultSet rs = connection.selectQuery(userIdPS);
        rs.next();
        return rs.getInt("id");
    }

    /**
     * Generates a user from a ResultSet
     * @param rs The ResultSet containing the user
     * @return A User object if the user could be parsed.
     * @throws SQLException If anything goes wrong when trying to parse a user object.
     */
    private User findUserFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String name = rs.getString("user_name");
        String mail = rs.getString("login_mail");
        Role role = Role.valueOf(rs.getString("user_role"));
        int account_id = rs.getInt("account_id");
        int account_balance = rs.getInt("user_balance");

        Account acc = new Account(account_id,account_balance);
        return new User(id,name,mail,role,acc);
    }
}
