public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        if(!by.contains("by ")){
            throw new DukeException("☹ OOPS!!! The description of a deadline must contain \"by\" time.");
        }else if(by.replace("by ", "").isBlank()){
            throw new DukeException("☹ OOPS!!! The time of a deadline cannot be empty.");
        }
        this.by = by.replace("by ", "");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFile(){
        return "D | " + super.toFile() + " | " + this.by;
    }
}
