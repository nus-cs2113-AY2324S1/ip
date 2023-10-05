package task;
import java.util.ArrayList;
import static utility.Constants.SOLIDLINE;
import utility.Ui;

public class TaskList {
    private ArrayList<Task> Tasks = new ArrayList<Task>();
    private Ui ui = new Ui();

    public TaskList() {
        super();
    }

    /**
     * Constructor with argument, to initialise a Tasklist
     * with an existing arraylist of tasks i.e. from storage
     * @param Tasks
     */
    public TaskList(ArrayList<Task> Tasks) {
        this.Tasks = Tasks;
    }

    /**
     * Calls ui to show all the current tasks
     * Used in "list" command
     */
    public void printTasks() {
        ui.showTasks(Tasks);
    }

    /**
     * Sets the isDone parameter in a Task of a certain index
     * @param index The index (zero-indexed) of the Task in the list
     * @param IsDone the state the Task should be set to
     */
    public void markTask(int index, boolean IsDone) {
        Tasks.get(index).setIsDone(IsDone);
        ui.showMarked(Tasks.get(index));
    }

    /**
     * Adds a task to the TaskList
     * @param newTask the Task object to be added
     */
    public void addTask(Task newTask) {
        Tasks.add(newTask);
        ui.showAdded(newTask);
    }

    /**
     * Removes all tasks from the TaskList
     */
    public void clearTasks() {
        Tasks.clear();
    }

    /**
     * Returns how many Tasks are in the TaskList
     * @return Number of Tasks in the TaskList
     */
    public int getTotalTasks() {
        return Tasks.size();
    }

    /**
     * Gets an individual Task of a certain index
     * @param index The (zero indexed) index of the Task
     * @return Task object
     */
    public Task getTask(int index) {
        return this.Tasks.get(index);
    }

    /**
     * Removes a Task of a certain index from the TaskList
     * @param index (zero indexed) index of the Task
     */
    public void deleteTask(int index) {
        ui.showDeleted(Tasks.get(index));
        this.Tasks.remove(index);
    }

    /**
     * Returns an ArrayList of Tasks, containing everything in the TaskList
     * @return ArrayList<Task>
     */
    public ArrayList<Task> getTaskData() {
        return this.Tasks;
    }

    /**
     * Searches the entire TaskList for anything containing the
     * searchWord. Supports case insensitivity and multiple words
     * @param searchWord String of search terms (case-insensitive)
     * @return An ArrayList<Task> of all the Tasks in the TaskList
     * that contain the searchWord
     */
    public ArrayList<Integer> searchTasks(String searchWord) {
        ArrayList<Integer> index = new ArrayList<Integer>();
        for(int i = 0; i < Tasks.size(); i++) {
            Task task = Tasks.get(i);
            String description = task.getDescription().toLowerCase();
            if(description.contains(searchWord.toLowerCase())) {
                index.add(i);
            }
        }
        return index;
    }
}
