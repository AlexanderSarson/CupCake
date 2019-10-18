package logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineItemTest {



    Bottom bottom;
    Topping topping;
    Cupcake cupcake;
    LineItem lineitem;

    @Before
    public void setup(){
        bottom = new Bottom (10,"Chocolate");
        topping = new Topping (5, "Coconut");
        cupcake = new Cupcake (1,bottom,topping);
        lineitem = new LineItem (cupcake, 10);
    }

    @Test
    public void testCalculateTotalPrice() {
        int expected = 150;
        int result = lineitem.calculateTotalPrice();
        assertEquals(expected,result);
        cupcake.getBottom().setPrice(8);
        cupcake.getTopping().setPrice(5);
        lineitem.setQuantity(5);
        int expected1 = 65;
        int result1 = lineitem.calculateTotalPrice();
        assertEquals(expected1,result1);
    }

    @Test
    public void testGetQuantity() {
        int expected = 10;
        int result = lineitem.getQuantity();
        assertEquals(expected,result);
    }

    @Test
    public void testSetQuantity() {
        int expected = 5;
        lineitem.setQuantity(5);
        int result = lineitem.getQuantity();
        assertEquals(expected,result);
    }

    @Test
    public void testGetCupcake() {
        Cupcake testCupcake = cupcake;
        assertEquals(lineitem.getCupcake(),testCupcake);
    }

    @Test
    public void testSetCupcake() {
        Bottom testBottom = new Bottom (5,"Raisin");
        Topping testTopping = new Topping (4,"Strawberry");
        Cupcake testCupcake = new Cupcake(1,testBottom,testTopping);
        assertNotEquals(lineitem.getCupcake(),testCupcake);
        lineitem.setCupcake(testCupcake);
        assertEquals(testCupcake, lineitem.getCupcake());
    }
}