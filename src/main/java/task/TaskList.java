package task;
import java.util.ArrayList;

import static utility.Constants.SOLIDLINE;

public class TaskList {
    private ArrayList<Task> Tasks = new ArrayList<Task>();
    public TaskList() {
        super();
    }
    public TaskList(ArrayList<Task> Tasks) {
        this.Tasks = Tasks;
    }

    public void printTasks() {
        System.out.print(SOLIDLINE);
        for(int i = 0; i < Tasks.size(); i++) {
            System.out.println((i+1) + ". " + Tasks.get(i).toString() );
        }
        System.out.print(SOLIDLINE);
    }

    public void markTask(int index, boolean IsDone) {
        Tasks.get(index).setIsDone(IsDone);
        System.out.println(SOLIDLINE + "Tres Bien! I have marked this as " +
                (IsDone ? "done: " : "not done yet: "));
        System.out.println("[" + Tasks.get(index).getStatusIcon() + "] " +
                Tasks.get(index).getDescription() + SOLIDLINE);
    }

    public void addTask(Task newTask) {
        Tasks.add(newTask);
        System.out.println(SOLIDLINE + "added: " + newTask + SOLIDLINE);
    }

    public void removeTask(int index) {
        Tasks.remove(index);
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

    public ArrayList<Task> getTaskData() {
        return this.Tasks;
    }

}
