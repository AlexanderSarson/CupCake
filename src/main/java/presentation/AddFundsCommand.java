package presentation;

import logic.LogicFacade;
import logic.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddFundsCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int value = Integer.parseInt(request.getParameter("value"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        LogicFacade logicFacade = getLogicFacade();
        logicFacade.addFunds(user,value);
        String page = "jsp/user/showUser.jsp";
        forwardToPage(request,response,page);
    }
}
