package Commands;

import Exceptions.KenException;
import Tasks.Task;
import Tasks.TaskList;

import java.util.ArrayList;

public class Find extends Command {
    private final String keyword;

    public Find(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void run(TaskList list) throws KenException {
        list.getTasks(keyword);
    }
}
