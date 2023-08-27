public class TaskManager {
    private Task[] tasks;
    private int totalTasks;

    public TaskManager(){
        this.tasks = new Task[100];
        totalTasks=0;
    }

    public void listTasks(){
        String[] allTasksDescription = new String[totalTasks];
        for(int i=0; i<totalTasks; i++){
            allTasksDescription[i] = (i+1) + ". " + tasks[i].getDescription();
        }
        Duke.formatPrint(allTasksDescription);
    }

    public void addTask(String description){
        Task task = new Task(description);
        tasks[totalTasks] = task;
        totalTasks++;
        Duke.formatPrint("\tadded: "+description);
    }

}
