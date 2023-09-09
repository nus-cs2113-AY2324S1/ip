package Commands;

import CSGPT.TaskList;
public class List extends Command{

    @Override
    public void execute(TaskList list) {
        list.getTasks();
    }
}
