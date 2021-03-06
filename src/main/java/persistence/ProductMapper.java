
package persistence;

import logic.Bottom;
import logic.Cupcake;
import logic.IProduct;
import logic.Topping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has the purpose of mapping Products from the database to jave objects. General data, such as Cupcakes and intances where all tables are being checked,
 * can be collected through this class. Data regarding Bottoms or Toppings, make use of {@link logic.Bottom} and {@link logic.Topping}
 * @author rando
 * @author Benjamin Paepke
 * @author Oscar
 * @version 1.01
 */
public class ProductMapper {
    protected String table = "", product_id = "", product_name = "", product_price ="";
    protected DataSource dataSource;
    public ProductMapper(DataSource dataSource) {this.dataSource = dataSource;}

    /**
     * Gets all cupcakes from database
     * @return an ArrayList of cupcakes
     * @throws ProductException if cupcakes cannot be fetched from database
     */

    public ArrayList<Cupcake> getAllCupcakes() throws ProductException {
        ArrayList<Cupcake> cupcakes = new ArrayList<>();
        String sql = "select * from Cupcakes join Toppings on Cupcakes.topping_id = Toppings.topping_id join Bottoms on Cupcakes.bottom_id = Bottoms.bottom_id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cupcakes.add(findCupcakeFromResultSet(rs,false));
            }
        } catch (SQLException e) {
            throw new ProductException("Error when fetching all Cupcakes");
        }
        return cupcakes;
    }



    /**
     *Gets all products, either topping or bottom
     * @return an ArrayList of the product
     * @throws ProductException if anything goes wrong while trying to get all products
     */
    public ArrayList<IProduct> getAllProducts() throws ProductException{
        ArrayList<IProduct> products = new ArrayList<>();
        String sql = "SELECT * FROM "+table;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(findProductFromResultSet(rs));
            }
        }catch(SQLException e){
            throw new ProductException("Error when fetching all products");
        }
        return products;
    }
    /**
     * Gets cupcake from a given ID
     * @param id the id of the cupcake
     * @return the cupcake with the given id
     * @throws ProductException if anything goes wrong while trying to fetch cupcake
     */
    public Cupcake getCupcakeFromID(int id) throws ProductException {
        String sql = "select * from Cupcakes join Toppings on Cupcakes.topping_id = Toppings.topping_id join Bottoms on Cupcakes.bottom_id = Bottoms.bottom_id where cupcake_id = ?";
        Cupcake cupcake = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next())
                throw new ProductException("Could not find cupcake with id: " + id);
            else
                cupcake = findCupcakeFromResultSet(rs,false);
        } catch (SQLException e) {
            throw new ProductException("Error when fetching Cupcake");
        }
        return cupcake;
    }

    /**
     * Gets a product from the database from the ID
     * @param id the ID of the product you want to get
     * @return the product containing the id
     * @throws ProductException if anything goes wrong while trying to get the product
     */


    public IProduct getProductFromID (int id) throws ProductException{
        String sql = "SELECT * FROM "+table+" where "+product_id+" = ?";
        IProduct product = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                throw new ProductException("Product with given ID doesn't exist");
            } else{
                product = findProductFromResultSet(rs);
            }

        }catch (SQLException e){
            throw new ProductException("Product couldn't be fetched from database");

        }
        return product;
    }

    /**
     * Finds a product from a given resultset
     * @param rs the resultset you want to find the product from
     * @return the product containing the ID
     * @throws SQLException if anything goes wrong while trying to get the product
     */

    private IProduct findProductFromResultSet(ResultSet rs) throws SQLException {
        IProduct product = null;
        int productID = rs.getInt(product_id);
        int productPrice = rs.getInt(product_price);
        String productName = rs.getString(product_name);
        String ProductPic = rs.getString(4);
        if (table.equals("Bottoms")) {
            product = new Bottom(productPrice, productName);
            product.setId(productID);

        } else if (table.equals("Toppings")) {
            product = new Topping(productPrice, productName);
            product.setId(productID);
        }
        return product;
    }

    /**
     * Finds cupcake from a given Resultset
     * @param rs the resultset from where you want to find the cupcake
     * @return the cupcake from the resultset
     * @throws SQLException if anything goes wrong while trying to find cupcake
     */

    private Cupcake findCupcakeFromResultSet(ResultSet rs, boolean isPremade) throws SQLException {
        String cupcakeID = "cupcake_id";
        if(isPremade)
                cupcakeID = "premadecupcake_id";
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
        int id = rs.getInt(cupcakeID);
        Cupcake cupcake = new Cupcake(bot, top);
        cupcake.setId(id);
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

    /**
     * Gets all premade cupcakes from the database
     * @return an arraylist of all premade cupcakes
     * @throws ProductException if anything goes wrong while trying to get all cupcakes
     */
    public List<Cupcake> getPremadeCucpakes() throws ProductException {
        List<Cupcake> premade = new ArrayList<>();
        String sql = "select * from PreMadeCupcakes join Toppings on PreMadeCupcakes.topping_id = Toppings.topping_id join Bottoms on PreMadeCupcakes.bottom_id = Bottoms.bottom_id;";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                premade.add(findCupcakeFromResultSet(rs,true));
            }
        } catch (SQLException e) {
            throw new ProductException("Could not fetch Premade-cupcakes");
        }
        return premade;
    }
}



