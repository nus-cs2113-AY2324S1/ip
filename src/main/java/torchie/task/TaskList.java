package torchie.task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void deleteTask(int index) {
        Task t = taskList.get(index);
        taskList.remove(t);
        t.announceTaskDelete();
    }

    public int getSize() {
        return taskList.size();
    }

    public boolean isInList (Task t) {
        return taskList.contains(t);
    }

    public int getIndex (Task t) {
        return taskList.indexOf(t);
    }

    public void showTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i=0; i<this.getSize(); i++){
            System.out.print( (i+1) + ".");
            System.out.println("\t" + taskList.get(i).toString());
        }
    }

    public void markTask(int index) {
        taskList.get(index).markTask();
    }

    public void unmarkTask(int index) {
        taskList.get(index).unmarkTask();
    }

    public void announceListSize() {
        System.out.println("Now you have " + this.getSize() + " task(s) in the list.");
    }
}
