package persistence;

class ToppingMapper extends ProductMapper {
    public ToppingMapper() {
        setup();
    }

    private void setup() {
        table = "Toppings";
        product_id = "topping_id";
        product_name = "topping_name";
        product_price = "topping_price";
    }
}
