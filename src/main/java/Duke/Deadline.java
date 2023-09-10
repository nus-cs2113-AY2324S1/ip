package Duke;

public class Deadline extends Task {

    protected String when;
    
    public Deadline(String description, String when) {
        super(description);
        this.when = when;
    }
    
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + "(by: " + when + ")";
    }
}
