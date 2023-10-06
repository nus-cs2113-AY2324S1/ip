package commands;

import ascii.AsciiArt;
import main.Parser;
import task.Todo;
/**
 * Represents a todo Command to add a new todo task to the task list
 */
public class TodoCommand extends Command {
    @Override
    public void execute(String statement, Parser processor) throws IllegalArgumentException {
        isValidTask(statement);
        processor.taskList.add(new Todo(statement));
        System.out.println("added: " + processor.taskList.get(processor.taskList.size() - 1).getStatus());
        System.out.println("Now you have " + processor.taskList.size() + " tasks in the list masta " + AsciiArt.getArt("uwu"));
    }
}
