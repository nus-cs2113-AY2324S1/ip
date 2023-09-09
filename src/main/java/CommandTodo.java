public class CommandTodo extends Command {
    private static final String NAME_EMPTY_ERROR =
            "Command 'todo' should have a string as a name of the task.";

    public String name;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        name = args;
        if (name.isEmpty()) {
            throw new InvalidCommandArgumentException(NAME_EMPTY_ERROR);
        }
    }

    @Override
    protected String getArgumentErrorDetail() {
        return "Usage: todo ((name))";
    }

    @Override
    public void run() {
        Nuke.addTodo(name);
    }
}
