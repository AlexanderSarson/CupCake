package presentation;

import logic.Bottom;
import logic.LogicFacade;
import logic.ShoppingCart;
import logic.Topping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author madsbrandt
*/
@RunWith(MockitoJUnitRunner.class)
public class RemoveFromCartTest {

    @Spy
    private RemoveFromCartCommand spyRemoveFromCartCommand;

    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;

    @Mock
    private RequestDispatcher mockRequestDispatcher;

    @Mock
    private LogicFacade mockLogicFacade;

    @Mock
    private ShoppingCart mockShoppingCart;

    @Mock
    private Topping mockTopping;

    @Mock
    private Bottom mockBottom;

    @Test
    public void testRemoveFromCartCommandExecute() throws Exception {
        when(mockRequest.getAttribute("topping")).thenReturn(mockTopping);
        when(mockRequest.getAttribute("bottom")).thenReturn(mockBottom);
        when(mockRequest.getAttribute("shoppingcart")).thenReturn(mockShoppingCart);
        when(mockRequest.getContextPath()).thenReturn("showCart.jsp");
        when(spyRemoveFromCartCommand.getLogicFacade()).thenReturn(mockLogicFacade);
        when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockRequestDispatcher);
        spyRemoveFromCartCommand.execute(mockRequest, mockResponse);
        verify(spyRemoveFromCartCommand).forwardToPage(mockRequest, mockResponse, "showCart");
        verify(mockLogicFacade).removeFromShoppingcart(mockTopping, mockBottom, mockShoppingCart);

    }
}
