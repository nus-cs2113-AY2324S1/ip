public class TaskManager {
    private Task[] tasks;
    private int totalTasks;

    public TaskManager(){
        this.tasks = new Task[100];
        totalTasks=0;
    }

    public void listTasks(){
        String[] allTasksDescription = new String[totalTasks+1];
        allTasksDescription[0] = "Here are the tasks in your list:";

        for(int i=0; i<totalTasks; i++){
            allTasksDescription[i+1] = (i+1) + ".[" + tasks[i].getIsDone() + "] " + tasks[i].getDescription();
        }
        Duke.formatPrint(allTasksDescription);
    }

    public void addTask(String description){
        Task task = new Task(description);
        tasks[totalTasks] = task;
        totalTasks++;
        Duke.formatPrint("added: "+description);
    }

    public void setTaskIsDone(int idx, boolean isDone){
        idx--;
        tasks[idx].setIsDone(isDone);
        String doneMsg = isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
        String[] messages = new String[]{
                doneMsg,
                "\t[" + tasks[idx].getIsDone() + "] " + tasks[idx].getDescription()
        };
        Duke.formatPrint(messages);
    }

}
