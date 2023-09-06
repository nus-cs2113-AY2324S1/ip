public class TaskList {
    private final Task[] Tasks = new Task[100];
    private int totalTasks = 0;
    private final String solidLine = "\n------------------------------------------------------------------------------------------------------------------------------\n";

    public void printTasks() {
        System.out.print(solidLine);
        for(int i = 0; i < totalTasks; i++) {
            System.out.println((i+1) + ". " + Tasks[i].toString() );
        }
        System.out.print(solidLine);
    }

    public void markTask(int index, boolean IsDone) {
        System.out.println(solidLine + "Tres Bien! I have marked this as " +
                (IsDone ? "done: " : "not done yet: "));
        Tasks[index-1].setIsDone(IsDone);
        System.out.println("[" + Tasks[index-1].getStatusIcon() + "] " +
                Tasks[index-1].getDescription() + solidLine);
    }

    public void addTask(Task newTask) {
        Tasks[totalTasks] = newTask;
        totalTasks++;
        System.out.println(solidLine + "added: " + newTask + solidLine);
    }
}
