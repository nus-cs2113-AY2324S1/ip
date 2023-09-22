package Task;

public class Deadline extends Task {
    protected String by;
    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }

    public Deadline(String name, boolean done, String by){
        super(name);
        this.done = done;
        this.by = by;
    }

    @Override
    public String toString(){
        String out = "[D]["
                + (done ? "X" : " ")
                + "] " + super.name
                + " (by: " + by + ")";
        return out;
    }

    @Override
    public String toFileLine(){
        return super.toFileLine() + " /DEADLINE /" + by;
    }
}
