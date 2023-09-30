package task;
import static utility.Constants.SOLIDLINE;

public class TaskList {
    private final Task[] Tasks = new Task[100];
    private int totalTasks = 0;

    public void printTasks() {
        System.out.print(SOLIDLINE);
        for(int i = 0; i < totalTasks; i++) {
            System.out.println((i+1) + ". " + Tasks[i].toString() );
        }
        System.out.print(SOLIDLINE);
    }

    public void markTask(int index, boolean IsDone) {
        Tasks[index].setIsDone(IsDone);
        System.out.println(SOLIDLINE + "Tres Bien! I have marked this as " +
                (IsDone ? "done: " : "not done yet: "));
        System.out.println("[" + Tasks[index].getStatusIcon() + "] " +
                Tasks[index].getDescription() + SOLIDLINE);
    }

    public void addTask(Task newTask) {
        Tasks[totalTasks] = newTask;
        totalTasks++;
        System.out.println(SOLIDLINE + "added: " + newTask + SOLIDLINE);
    }

    public int getTotalTasks() {
        return this.totalTasks;
    }

    public Task getTask(int index) {
        return this.Tasks[index];
    }
}
