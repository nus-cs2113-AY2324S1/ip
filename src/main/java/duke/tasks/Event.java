package duke.tasks;

//Create an event class that inherits from the task class
public class Event extends Task {
    protected String eventTime;
    public Event(String taskName, String eventTime){
        super(taskName);
        this.eventTime = eventTime;
    }
    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.isDone()){
            return "[E][X] " + this.taskName + " (" + this.eventTime + ")";
        }else{
            return "[E][ ] " + this.taskName + " (" + this.eventTime + ")";
        }
    }
    public String getEventTime(){
        return this.eventTime;
    }
}