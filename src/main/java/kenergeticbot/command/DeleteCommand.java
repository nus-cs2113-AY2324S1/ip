package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.exceptionhandler.KenergeticBotException;
import kenergeticbot.task.Task;
import kenergeticbot.ui.TextUi;

import java.util.ArrayList;

import static kenergeticbot.exceptionhandler.KenergeticBotException.*;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    private final int listIndex;
    public DeleteCommand(int listIndex) {
        this.listIndex = listIndex;
    }
    public static void checkValid (String item) throws KenergeticBotException {
        String formattedString = item.replace("delete", "").trim();
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException(COMMAND_TYPO_NO_NUMBER);
        }
    }
    public void execute(TaskList taskList) {
        TextUi.printLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + taskList.getTask(listIndex - 1));
        taskList.remove(listIndex - 1);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.getSize());
        TextUi.printLine();
    }

}
