package logic;
import logic.Account;
import logic.BaseEntity;
import logic.Bottom;
import logic.Cupcake;
import logic.LineItem;
import logic.Role;
import logic.ShoppingCart;
import logic.Topping;
import logic.User;
import logic.Order;
import persistence.ProductException;
import persistence.StorageFacade;
import persistence.UserException;
import persistence.UserMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oscar
 * @author Benjamin Paepke
 * version 1.0
 */


public class LogicFacade {
    private StorageFacade storageFacade = new StorageFacade();
    public LogicFacade(){}

    // ------ PRODUCT ------
    public ArrayList<Cupcake> getAllCupcakes() throws ProductException {
        return storageFacade.getAllProducts();
    }
    public Cupcake getCupcakeByID(int id) throws ProductException {
        return storageFacade.getProduct(id);
    }

    public List getPremadeCupcakes(){
        // TODO(Benjamin): Implement me!
        return null;
    }

    // ------ BOTTOM ------
    public Bottom createBottom(String name, int price, String picturePath) throws ProductException {
        Bottom bottom = new Bottom(price,name);
        storageFacade.createBottom(bottom);
        return bottom;
    }
    public Bottom updateBottom(Bottom bottom) throws ProductException {
        storageFacade.updateBottom(bottom);
        return bottom;
    }
    public void deleteBottom(Bottom bottom) {
        storageFacade.deleteBottom(bottom);
    }
    // ------ TOPPING ------
    public Topping createTopping(String name, int price, String picturePath) throws ProductException {
        Topping topping = new Topping(price,name);
        storageFacade.createTopping(topping);
        return topping;
    }
    public Topping updateBottom(Topping topping) throws ProductException {
        storageFacade.updateTopping(topping);
        return topping;
    }
    public void deleteTopping(Topping topping) {
        storageFacade.deleteTopping(topping);
    }

    // ------ ORDER ------
    public ShoppingCart getShoppingCart() {
        // TODO(Benjamin): Implement me!
        return null;
    }
    public void addToShoppingCart(Bottom bottom, Topping topping, ShoppingCart shoppingCart){
        // TODO(Benjamin): Complete me!
        Cupcake cupcake = new Cupcake(bottom,topping);
    }
    public Order submitOrder(User user, ShoppingCart shoppingcart){
        // TODO(Benjamin) Implement me!
        return null;
    }

    // ------ USER ------
    public User newUser(String name, String email, String password) throws UserException {
        // TODO(Benjamin) All new users will be set to Customers, add another param for choosing?
        User user = new User(name,email,Role.CUSTOMER,new Account(0));
        return storageFacade.createUser(user,password);
    }
    // TODO(Benjamin): Why is there a duplicate createUser (CreateUser and newUser???)
    public void createUser(String name, String mail, String password, Role role, int balance) throws UserException {
        Account account = new Account(balance);
        User user = new User(name,mail,role,account);
        storageFacade.createUser(user,password);
    }
    public void updateUser(User user) throws UserException {
        storageFacade.updateUser(user);
    }
    public User login(String email, String password) throws UserException {
        // TODO(Benjamin) Insert Encryption for password here!
        return storageFacade.validateUser(email,password);
    }

    public void addFunds(User user, int amountToDeposit) throws IllegalArgumentException{
        // TODO(Benjamin): Complete me!
        if(amountToDeposit < 0) throw new IllegalArgumentException ("The amount must be a positive number");
    }

    public List getOrdersForUser(User user){
        // TODO(Benjamin): Implement me!
        return null;
    }
}
