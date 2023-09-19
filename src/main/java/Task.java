//Create a task class
public class Task {
    protected String taskName;
    protected boolean isDone;
    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }
    //method to mark as done
    public void markAsDone(){
        this.isDone = true;
    }
    //method to mark as not done
    public void markAsNotDone(){
        this.isDone = false;
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

//Create a todo class that inherits from the task class
class Todo extends Task {
    protected Todo(String taskName){
        super(taskName);
    }
    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.isDone){
            return "[T][X] " + this.taskName;
        }else{
            return "[T][ ] " + this.taskName;
        }
    }
}

//Create a deadline class that inherits from the task class
class Deadline extends Task {
    protected String deadline;
    public Deadline(String taskName, String deadline){
        super(taskName);
        this.deadline = deadline;
    }
    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.isDone){
            return "[D][X] " + this.taskName + " (" + this.deadline + ")";
        }else{
            return "[D][ ] " + this.taskName + " (" + this.deadline + ")";
        }
    }
}

//Create an event class that inherits from the task class
class Event extends Task {
    protected String eventTime;
    public Event(String taskName, String eventTime){
        super(taskName);
        this.eventTime = eventTime;
    }
    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.isDone){
            return "[E][X] " + this.taskName + " (" + this.eventTime + ")";
        }else{
            return "[E][ ] " + this.taskName + " (" + this.eventTime + ")";
        }
    }
}
