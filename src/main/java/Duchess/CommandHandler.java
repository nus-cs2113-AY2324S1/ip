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

            /**Checks for command keywords
             * and executes corresponding methods
             * from TaskList class if found.
             */

            case Constants.endCommand:
                System.out.println(DefaultStrings.endString);
                return Constants.exitFlag; // Exit program

            case Constants.listCommand:
                taskList.listTasks();
                break;

            case Constants.markCommand:
                int taskNumber = Integer.parseInt(commandArray[1]);
                taskList.markTask(taskNumber);
                break;

            case Constants.unmarkCommand:
                taskNumber = Integer.parseInt(commandArray[1]);
                taskList.unmarkTask(taskNumber);
                break;

            case Constants.todoCommand:

                String toDoName = command.substring(5);
                if (toDoName.equals("")) {
                    System.out.println(DefaultStrings.emptyToDoString);
                    break;
                }

                Task newToDo = new ToDo(toDoName);
                taskList.addTask(newToDo);
                System.out.println(DefaultStrings.addedString + newToDo.toString());
                System.out.println(DefaultStrings.splittingLine);
                break;

            case Constants.deadlineCommand:
            
                try{
                    String deadlineName = command.substring(9, command.indexOf(Constants.deadlineTime) - 1);
                    String deadlineTime = command.substring(command.indexOf(Constants.deadlineTime) + Constants.deadlineTime.length() + 1);
                    Task newDeadline = new Deadline(deadlineName, deadlineTime);
                    taskList.addTask(newDeadline);
                    System.out.println(DefaultStrings.addedString + newDeadline.toString());
                    System.out.println(DefaultStrings.splittingLine);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(DefaultStrings.emptyDeadlineString);
                }
                break;


            case Constants.eventCommand:
                try{
                    String eventName = command.substring(6, command.indexOf(Constants.eventStartTime) - 1);
                    String eventStartTime = command.substring(command.indexOf(Constants.eventStartTime) + 
                            Constants.eventStartTime.length() + 1, command.indexOf(Constants.eventEndTime) - 1);
                    String eventEndTime = command.substring(command.indexOf(Constants.eventEndTime) + Constants.eventEndTime.length() + 1);
                    Task newEvent = new Event(eventName, eventStartTime, eventEndTime);
                    taskList.addTask(newEvent);
                    System.out.println(DefaultStrings.addedString + newEvent.toString());
                    System.out.println(DefaultStrings.splittingLine);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(DefaultStrings.emptyEventString);
                }
            
            default: // Unrecognisedcommand
                System.out.println(DefaultStrings.unrecognisedString);
                break;
        } 

        return Constants.stayFlag; // Continue program

    }   
}
