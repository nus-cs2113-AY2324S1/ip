public class DeadlineCommand extends Command {

    @Override
    public void execute(TaskList tasks) throws FrankException {
        System.out.println("Bisa! What is the task?");
        String description = input.nextLine();
        System.out.println("Ke Yi! When is it due?");
        String dueDate = input.nextLine();
        if(description.isEmpty() || dueDate.isEmpty()) {
            throw new FrankException("Brough you forgot to fill in something!");
        }
        tasks.addTask(new Deadline(description, dueDate));
    }
}
