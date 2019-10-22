package persistence;

import logic.IProduct;

public class ToppingMapper extends ProductMapper {
    public ToppingMapper(SQLConnection connection) {
        super(connection);
        table = "Toppings";
        product_id = "topping_id";
        product_name = "topping_name";
        product_price = "topping_price";
    }
}
