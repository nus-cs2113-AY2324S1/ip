public class UnmarkCommand extends Command{
    public UnmarkCommand(String command) {
        super(command);
    }
    @Override
    public void execute(TaskList tasks) throws FrankException {
        int index;
        try {
            index = Integer.parseInt(commands[1]);
            // This is the user index starting from 1
            index--;
            if(index < 0 || index + 1 > tasks.getTotalTasks()) {
                throw new FrankException("Brough it is out of index!");
            }
            if(!tasks.getTask(index).getIsDone()) {
                throw new FrankException("Brough it is already unmarked!");
            }
        } catch (NullPointerException e) {
            throw new FrankException("Brough there is no task to unmark!" );
        } catch (NumberFormatException e) {
            throw new FrankException("Brough please put the number index in the second word. ");
        }
        tasks.markTask(index,false);
    }
}
