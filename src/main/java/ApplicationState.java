import java.util.ArrayList;
import java.util.HashMap;

public class ApplicationState {
    private static ApplicationState appState = null;
    private final HashMap<String, Object> states = new HashMap<>();
    private final ArrayList<Task> TASKS = new ArrayList<>();

    private ApplicationState() {
    }

    public static ApplicationState getAppState() {
        if (appState == null) {
            appState = new ApplicationState();
        }
        return appState;
    }

    public Object getStateObject(String key) {
        if (!states.containsKey(key)) {
            return null;
        }
        return states.get(key);
    }

    public void setStateObject(String key, Object state) {
        states.put(key, state);
    }
}
