package presentation;

import logic.LogicFacade;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * The purpose of Command is to carry out an action It keeps a list of html
 * actions mapped to related commands
 *
 * Pattern: Command
 *
 * @author Alexander
 */
public  class Command {

    private HashMap<String, Command> commands;

    private void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("addToCart", new AddToCartCommand());
        commands.put("submitOrder", new SubmitOrderCommand());
        commands.put("redirect", new RedirectCommand());
        commands.put("removeFromCart", new RemoveFromCartCommand());
        commands.put("showAdminPanel", new ShowAdminPanelCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("showUser", new ShowUserCommand());
    }

    public Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception { //TODO make custom exception
    }

    public void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(page + ".jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LogicFacade getLogicFacade() {
        return new LogicFacade();
    }
}
