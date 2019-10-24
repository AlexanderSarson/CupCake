package presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class UnknownCommandTest {

    @Spy
    private UnknownCommand spyUnknownCommand;

    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;

    @Mock
    private RequestDispatcher mockRequestDispatcher;
    
    @Test
    public void testUnknownCommandExecute() throws Exception {
        when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockRequestDispatcher);
        spyUnknownCommand.execute(mockRequest, mockResponse);
        verify(spyUnknownCommand).forwardToPage(mockRequest, mockResponse, "index");
    }

}
