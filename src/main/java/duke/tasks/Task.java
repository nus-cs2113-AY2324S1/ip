package duke.tasks;
//Create a task class
public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName){
        this.taskName = taskName;
        this.setDone(false);
    }

    public Task(String taskName, boolean isDone){
        this.taskName = taskName;
        this.setDone(isDone);
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskName(){
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.isDone){
            return "[X] " + this.taskName;
        }else{
            return "[ ] " + this.taskName;
        }
    }

}
