package Commands;

import Ken.TaskList;

public class List extends Command {

    @Override
    public void run(TaskList list) {
        list.getTasks();
    }
}
