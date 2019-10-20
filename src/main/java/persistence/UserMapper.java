package persistence;

import logic.Account;
import logic.Role;
import logic.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserMapper {
    private SQLConnection connection;
    public UserMapper(SQLConnection connection) {
        this.connection = connection;
    }

    public User login(String mail, String password) {
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
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getAllUser() {
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
            e.printStackTrace();
        }
        return users;
    }



    public void deleteUser(long id) throws IllegalArgumentException {
        String sql = "select * from Users WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = connection.selectQuery(ps);
            if (!rs.next()) {
                throw new IllegalArgumentException("User doesn't exist in database");
            } else {
                connection.getConnection().setAutoCommit(false);
                try {
                    //Delete from logins
                    String deleteLogin = "DELETE * from logins WHERE user_id = ?";
                    PreparedStatement loginPS = connection.getConnection().prepareStatement(deleteLogin);
                    loginPS.setLong(1, id);
                    //Need an explanation on the below if statement
                    if (connection.executeQuery(loginPS)) {
                        throw new SQLException("User login could not be deleted");

                    }
                    //Delete from accounts
                    String deleteAccount = "DELETE * from accounts where user_id = ?";
                    PreparedStatement accountPS = connection.getConnection().prepareStatement(deleteAccount);
                    accountPS.setLong(1, id);
                    if (connection.executeQuery(accountPS)) {
                        throw new SQLException("User account could not be deleted");
                    }
                    //Delete from users
                    String deleteUser = "DELETE * from users WHERE user_ID = ?";
                    PreparedStatement userPS = connection.getConnection().prepareStatement(deleteUser);
                    userPS.setLong(1, id);
                    if (connection.executeQuery(userPS)) {
                        throw new SQLException("User could not be deleted");
                    }
                    //Commit all transactions
                    connection.getConnection().commit();
                } catch (SQLException ex) {
                    connection.getConnection().rollback();
                    //What exception has to be thrown??
                    throw new IllegalArgumentException("User could not be deleted");
                } finally {
                    connection.getConnection().setAutoCommit(true);
                }
            }

        }catch (SQLException e){
                //Again, what exception do we throw?
            throw new IllegalArgumentException("User could not be deleted");

            }
    }


    public void createUser(User user, Account account, String password) throws UserCreationException {
        String sql = "select * from logins where login_mail = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1,user.getMail());
            ResultSet rs = connection.selectQuery(ps);
            if(rs.next()) {
                // If we already have a user with the given mail address.
                throw new UserCreationException("User mail already exists in database");
            } else {
                // Begin transaction.
                connection.getConnection().setAutoCommit(false);
                try {
                    // Insert into Users
                    String userPrepare = "INSERT INTO Users(user_name,user_role) values(?,?)";
                    PreparedStatement userPS = connection.getConnection().prepareStatement(userPrepare);
                    userPS.setString(1, user.getName());
                    userPS.setString(2, user.getRole().name());
                    if(connection.executeQuery(userPS)) {
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
                    if(connection.executeQuery(loginPS)) {
                        throw new SQLException("Could not insert into login");
                    }

                    // Insert into Accounts
                    String accountPrepare = "INSERT INTO Accounts(user_id,user_balance) values (?,?)";
                    PreparedStatement accPS = connection.getConnection().prepareStatement(accountPrepare);
                    accPS.setInt(1,user.getID());
                    accPS.setInt(2,account.getBalance());
                    if(connection.executeQuery(accPS)) {
                        throw new SQLException("Could not insert into account");
                    } else {
                        account.setId(lastID());
                    }
                    // Commit all transactions
                    connection.getConnection().commit();
                } catch (SQLException ex) {
                    // If anything goes wrong - rollback.
                    connection.getConnection().rollback();
                    throw new UserCreationException("User creation failed");
                }
                finally {
                    // What ever happens set auto commit back to true.
                    connection.getConnection().setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            throw new UserCreationException("User creation failed");
        }
    }

    private int lastID() throws SQLException {
        PreparedStatement userIdPS = connection.getConnection().prepareStatement("select last_insert_id() as id");
        ResultSet rs = connection.selectQuery(userIdPS);
        rs.next();
        return rs.getInt("id");
    }

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
