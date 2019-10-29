package presentation;
/*
author madsbrandt
*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitJSONOrderCommand extends Command {

    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().print("Hello!");
    }
}
