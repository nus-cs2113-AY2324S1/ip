/**
 * This is the ToDo class which contains task description only. It inherits from the Task class.
 */

package chatbot;

public class ToDo extends Task{
    public ToDo(String description, boolean isDone){
        super(description, isDone);
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
