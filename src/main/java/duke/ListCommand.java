package duke;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // You can include any logic related to the "list" command here
        TaskList.printList(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false; // This command does not indicate program exit
    }
}
