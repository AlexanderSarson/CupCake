package presentation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Alexander
 */
@RunWith(MockitoJUnitRunner.class)
public class RedirectCommandTest {

    @Spy
    private RedirectCommand spyRedirectCommand;

    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;

    @Test
    public void testLoginCommandExecute() throws Exception {
        when(mockRequest.getParameter("target")).thenReturn("index.jsp");
        spyRedirectCommand.execute(mockRequest, mockResponse);
        verify(mockResponse).sendRedirect("index.jsp");
    }

}
