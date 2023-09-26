package main;

import ascii.AsciiArt;
import commands.DeadlineCommand;
import commands.UnmarkCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.EventCommand;
import commands.Command;
import commands.ListCommand;
import commands.AsciiCommand;
import commands.DeleteCommand;
import commands.FindCommand;

import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * The main processor of the code to check which command to initiate
 */
public class ResponseProcessor {
    public List<Task> taskList = new ArrayList<>();
    public HashMap<String, Command> commandProcessor = new HashMap<>() {{
        put("mark", new MarkCommand());
        put("unmark", new UnmarkCommand());
        put("deadline", new DeadlineCommand());
        put("event", new EventCommand());
        put("todo", new TodoCommand());
        put("list", new ListCommand());
        put("ascii", new AsciiCommand());
        put("delete", new DeleteCommand());
        put("find", new FindCommand());
    }};
    /**
     * Check against the hashmap of commands to execute
     *
     * @param response The input of the user
     */
    public void process(String response) {
        String command = response.split(" ")[0];
        if (commandProcessor.containsKey(command)) {
            String statement = removeFirstWord(response);
            try {
                commandProcessor.get(command).execute(statement, this);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("I don't understand masta! Type a command uwu! " + AsciiArt.getArt("sad"));
        }
    }
    /**
     * Remove the command word from the user input
     *
     * @param response The user input
     */
    public static String removeFirstWord(String response) {
        int index = response.indexOf(" ");
        if (index == -1) {
            return "";
        }
        return response.substring(index + 1);
    }
}