public class Deadline extends Todo {
    protected String byDate;

    public Deadline(String description, String date) {
        super(description);
        setByDate(date);
    }

    public String getByDate() {
        return byDate;
    }

    public void setByDate(String date) {
        int spaceCut = date.indexOf(" ");
        byDate = date.substring(spaceCut + 1);
    }

    @Override
    public String toString() {
        //super.toString();
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[D]" + isDoneString + getDescription() + "(do by: " + getByDate() + ")";
    }
}
