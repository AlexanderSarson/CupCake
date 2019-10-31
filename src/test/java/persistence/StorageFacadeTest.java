package persistence;

import logic.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

    private ArrayList<String> scanFromFile(String filename) {
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

    private void rebuildDB() {
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
        account = new Account(1000);
        account.setId(1);
        user = new User(1,"userNameTest","loginMailTest",Role.CUSTOMER,account);
    }

    @After
    public void tearDownClass() {
        ArrayList<String> DBsetUp = scanFromFile("CupCake_Setup.sql");
        rebuildDB();
    }

    // ----- CUPCAKE -----
    @Test
    public void testGetAllCupcakes() throws ProductException {
        ArrayList<Cupcake> cupcakes = productMapper.getAllCupcakes();
        assertEquals(50, cupcakes.size());
    }
    @Test
    public void testGetCupcakeFromID() throws ProductException {
        Cupcake cupcake = productMapper.getCupcakeFromID(10);
        assertEquals(10,cupcake.getId());
        assertEquals(2,cupcake.getTopping().getId());
        assertEquals(5,cupcake.getBottom().getId());
    }

    @Test
    public void testGetPremadeCupcakes() throws ProductException {
        List<Cupcake> cupcakes = productMapper.getPremadeCucpakes();
        assertEquals(10, cupcakes.size());
    }


    // ----- ORDER -----
    @Test
    public void testGetAllOrders() throws OrderException {
        List<Order> orders = orderMapper.getAllOrders(user);
        assertEquals(1,orders.size());
    }
    @Test
    public void testCreateOrder() throws OrderException, UserBalanceException {
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
        assertEquals(order.getDate(),lastOrder.getDate());
        assertEquals(order.getTotalQuantity(),lastOrder.getTotalQuantity());
    }
    @Test
    public void testDeleteOrder() throws OrderException {
        int size = orderMapper.getNumberOfOrders();
        Order order = new Order();
        order.setId(1);
        orderMapper.deleteOrder(order);
        int newSize = orderMapper.getNumberOfOrders();
        assertEquals(size-1, newSize);
    }
    @Test(expected = OrderException.class)
    public void testDeleteOrder_with_non_existing_order() throws OrderException {
        int size = orderMapper.getNumberOfOrders();
        Order order = new Order();
        order.setId(-100);
        orderMapper.deleteOrder(order);
        int newSize = orderMapper.getNumberOfOrders();
        assertEquals(size, newSize);
    }

    // ----- User -----
    @Test
    public void testGetAllUsers() throws UserException {
        List<User> users = userMapper.getAllUser();
        assertEquals(3,users.size());
    }
    @Test
    public void testAddFunds() throws UserException {
        int exp = user.getAccount().getBalance() + 1000;
        userMapper.addFunds(user,1000);
        assertEquals(exp, user.getAccount().getBalance());
    }
    @Test (expected = UserException.class)
    public void testAdd_negative_amount_funds() throws UserException{
        Account newAcc = new Account (1000);
        User user = new User("PeterLarsen","PeterL@example.com",Role.CUSTOMER,newAcc);
        user = userMapper.createUser(user,account,"Larsen1234");
        userMapper.addFunds(user,-100);
    }

    @Test
    public void testCreateUser() throws UserException {
        Account newAcc = new Account(1000);
        User user = new User("PeterLarsen","PeterL@example.com",Role.CUSTOMER,newAcc);
        user = userMapper.createUser(user,account,"Larsen1234");
        assertEquals(4, user.getId());
    }
//    @Test(expected = UserException.class)
//    public void testCreateUser_with_existing_mail() throws UserException {
//        Account newAcc = new Account(1000);
//        User user = new User("PeterLarsen","loginMailTest",Role.CUSTOMER,newAcc);
//        user = userMapper.createUser(user,account,"Larsen1234");
//    }
    @Test
    public void testUpdateUser() throws UserException {
        User user = new User(1,"Peter", "Peter@example.com",Role.CUSTOMER,account);
        userMapper.updateUser(user,null);
    }
    @Test(expected = UserException.class)
    public void testUpdateUser_with_non_existing_user() throws UserException {
        User user = new User(1000,"Peter", "Peter@example.com",Role.CUSTOMER,account);
        userMapper.updateUser(user,null);
    }
    @Test
    public void testDeleteUser() throws UserException {
        userMapper.deleteUser(user);
    }
    @Test
    @Ignore
    public void testValidateUser() throws UserException {
        User user = userMapper.login("lognValidation@example.com","73941847d9611927275d93139981ee78316de50bc51bf398f8ccdd778c7723f370cb252c5293c085ec3c6a3d185246837ed71d651a679cb680793581ad77ac24");
        assertNotNull(user);
    }

    // ----- BOTTOM -----
    @Test
    public void testCreateBottom() throws ProductException {
        Bottom bottom = new Bottom(12,"Brick");
        bottomMapper.createProduct(bottom);
        assertEquals(6, bottom.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreate_bottom_with_negative_price() throws ProductException{
        Bottom bottom = new Bottom(-5,"RichChocolate");
        bottomMapper.createProduct(bottom);
    }
    @Test
    public void testUpdateBottom() throws ProductException {
        Bottom bottom = new Bottom(10,"WhiteChocolate");
        bottom.setId(1);
        bottomMapper.updateProduct(bottom);
    }
    @Test(expected = ProductException.class)
    public void testUpdateBottom_with_non_existing_product() throws ProductException {
        Bottom bottom = new Bottom(10,"WhiteChocolate");
        bottom.setId(11);
        bottomMapper.updateProduct(bottom);
    }
    @Test(expected = ProductException.class)
    public void testCreate_existing_bottom() throws ProductException{
        Bottom bottom = new Bottom(1,"Chocolate");
        bottomMapper.createProduct(bottom);
    }

    @Test (expected = ProductException.class)
    public void testUpdate_bottom_with_existing_name() throws ProductException {
        Bottom bot = new Bottom (1,"Rockwool");
        bottomMapper.createProduct(bot);
        bot.setName("Chocolate");
        bottomMapper.updateProduct(bot);
    }

        @Test
        public void test_get_bottom_from_ID() throws ProductException{
        Bottom bottom = new Bottom(12,"Brick");
        bottomMapper.createProduct(bottom);
        assertEquals(bottom.getName(),bottomMapper.getProductFromID(6).getName());
        assertEquals(bottom.getPrice(),bottomMapper.getProductFromID(6).getPrice());
    }
        @Test (expected = ProductException.class)
        public void test_get_bottom_from_non_existing_ID() throws ProductException, SQLException{
            Bottom bottom = new Bottom(12,"Brick");
            bottomMapper.createProduct(bottom);
            bottomMapper.getProductFromID(7);
        }
        @Test
        public void get_all_bottoms() throws ProductException{
        ArrayList<IProduct> allBottoms;
        allBottoms = bottomMapper.getAllProducts();
        assertEquals(5,allBottoms.size());
        }


    // ----- TOPPING -----
    @Test
    public void testCreateTopping() throws ProductException {
        Topping topping = new Topping(13,"Stone");
        toppingMapper.createProduct(topping);
        assertEquals(11, topping.getId());
    }
    @Test
    public void testUpdateTopping() throws ProductException {
        Topping topping = new Topping(13, "Stone");
        topping.setId(1);
        toppingMapper.updateProduct(topping);
    }
    @Test(expected = ProductException.class)
    public void testUpdateTopping_with_non_existing_topping() throws ProductException {
        Topping topping = new Topping(13, "Stone");
        topping.setId(50);
        toppingMapper.updateProduct(topping);
    }
    @Test(expected = IllegalArgumentException.class)
        public void testCreate_topping_with_negative_price() throws ProductException{
        Topping top = new Topping (-1,"BlueFrosting");
        toppingMapper.createProduct(top);
        }
    @Test(expected = ProductException.class)
        public void testCreate_existing_topping() throws ProductException{
        Topping top = new Topping(1,"Chocolate");
        toppingMapper.createProduct(top);
    }

    @Test (expected = ProductException.class)
    public void testUpdate_topping_with_existing_name() throws ProductException{
        Topping top = new Topping (1,"Rockwool");
        toppingMapper.createProduct(top);
        top.setName("Chocolate");
        toppingMapper.updateProduct(top);
    }

    @Test
    public void test_get_topping_from_ID() throws ProductException{
        Topping topping = new Topping(12,"Brick");
        toppingMapper.createProduct(topping);
        assertEquals(topping.getName(),toppingMapper.getProductFromID(11).getName());
        assertEquals(topping.getPrice(),toppingMapper.getProductFromID(11).getPrice());
    }
    @Test (expected = ProductException.class)
        public void test_get_topping_from_non_existing_ID() throws ProductException, SQLException{
        Topping topping = new Topping (12,"Space Cake");
        toppingMapper.createProduct(topping);
        toppingMapper.getProductFromID(12);
        }
    @Test
    public void get_all_Toppings() throws ProductException{
        ArrayList<IProduct> allToppings;
        allToppings = toppingMapper.getAllProducts();
        assertEquals(10,allToppings.size());
    }
    }
