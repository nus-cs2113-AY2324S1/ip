public class Deadline extends Task {
    protected String by;
    public Deadline(String name, String by){
        super(name);
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
}
