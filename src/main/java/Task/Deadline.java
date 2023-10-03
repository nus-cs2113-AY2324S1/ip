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

    /**
     * Formats class into a row in the save file
     * Each parameter separated by " /"
     * Format is [NAME] /[DONE] /[TYPE] /[BY]
     *
     * @return String formatted for saving in a .txt
     */
    @Override
    public String toFileLine(){
        return super.toFileLine() + " /DEADLINE /" + by;
    }
}
