package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.exceptionhandler.KenergeticBotException;
import kenergeticbot.task.Task;
import kenergeticbot.ui.TextUi;

import java.util.ArrayList;

import static kenergeticbot.exceptionhandler.KenergeticBotException.*;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the last task listing as not done.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    private final int listIndex;
    public  UnmarkCommand(int listIndex) {
        this.listIndex = listIndex;
    }
    public static void checkValid(String item) throws KenergeticBotException {
        String formattedString = item.replace("unmark", "").trim();
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException(COMMAND_TYPO_NO_NUMBER);
        }
    }
    public void execute(TaskList taskList) {
        TextUi.printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        taskList.getTask(listIndex - 1).unmark();
        System.out.printf("       %s\n", taskList.getTask(listIndex - 1));
        TextUi.printLine();
    }
}
