public class Event  extends Task {
    public String from;
    public String to;

    public Event(String name, String from, String to){
        super(name);
        this.from = from;
        this.to = to;
    }
}
