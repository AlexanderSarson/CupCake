package presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author Alexander
 */

@RunWith(MockitoJUnitRunner.class)
public class FrontControllerTest {
    
    @Spy
    private FrontController spyFrontController;
    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;
    
    @Mock
   private Command mockCommand;
    
    @Mock
    private RequestDispatcher mockRequestDispatcher;
      

    /**
     * Test of processRequest method, of class FrontController.
     */
    @Test
    public void testProcessRequest() throws Exception {
        when(spyFrontController.getCommand()).thenReturn(mockCommand);
        when(mockCommand.from(mockRequest)).thenReturn(mockCommand);
        spyFrontController.processRequest(mockRequest,mockResponse);
        verify(mockCommand).execute(mockRequest,mockResponse);
    }
}
