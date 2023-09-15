package task;

import exception.DukeException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public static Todo newTodoTask(String userCommand) throws DukeException {
        String[] todoSplit = userCommand.split(" ", 2);
        if ( todoSplit[1].isEmpty() ){
            throw new DukeException("You got nothing to create. Please try again.");
        }
        return new Todo(todoSplit[1]);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
