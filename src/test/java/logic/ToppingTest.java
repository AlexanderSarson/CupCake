package logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Oscar
 * version 1.0
 */

public class ToppingTest {

    Topping topping;

    @Before
    public void setup() {

       topping = new Topping ( 5, "Chocolate");
    }
    @Test
    public void testGetName(){
        String expected = "Chocolate";
        String result = topping.getName();
        assertEquals(expected, result);
    }
    @Test
    public void testSetName(){
        String expected = "Rasberry";
        topping.setName("Rasberry");
        String result = topping.getName();
        assertEquals(expected,result);

    }
    @Test
    public void testGetPrice(){
        long expected = 5;
        long result = topping.getPrice();
        assertEquals(expected,result);
    }
    @Test
    public void testSetPrice(){
        int expected = 10;
        topping.setPrice(10);
        long result = topping.getPrice();
        assertEquals(expected,result);
    }
}