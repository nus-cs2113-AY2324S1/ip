package Commands;

import Tasks.TaskList;

public class List extends Command {

    @Override
    public void run(TaskList list) {
        list.getTasks();
    }
}
