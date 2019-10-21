/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Bottom;
import logic.Cupcake;
import logic.IProduct;
import logic.Topping;

/**
 *
 * @author rando, Benjamin
 */
public class ProductMapper {

    private SQLConnection connection;

    public ProductMapper(SQLConnection connection) {
        this.connection = connection;
    }

    public ArrayList<Cupcake> getAllProducts() throws ProductException {
        ArrayList<Cupcake> cupcakes = new ArrayList<>();
        String sql = "SELECT * FROM bottoms, toppings";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet rs = connection.selectQuery(ps);
            while(rs.next()){
                cupcakes.add(findCupcakeFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new ProductException("Error when fetching all Cupcakes");
        }
        return cupcakes;
    }
    
    public Cupcake getProductFromID(int id) throws ProductException {
        String sql = "SELECT * FROM Cupcakes WHERE cupcake_id = ?";
        Cupcake cupcake = null;
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            cupcake = findCupcakeFromResultSet(connection.selectQuery(ps));
        } catch (SQLException e) {
            throw new ProductException("Error when fetching Cupcake");
        }
        return cupcake;
    }

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
        return new Cupcake(bot, top);
    }

    private void checkProductType(IProduct product, String table, String c1, String c2) {
        if(product instanceof Topping) { table = "Toppings"; c1 = "topping_name"; c2 = "topping_price"; }
        else if (product instanceof Bottom) { table = "Bottoms"; c1 = "bottom_name"; c2 = "bottom_price"; }
    }

    /**
     * Creates a product, either {@link logic.Bottom} or
     * @param product The topping to be created.
     * @throws ProductException If anything goes wrong in the creation of the topping.
     */
    public void createProduct(IProduct product) throws ProductException {
        String table ="", c1 = "",c2 = "";
        checkProductType(product,table,c1,c2);

        String sql = "SELECT * FROM "+table+" where "+c1+" = ?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1,product.getName());
            ResultSet rs = connection.selectQuery(statement);
            if(rs.next()){
                throw new ProductException("Product already exists");
            }
            else {
                sql = "INSERT INTO Toppings ("+c1+", "+c2+") VALUES (?,?)";
                statement = connection.getConnection().prepareStatement(sql);
                statement.setString(1,product.getName());
                statement.setInt(2, product.getPrice());
                if(connection.executeQuery(statement)) {
                    int id = connection.lastID();
                    product.setId(id);
                }
                else {
                    throw new ProductException("Could not create product");
                }
            }
        } catch (SQLException e) {
            throw new ProductException("Connection failed");
        }
    }
}



