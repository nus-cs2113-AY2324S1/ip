/**
 * This is the Event class which contains a task description, event start time and event end time.
 * It inherits from the task class.
 */

package tasks;

public class Event extends Task{
    private String start;
    private String end;

    public Event(String[] parts, boolean isDone){
        super(parts[0], isDone);
        this.start = parts[1];
        this.end = parts[2];
    }

    @Override
    public String getTime(){
        return start + "|" + end;
    }

    @Override
    public void show(){
        System.out.print("[D][");
        if(isDone){
            System.out.print("X");
        }
        else{
            System.out.print(" ");
        }
        System.out.println("] " + description + " (from:" + start + " to:" + end + ")");
    }
}
