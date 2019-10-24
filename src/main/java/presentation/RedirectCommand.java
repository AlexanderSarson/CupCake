package presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getParameter("target").replaceAll(".jsp","");
        forwardToPage(request,response,path);
    }
}
