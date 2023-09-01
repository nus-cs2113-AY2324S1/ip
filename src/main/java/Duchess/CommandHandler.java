package Duchess;


/** Class to handle commands and to return exit conditions */
public class CommandHandler {

    /** List of tasks user needs to keep track of */
    private static TaskList taskList = new TaskList();

    /** Constructor class to be declared. */
    public CommandHandler(){
    }
    
    
    /**
    * Echoes input message as default command.
    * If exit command is parsed, return flag to trigger exit condition.
    *
    * @param command Command to be handled.
    * @method 
    * @return Flag to initiate exit of program.
    * @see Message echoed in console.
    */
    public int ParseCommand(String command) {
        switch (command) {
            default:
                Task newTask = new Task();
                newTask.setName(command);
                taskList.addTask(newTask);
                System.out.println(DefaultStrings.addedString + command);
                System.out.println(DefaultStrings.splittingLine);
                return Constants.stayFlag;

            case Constants.endCommand:
                System.out.println(DefaultStrings.endString);
                return Constants.exitFlag;

            case Constants.listCommand:
                taskList.listTasks();
                return Constants.stayFlag;

        }
    }   
}
