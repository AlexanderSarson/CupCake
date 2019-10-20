/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class LoginCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LogicFacade logicFacade = getLogicFacade();
        User user = logicFacade.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        String page = "index";
        forwardToPage(request, response, page);
    }
}
