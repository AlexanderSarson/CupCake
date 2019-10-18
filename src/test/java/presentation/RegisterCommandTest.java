package presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Account;
import logic.LogicFacade;
import logic.Role;
import logic.User;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 *
 * @author Alexander
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {

    @InjectMocks
    private RegisterCommand injectRegisterCommand;

    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;
    
    @Mock
    private HttpSession mockSession;
    
    @Mock
    private User mockUser;
    
    @Mock
    private LogicFacade mockLogicFacade;
    
    @Mock
    private Account mockAccount;
    

    @Test
    public void testNewUser() throws Exception{
        when(mockRequest.getParameter("name")).thenReturn("Joe");
        when(mockRequest.getParameter("email")).thenReturn("Joe@email.dk");
        when(mockRequest.getParameter("password")).thenReturn("password123");
        when(mockLogicFacade.createUser(anyString(), anyString(), Role.CUSTOMER, mockAccount)).thenReturn(mockUser);
        when(mockUser.getRole()).thenReturn(Role.CUSTOMER);
        String expected = "somepage";
        String result = injectRegisterCommand.execute(mockRequest, mockResponse);
        assertEquals(expected, result);
                     
    }

}
