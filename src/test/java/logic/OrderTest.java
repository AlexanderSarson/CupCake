package logic;

import org.junit.Before;
import org.junit.Test;
import persistence.OrderException;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    private Order order;
    private LineItem item;

    @Before
    public void setUp() {
        order = new Order(LocalDate.of(2000,2,1));
        item = new LineItem(new Cupcake(
                        new Bottom(20,"Blueberry"),
                        new Topping(20,"Chocolate")), 2);
        order.addLineItem(item);
    }

    @Test
    public void test_addLineItem() {
        int size = order.getSize();
        order.addLineItem(new LineItem(new Cupcake(null,null),2));
        assertEquals(size+1, order.getSize());
    }

    @Test
    public void test_removeLineItem() {
        int size = order.getSize();
        order.removeLineItem(item);
        assertEquals(size-1, order.getSize());
    }

    @Test
    public void test_RemoveLineItem_by_index() throws OrderException {
        int size = order.getSize();
        order.removeLineItem(0);
        assertEquals(size-1, order.getSize());
    }

    @Test
    public void test_RemoveLineItem_non_existing_item() {
        int size = order.getSize();
        order.removeLineItem(new LineItem(null,2));
        assertEquals(size,order.getSize());
    }

    @Test(expected = OrderException.class)
    public void test_RemoveLineItem_non_existing_index() throws OrderException {
        int size = order.getSize();
        order.removeLineItem(2000);
    }

    @Test
    public void test_getLineItem() throws OrderException {
        assertEquals(item, order.getLineItem(0));
    }

    @Test
    public void test_getOrderPrice() {
        order.addLineItem(item);
        assertEquals(160,order.getOrderPrice());
    }

    @Test
    public void test_addCupcakeToOrder_adding_same_cupcake_two_times() {
        Bottom bottom = new Bottom(5,"WhiteChocolate");
        Topping topping = new Topping(5, "Nutmeg");
        Cupcake cupcake = new Cupcake(bottom,topping);
        ShoppingCart cart = new ShoppingCart();
        cart.addCupcakeToOrder(cupcake);
        assertEquals(1, cart.getTotalQuantity());
        cart.addCupcakeToOrder(cupcake);
        assertEquals(2,cart.getTotalQuantity());
    }

    @Test
    public void test_addCupcakeToOrder_adding_differet_cupcakes() {
        Bottom bottom = new Bottom(5,"WhiteChocolate");
        Topping topping = new Topping(5, "Nutmeg");
        Cupcake cupcake = new Cupcake(bottom,topping);
        Bottom anotherBottom = new Bottom(5,"WhiteChocolate");
        Topping anotherTopping = new Topping(5, "Nutmeg");
        Cupcake anotherCupcake = new Cupcake(anotherBottom,anotherTopping);
        ShoppingCart cart = new ShoppingCart();
        cart.addCupcakeToOrder(cupcake);
        assertEquals(1, cart.getTotalQuantity());
        cart.addCupcakeToOrder(anotherCupcake);
        assertEquals(2,cart.getTotalQuantity());
    }

    @Test
    public void test_getTotalQuantity() {
        int exp = item.getQuantity()*2;
        order.addLineItem(item);
        int res = order.getTotalQuantity();
        assertEquals(exp,res);
    }

    @Test
    public void test_getSize() {
        assertEquals(1,order.getSize());
    }
}