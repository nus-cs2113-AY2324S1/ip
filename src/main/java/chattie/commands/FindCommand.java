package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private static String command;
    private static final int FIND_LENGTH = 5;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        String item = command.substring(FIND_LENGTH);
        ArrayList<Task> filteredList = tasks.find(item);

        if (filteredList.isEmpty()) {
            throw new ChattieException(ErrorType.EMPTY_LIST);
        }

        ui.printFilteredList(filteredList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
