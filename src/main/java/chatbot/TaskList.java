package chatbot;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>(100);
    /**
     * Get the list of tasks
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
    /**
     * Add a task to the list of tasks
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
