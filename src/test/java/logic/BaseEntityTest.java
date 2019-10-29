package logic;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Oscar
 * version 1.0
 */


public class BaseEntityTest {

    BaseEntity base;

    @Before
    public void setup(){
        base = new Topping(1,null);
    }

    @Test
    public void test_GetId() {
        long expected = 1;
        base.setId(1);
        long result = base.getId();
        assertEquals(expected, result);


    }

    @Test
    public void test_SetId() {
        long expected = 2;
        base.setId(2);
        long result = base.getId();
        assertEquals(expected,result);
    }
}