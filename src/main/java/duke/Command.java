package duke;

public class Command {
    protected String COMMAND;
    protected String[] ARGUMENTS;
    protected boolean Exit;

    public Command(String command, String[] arguments){
        this.COMMAND = command;
        this.ARGUMENTS = arguments;
        this.Exit = false;
    }

    public void execute(TaskList TASKS) throws DukeException{
        switch (this.COMMAND) {
            case "todo":
                TASKS.addTodoInList(this.ARGUMENTS);
                return;

            case "deadline":
                TASKS.addDeadlineInList(this.ARGUMENTS);
                return;

            case "event":
                TASKS.addEventInList(this.ARGUMENTS);
                return;

            case "list":
                TASKS.print();
                return;

            case "mark":
                TASKS.markTaskComplete(this.ARGUMENTS);
                return;

            case "unmark":
                TASKS.unmarkTaskIncomplete(this.ARGUMENTS);
                return;

            case "delete":
                TASKS.deleteTaskInList(this.ARGUMENTS);
                return;

            case "bye":
                setExit();
                return;

            default: // unknown command exception
                throw new DukeException("I don't know what that means...");
        }
    }

    public void setExit(){
        this.Exit = true;
    }

    public boolean isExit(){
        return this.Exit;
    }
}
