/**
 * The TaskList class handles the instantaneous
 */
package Chatty;
import Chatty.tasks.*;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * initializes the task list
     * @param tasks list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * adds a task to the list
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * gets size of task list
     * @return size of task list
     */
    public int size(){
        return tasks.size();
    }

    /**
     * get a specific task
     * @param index index of task to get
     * @return specific task
     */
    public Task get(int index){
        return tasks.get(index);
    }

    /**
     * deletes a task
     * @param index index of task to delete
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * mark task as done / not done
     * @param index index of task to be mark done / not done
     * @param isDone whether task is to be marked done / not done
     */
    public void markTask(int index, boolean isDone){
        if (isDone) {
            tasks.get(index).mark();
        } else {
            tasks.get(index).unmark();
        }
    }
}
