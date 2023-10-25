package nupjuk;
/**
 * Task class
 * including description and check whether it has been completed
 */
public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String getTypeIcon(){
        return " ";
    }

    public void doMark(){
        isDone = true;
    }

    public void unMark(){
        isDone = false;
    }

}
