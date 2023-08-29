public class Task {
    private String name;
    private boolean done;

    public Task(String name){
        this.name = name;
        this.done = false;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean getDone(){
        return done;
    }

    public String getName(){
        return this.name;
    }
}
