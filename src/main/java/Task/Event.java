package Task;

public class Event  extends Task {
    public String from;
    public String to;

    public Event(String name, String from, String to){
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(String name, boolean done, String from, String to){
        super(name);
        this.done = done;
        this.from = from;
        this.to = to;
    }

    /**
     * Expresses the task in a String format.
     *
     * @return String containing info of the Task
     */
    @Override
    public String toString(){
        String out = "[E]["
                + (done ? "X" : " ")
                + "] " + super.name
                + " (from: " + from
                + " to: " + to + ")";
        return out;
    }


    /**
     * Formats class into a row in the save file
     * Each parameter separated by " /"
     * Format is [NAME] /[DONE] /[TYPE] /[FROM] /[TO]
     *
     * @return String formatted for saving in a .txt
     */
    @Override
    public String toFileLine(){
        return super.toFileLine() + " /EVENT /" + from + " /" + to;
    }
}
