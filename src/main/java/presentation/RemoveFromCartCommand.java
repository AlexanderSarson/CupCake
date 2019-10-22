package presentation;

import logic.Bottom;
import logic.LogicFacade;
import logic.ShoppingCart;
import logic.Topping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author madsbrandt
*/

public class RemoveFromCartCommand extends Command {

    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
        Topping topping = (Topping) request.getAttribute("topping");
        Bottom bottom = (Bottom) request.getAttribute("bottom");
        ShoppingCart cart = (ShoppingCart) request.getAttribute("shoppingcart");
        LogicFacade logicFacade = getLogicFacade();
        logicFacade.removeFromShoppingcart(topping, bottom, cart);
        String page = request.getContextPath().replaceAll(".jsp", "");
        forwardToPage(request, response, page);
    }

}
