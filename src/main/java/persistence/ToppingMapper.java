package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Bottom;
import logic.Topping;

class ToppingMapper extends ProductMapper {
    public ToppingMapper(DataSource dataSource) {
        super(dataSource);
        setup();
    }

    private void setup() {
        table = "Toppings";
        product_id = "topping_id";
        product_name = "topping_name";
        product_price = "topping_price";
    }

//    public ArrayList<Topping> getAllToppings() throws ProductException {
//        ArrayList<Topping> toppingList = new ArrayList<>();
//        String sql = "SELECT * FROM toppings";
//        try {
//            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
//            ResultSet rs = connection.selectQuery(ps);
//            while (rs.next()) {
//                //Cupcake Topping object creation
//                int topID = rs.getInt("topping_id");
//                int topPrice = rs.getInt("topping_price");
//                String topName = rs.getString("topping_name");
//                String topPic = rs.getString("topping_picture");
//                Topping top = new Topping(topPrice, topName);
//                top.setId(topID);
//                toppingList.add(top);
//            }
//        } catch (SQLException e) {
//            throw new ProductException("Error when fetching all Toppings");
//        }
//        return toppingList;
//    }
}
