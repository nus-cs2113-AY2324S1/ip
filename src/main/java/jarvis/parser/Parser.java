package jarvis.parser;

import jarvis.command.Command;
import jarvis.command.CommandList;
import jarvis.command.CommandDelete;
import jarvis.command.CommandFind;
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
    /**
     * Converts the raw user input string into a corresponding command object.
     *
     * Takes the user input, identifies the intended command, and returns an object of a class
     * that extends 'Command'. In case of invalid or improperly formatted input, an exception is
     * thrown, providing user feedback regarding the incorrect input.
     *
     * @param userInput Raw input string provided by the user.
     * @return Corresponding command object based on user input.
     */
    public Command parseCommand(String userInput) throws JarvisException {
        String commandTitle = userInput.split(" ")[0];
        String commandDescription;
        int taskIndex;
        try{
            switch(commandTitle){
            // Use the extracted command title to determine and return the appropriate Command object.
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
            case "find":
                return new CommandFind(userInput);
            default:
                // If the command title is not recognized, throw an exception.
                throw JarvisException.invalidCommand();
            }
        }catch(JarvisException e){
            // Handle the caught JarvisException by outputting its message to the user.
            System.out.println(e.getMessage());
        }catch (NumberFormatException nfe) {
            throw new JarvisException("Please provide a valid integer for the task number, sir!");
        }
        // Return a default command (with null type) if command parsing fails.
        return new Command(null);
    }
}
