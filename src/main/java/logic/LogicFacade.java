package logic;

import persistence.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Oscar
 * version 1.0
 */

public class LogicFacade {
    private StorageFacade storageFacade;
    public LogicFacade() {
        try {
            storageFacade = new StorageFacade();
        } catch (IOException e) {
        }
    }

    public List<Cupcake> getPremadeCupcakes() throws ProductException {
        return storageFacade.getPremadeCupcakes();
    }

    public IProduct parseToIProduct(String str) {
        String[] parts = str.split(",");
        int id = Integer.parseInt(parts[1]);
        int price = Integer.parseInt(parts[2]);
        String name = parts[3];
        IProduct product = null;
        if(parts[0].equals("Bottom")) {
            product = new Bottom(price,name);
            product.setId(id);
        } else if (parts[0].equals("Topping")) {
            product = new Topping(price,name);
            product.setId(id);
        } else {
        }
        return product;
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
    public List<IProduct> getAllBottoms() throws ProductException {
        return storageFacade.getAllBottoms();
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
    public List<IProduct> getAllToppings() throws ProductException {
        return storageFacade.getAllToppings();
    }
    public void deleteTopping(Topping topping) {
        storageFacade.deleteTopping(topping);
    }

    // ------ ORDER ------
    public ShoppingCart getShoppingCart() {
        return new ShoppingCart();
    }
    public void addToShoppingCart(Bottom bottom, Topping topping, ShoppingCart shoppingCart){
        shoppingCart.addCupcakeToOrder(new Cupcake(bottom,topping));
    }
    public ShoppingCart submitOrder(User user, ShoppingCart shoppingcart) throws OrderException, UserBalanceException {
        if(shoppingcart.getDate() == null)
            shoppingcart.setDate(LocalDate.now());
        return (ShoppingCart)storageFacade.createOrder(shoppingcart, user);
    }
    public Order submitOrder(List<LineItem> items, User user) throws UserBalanceException, OrderException {
        Order order = new Order(items, LocalDate.now());
        return storageFacade.createOrder(order,user);
    }
    public List<Order> getOrdersForUser(User user) throws OrderException {
        return storageFacade.getAllOrders(user);
    }
    public List<Order> getAllOrders() throws OrderException, UserException {
        return storageFacade.getAllOrders();
    }

    // ------ USER ------
    public User newUser(String name, String email, String password) throws UserException {
        User user = new User(name,email,Role.CUSTOMER,new Account(0));
        return storageFacade.createUser(user,Encryption.encryptPsw(password));
    }
    public void updateUser(User user, String password) throws UserException {
        storageFacade.updateUser(user, Encryption.encryptPsw(password));
    }
    public boolean validatePassword(User user, String password) {
        try {
            User storedUser = storageFacade.validateUser(user.getMail(),Encryption.encryptPsw(password));
            if(storedUser.getId() != user.getId())
                return false;
        } catch (UserException e) {
            return false;
        }
        return true;
    }
    public User login(String email, String password) throws UserException {
        return storageFacade.validateUser(email,Encryption.encryptPsw(password));
    }
    public void addFunds(User user, int amountToDeposit) throws UserException {
        if(amountToDeposit < 0)
            throw new UserException("The amount must be a positive number");
        else {
            storageFacade.addFunds(user,amountToDeposit);
        }
    }
    public List<User> getAllUsers() throws UserException {
        return storageFacade.getAllUsers();
    }

    public void removeFromShoppingCart(Topping topping, Bottom bottom, ShoppingCart cart) {
        cart.removeCupcakeFromOrder(new Cupcake(bottom,topping));
    }
}

