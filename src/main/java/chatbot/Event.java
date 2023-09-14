package chatbot;

public class Event extends Task{
    private String start;
    private String end;

    public Event(String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;
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
        System.out.println("] " + description + " (" + start + " to " + end + ")");
    }
}
