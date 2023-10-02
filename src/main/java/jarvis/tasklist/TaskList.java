package jarvis.tasklist;

import jarvis.exception.JarvisException;
import jarvis.tasks.Task;
import jarvis.tasks.Todo;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;

import java.util.ArrayList;

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
    }

    /**
     * TODO: review the need for displayMessage
     * @param userInput
     * @param taskType
     * @param displayMessage
     */
    public void addToTaskList(String userInput, Task.TaskType taskType, boolean displayMessage){
        switch(taskType){
        case TODO:
            try{
                String description = TaskManager.parseToDoDescription(userInput);
//                System.out.println(description); // debug
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
            System.out.println("Deadline");
            break;
        case EVENT:
            System.out.println("Event");
            break;
        }
    }
}
