public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;
    }

    //override
    public String getTypeIcon(){ return "D"; }

    //override
    public String getDescription(){
        return String.format("%s (by: %s)", description, deadline);
    }
}
