package logic;
import logic.Account;
import logic.BaseEntity;
import logic.Bottom;
import logic.Cupcake;
import logic.LineItem;
import logic.LogicFacade;
import logic.Role;
import logic.ShoppingCart;
import logic.Topping;
import logic.User;
import logic.Order;

import java.util.List;


public class LogicFacade {

    public LogicFacade(){

    }

    public User login(String email, String password){
        return null;

    }

    public User newUser(String name, String email, String password){
        return null;
    }

    public ShoppingCart getShoppingCart(){
        return null;
    }

    public List getPremadeCupcakes(){
        return null;
    }

    public void addToShoppingCart(Bottom bottom, Topping topping, ShoppingCart shoppingCart){
        Cupcake cupcake = new Cupcake(bottom,topping);

    }

    public Order submitOrder(User user, ShoppingCart shoppingcart){
        return null;
    }

    public List getOrdersForUser(User user){
        return null;
    }

    public void addFunds(User user, int amountToDeposit) throws IllegalArgumentException{
        if(amountToDeposit < 0) throw new IllegalArgumentException ("The amount must be a positive number");
    }

    public void updateUser(User user){
        
    }

}
