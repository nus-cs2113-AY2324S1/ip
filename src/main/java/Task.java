import java.util.ArrayList;

public class Task {
    private final static String STATE_KEY = "Tasks";
    protected String name;
    protected Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getNameWithStatus() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public static void addTask(Task task) {
        ArrayList<Task> tasks = getTasks();
        tasks.add(task);
    }

    public static Task getTask(int pos) {
        ArrayList<Task> tasks = getTasks();
        if (pos < 0 || pos >= tasks.size()) {
            return null;
        }
        return tasks.get(pos);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Task> getTasks() {
        ApplicationState appState = ApplicationState.getAppState();
        Object tasks = appState.getStateObject(STATE_KEY);
        if (tasks == null) {
            tasks = new ArrayList<Task>();
            appState.setStateObject(STATE_KEY, tasks);
        }
        return (ArrayList<Task>) tasks;
    }
}
