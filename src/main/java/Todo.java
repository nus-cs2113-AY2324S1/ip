public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }


    public static Todo newTodoTask(String userCommand) {
        String[] todoSplit = userCommand.split(" ", 2);
        return new Todo(todoSplit[1]);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
