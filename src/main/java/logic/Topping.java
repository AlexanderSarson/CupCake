package logic;

/**
 * @author Oscar
 * version 1.0
 */

/**
 * The Topping class represents the topping in the Cupcake object
 * Topping has the attributes id, price, name.
 */
public class Topping extends BaseEntity implements IProduct{
    private int price;
    private String name;

    /**
     * Constructor of the Topping
     * @param price The price of the Topping
     * @param name The name of the topping
     * @throws IllegalArgumentException If the price has a negative value.
     */
    public Topping(int price, String name) throws IllegalArgumentException{
        if(price<0)throw new IllegalArgumentException("Price must be a positive number");
        else
            this.price = price;
        this.name = name;
    }

    /**
     * Gets the name of the topping object
     * @return name of the topping
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the topping object
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the topping
     * @return the price of the topping
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the new price of the topping
     * @param price The new price
     * @throws IllegalArgumentException if the price is a negative value.
     */
    public void setPrice(int price) throws IllegalArgumentException {
        if(price<0)
            throw new IllegalArgumentException("Price must be a positive value");
        else
            this.price = price;
    }
}
