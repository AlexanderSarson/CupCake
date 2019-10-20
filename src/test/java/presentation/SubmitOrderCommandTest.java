package presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.LogicFacade;
import logic.Order;
import logic.ShoppingCart;
import logic.User;
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
public class SubmitOrderCommandTest {

    @Spy
    private SubmitOrderCommand spySubmitOrderCommand;

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
    
    @Mock
    private ShoppingCart mockShoppingCart;
    
    @Mock
    private Order mockOrder;
    
    @Mock
    private User mockUser;
    
    @Test
    public void testSubmitOrderCommandExecute() throws Exception {
        when(mockRequest.getAttribute("shoppingcart")).thenReturn(mockShoppingCart);
        when(mockRequest.getAttribute("user")).thenReturn(mockUser);
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(spySubmitOrderCommand.getLogicFacade()).thenReturn(mockLogicFacade);
        when(mockLogicFacade.submitOrder(mockUser, mockShoppingCart)).thenReturn(mockOrder);
        when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockRequestDispatcher);
        spySubmitOrderCommand.execute(mockRequest, mockResponse);
        verify(spySubmitOrderCommand).forwardToPage(mockRequest, mockResponse, "invoice");
        verify(mockLogicFacade).submitOrder(mockUser,mockShoppingCart);
        verify(mockSession).setAttribute(anyString(),any() );
    }

}
