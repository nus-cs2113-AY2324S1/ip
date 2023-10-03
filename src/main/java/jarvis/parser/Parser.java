package jarvis.parser;

import jarvis.command.Command;
import jarvis.command.CommandList;
import jarvis.command.CommandDelete;
import jarvis.command.CommandMark;
import jarvis.command.CommandUnmark;
import jarvis.command.CommandToDo;
import jarvis.command.CommandEvent;
import jarvis.command.CommandDeadline;
import jarvis.command.CommandExit;
import jarvis.exception.JarvisException;
/**
 * Represents a parser that converts user inputs into command objects.
 */
public class Parser {
    public Command parseCommand(String userInput){
        String commandTitle = userInput.split(" ")[0];
        String commandDescription;
        int taskIndex;
        try{
            switch(commandTitle){
            case "bye":
                return new CommandExit();
            case "list":
                return new CommandList();
            case "deadline":
                return new CommandDeadline(userInput);
            case "event":
                return new CommandEvent(userInput);
            case "todo":
                return new CommandToDo(userInput);
            case "mark":
                commandDescription = userInput.split(" ")[1];
                taskIndex = Integer.parseInt(commandDescription.strip());
                return new CommandMark(taskIndex);
            case "unmark":
                commandDescription = userInput.split(" ")[1];
                taskIndex = Integer.parseInt(commandDescription.strip());
                return new CommandUnmark(taskIndex);
            case "delete":
                commandDescription = userInput.split(" ")[1];
                taskIndex = Integer.parseInt(commandDescription.strip());
                return new CommandDelete(taskIndex);
            default:
                throw JarvisException.invalidCommand();
            }
        }catch(JarvisException e){
            System.out.println(e.getMessage());
            // TODO: send the help /help
        }

        return new Command(null);
    }
}
