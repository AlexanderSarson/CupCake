package presentation;

import logic.LogicFacade;
import logic.User;
import persistence.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LogicFacade logicFacade = getLogicFacade();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = (String) request.getParameter("userName");
        String mail = (String) request.getParameter("mail");
        String oldPassword = (String) request.getParameter("oldPassword");
        String newPassword = (String) request.getParameter("newPassword");
        User newUserInfo = new User(username,mail,user.getRole(),user.getAccount());
        newUserInfo.setID(user.getId());
        if(logicFacade.validatePassword(user,oldPassword)) {
            logicFacade.updateUser(newUserInfo,newPassword);
            session.setAttribute("user",newUserInfo);
            forwardToPage(request,response,"jsp/user/showUser.jsp");
        }
        else {
            throw new UserException("Password does not match with current");
        }
    }
}
