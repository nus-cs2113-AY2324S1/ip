package duke.tasks;

//Create a deadline class that inherits from the task class
public class Deadline extends Task {
    protected String deadline;
    public Deadline(String taskName, String deadline){
        super(taskName);
        this.deadline = deadline;
    }
    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.isDone()){
            return "[D][X] " + this.taskName + " (" + this.deadline + ")";
        }else{
            return "[D][ ] " + this.taskName + " (" + this.deadline + ")";
        }
    }
    public String getDeadline(){
        return this.deadline;
    }
}
