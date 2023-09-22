public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks) {
        tasks.printTasks();
    }
}
