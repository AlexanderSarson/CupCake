package presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander
 */
public class UnknownCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "index.jsp";
        forwardToPage(request, response, page);
    }
}
