package Data;

import Data.Task;
import Exceptions.CSGPTMissingTaskException;
import java.util.ArrayList;
import Ui.TextUi;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int taskNumber) throws CSGPTMissingTaskException {
        if (taskNumber > list.size() || taskNumber < 1)
            throw new CSGPTMissingTaskException();
        list.remove(taskNumber - 1);
    }

    public Task getTask(int taskNumber) {
        return list.get(taskNumber - 1);
    }

    public void mark(int taskNumber, boolean isDone) throws CSGPTMissingTaskException {
        if (taskNumber > list.size() || taskNumber < 1)
            throw new CSGPTMissingTaskException();
        list.get(taskNumber - 1).setDone(isDone);
    }

    public int size() {
        return list.size();
    }

    public void getTasks(TextUi ui) {
        if (list.isEmpty()) {
            ui.printText("You have no tasks at hand, mortal.");
            return;
        }
        String[] text = new String[list.size() + 1];
        text[0] = "These are the chores you have at hand, mortal:";
        for (int i = 0; i < list.size(); i++) {
            text[i+1] = ((i + 1) + ". " + list.get(i).toString());
        }
        ui.printMultipleText(text);
    }
}
