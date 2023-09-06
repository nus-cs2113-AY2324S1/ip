public class Task {
    protected String name;
    protected boolean done;

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

    @Override
    public String toString(){
        String out = "["
                + (done ? "X" : " ")
                + "] " + this.name;
        return out;
    }


}
