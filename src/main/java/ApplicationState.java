import java.util.ArrayList;

public class ApplicationState {
    private static ApplicationState appState = null;
    private final ArrayList<Task> tasks = new ArrayList<>();

    private ApplicationState() {
    }

    public static ApplicationState getAppState() {
        if (appState == null) {
            appState = new ApplicationState();
        }
        return appState;
    }

    public void addTask(String task) {
        this.tasks.add(new Task(task));
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int pos) {
        if (pos < 0 || pos >= this.tasks.size()) {
            return null;
        }
        return this.tasks.get(pos);
    }
}
