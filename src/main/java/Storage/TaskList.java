package Storage;

import Soccat.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a taskList object consisting of tasks.
 * A taskList object contains various methods to add,
 * remove, search, edit and list tasks.
 * */

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task, Storage taskFile) throws IOException {
        tasks.add(task);
        taskFile.setTaskData(tasks);
    }

    public int getTaskListLength() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Get a task based on the index
     *
     * @param taskIndex 0-based index of task in the arraylist
     * @return Task object pointed to by the taskIndex
     * @throws IndexOutOfBoundsException if taskIndex is out of range
     * */
    public Task getTask(int taskIndex) throws IndexOutOfBoundsException {
        return tasks.get(taskIndex);
    }

    /**
     * Remove a task based on the index
     *
     * @param taskIndex 0-based index of task in the arraylist
     * @return Task object pointed to by the taskIndex
     * @throws IndexOutOfBoundsException if taskIndex is out of range
     * @throws IOException if the attempt to update task file failed
     * */
    public Task removeTask(int taskIndex, Storage taskFile) throws IOException, IndexOutOfBoundsException {
        Task task = tasks.remove(taskIndex);
        taskFile.setTaskData(tasks);
        return task;
    }

    /**
     * Mark a task to done or undone based on the index
     *
     * @param taskIndex 0-based index of task in the arraylist
     * @param isDone the boolean value of done to be updated
     * @return Task object pointed to by the taskIndex
     * @throws IndexOutOfBoundsException if taskIndex is out of range
     * @throws IOException if the attempt to update task file failed
     * */
    public Task markTask(int taskIndex, boolean isDone, TaskList tasks, Storage taskFile)
            throws IOException, IndexOutOfBoundsException{
        Task task = tasks.getTask(taskIndex);
        if (task.getDone() == isDone) {
            return task;
        }
        task.setDone(isDone);
        taskFile.setTaskData(tasks.getTasks());
        return task;
    }

    /**
     * Search a task based on keywords
     *
     * @param searchWord A string containing the search keywords
     * @return ArrayList of integers containing task indexes
     * */
    public ArrayList<Integer> searchTasks(String searchWord) {
        ArrayList<Integer> resultTasks = new ArrayList<Integer>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskNameLowerCase = task.getName().toLowerCase();
            if (taskNameLowerCase.contains(searchWord.toLowerCase())) {
                resultTasks.add(i);
            }
        }
        return resultTasks;
    }

}
