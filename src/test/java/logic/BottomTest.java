package logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Oscar
 * version 1.0
 */

public class BottomTest {

    Bottom bottom;
    @Before
    public void setup(){
        bottom  = new Bottom ( 10,"Strawberry");
    }

    @Test
    public void testGetPrice() {
        int expected = 10;
        int result = bottom.getPrice();
        assertEquals(expected,result);

    }

    @Test
    public void testSetPrice() {
        int expected = 5;
        bottom.setPrice(5);
        int result = bottom.getPrice();
    }

    @Test
    public void testGetName() {
        String expected = "Strawberry";
        String result = bottom.getName();
        assertEquals(expected,result);
    }

    @Test
    public void testSetName() {
        String expected = "Coconut";
        bottom.setName("Coconut");
        String result = bottom.getName();
        assertEquals(expected,result);

    }
}