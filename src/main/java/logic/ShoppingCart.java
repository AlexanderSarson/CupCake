package logic;

import java.time.LocalDate;

public class ShoppingCart extends Order {
    public ShoppingCart() {
    }
    public ShoppingCart(LocalDate date) {
        super(date);
    }
}
