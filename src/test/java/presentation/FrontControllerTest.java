/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author Alexander
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class FrontControllerTest {
    
    @InjectMocks
    private FrontController injectFrontController;
    
    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;
    
    @Mock
    private Command mockCommand;
    
    @Mock
    private RequestDispatcher mockRequestDIspatcher;
      

    /**
     * Test of processRequest method, of class FrontController.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        FrontController instance = new FrontController();
        instance.processRequest(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommand method, of class FrontController.
     */
    @Test
    public void testGetCommand() {
        System.out.println("getCommand");
        FrontController instance = new FrontController();
        Command expResult = null;
        Command result = instance.getCommand();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }





    
}
