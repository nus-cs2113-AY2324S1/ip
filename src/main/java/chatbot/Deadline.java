package chatbot;

public class Deadline extends Task{
    private String deadline;
    public Deadline(String description, boolean isDone, String deadline){
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getTime(){
        return deadline;
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
        System.out.println("] " + description + " (by: " + deadline + ")");
    }
}
