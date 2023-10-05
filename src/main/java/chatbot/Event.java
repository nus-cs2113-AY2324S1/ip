/**
 * This is the Event class which contains a task description, event start time and event end time.
 * It inherits from the task class.
 */

package chatbot;

public class Event extends Task{
    private String start;
    private String end;

    public Event(String description, boolean isDone, String start, String end){
        super(description, isDone);
        this.start = start;
        this.end = end;
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
