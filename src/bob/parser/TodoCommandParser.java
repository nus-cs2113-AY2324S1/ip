package bob.parser;

import bob.BobException;
import bob.commands.TodoCommand;
import bob.todo.Todo;

public class TodoCommandParser implements Parser<TodoCommand> {

    public TodoCommand parse(String input) throws BobException {
        if (input.isEmpty()) {
            throw new BobException("The description of a todo cannot be empty");
        }

        return new TodoCommand(new Todo(input));
    }
}
