package presentation;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.LogicFacade;

/**
 * The purpose of Command is to carry out an action 
 * It keeps a list of html actions mapped to related commands
 *
 * Pattern: Command
 *
 * @author Alexander
 */
public  class Command {

    private  HashMap<String, Command> commands;

    private  void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put( "register", new RegisterCommand() );
    }

    public Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{ //TODO make custom exception
        return "";
    }
    
    public LogicFacade getLogicFacade(){
        return new LogicFacade();
    }
}