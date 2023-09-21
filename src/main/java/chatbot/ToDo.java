package chatbot;

public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    @Override
    public void show(){
        System.out.print("[T][");
        if(isDone){
            System.out.print("X");
        }
        else{
            System.out.print(" ");
        }
        System.out.println("] " + description);
    }
}
