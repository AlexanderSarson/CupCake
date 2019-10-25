
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Bottom;
import logic.Cupcake;
import logic.IProduct;
import logic.Topping;

/**
 * This class has the purpose of mapping Products from the database to jave objects. General data, such as Cupcakes and intances where all tables are being checked,
 * can be collected through this class. Data regarding Bottoms or Toppings, make use of {@link logic.Bottom} and {@link logic.Topping}
 * @author rando
 * @author Benjamin Paepke
 * @version 1.01
 */
public class ProductMapper {
    protected String table = "", product_id = "", product_name = "", product_price ="";
    private DataSource dataSource;
    public ProductMapper(DataSource dataSource) {this.dataSource = dataSource;}

    /**
     * Gets all cupcakes from database
     * @return an ArrayList of cupcakes
     * @throws ProductException if cupcakes cannot be fetched from database
     */
    public ArrayList<Cupcake> getAllProducts() throws ProductException {
        ArrayList<Cupcake> cupcakes = new ArrayList<>();
        String sql = "select * from Cupcakes join Toppings on Cupcakes.topping_id = Toppings.topping_id join Bottoms on Cupcakes.bottom_id = Bottoms.bottom_id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cupcakes.add(findCupcakeFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new ProductException("Error when fetching all Cupcakes");
        }
        return cupcakes;
    }

    /**
     * Gets cupcake from a given ID
     * @param id the id of the cupcake
     * @return the cupcake with the given id
     * @throws ProductException if anything goes wrong while trying to fetch cupcake
     */
    public Cupcake getProductFromID(int id) throws ProductException {
        String sql = "select * from Cupcakes join Toppings on Cupcakes.topping_id = Toppings.topping_id join Bottoms on Cupcakes.bottom_id = Bottoms.bottom_id where cupcake_id = ?";
        Cupcake cupcake = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next())
                throw new ProductException("Could not find cupcake with id: " + id);
            else
                cupcake = findCupcakeFromResultSet(rs);
        } catch (SQLException e) {
            throw new ProductException("Error when fetching Cupcake");
        }
        return cupcake;
    }

    /**
     * Finds cupcake from a given Resultset
     * @param rs the resultset from where you want to find the cupcake
     * @return the cupcake from the resultset
     * @throws SQLException if anything goes wrong while trying to find cupcake
     */
    private Cupcake findCupcakeFromResultSet(ResultSet rs) throws SQLException {
        //Cupcake Topping object creation
        int topID = rs.getInt("topping_id");
        int topPrice = rs.getInt("topping_price");
        String topName = rs.getString("topping_name");
        String topPic = rs.getString("topping_picture");
        Topping top = new Topping(topPrice, topName);
        top.setId(topID);
        //Cupcake Bottom object creation
        int botID = rs.getInt("bottom_id");
        int botPrice = rs.getInt("bottom_price");
        String botName = rs.getString("bottom_name");
        String botPic = rs.getString("bottom_picture");
        Bottom bot = new Bottom(botPrice, botName);
        bot.setId(botID);
        //Completed Cupcake object return
        int cupcakeID = rs.getInt("cupcake_id");
        Cupcake cupcake = new Cupcake(bot, top);
        cupcake.setId(cupcakeID);
        return cupcake;
    }

    /**
     * Creates a product, either {@link logic.Bottom} or {@link logic.Topping}
     * @param product The topping to be created.
     * @throws ProductException If anything goes wrong in the creation of the topping.
     */
    public void createProduct(IProduct product) throws ProductException {
        String sql = "SELECT * FROM "+table+" where "+product_name+" = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,product.getName());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                throw new ProductException("Product already exists");
            }
            else {
                sql = "INSERT INTO "+table+" ("+product_name+", "+product_price+") VALUES (?,?)";
                try (PreparedStatement insertPS = connection.prepareStatement(sql);) {
                    insertPS.setString(1,product.getName());
                    insertPS.setInt(2, product.getPrice());
                    if(insertPS.executeUpdate() == 1) {
                        int id = dataSource.lastID(connection, insertPS);
                        product.setId(id);
                    }
                    else {
                        throw new ProductException("Could not create product");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ProductException("Connection failed");
        }
    }

    /**
     * Updates a product either {@link logic.Bottom} or {@link logic.Topping}
     * @param product The bottom or topping to be updated
     * @throws ProductException If anything goes wrong while updating product
     */
    public void updateProduct(IProduct product) throws ProductException{
        String sql = "SELECT * FROM "+table+" where "+product_id+" = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,product.getId());
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                throw new ProductException("Product doesn't exist in database");
            }
            else{
                sql = "UPDATE "+table+" SET "+product_name+"= ?, "+product_price+" = ? WHERE "+product_id+" = ?";
                try (PreparedStatement updatePS = connection.prepareStatement(sql)) {
                    updatePS.setString(1,product.getName());
                    updatePS.setInt(2,product.getPrice());
                    updatePS.setInt(3,product.getId());
                    if(updatePS.executeUpdate() != 1){
                        throw new SQLException("Product could not be updated");
                    }
                }
            }
        }catch(SQLException e){
            throw new ProductException("Product could not be updated");
        }
    }
}



