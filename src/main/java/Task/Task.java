package Task;

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

    @Override
    public String toString(){
        String out = "["
                + (done ? "X" : " ")
                + "] " + this.name;
        return out;
    }

    /*
    Formats class into a row in the save file
    Each parameter separated by " /"
    Format is [NAME] /[DONE] /[TYPE] /{any additional parameters}
     */
    public String toFileLine() {
        return name + " /" + (done ? "true" : "false");
    }
}
