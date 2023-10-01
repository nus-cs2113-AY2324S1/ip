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
    public TaskList(ArrayList<Task> Tasks) {
        this.Tasks = Tasks;
    }

    public void printTasks() {
        ui.showTasks(Tasks);
    }

    public void markTask(int index, boolean IsDone) {
        Tasks.get(index).setIsDone(IsDone);
        ui.showMarked(Tasks.get(index));
    }

    public void addTask(Task newTask) {
        Tasks.add(newTask);
        ui.showAdded(newTask);
    }

    public void clearTasks() {
        Tasks.clear();
    }

    public int getTotalTasks() {
        return Tasks.size();
    }

    public Task getTask(int index) {
        return this.Tasks.get(index);
    }

    public void deleteTask(int index) {
        ui.showDeleted(Tasks.get(index));
        this.Tasks.remove(index);
    }
    public ArrayList<Task> getTaskData() {
        return this.Tasks;
    }
}
