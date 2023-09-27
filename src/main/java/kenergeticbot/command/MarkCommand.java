package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.exceptionhandler.KenergeticBotException;
import kenergeticbot.ui.TextUi;

import static kenergeticbot.exceptionhandler.KenergeticBotException.*;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the last task listing as done.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    private final int listIndex;
    public  MarkCommand(int listIndex) {
        this.listIndex = listIndex;
    }
    public static void checkValid(String item) throws KenergeticBotException {
        String formattedString = item.replace("mark", "").trim();
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException(COMMAND_TYPO_NO_NUMBER);
        }
    }
    public void execute(TaskList taskList){
        TextUi.printLine();
        System.out.println("     Nice! I've marked this task as done:");
        taskList.getTask(listIndex - 1).mark();
        System.out.printf("       %s\n", taskList.getTask(listIndex - 1));
        TextUi.printLine();
    }
}
