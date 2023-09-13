public class Task {
    protected String description;
    protected String taskType;
    protected boolean isDone;

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
