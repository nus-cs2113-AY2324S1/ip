import java.util.ArrayList;
import java.util.Arrays;

public class ApplicationState {
    private static ApplicationState appState = null;
    private final ArrayList<String> tasks = new ArrayList<>();

    private ApplicationState() {
    }

    public static ApplicationState getAppState() {
        if (appState == null)
            appState = new ApplicationState();
        return appState;
    }

    public void addTask(String task) {
        this.tasks.add(task);
    }

    public ArrayList<String> getTasks() {
        return this.tasks;
    }
}
