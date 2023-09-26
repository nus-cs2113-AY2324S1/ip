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
    public static String removeFirstWord(String str) {
        int index = str.indexOf(" ");
        if (index == -1) {
            return "";
        }
        return str.substring(index + 1);
    }
}