package persistence;

public class BottomMapper extends ProductMapper {
    public BottomMapper(SQLConnection connection) {
        super(connection);
        table = "Toppings";
        product_id = "topping_id";
        product_name = "topping_name";
        product_price = "topping_price";
    }
}
