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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public boolean deleteProductFromID(int id) throws ProductException{
        String[] topOrBot = topOrBot(validation);
        String table = topOrBot[0]; //Toppings/Bottoms
        String row = topOrBot[1]; //topping/bottom
        String sql = "DELETE FROM " + table + " WHERE " + row + "_id = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            return connection.executeQuery(ps); //If sucsess <-True
        } catch (SQLException e) {
            throw new ProductException("Error when fetching Cupcake");
        }

//TODO(Tobias): Update All Cupcakes?
        //Update Cupcakes Topping id + Alle Bottom id
        //Update Cupcakes Bottom id + Alle Topping id
        return false;
        
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
        
    }





}



