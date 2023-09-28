package Duke.Task;

import Duke.Task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;


    public TaskList(){
        tasks = new ArrayList<>();
    }

    //functions to add: get num task etc....
    public int getNumTask(){
        return tasks.size();
    }
    public void addTask(Task task){
        tasks.add(task);
    }
    public Task getTask(int taskIndex){
        return tasks.get(taskIndex - 1);
    }
    public void deleteTask(int taskIndex){
        tasks.remove(taskIndex - 1);
    }
}
