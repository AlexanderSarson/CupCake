package presentation;

import logic.Bottom;
import logic.LogicFacade;
import logic.ShoppingCart;
import logic.Topping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author madsbrandt
*/

public class RemoveFromCartCommand extends Command {

    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        LogicFacade logicFacade = getLogicFacade();
        Topping topping = (Topping) logicFacade.parseToIProduct(request.getParameter("topping"));
        Bottom bottom = (Bottom) logicFacade.parseToIProduct(request.getParameter("bottom"));
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        logicFacade.removeFromShoppingCart(topping,bottom,cart);
        session.setAttribute("shoppingCart",cart);
        response.sendRedirect("jsp/cart/showCart.jsp");
    }

}
