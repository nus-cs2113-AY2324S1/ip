package duke;

class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

}

