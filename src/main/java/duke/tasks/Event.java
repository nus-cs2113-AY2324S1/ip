package duke.tasks;

//Create an event class that inherits from the task class
public class Event extends Task {
    private String fromTime;
    private String toTime;

    public Event(String taskName, String fromTime, String toTime){
        super(taskName);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public String getFromTime(){
        return this.fromTime;
    }

    public void setFromTime(String fromTime){
        this.fromTime = fromTime;
    }

    public String getToTime(){
        return this.toTime;
    }

    public void setToTime(String toTime){
        this.toTime = toTime;
    }

    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.getIsDone()){
            return "[E][X] " + this.getTaskName() + " (" + this.fromTime + " to " + this.toTime + ")";
        }else{
            return "[E][ ] " + this.getTaskName() + " (" + this.fromTime + " to " + this.toTime + ")";
        }
    }
}