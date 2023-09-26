package rene.parser;

import rene.command.*;
import rene.exception.ReneExceptions;

public class Parser {
    public Command parseCommand(String userInput) {
        String commandTitle = userInput.split(" ")[0];
        String commandDetails;
        int taskIndex;
        try {
            switch (commandTitle) {
                case "bye":
                    return new CommandExit();
                case "list":
                    return new CommandList();
                case "mark":
                    commandDetails = userInput.split(" ")[1];
                    taskIndex = Integer.parseInt(commandDetails.strip());
                    return new CommandMark(taskIndex);
                case "unmark":
                    commandDetails = userInput.split(" ")[1];
                    taskIndex = Integer.parseInt(commandDetails.strip());
                    return new CommandUnmark(taskIndex);
                case "delete":
                    commandDetails = userInput.split(" ")[1];
                    taskIndex = Integer.parseInt(commandDetails.strip());
                    return new CommandDelete(taskIndex);
                case "todo":
                    return new CommandTodo(userInput);
                case "deadline":
                    return new CommandDeadline(userInput);
                case "event":
                    return new CommandEvent(userInput);
                case "find":
                    return new CommandFind(userInput);
                default:
                    throw new ReneExceptions("Invalid Input");
            }
        }
        catch(NumberFormatException | ArrayIndexOutOfBoundsException invalidIndex){
            System.out.println("    Pwease enter valid integer index!");
        }
        catch (ReneExceptions exception){
            String exceptionMessage = exception.getMessage();
            if (exceptionMessage.equals("Invalid Input")) {
                System.out.println("    Pwease enter a valid command :0");
                System.out.println("    Valid commands are: todo,\n" +
                        "                        deadline /by [time],\n" +
                        "                        event /from [start] /to [end],\n" +
                        "                        list,\n" +
                        "                        mark [task number],\n" +
                        "                        unmark [task number],\n" +
                        "                        delete [task number],\n" +
                        "                        find /description [description]\n" +
                        "                        find /time [time]\n" +
                        "                        bye");
            }
        }
        return new Command();
    }

}
