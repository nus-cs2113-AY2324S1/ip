/**
 * This is the task class which is the base class for todo, deadline and event classes.
 * All task class objects can be marked as done or not done yet
 */

package tasks;

public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public void show(){
        System.out.print("[?][");
        if(isDone){
            System.out.print("X");
        }
        else{
            System.out.print(" ");
        }
        System.out.println("] " + description);
    }

    public void mark(){
        isDone = true;
        System.out.println("Marked as done:");
        System.out.println(description);
    }

    public void unmark(){
        isDone = false;
        System.out.println("Marked as not done yet:");
        System.out.println(description);
    }

    public String getDescription() {
        return description;
    }

    public String getDone() {
        return Boolean.toString(isDone);
    }

    public String getTime(){
        return "";
    }
}