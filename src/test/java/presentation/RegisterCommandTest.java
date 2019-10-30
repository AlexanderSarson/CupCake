package presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.LogicFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import org.mockito.Spy;

/**
 *
 * @author Alexander
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {

    @Spy
    private RegisterCommand spyRegisterCommand;

    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;

    @Mock
    private HttpSession mockSession;

    @Mock
    private RequestDispatcher mockRequestDispatcher;
    
    @Mock
    private LogicFacade mockLogicFacade;
    
    @Test
    public void testRegisterCommandExecute() throws Exception {
        when(mockRequest.getParameter("email")).thenReturn("Joe@email.dk");
        when(mockRequest.getParameter("password")).thenReturn("password123");
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(spyRegisterCommand.getLogicFacade()).thenReturn(mockLogicFacade);
        when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockRequestDispatcher);
        spyRegisterCommand.execute(mockRequest, mockResponse);
        verify(spyRegisterCommand).forwardToPage(mockRequest, mockResponse, "index.jsp");
        verify(mockLogicFacade).newUser(null, "Joe@email.dk", "password123");
        verify(mockSession).setAttribute(anyString(),any() );
    }

}
