package duke;

import java.util.ArrayList;

public class FindTaskCommand implements Command {
    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

