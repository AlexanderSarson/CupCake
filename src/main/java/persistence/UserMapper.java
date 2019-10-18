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


    public void deleteUser(long id){
        String sql = "DELETE from Users WHERE id = ?";
        try{
            PreparedStatement ps = 
        }
    }


    public boolean createUser(String name, String role) {
        String sql = "INSERT INTO USERS(user_name, user_role) VALUES (?, ?)";
        try {
            PreparedStatement ps;
            ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, role);
            return connection.executeQuery(ps); //If sucsess <-True
        } catch (SQLException ex) {
        }
        return false;
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
