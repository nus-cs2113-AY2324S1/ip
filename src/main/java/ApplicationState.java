import java.util.ArrayList;

public class ApplicationState {
    private static ApplicationState appState = null;
    private final ArrayList<Task> TASKS = new ArrayList<>();

    private ApplicationState() {
    }

    public static ApplicationState getAppState() {
        if (appState == null) {
            appState = new ApplicationState();
        }
        return appState;
    }

    public void addTask(String task) {
        this.TASKS.add(new Task(task));
    }

    public ArrayList<Task> getTasks() {
        return this.TASKS;
    }

    public Task getTask(int pos) {
        if (pos < 0 || pos >= this.TASKS.size()) {
            return null;
        }
        return this.TASKS.get(pos);
    }
}
