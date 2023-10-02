package jarvis.tasklist;

import jarvis.exception.JarvisException;
import jarvis.parser.Parser;
import jarvis.tasks.Task;
import jarvis.tasks.Todo;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the list of tasks registered on the ChatBot
 * list of tasks is temporary and will be disposed upon program termination
 */

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    public int getTaskListSize(){
        return taskList.size();
    }

    private void displayTaskCount() {
        System.out.println("Now you have " + getTaskListSize() + " jarvis.tasks in the list.");
    }

    public void printTaskList() {
        System.out.println("Here's your jarvis.tasks!");
        for (int i = 0; i < taskList.size(); i++) {
            int indexNum = i + 1;
            System.out.println(indexNum + "." + taskList.get(i));
        }
        System.out.println("~~ End of list ~~ ");
    }

    /**
     * TODO: review the need for displayMessage
     * @param userInput - takes in user input
     * @param taskType - define taskType - TODO, DEADLINE, EVENT
     * @param displayMessage - boolean config to display message on CLI
     */
    public void addToTaskList(String userInput, Task.TaskType taskType, boolean displayMessage){
        switch(taskType){
        case TODO:
            try{
                String description = TaskManager.parseToDoDescription(userInput);
                taskList.add(new Todo(description));
                if(displayMessage){
                    TaskManager.showTodo(description);
                    displayTaskCount();
                }
                break;
            } catch(JarvisException e){
                System.out.println(e.getMessage());
                return;
            }
        case DEADLINE:
            try{
                List<String> deadlineDescription = TaskManager.parseDeadlineDescription(userInput);
                String description = deadlineDescription.get(0);
                String time = deadlineDescription.get(1);
                taskList.add(new Deadline(description, time));
                if(displayMessage){
                    TaskManager.showDeadline(description, time);
                    displayTaskCount();
                }
                break;
            } catch(JarvisException e){
                System.out.println(e.getMessage());
                return;
            }
        case EVENT:
            try{
                List<String> eventDescription = TaskManager.parseEventDescription(userInput);
                String description = eventDescription.get(0);
                String time = eventDescription.get(1);
                taskList.add(new Event(description, time));
                if(displayMessage){
                    TaskManager.showEvent(description, time);
                    displayTaskCount();
                }
                break;
            } catch(JarvisException e){
                System.out.println(e.getMessage());
                return;
            }
        default:
            break;
        }
    }
}
