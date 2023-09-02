package Commands;

import CSGPT.TaskList;
public class List extends Command{

    TaskList list;

    public List(TaskList list) {
        this.list = list;
    }

    @Override
    public void execute() {
        list.getTasks();
    }
}
