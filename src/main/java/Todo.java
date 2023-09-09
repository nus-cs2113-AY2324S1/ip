public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String printTask() {
        String typeOfTask = "[T]";
        return typeOfTask + super.printTask();
    }
}
