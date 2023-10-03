public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription();
    }
}
