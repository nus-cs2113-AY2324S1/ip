import java.util.ArrayList;

public class List {
    ArrayList<Task> tasks;

    public List() {
        this.tasks = new ArrayList<Task>(100);
    }

    public void add(String task) {
        this.tasks.add(Task.createTask(task, this.tasks.size() + 1));
    }

    @Override
    public String toString() {
        String output = "Here are the tasks in your list: \n";
        for (int i = 1; i <= tasks.size(); i++) {
            output = this.tasks.get(i-1) + "\n";
        }
        return output;
    }
}
