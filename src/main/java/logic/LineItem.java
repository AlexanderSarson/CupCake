package logic;

/**
 * The lineitem class represents an itemline containing product information and quantity.
 * @author Oscar
 * version 1.0
 */
public class LineItem extends BaseEntity {

    private int quantity;
    private Cupcake cupcake;

    /**
     * Constructor of a lineitem
     * @param cupcake the chosen cupcake
     * @param quantity amount of the chosen cupcake
     * @throws IllegalArgumentException if the quantity is a negative value.
     */
    public LineItem(Cupcake cupcake, int quantity) throws IllegalArgumentException{
        if (quantity<0) {
            throw new IllegalArgumentException("Quantity must a positive number");
        }else {
            this.quantity = quantity;
        }
        this.cupcake = cupcake;
    }

    /**
     * Calculates the price of the lineitem object
     * @return the total price.
     */
    public int calculateTotalPrice(){
        int cupcakePrice = cupcake.getBottom().getPrice()+cupcake.getTopping().getPrice();
        int totalPrice = cupcakePrice*quantity;
        return totalPrice;
    }

    /**
     * Gets the quantity
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity new quantity of the lineitem
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    /**
     * Gets the cupcake of a given lineitem
     * @return the cupcake
     */
    public Cupcake getCupcake() {
        return cupcake;
    }

    /**
     * Sets the new cupcake of the lineitem
     * @param cupcake the new cupcake
     */
    public void setCupcake(Cupcake cupcake) {
        this.cupcake = cupcake;
    }
}
