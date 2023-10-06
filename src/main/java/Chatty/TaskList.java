/**
 * The TaskList class handles the instantaneous
 */
package Chatty;
import Chatty.tasks.*;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int size(){
        return tasks.size();
    }

    public Task get(int i){
        return tasks.get(i);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTask(int index, boolean isDone){
        if (isDone) {
            tasks.get(index).mark();
        } else {
            tasks.get(index).unmark();
        }
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
