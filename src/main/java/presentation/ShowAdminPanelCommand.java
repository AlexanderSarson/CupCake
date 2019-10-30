package presentation;
/*
author madsbrandt
*/

import logic.LogicFacade;
import logic.Order;
import logic.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAdminPanelCommand extends Command {
    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
        LogicFacade logicFacade = new LogicFacade();
        List<Order> orders = logicFacade.getAllOrders();
        List<User> users = logicFacade.getAllUsers();
        request.getSession().setAttribute("allOrders", orders);
        request.getSession().setAttribute("allUsers", users);
        forwardToPage(request, response, "jsp/admin/adminPanel.jsp");
    }
}