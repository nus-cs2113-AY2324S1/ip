package Duke;

public class Deadline extends Task {

    protected String when;
    
    public Deadline(String description, String when) {
        super(description);
        this.when = when;
        this.type = "D";
    }
    
    @Override
    public String getDescription() {
        return "[" + type + "]" + super.getDescription() + "(by: " + when + ")";
    }

    @Override
    public String getWhen() {
        return when;
    }
}
