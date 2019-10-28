package persistence;

import logic.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class StorageFacadeTest {
    private static DataSource dataSource;
    static {
        try {
            dataSource = new DataSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UserMapper userMapper = new UserMapper(dataSource);
    private ProductMapper productMapper = new ProductMapper(dataSource);
    private BottomMapper bottomMapper = new BottomMapper(dataSource);
    private ToppingMapper toppingMapper = new ToppingMapper(dataSource);
    private OrderMapper orderMapper = new OrderMapper(dataSource);

    private static ArrayList<String> scanFromFile(String filename) {
        ArrayList<String> lines = new ArrayList();
        try {
            Scanner scan = new Scanner(new File("Scripts/" + filename));
            scan.useDelimiter(Pattern.compile(";"));
            while (scan.hasNext()) {
                lines.add(scan.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private Account account;
    private User user;

    private static void rebuildDB() {
        ArrayList<String> DBsetUp = scanFromFile("CupCake_Setup.sql");
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()) {
            for (String sqlStatement : DBsetUp) {
                if(!sqlStatement.isEmpty())
                    stmt.executeUpdate(sqlStatement);
            }
        } catch (SQLException e) {
        }
    }

    @Before
    public void setUp() {
        account = new Account(20);
        account.setId(1);
        user = new User(1,"userNameTest","loginMailTest",Role.CUSTOMER,account);
        rebuildDB();
    }

    @AfterClass
    public static void tearDownClass() {
        ArrayList<String> DBsetUp = scanFromFile("CupCake_Setup.sql");
        rebuildDB();
    }

    // ----- PRODUCT -----
    @Test
    public void getAllProducts() throws ProductException {
        ArrayList<Cupcake> cupcakes = productMapper.getAllProducts();
        assertEquals(50, cupcakes.size());
    }
    @Test
    public void getProduct() throws ProductException {
        Cupcake cupcake = productMapper.getProductFromID(10);
        assertEquals(10,cupcake.getId());
        assertEquals(2,cupcake.getTopping().getId());
        assertEquals(5,cupcake.getBottom().getId());
    }

    @Test
    public void getPremadeCupcakes() throws ProductException {
        List<Cupcake> cupcakes = productMapper.getPremadeCucpakes();
        assertEquals(10, cupcakes.size());
    }


    // ----- ORDER -----
    @Test
    public void getAllOrders() throws OrderException {
        ArrayList<Order> orders = orderMapper.getAllOrders(user);
        assertEquals(1,orders.size());
    }
    @Test
    public void createOrder() throws OrderException, UserBalanceException {
        Order order = new Order(LocalDate.now());
        Bottom bottom = new Bottom(1,"test");
        bottom.setId(1);
        Topping topping = new Topping(1,"test");
        topping.setId(1);
        Cupcake cupcake = new Cupcake(bottom,topping);
        cupcake.setId(1);
        order.addLineItem(new LineItem(cupcake,1));

        orderMapper.createOrder(order,user);

        assertEquals(2,order.getId());
        List<Order> orders = orderMapper.getAllOrders(user);
        Order lastOrder = orders.get(orders.size()-1);

        assertEquals(order.getId(),lastOrder.getId());
        //assertEquals(order.getDate(),lastOrder.getDate());
        assertEquals(order.getTotalQuantity(),lastOrder.getTotalQuantity());
    }
    @Test
    public void deleteOrder() throws OrderException {
        int size = orderMapper.getNumberOfOrders();
        Order order = new Order();
        order.setId(1);
        orderMapper.deleteOrder(order);
        int newSize = orderMapper.getNumberOfOrders();
        assertEquals(size-1, newSize);
    }
    @Test(expected = OrderException.class)
    public void deleteOrder_with_non_existing_order() throws OrderException {
        int size = orderMapper.getNumberOfOrders();
        Order order = new Order();
        order.setId(-100);
        orderMapper.deleteOrder(order);
        int newSize = orderMapper.getNumberOfOrders();
        assertEquals(size, newSize);
    }

    // ----- User -----
    @Test
    public void getAllUsers() throws UserException {
        ArrayList<User> users = userMapper.getAllUser();
        assertEquals(2,users.size());
    }
    @Test
    public void addFunds() throws UserException {
        int exp = user.getAccount().getBalance() + 1000;
        userMapper.addFunds(user,1000);
        assertEquals(exp, user.getAccount().getBalance());
    }

    @Test
    public void createUser() throws UserException {
        Account newAcc = new Account(1000);
        User user = new User("PeterLarsen","PeterL@example.com",Role.CUSTOMER,newAcc);
        user = userMapper.createUser(user,account,"Larsen1234");
        assertEquals(3, user.getId());
    }
    @Test(expected = UserException.class)
    public void createUser_with_existing_mail() throws UserException {
        Account newAcc = new Account(1000);
        User user = new User("PeterLarsen","loginMailTest",Role.CUSTOMER,newAcc);
        user = userMapper.createUser(user,account,"Larsen1234");
    }
    @Test
    public void updateUser() throws UserException {
        User user = new User(1,"Peter", "Peter@example.com",Role.CUSTOMER,account);
        userMapper.updateUser(user);
    }
    @Test(expected = UserException.class)
    public void updateUser_with_non_existing_user() throws UserException {
        User user = new User(1000,"Peter", "Peter@example.com",Role.CUSTOMER,account);
        userMapper.updateUser(user);
    }
    @Test
    public void deleteUser() throws UserException {
        userMapper.deleteUser(user);
    }
    @Test
    public void validateUser() throws UserException {
        User user = userMapper.login("loginMailTest","loginPasswordTest");
        assertNotNull(user);
    }

    // ----- BOTTOM -----
    @Test
    public void createBottom() throws ProductException {
        Bottom bottom = new Bottom(12,"Brick");
        bottomMapper.createProduct(bottom);
        assertEquals(6, bottom.getId());
    }
    @Test
    public void updateBottom() throws ProductException {
        Bottom bottom = new Bottom(10,"WhiteChocolate");
        bottom.setId(1);
        bottomMapper.updateProduct(bottom);
    }
    @Test(expected = ProductException.class)
    public void updateBottom_with_non_existing_product() throws ProductException {
        Bottom bottom = new Bottom(10,"WhiteChocolate");
        bottom.setId(11);
        bottomMapper.updateProduct(bottom);
    }

    // ----- TOPPING -----
    @Test
    public void createTopping() throws ProductException {
        Topping topping = new Topping(13,"Stone");
        toppingMapper.createProduct(topping);
        assertEquals(11, topping.getId());
    }
    @Test
    public void updateTopping() throws ProductException {
        Topping topping = new Topping(13, "Stone");
        topping.setId(1);
        toppingMapper.updateProduct(topping);
    }
    @Test(expected = ProductException.class)
    public void updateTopping_with_non_existing_topping() throws ProductException {
        Topping topping = new Topping(13, "Stone");
        topping.setId(50);
        toppingMapper.updateProduct(topping);
    }
}