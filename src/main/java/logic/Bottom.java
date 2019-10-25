package logic;



/**
 * The Bottom class represents the bottom in the Cupcake object
 * The bottom contains the attributes id, price & name
 *  @author Oscar
 *  version 1.0
 */

public class Bottom extends BaseEntity implements IProduct{

    private int price;
    private String name;

    /**
     * Constructor of a bottom.
     * @param price the price of the bottom
     * @param name the name of the bottom
     * @throws IllegalArgumentException if the price is a negative value.
     */
    public Bottom (int price, String name ) throws IllegalArgumentException{
        if (price < 0) throw new IllegalArgumentException("Price must be a positive value");
        else {
            this.price = price;
            this.name = name;
        }
    }

    /**
     * Gets the price of the bottom object
     * @return The price of the bottom
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the bottom
     * @param price The new price
     * @Throws IllegalArgumentException if the price is a negative value.
     */
    public void setPrice(int price) throws IllegalArgumentException{
        if(price<0)
            throw new IllegalArgumentException("Price must be a positive value");
        else
            this.price = price;
    }

    /**
     * Gets the name of the bottom
     * @return the name of the bottom.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the bottom.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
