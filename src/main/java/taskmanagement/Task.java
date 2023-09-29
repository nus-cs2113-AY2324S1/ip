package taskmanagement;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected String taskType;
    public boolean isDone;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    // Default constructor so that the class can be used as a superclass
    public Task(){
    }

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
