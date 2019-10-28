package logic;

import java.time.LocalDate;

public class ShoppingCart extends Order {
    public ShoppingCart() {
    }

    /**
     * Basic constructor
     * @param date The date of the shopping cart
     */
    public ShoppingCart(LocalDate date) {
        super(date);
    }
}
