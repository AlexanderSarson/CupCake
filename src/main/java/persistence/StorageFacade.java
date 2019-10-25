package persistence;

import logic.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides all necessary methods of the storage layer, to any that might have a use for them.
 * @author rando
 * @author Benjamin Paepke
 */
public class StorageFacade {
    private final SQLConnection con = new SQLConnection();
    private DataSource dataSource = new DataSource();
    private PreparedStatement ps;

    private OrderMapper orderMapper = new OrderMapper(dataSource);
    private UserMapper userMapper = new UserMapper(dataSource);
    private ProductMapper productMapper = new ProductMapper(dataSource);

    private BottomMapper bottomMapper = new BottomMapper(dataSource);
    private ToppingMapper toppingMapper = new ToppingMapper(dataSource);

    public StorageFacade() throws IOException {
    }

    // ------ PRODUCT ------
    public ArrayList<Cupcake> getAllProducts() throws ProductException {
        return productMapper.getAllProducts();
    }
    public Cupcake getProduct(int id) throws ProductException {
        return productMapper.getProductFromID(id);
    }

    // ------ USER ------
    public ArrayList<User> getAllUsers() throws UserException {
        return userMapper.getAllUser();
    }
    public User createUser(User user, String password) throws UserException {
        return userMapper.createUser(user,user.getAccount(),password);
    }
    public void addFunds(User user, int amount) throws UserException {
        userMapper.addFunds(user,amount);
    }

    // ------ ORDER ------
    public List<Order> getAllOrders(User user) throws OrderException { return orderMapper.getAllOrders(user); }
    public Order createOrder(Order order, User user) throws SQLException, OrderException { return orderMapper.createOrder(order, user); }
    public void deleteOrder(Order order) throws SQLException, OrderException { orderMapper.deleteOrder(order);}

    public void updateUser(User user) throws UserException {
        userMapper.updateUser(user);
    }
    public boolean deleteUser(User user) throws UserException {
        userMapper.deleteUser(user);
        return true;
    }
    public User validateUser(String email, String password) throws UserException {
        return userMapper.login(email,password);
    }

    // ------ BOTTOM ------
    public void createBottom(Bottom bottom) throws ProductException {
        bottomMapper.createProduct(bottom);
    }
    public void updateBottom(Bottom bottom) throws ProductException {
        bottomMapper.updateProduct(bottom);
    }
    public void deleteBottom(Bottom bottom) {
        //bottomMapper.deleteProduct(bottom);
    }
    public ArrayList<Bottom> getAllBottoms() throws ProductException {
        return null;
    }

    // ------ TOPPING ------
    public void createTopping(Topping topping) throws ProductException {
        toppingMapper.createProduct(topping);
    }
    public void updateTopping(Topping topping) throws ProductException {
        toppingMapper.updateProduct(topping);
    }
    public void deleteTopping(Topping topping) {
        //toppingMapper.deleteProduct(topping);
    }
    public ArrayList<Topping> getAllToppings() throws ProductException {
        return null;
    }
}
