/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Bottom;
import logic.LogicFacade;
import logic.Order;
import logic.ShoppingCart;
import logic.Topping;
import logic.User;

/**
 *
 * @author Alexander
 */
public class SubmitOrderCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ShoppingCart cart = (ShoppingCart) request.getAttribute("shoppingcart");
        User user = (User) request.getAttribute("user");
        LogicFacade logicFacade = getLogicFacade();
        Order order = logicFacade.submitOrder(user, cart);
        HttpSession session = request.getSession();
        session.setAttribute("order", order);
        String page = "invoice";
        forwardToPage(request, response, page);
    }
}
