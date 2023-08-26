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

    @Override
    public String toString() {
        if (size == 0) {
            return "\t Empty list!";
        }

        String result = "";
        for (int i = 0; i < size; i++) {
            String task = taskList[i].getTask();
            String line = String.format("\t %d. %s", i + 1, task);
            result += line + System.lineSeparator();
        }
        return result;
    }
}
