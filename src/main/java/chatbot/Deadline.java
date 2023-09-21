package chatbot;

public class Deadline extends Task{
    private String deadline;
    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;
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
