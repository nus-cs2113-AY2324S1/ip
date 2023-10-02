package jarvis.parser;

import jarvis.command.*;
import jarvis.exception.JarvisException;
/**
 * Represents a parser that converts user inputs into command objects.
 */
public class Parser {
    public Command parseCommand(String userInput){
        String commandTitle = userInput.split(" ")[0];
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
            default:
                throw JarvisException.invalidCommand();
            }
        }catch(NumberFormatException | ArrayIndexOutOfBoundsException invalidIndex){
            System.out.println("Please enter valid integer index!");
        }catch(JarvisException e){
            System.out.println(e.getMessage());
            // TODO: send the help /help
        }

        return new Command(null);
    }
}
