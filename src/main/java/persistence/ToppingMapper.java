package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Bottom;
import logic.Topping;

class ToppingMapper extends ProductMapper {

    private SQLConnection connection;

    /**
     * Constructor of a ToppingMapper
     * @param connection is the connection to the database
     */

    public ToppingMapper(SQLConnection connection) {
        super(connection);
        table = "Toppings";
        product_id = "topping_id";
        product_name = "topping_name";
        product_price = "topping_price";
    }

    /**
     * Gets all Toppings from the database
     * @return an ArrayList of Toppings
     * @throws ProductException if toppings cannot be fetched from the database
     */
    public ArrayList<Topping> getAllToppings() throws ProductException {
        ArrayList<Topping> toppingList = new ArrayList<>();
        String sql = "SELECT * FROM toppings";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet rs = connection.selectQuery(ps);
            while (rs.next()) {
                //Cupcake Topping object creation
                int topID = rs.getInt("topping_id");
                int topPrice = rs.getInt("topping_price");
                String topName = rs.getString("topping_name");
                String topPic = rs.getString("topping_picture");
                Topping top = new Topping(topPrice, topName);
                top.setId(topID);
                toppingList.add(top);
            }
        } catch (SQLException e) {
            throw new ProductException("Error when fetching all Toppings");
        }
        return toppingList;
    }
}
