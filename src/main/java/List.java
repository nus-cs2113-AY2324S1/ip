import java.util.ArrayList;

public class List {
    ArrayList<Task> tasks;

    public List() {
        this.tasks = new ArrayList<Task>(100);
    }

    public void add(String task) {
        this.tasks.add(Task.createTask(task));
    }

    @Override
    public String toString() {
        String output = "Here are the tasks in your list: \n";
        for (int i = 1; i <= tasks.size(); i++) {
            output = output + i + "." + this.tasks.get(i-1) + "\n";
        }
        return output;
    }
}
