package logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CupcakeTest {
    Bottom bottom;
    Topping topping;
    Cupcake cupcake;

    @Before
    public void setup(){
        bottom = new Bottom (10,"Chocolate");
        topping = new Topping (5, "Coconut");
        cupcake = new Cupcake (1,bottom,topping);
    }

    @Test
    public void testGetBottom() {
        assertEquals(bottom,cupcake.getBottom());
    }

    @Test
    public void testGetTopping() {
        assertEquals(topping,cupcake.getTopping());
    }

    @Test
    public void testSetBottom() {
        Bottom newBottom = new Bottom (12,"Strawberry");
        cupcake.setBottom(newBottom);
        assertNotEquals(bottom,cupcake.getBottom());
        assertEquals(newBottom,cupcake.getBottom());
    }

    @Test
    public void testSetTopping() {
        Topping newTopping = new Topping (7,"Rasberry");
        cupcake.setTopping(newTopping);
        assertNotEquals(topping,cupcake.getTopping());
        assertEquals(newTopping,cupcake.getTopping());
    }
}