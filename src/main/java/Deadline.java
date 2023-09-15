public class Deadline extends Task {

    protected String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    public static Deadline newDdl(String userCommand) {
        // command format: deadline return book /by Sunday
        String[] ddlSplit = userCommand.split("/");
        int spaceIndex = ddlSplit[0].indexOf(" ");
        String ddlTask = ddlSplit[0].substring(spaceIndex + 1).trim();

        return new Deadline(ddlTask, ddlSplit[1].substring(3));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }
}
