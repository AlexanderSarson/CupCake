package logic;

/**
 * @author Oscar
 * version 1.0
 */

/**
 * This class represents the topping in the Cupcake object
 * Topping has the attributes id, price, name.
 */

public class Topping extends BaseEntity{
    private int price;
    private String name;

    public Topping(int price, String name) throws IllegalArgumentException{

        this.price = price;
        this.name = name;
    }

    /**
     * This method gets the name of the topping object
     * @return name of the topping
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the topping object
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the price of the particular topping
     * @return the price of the particular topping
     */
    public int getPrice() {
        return price;
    }

    /**
     * This method sets the price of the topping project
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
