package persistence;

class BottomMapper extends ProductMapper {
    public BottomMapper() {
        setup();
    }

    private void setup() {
        table = "Bottoms";
        product_id = "bottom_id";
        product_name = "bottom_name";
        product_price = "bottom_price";
    }
}
