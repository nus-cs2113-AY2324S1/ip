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
    private static final int ZERO_INDEX_OFFSET = 1;
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

    public static int extractIndex(String userInput, int startIndex) {
        if (startIndex < userInput.length()) {
            return Integer.parseInt(userInput.substring(startIndex).trim()) - 1; // Convert to 0-indexed
        }
        else {
            return -1;
        }
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

    // TODO: abstract mark, unmark and delete to TaskManager
    public void markTaskAsDone(int index, boolean showMessage) throws JarvisException {
        int zero_index = index - ZERO_INDEX_OFFSET;
        try {
            if (isValidIndex(zero_index)) {
                if(taskList.get(zero_index).taskIsDone()){
                    System.out.println("Task was previously set to done! No additional actions required");
                }
                else {
                    taskList.get(zero_index).markAsDone();
                    if(showMessage){
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    " + taskList.get(zero_index));
                    }
                }
            }
            else {
                throw JarvisException.invalidTaskNumber(zero_index);
            }
        } catch (JarvisException e) {
            System.out.println(e.getMessage());
        }
    }

    public void markTaskAsUndone(int index) throws JarvisException {
        int zero_index = index - ZERO_INDEX_OFFSET;
        try {
            if (isValidIndex(zero_index)) {
                if(!taskList.get(zero_index).taskIsDone()){
                    System.out.println("Task was previously set to undone! No additional actions required");
                }
                else {
                    taskList.get(zero_index).markAsUndone();
                    System.out.println("Oh NO! I've unmarked this task as undone:");
                    System.out.println("    " + taskList.get(zero_index));
                    System.out.println("Get Grinding Son");
                }
            }
            else {
                throw JarvisException.invalidTaskNumber(index);
            }
        } catch (JarvisException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int index) throws JarvisException {
        int zero_index = index - ZERO_INDEX_OFFSET;
        try {
            if (isValidIndex(zero_index)) {
                Task removedTask = taskList.remove(zero_index);
                System.out.println("Noted. I've removed this task:");
                System.out.println("    " + removedTask);
                displayTaskCount();
            } else {
                throw JarvisException.invalidTaskNumber(zero_index);
            }
        } catch (JarvisException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < taskList.size();
    }
}
