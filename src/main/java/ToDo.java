public class ToDo extends Task {
    public String from;
    public String to;
    public ToDo(String name, String from, String to){
        super(name);
        this.from = from;
        this.to = to;
    }
}
