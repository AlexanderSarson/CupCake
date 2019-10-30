package presentation;

import logic.LogicFacade;
import logic.Order;
import logic.User;
import persistence.OrderException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShowUserCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null) {
            LogicFacade logicFacade = getLogicFacade();
            List<Order> userOrders = new ArrayList<>();
            try {
                userOrders = logicFacade.getOrdersForUser(user);
            } catch (OrderException e) {
            }
            session.setAttribute("userOrders", userOrders);
            String page = "jsp/user/showUser.jsp";
            forwardToPage(request,response,page);
        }
    }
}
