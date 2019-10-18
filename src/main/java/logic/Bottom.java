package logic;

/**
 * @author Oscar
 * version 1.0
 */

/**
 * This class represents the bottom in the Cupcake object
 * The bottom contains the attributes id, price & name
 */

public class Bottom extends BaseEntity{

    private int price;
    private String name;

    public Bottom (int price, String name ) throws IllegalArgumentException{
        if (price < 0) throw new IllegalArgumentException("Price must be a positive number");
        else
            this.price=price;
            this.name=name;
    }

    /**
     * This methods get the price of the bottom object
     * @return price of the bottom
     */
    public int getPrice() {
        return price;
    }

    /**
     * This method sets the price of the bottom project
     * @param price
     */
    public void setPrice(int price) throws IllegalArgumentException{
        if(price<0)
            throw new IllegalArgumentException("Price must be a positive value");
        else
            this.price = price;
    }

    /**
     * This methods gets the name of the bottom
     * @return the name attribute of particular bottom
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name attribute of the particular bottom
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
