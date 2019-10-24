package persistence;

class BottomMapper extends ProductMapper {
    public BottomMapper(SQLConnection connection) {
        super(connection);
        table = "Bottoms";
        product_id = "bottom_id";
        product_name = "bottom_name";
        product_price = "bottom_price";
    }
}
