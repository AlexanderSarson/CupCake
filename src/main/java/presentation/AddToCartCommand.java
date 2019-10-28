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
        Topping top = (Topping) logicFacade.parseToIProduct(request.getParameter("topping"));
        Bottom bot = (Bottom) logicFacade.parseToIProduct(request.getParameter("bottom"));
        HttpSession session = request.getSession();

        ShoppingCart cart;
        if (session.getAttribute("shoppingCart") == null) {
            cart = logicFacade.getShoppingCart();
        } else {
            cart = (ShoppingCart) session.getAttribute("shoppingCart");
        }

        logicFacade.addToShoppingCart(bot, top, cart);
        session.setAttribute("shoppingCart", cart);
        response.sendRedirect("jsp/shop/mainShop.jsp");
//        String page = request.getContextPath().replaceAll(".jsp", "");
//        forwardToPage(request, response, page);
    }
}
