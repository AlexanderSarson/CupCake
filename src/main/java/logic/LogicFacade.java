package logic;

import persistence.StorageFacade;

import java.util.List;

/**
 * @author Oscar
 * version 1.0
 */


public class LogicFacade {
    private StorageFacade storageFacade = new StorageFacade();

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

    public List<Cupcake> getPremadeCupcakes(){
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

    public void createUser(String name, String mail, String password, Role role, int balance) {
        Account account = new Account(-1,balance);
        User user = new User(name,mail,role,account);
        storageFacade.createUser(user,account,password);
    }

    public void updateUser(User user){
        
    }

    public void removeFromShoppingcart (Topping topping, Bottom bottom, ShoppingCart cart) {
    }
}
