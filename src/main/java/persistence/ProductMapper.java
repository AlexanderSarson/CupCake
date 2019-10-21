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


    public void createTop(Topping top) {
        String sql = "SELECT * FROM Toppings where topping_name = ?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1,top.getName());
            ResultSet rs = connection.selectQuery(statement);
            if(rs.next()){
                // There should not be a topping with the same name.
            }
            else {
                sql = "INSERT INTO Toppings (topping_name, topping_price) VALUES (?,?)";
                statement = connection.getConnection().prepareStatement(sql);
                statement.setString(1,top.getName());
                statement.setInt(2, top.getPrice());
                if(connection.executeQuery(statement)) {
                    int id = connection.lastID();
                    top.setId(id);
                }
                else {
                    // We could not create the top.
                }
            }
        } catch (SQLException e) {

        }
    }
















}



