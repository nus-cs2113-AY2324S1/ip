public class Task {
    private String input;
    private boolean done;

    public void markAsDone(){
        done = true;
    }
    public void markAsNotDone(){
        done = false;
    }
    public Task(){
        input = "";
        done = false;
    }
    public Task(String input){
        this.input = input;
        done = false;
    }
    public boolean taskDone(){
        return done;
    }
    public String taskInput(){
        return input;
    }
}
