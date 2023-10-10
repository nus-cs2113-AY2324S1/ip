import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> tasks;
    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Add the task to the list
     *
     * @param task The task list to be added to the list
     */
    public void addTask(Task task){
        this.tasks.add(task);
    }

    /**
     * Delete the task with the index
     *
     * @param index The index of task to be deleted
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException{
        this.tasks.remove(index-1);
    }

    /**
     * Mark the task with the index as done
     *
     * @param index The index of task to be marked as done
     */
    public void markTask(int index) throws IndexOutOfBoundsException{
        this.tasks.get(index-1).markAsDone();
    }

    /**
     * Unark the task with the index as done
     *
     * @param index The index of task to be unmarked as done
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException{
        this.tasks.get(index-1).unmarkAsDone();
    }

    /**
     * Find the task with the description
     *
     * @param description The description of task to be found
     * @return The task list with the description
     */
    public TaskList find(String description){
        TaskList temp = new TaskList();
        for(Task task : this.tasks){
            if(task.toString().contains(description)){
                temp.addTask(task);
            }
        }
        return temp;
    }

    /**
     * Get the task with the index
     *
     * @param index The index of task to be get
     * @return The task with the index
     */
    public Task get(int index){
        return this.tasks.get(index-1);
    }

    /**
     * Get the size of the list
     *
     * @return The size of the list
     */
    public int size(){
        return this.tasks.size();
    }
}
