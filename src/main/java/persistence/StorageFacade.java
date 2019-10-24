package persistence;

import logic.Bottom;
import logic.Cupcake;
import logic.Topping;
import logic.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rando
 */
public class StorageFacade {
    private final SQLConnection con = new SQLConnection();
    private PreparedStatement ps;
    private UserMapper userMapper = new UserMapper(con);
    private ProductMapper productMapper = new ProductMapper(con);
    private BottomMapper bottomMapper = new BottomMapper(con);
    private ToppingMapper toppingMapper = new ToppingMapper(con);

    // ------ PRODUCT ------
    public ArrayList<Cupcake> getAllProducts() throws ProductException {
        return productMapper.getAllProducts();
    }
    public Cupcake getProduct(int id) throws ProductException {
        return productMapper.getProductFromID(id);
    }

    // ------ USER ------
    public ArrayList<User> getAllUsers() throws UserException {
        return userMapper.getAllUser();
    }
    public User createUser(User user, String password) throws UserException {
        return userMapper.createUser(user,user.getAccount(),password);
    }
    public boolean updateUser(User user) throws UserException {
        boolean res = false;
        try {
            userMapper.updateUser(user);
            res = true;
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
        return res;
    }
    public boolean deleteUser(User user) throws UserException {
        userMapper.deleteUser(user);
        return true;
    }
    public User validateUser(String email, String password) throws UserException {
        return userMapper.login(email,password);
    }

    // ------ BOTTOM ------
    public void createBottom(Bottom bottom) throws ProductException {
        bottomMapper.createProduct(bottom);
    }
    public void updateBottom(Bottom bottom) throws ProductException {
        bottomMapper.updateProduct(bottom);
    }
    public void deleteBottom(Bottom bottom) {
        //bottomMapper.deleteProduct(bottom);
    }

    // ------ TOPPING ------
    public void createTopping(Topping topping) throws ProductException {
        toppingMapper.createProduct(topping);
    }
    public void updateTopping(Topping topping) throws ProductException {
        toppingMapper.updateProduct(topping);
    }
    public void deleteTopping(Topping topping) {
        //toppingMapper.deleteProduct(topping);
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
