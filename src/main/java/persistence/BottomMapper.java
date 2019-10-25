package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Bottom;

class BottomMapper extends ProductMapper {

    private SQLConnection connection;

    public BottomMapper(SQLConnection connection) {
        super(connection);
        table = "Bottoms";
        product_id = "bottom_id";
        product_name = "bottom_name";
        product_price = "bottom_price";
    }

    public ArrayList<Bottom> getAllBottoms() throws ProductException {
        ArrayList<Bottom> bottomList = new ArrayList<>();
        String sql = "SELECT * FROM bottoms";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet rs = connection.selectQuery(ps);
            while (rs.next()) {
                //Cupcake Bottom object creation
                int botID = rs.getInt("bottom_id");
                int botPrice = rs.getInt("bottom_price");
                String botName = rs.getString("bottom_name");
                String botPic = rs.getString("bottom_picture");
                Bottom bot = new Bottom(botPrice, botName);
                bot.setId(botID);
                bottomList.add(bot);
            }
        } catch (SQLException e) {
            throw new ProductException("Error when fetching all Bottoms");
        }
        return bottomList;
    }
}
