package Commands;

import CSGPT.CSGPT;
import CSGPT.Task;
import CSGPT.TaskList;

public class Add extends Command{
    private final Task task;
    private final TaskList list;

    public Add(Task task, TaskList list) {
        this.task = task;
        this.list = list;
    }

    @Override
    public void execute() {
        list.add(task);
        String[] text = {"Added: " + task.getDescription(), "Now you have " + list.size() + " tasks in the list."};
        CSGPT.printMultipleText(text);
    }
}
