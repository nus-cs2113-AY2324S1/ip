package Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    // private final Task[] Tasks = new Task[100];
    private final List<Task> Tasks = new ArrayList<Task>();
    // private int totalTasks = 0;
    private final String solidLine = "\n------------------------------------------------------------------------------------------------------------------------------\n";

    public void printTasks() {
        System.out.print(solidLine);
        for(int i = 0; i < Tasks.size(); i++) {
            System.out.println((i+1) + ". " + Tasks.get(i).toString() );
        }
        System.out.print(solidLine);
    }

    public void markTask(int index, boolean IsDone) {
        Tasks.get(index).setIsDone(IsDone);
        System.out.println(solidLine + "Tres Bien! I have marked this as " +
                (IsDone ? "done: " : "not done yet: "));
        System.out.println("[" + Tasks.get(index).getStatusIcon() + "] " +
                Tasks.get(index).getDescription() + solidLine);
    }

    public void addTask(Task newTask) {
        // Tasks[totalTasks] = newTask;
        Tasks.add(newTask);
        // totalTasks++;
        System.out.println(solidLine + "added: " + newTask + solidLine);
    }

    public int getTotalTasks() {
        // return this.totalTasks;
        return Tasks.size();
    }

    public Task getTask(int index) {
        return this.Tasks.get(index);
    }

    public void deleteTask(int index) {
        System.out.println(solidLine + "deleted: " + Tasks.get(index) + solidLine);
        this.Tasks.remove(index);
    }
}
