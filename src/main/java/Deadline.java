public class Deadline extends Todo {
    protected String byDate;

    public Deadline(String description, String date) {
        super(description);
        int spaceCut = date.indexOf(" ");
        byDate = date.substring(spaceCut + 1);
    }

    public String getByDate() {
        return byDate;
    }

    public void setByDate(String byDate) {
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        super.toString();
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[D]" + isDoneString + description + "(do by: " + byDate + ")";
    }
}
