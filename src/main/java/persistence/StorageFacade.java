package persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rando
 */
public class StorageFacade {

    private final SQLConnection con = new SQLConnection();
    private PreparedStatement ps;

    public User getAllUsers() {
        String sql = "SELECT * FROM Users";
        try {
            ps = con.getConnection().prepareStatement(sql);
            con.selectQuery(ps);
        } catch (SQLException ex) {
            Logger.getLogger(StorageFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Products getAllProducts() {
        String sql = "SELECT * FROM Cupcakes";
        try {
            ps = con.getConnection().prepareStatement(sql);
            con.selectQuery(ps);
        } catch (SQLException ex) {
            Logger.getLogger(StorageFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Products getProduct(int id) {
        String sql = "SELECT * FROM Cupcakes WHERE cupcake_id = ?";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            con.selectQuery(ps);
        } catch (SQLException ex) {
            Logger.getLogger(StorageFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean createUser(String name, String role) {
        String sql = "INSERT INTO USERS(user_name, user_role) VALUES (?, ?)";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, role);
            return con.executeQuery(ps); //If sucsess <-True
        } catch (SQLException ex) {
            Logger.getLogger(StorageFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateUser(String name, String role, int id) {
        String sql = "UPDATE USER SET user_name = ?, user_role = ? WHERE user_id = ?";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, role);
            ps.setInt(3, id);
            return con.executeQuery(ps); //If sucsess <-True
        } catch (SQLException ex) {
            Logger.getLogger(StorageFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM User WHERE user_id = ?";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            return con.executeQuery(ps); //If sucsess <-True
        } catch (SQLException ex) {
            Logger.getLogger(StorageFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean createProduct(String name, double price, String pic, String validation) {
        String[] topOrBot = topOrBot(validation);
        String table = topOrBot[0]; //Toppings/Bottoms
        String row = topOrBot[1]; //topping/bottom
        String sql = "INSERT INTO " + table + "(" + row + "_name, " + row + "_price, " + row + "_picture) VALUES(?, ?, ?)";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, pic);
            return con.executeQuery(ps); //If sucsess <-True
        } catch (SQLException ex) {
            Logger.getLogger(StorageFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

//TODO(Tobias): Update All Cupcakes?
        //Update Cupcakes Topping id + Alle Bottom id
        //Update Cupcakes Bottom id + Alle Topping id
        return false;
    }

    public boolean updateProduct(String name, double price, String pic, int id, String validation) {
        String[] topOrBot = topOrBot(validation);
        String table = topOrBot[0]; //Toppings/Bottoms
        String row = topOrBot[1]; //topping/bottom
        String sql = "UPDATE " + table + " SET " + row + "_name = ?, " + row + "_price = ?, " + row + "_picture = ? WHERE " + row + "_id = ?";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, pic);
            ps.setInt(4, id);
            return con.executeQuery(ps); //If sucsess <-True
        } catch (SQLException ex) {
            Logger.getLogger(StorageFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

//TODO(Tobias): Update All Cupcakes?
        //Update Cupcakes Topping id + Alle Bottom id
        //Update Cupcakes Bottom id + Alle Topping id
        return true; //If sucsess
    }

    public boolean deleteProduct(int id, String validation) {
        String[] topOrBot = topOrBot(validation);
        String table = topOrBot[0]; //Toppings/Bottoms
        String row = topOrBot[1]; //topping/bottom
        String sql = "DELETE FROM " + table + " WHERE " + row + "_id = ?";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            return con.executeQuery(ps); //If sucsess <-True
        } catch (SQLException ex) {
            Logger.getLogger(StorageFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

//TODO(Tobias): Update All Cupcakes?
        //Update Cupcakes Topping id + Alle Bottom id
        //Update Cupcakes Bottom id + Alle Topping id
        return false;
        
    }

    private String[] topOrBot(String validation) {
        String table = ""; //Toppings/Bottoms
        String row = ""; //topping/bottom
        switch (validation) {
            case "top":
                table = "Toppings";
                row = "topping";
                break;
            case "bot":
                table = "Bottoms";
                row = "bottom";
                break;
        }
        return new String[]{table, row};
    }

}


//LAV Private metode der opdaterer alle cupcakes så de er up 2 date hele tiden.
