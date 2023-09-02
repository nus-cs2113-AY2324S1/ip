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

        for (int i=0; i<totalTasks; i++){
            allTasksDescription[i+1] = (i+1) + "." + tasks[i];
        }
        Duke.formatPrint(allTasksDescription);
    }

    public void addTask(String description){
        Todo task = new Todo(description);
        incrementAndPrintNewTask(task);
    }

    public void addTask(String description, String by){
        Deadline task = new Deadline(description, by);
        incrementAndPrintNewTask(task);
    }

    public void addTask(String description, String from, String to){
        Event task = new Event(description, from, to);
        incrementAndPrintNewTask(task);
    }

    public void setTaskIsDone(int idx, boolean isDone){
        idx--;
        tasks[idx].setIsDone(isDone);
        String doneMsg = isDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        String[] messages = new String[]{
                doneMsg,
                "\t" + tasks[idx]
        };
        Duke.formatPrint(messages);
    }

    public String getNumberOfTasks(){
        String taskWord = totalTasks > 1 ? " tasks" : " task";
        return "Now you have " + totalTasks + taskWord + " in the list.";
    }

    public void incrementAndPrintNewTask(Task t){
        tasks[totalTasks] = t;
        totalTasks++;
        String[] messages = new String[]{
                "Got it. I've added this task:",
                "\t" + t,
                getNumberOfTasks()
        };
        Duke.formatPrint(messages);
    }

}
