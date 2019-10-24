package presentation;

import logic.LogicFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

/**
 *
 * @author Alexander
 */
@RunWith(MockitoJUnitRunner.class)
public class LogOutCommandTest {

    @Spy
    private LogOutCommand spyLogOutCommand;

    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;

    @Mock
    private HttpSession mockSession;

    @Mock
    private RequestDispatcher mockRequestDispatcher;
    
    @Test
    public void testLoginCommandExecute() throws Exception {
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockRequestDispatcher);
        spyLogOutCommand.execute(mockRequest, mockResponse);
        verify(spyLogOutCommand).forwardToPage(mockRequest, mockResponse, "index");
        verify(mockSession).invalidate();
    }

}
