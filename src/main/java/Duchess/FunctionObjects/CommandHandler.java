package Duchess.FunctionObjects;

import Duchess.TaskObjects.Deadline;
import Duchess.TaskObjects.Event;
import Duchess.TaskObjects.Task;
import Duchess.TaskObjects.TaskList;
import Duchess.TaskObjects.ToDo;
import Duchess.TextObjects.Constants;
import Duchess.TextObjects.DefaultStrings;
import Duchess.ErrorObjects.IncompleteTaskError;
import Duchess.ErrorObjects.UnrecognisedCommandError;





/** Class to handle commands and to return exit conditions */
public class CommandHandler {

    /** List of tasks user needs to keep track of */
    private static TaskList taskList = new TaskList();

    private StringHandler stringHandler = new StringHandler();

    /** Constructor class to be declared. */
    public CommandHandler(){
        taskList.importTasks(Constants.taskFilePath);
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
    public int ParseCommand (String command) throws IncompleteTaskError, UnrecognisedCommandError  {
        String[] commandArray = command.split(" "); // Split input into array of strings
        switch (commandArray[0]) { // Check first word of input for command

            /**Checks for command keywords
             * and executes corresponding methods
             * from TaskList class if found.
             */

            case Constants.endCommand:
                System.out.println(DefaultStrings.endString);
                taskList.saveTasks(Constants.taskFilePath);
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

                try{
                    Task newToDo = stringHandler.processToDoString(command);
                    taskList.addTask(newToDo);
                    System.out.println(DefaultStrings.addedString + newToDo.toString());
                    System.out.println(DefaultStrings.splittingLine);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new IncompleteTaskError(DefaultStrings.emptyToDoString, new ToDo());
                }
                break;

            case Constants.deadlineCommand:
            
                try{
                    Task newDeadline = stringHandler.processDeadlineString(command);
                    taskList.addTask(newDeadline);
                    System.out.println(DefaultStrings.addedString + newDeadline.toString());
                    System.out.println(DefaultStrings.splittingLine);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new IncompleteTaskError(DefaultStrings.emptyDeadlineString, new Deadline());
                }
                break;


            case Constants.eventCommand:
                try{
                    Task newEvent = stringHandler.processEventString(command);
                    taskList.addTask(newEvent);
                    System.out.println(DefaultStrings.addedString + newEvent.toString());
                    System.out.println(DefaultStrings.splittingLine);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new IncompleteTaskError(DefaultStrings.emptyEventString, new Event());
                }
                break;
            
            default: // Unrecognisedcommand
                throw new UnrecognisedCommandError(DefaultStrings.unrecognisedString);
        } 

        return Constants.stayFlag; // Continue program

    }   

    public void AddTask(Task task){
        taskList.addTask(task);
    }
}
