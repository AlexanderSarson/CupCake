package logic;
import logic.Topping;
import logic.Bottom;

/**
 * The Cupcake class represents the cupcake object, containing a bottom and a topping.
 * @author Oscar
 * version 1.0
 */
public class Cupcake extends BaseEntity {

    private Bottom bottom;
    private Topping topping;

    /**
     * Constructor of the cupcake, if we don't have an id.
     * @param bottom bottom of the cupcake
     * @param topping topping of the cupcake
     */
    public Cupcake (Bottom bottom, Topping topping){
        this.bottom=bottom;
        this.topping=topping;
    }

    /**
     * Constructor of the cupcake
     * @param id The id of the cupcake
     * @param bottom bottom of the cupcake
     * @param topping topping of the cupcake
     */
    public Cupcake(long id, Bottom bottom, Topping topping){
        this.bottom=bottom;
        this.topping=topping;
    }

    /**
     * Gets the price of the cupcake
     * @return price of the cupcake
     */
    public int getPrice(){
        int price = bottom.getPrice()+topping.getPrice();
        return price;
    }

    /**
     * Gets the bottom of the cupckae
     * @return the bottom of the cupcake.
     */
    public Bottom getBottom() {
        return bottom;
    }

    /**
     * Gets the topping of the cupcake
     * @return the topping of the cupcake.
     */
    public Topping getTopping() {
        return topping;
    }

    /**
     * Sets the bottom of the cupcake
     * @param bottom the new bottom of the cupcake
     */
    public void setBottom(Bottom bottom) {
        this.bottom = bottom;
    }

    /**
     * Sets the topping of the cupcake
     * @param topping the new topping of the cupcake
     */
    public void setTopping(Topping topping) {
        this.topping = topping;
    }
}
