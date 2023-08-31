package dawson;

public class TaskList {
    private Task[] taskList;
    private int size;

    public TaskList() {
        this.taskList = new Task[100];
        this.size = 0;
    }

    public void add(Task task) {
        taskList[size] = task;
        size++;
    }

    public boolean isIndexValid(int index) {
        return index >= 0 && index < size;
    }

    public String markAsDoneIndex(int index) {
        if (!isIndexValid(index)) return "";
        taskList[index].markAsDone();
        return taskList[index].toString();
    }

    public String unmarkIndex(int index) {
        if (!isIndexValid(index)) return "";
        taskList[index].unmark();
        return taskList[index].toString();
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "\t Empty list!";
        }

        String result = "\t Here are the tasks in your list: " + System.lineSeparator();
        for (int i = 0; i < size; i++) {
            String task = taskList[i].toString();
            String line = String.format("\t %d. %s", i + 1, task);
            result += line + System.lineSeparator();
        }
        return result;
    }
}
