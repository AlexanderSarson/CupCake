package logic;

public class LineItem extends BaseEntity {

    private int quantity;
    private Cupcake cupcake;

    public LineItem(Cupcake cupcake, int quantity){
        this.quantity = quantity;
        this.cupcake = cupcake;
    }

    public int calculateTotalPrice(){
        int cupcakePrice = cupcake.getBottom().getPrice()+cupcake.getTopping().getPrice();
        int totalPrice = cupcakePrice*quantity;
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cupcake getCupcake() {
        return cupcake;
    }

    public void setCupcake(Cupcake cupcake) {
        this.cupcake = cupcake;
    }
}
