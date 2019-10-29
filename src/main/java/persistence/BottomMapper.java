package persistence;

import logic.Bottom;
import logic.IProduct;
import logic.Topping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class BottomMapper extends ProductMapper {
    public BottomMapper(DataSource dataSource) {
        super(dataSource);
        setup();
    }

    private void setup() {

        table = "Bottoms";
        product_id = "bottom_id";
        product_name = "bottom_name";
        product_price = "bottom_price";
    }


    public List<Bottom> getAllBottoms() throws ProductException {
        List<Bottom> products = new ArrayList<>();
        String sql = "select * from "+table;
        try (Connection connection = super.dataSource.getConnection();
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

    private Bottom findProductFromResultSet(ResultSet rs) throws SQLException {
        Bottom product = null;
        int productID = rs.getInt(product_id);
        int productPrice = rs.getInt(product_price);
        String productName = rs.getString(product_name);
        String ProductPic = rs.getString(4);
        product = new Bottom(productPrice, productName);
        product.setId(productID);
        return product;
    }

    /**
     * Gets all Bottoms from the database
     * @return an ArrayList of Bottoms
     * @throws ProductException is anything goes wrong while fetching all bottoms from the database
     */

//    public ArrayList<Bottom> getAllBottoms() throws ProductException {
//        ArrayList<Bottom> bottomList = new ArrayList<>();
//        String sql = "SELECT * FROM bottoms";
//        try {
//            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
//            ResultSet rs = connection.selectQuery(ps);
//            while (rs.next()) {
//                //Cupcake Bottom object creation
//                int botID = rs.getInt("bottom_id");
//                int botPrice = rs.getInt("bottom_price");
//                String botName = rs.getString("bottom_name");
//                String botPic = rs.getString("bottom_picture");
//                Bottom bot = new Bottom(botPrice, botName);
//                bot.setId(botID);
//                bottomList.add(bot);
//            }
//        } catch (SQLException e) {
//            throw new ProductException("Error when fetching all Bottoms");
//        }
//        return bottomList;
//    }

}
