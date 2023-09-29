package duke.tasks;
//Create a task class
public class Task {
    protected String taskName;
    private boolean isDone;
    public Task(String taskName){
        this.taskName = taskName;
        this.setDone(false);
    }
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    //method to mark as done
    public void markAsDone(){
        this.setDone(true);
    }
    //method to mark as not done
    public void markAsNotDone(){
        this.setDone(false);
    }
    public String getTaskName(){
        return this.taskName;
    }
    public boolean getIsDone(){
        return this.isDone();
    }
    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.isDone()){
            return "[X] " + this.taskName;
        }else{
            return "[ ] " + this.taskName;
        }
    }
}
