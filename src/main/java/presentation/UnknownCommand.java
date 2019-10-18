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
public class UnknownCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "index";
        forwardToPage(request, response, page);
    }
}
