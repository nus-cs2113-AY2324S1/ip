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
        String[] commandArray = command.split(" "); // Split input into array of strings
        switch (commandArray[0]) { // Check first word of input for command

            default: // Add task
                Task newTask = new Task();
                newTask.setName(command);
                taskList.addTask(newTask);
                System.out.println(DefaultStrings.addedString + command);
                System.out.println(DefaultStrings.splittingLine);

            /**Checks for command keywords
             * and executes corresponding methods
             * from TaskList class if found.
             */

            case Constants.endCommand:
                System.out.println(DefaultStrings.endString);
                return Constants.exitFlag; // Exit program

            case Constants.listCommand:
                taskList.listTasks();

            case Constants.markCommand:
                int taskNumber = Integer.parseInt(commandArray[1]);
                taskList.markTask(taskNumber);

            case Constants.unmarkCommand:
                taskNumber = Integer.parseInt(commandArray[1]);
                taskList.unmarkTask(taskNumber);
        }

        return Constants.stayFlag; // Continue program
        
    }   
}
