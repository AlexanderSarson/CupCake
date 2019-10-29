package persistence;

import logic.Bottom;
import logic.Topping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Topping> getAllToppings() throws ProductException {
        List<Topping> products = new ArrayList<>();
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

    private Topping findProductFromResultSet(ResultSet rs) throws SQLException {
        Topping product = null;
        int productID = rs.getInt(product_id);
        int productPrice = rs.getInt(product_price);
        String productName = rs.getString(product_name);
        String ProductPic = rs.getString(4);
        product = new Topping(productPrice, productName);
        product.setId(productID);
        return product;
    }

}
