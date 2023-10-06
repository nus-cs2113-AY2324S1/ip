import java.util.ArrayList;

/**
 * List of tasks
 * Based on ArrayList
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /* get ith Task */
    public Task getTask(int idx){
        return tasks.get(idx);
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(int idx){
        tasks.remove(idx);
    }

    public int getSize(){
        return tasks.size();
    }

}
