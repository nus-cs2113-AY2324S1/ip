package chatbot;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>(100);

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
