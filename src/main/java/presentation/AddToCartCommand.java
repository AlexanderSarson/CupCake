/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import logic.Bottom;
import logic.LogicFacade;
import logic.ShoppingCart;
import logic.Topping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander
 */
public class AddToCartCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LogicFacade logicFacade = getLogicFacade();
        Topping topping;
        Bottom bottom;
        String toppingString = request.getParameter("topping");
        String bottomString = request.getParameter("bottom");
        if(toppingString != null && bottomString != null) {
            topping = (Topping) logicFacade.parseToIProduct(toppingString);
            bottom = (Bottom) logicFacade.parseToIProduct(bottomString);
        } else {
            // Get the Custom cupcake instead
            topping = (Topping) logicFacade.parseToIProduct(request.getParameter("customTopping"));
            bottom = (Bottom) logicFacade.parseToIProduct(request.getParameter("customBottom"));
        }

        HttpSession session = request.getSession();

        ShoppingCart cart;
        if (session.getAttribute("shoppingCart") == null) {
            cart = logicFacade.getShoppingCart();
        } else {
            cart = (ShoppingCart) session.getAttribute("shoppingCart");
        }

        logicFacade.addToShoppingCart(bottom, topping, cart);
        session.setAttribute("shoppingCart", cart);
        response.sendRedirect("jsp/shop/mainShop.jsp");
    }
}
