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

/**
 *
 * @author Alexander
 */
public class AddToCartCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Topping top = (Topping) request.getAttribute("topping");
        Bottom bot = (Bottom) request.getAttribute("bottom");
        ShoppingCart cart = (ShoppingCart) request.getAttribute("shoppingcart");
        LogicFacade logicFacade = getLogicFacade();
        logicFacade.addToShoppingCart(bot, top, cart);
        String page = request.getContextPath().replaceAll(".jsp", "");
        forwardToPage(request, response, page);
    }
}
