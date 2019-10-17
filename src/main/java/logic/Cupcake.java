package logic;
import logic.Topping;
import logic.Bottom;

public class Cupcake extends BaseEntity {

    private Bottom bottom;
    private Topping topping;

    public Cupcake(long id, Bottom bottom, Topping topping){
        this.bottom=bottom;
        this.topping=topping;

    }

    public Bottom getBottom() {
        return bottom;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setBottom(Bottom bottom) {
        this.bottom = bottom;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }
}
