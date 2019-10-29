package presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.LogicFacade;
import logic.User;

/**
 *
 * @author Alexander
 */
public class RegisterCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LogicFacade logicFacade = getLogicFacade();
        User user = logicFacade.newUser(name, email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        String page = "index";
        forwardToPage(request, response, page);
    }
}
