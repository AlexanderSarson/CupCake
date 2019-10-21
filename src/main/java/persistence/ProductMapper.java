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

    public ArrayList<Cupcake> getAllProducts() {
        ArrayList<Cupcake> cupcakes = new ArrayList<>();
        String sql = "SELECT * FROM bottoms, toppings";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet rs = connection.selectQuery(ps);
            while(rs.next()){
                Cupcake cupcake = findCupcakeFromResultSet(rs);
                cupcakes.add(cupcake);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cupcakes;
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



