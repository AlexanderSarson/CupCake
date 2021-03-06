/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import logic.LogicFacade;
import logic.Order;
import logic.ShoppingCart;
import logic.User;
import persistence.OrderException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 *x
 * @author Alexander
 */
public class SubmitOrderCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        User user = (User) session.getAttribute("user");
        cart.setDate(LocalDate.now());
        LogicFacade logicFacade = getLogicFacade();
        String page;

        if (user != null) {
            try {
                Order order = logicFacade.submitOrder(user, cart);
                session.setAttribute("order", order);
                page = "jsp/cart/invoice.jsp";
                session.setAttribute("shoppingCart", null);
            } catch (OrderException e) {
                page = "jsp/shop/mainshop.jsp";
            }
        } else {
            page = "jsp/user/login.jsp";
        }

        forwardToPage(request, response, page);
    }
}
