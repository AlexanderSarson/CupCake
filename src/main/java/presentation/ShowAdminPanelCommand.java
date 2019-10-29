package presentation;
/*
author madsbrandt
*/

import logic.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAdminPanelCommand extends Command {
    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
        LogicFacade logicFacade = new LogicFacade();
        // List<Order> orders = logicFacade.getAllOrders();
        // List<User> users = logicFacade.getAllUsers();
        // request.getSession().setAttribute("orders", orders);
        // request.getSession().setAttribute("users", users);
        forwardToPage(request, response, "/jsp/admin/adminPanel.jsp");

    }
}
