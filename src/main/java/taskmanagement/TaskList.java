package taskmanagement;


import java.util.ArrayList;

/**
 * Represents a list of tasks in the Zran application.
 * Manages a collection of Task objects.
 */
public class TaskList {
    public ArrayList<Task> listItems;

    /**
     * Default constructor for TaskList.
     */
    public TaskList(){
        this.listItems = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList instance with the given list of tasks.
     *
     * @param items The initial list of tasks.
     */
    public TaskList(ArrayList<Task> items){
        this.listItems=items;
    }

}
