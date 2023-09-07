public class Deadline extends Task {

    public Deadline(String description, String by) {
        super(description);
        super.taskType = "deadline";
        super.deadline = by;
    }
}
