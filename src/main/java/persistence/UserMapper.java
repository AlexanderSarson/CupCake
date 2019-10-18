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
        return null;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from Users join Accounts on Users.user_id = Accounts.user_id join Logins on Users.user_id = Logins.user_id";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet rs = connection.selectQuery(ps);
            while(rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String mail = rs.getString("login_mail");
                Role role = Role.valueOf(rs.getString("user_role"));
                int account_id = rs.getInt("account_id");
                int account_balance = rs.getInt("user_balance");

                Account acc = new Account(account_id,account_balance);
                User user = new User(id,name,mail,role,acc);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
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

}
