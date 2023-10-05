package Commands;

import java.io.IOException;
import Task.TaskList;

public class FindCommand extends Command{
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute() throws IOException {
        TaskList.findTasks(keyword);
    }
}
