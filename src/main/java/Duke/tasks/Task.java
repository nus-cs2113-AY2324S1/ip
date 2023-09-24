package duke.tasks;

/**
 * Represents an overarching task class and what a task should contain.
 */
public class Task {
    public  String toBeDone;
    public String[] markAsDone;
    public String[] taskType;
    public String dueDate;
    public String startTime;
    public String endTime;

    public Task(String newTask) {
        toBeDone = newTask;
        markAsDone = new String[1];
        markAsDone[0] = " ";
        taskType  = new String[1];
        taskType[0] = " ";

    }

    public void setDone(){
        markAsDone[0] ="X";
    }

    public void setNotDone(){
        markAsDone[0] =" ";
    }
}
