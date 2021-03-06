package persistence;

import logic.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides all necessary methods of the storage layer, to any that might have a use for them.
 * @author rando
 * @author Benjamin Paepke
 */
public class StorageFacade {
    private DataSource dataSource = new DataSource();

    private OrderMapper orderMapper = new OrderMapper(dataSource);
    private UserMapper userMapper = new UserMapper(dataSource);
    private ProductMapper productMapper = new ProductMapper(dataSource);

    private BottomMapper bottomMapper = new BottomMapper(dataSource);
    private ToppingMapper toppingMapper = new ToppingMapper(dataSource);

    public StorageFacade() throws IOException {
    }

    // ------ PRODUCT ------
    public List<Cupcake> getPremadeCupcakes() throws ProductException {
        return productMapper.getPremadeCucpakes();
    }
    public ArrayList<Cupcake> getCupCakes() throws ProductException {
        return productMapper.getAllCupcakes();
    }
    public Cupcake getCupcake(int id) throws ProductException {
        return productMapper.getCupcakeFromID(id);
    }

    // ------ USER ------
    public List<User> getAllUsers() throws UserException {
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
    public List<Order> getAllOrders() throws OrderException, UserException {
        List<User> users = getAllUsers();
        List<Order> orders = new ArrayList<>();
        for (User user: users) {
            try {
                orders.addAll(getAllOrders(user));
            } catch (OrderException e) {
            }
        }
        return orders;
    }
    public Order createOrder(Order order, User user) throws OrderException, UserBalanceException { return orderMapper.createOrder(order, user); }
    public void deleteOrder(Order order) throws SQLException, OrderException { orderMapper.deleteOrder(order);}

    public void updateUser(User user, String password) throws UserException {
        userMapper.updateUser(user, password);
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
    public List<IProduct> getAllBottoms() throws ProductException {
        return bottomMapper.getAllProducts();
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
    public List<IProduct> getAllToppings() throws ProductException {
        return toppingMapper.getAllProducts();
    }
}
