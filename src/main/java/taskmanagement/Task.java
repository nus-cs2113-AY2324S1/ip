package taskmanagement;

import java.time.format.DateTimeFormatter;
/**
 * The base class for all task classes in the Zran application.
 * Represents a task in the application.
 */
public class Task {
    protected String description;
    protected String taskType;
    public boolean isDone;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    /**
     * Default constructor for the Task class.
     * Enables the class to be used as a superclass.
     */
    public Task(){
    }

    /**
     * Constructs an instance of Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public String getStatus(){ return (isDone? "1" : "0");  }

    public String getTaskType() {
        return (taskType);
    }

    public void setAsDone(){
        this.isDone = true;
    }

    public void setAsNotDone(){
        this.isDone = false;
    }

    public String getDescription() {return (description);}

}
