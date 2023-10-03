package jarvis.tasklist;

import jarvis.exception.JarvisException;
import jarvis.parser.Parser;
import jarvis.tasks.Task;
import jarvis.tasks.Todo;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;


/**
 * Stores the list of tasks registered on the ChatBot
 * list of tasks is temporary and will be disposed upon program termination
 */

public class TaskList {
    private static final int ZERO_INDEX_OFFSET = 1;
    private final ArrayList<Task> taskList = new ArrayList<>();
    DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm" );

    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    public int getTaskListSize(){
        return taskList.size();
    }

    private void displayTaskCount() {
        System.out.println("Now you have " + getTaskListSize() + " tasks in the list sir.");
    }

    public void printTaskList() {
        System.out.println("Here's your tasks sir!");
        for (int i = 0; i < taskList.size(); i++) {
            int indexNum = i + 1;
            System.out.println(indexNum + "." + taskList.get(i));
        }
        System.out.println("~~ End of Task list ~~ ");
    }

    /**
     * Adds a task to the task list based on user input and type.
     *
     * @param userInput       User's input
     * @param taskType        Type of task - TODO, DEADLINE, or EVENT
     * @param displayMessage  Flag to decide whether to display a message on the CLI after adding
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
                LocalDateTime  deadlineDateTime = LocalDateTime.parse(time, inputDateTimeFormatter);
                taskList.add(new Deadline(description, deadlineDateTime));
                if(displayMessage){
                    TaskManager.showDeadline(description, deadlineDateTime);
                    displayTaskCount();
                }
                break;
            } catch(JarvisException e){
                System.out.println(e.getMessage());
                return;
            }catch (DateTimeParseException incorrectTimeFormat) {
                System.out.println("Please provide the datetime format as dd-MM-yyyy HH:mm sir!");
                return;
            }
        case EVENT:
            try{
                List<String> eventDescription = TaskManager.parseEventDescription(userInput);
                String description = eventDescription.get(0);
                String time = eventDescription.get(1);
                String[] times = time.split(" to ", 2);
                LocalDateTime startDateTime = LocalDateTime.parse(times[0], inputDateTimeFormatter);
                LocalDateTime endDateTime = LocalDateTime.parse(times[1], inputDateTimeFormatter);
                taskList.add(new Event(description, startDateTime, endDateTime));
                if(displayMessage){
                    TaskManager.showEvent(description, startDateTime, endDateTime);
                    displayTaskCount();
                }
                break;
            } catch(JarvisException e){
                System.out.println(e.getMessage());
                return;
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException incorrectTimeFormat) {
                System.out.println("Please provide the datetime range format as 'dd-MM-yyyy HH:mm to dd-MM-yyyy HH:mm' sir!");
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
                    System.out.println("Task was previously set to done! No additional actions required sir.");
                }
                else {
                    taskList.get(zero_index).markAsDone();
                    if(showMessage){
                        System.out.println("Nice! I've marked this task as done sir:");
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
                    System.out.println("Task was previously set to undone! No additional actions required sir.");
                }
                else {
                    taskList.get(zero_index).markAsUndone();
                    System.out.println("Oh NO! I've unmarked this task as undone sir:");
                    System.out.println("    " + taskList.get(zero_index));
                    System.out.println("Get Grinding SIR");
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
                System.out.println("Noted sir. I've removed this task sir:");
                System.out.println("    " + removedTask);
                displayTaskCount();
            } else {
                throw JarvisException.invalidTaskNumber(zero_index);
            }
        } catch (JarvisException e) {
            System.out.println(e.getMessage());
        }
    }
    public void searchListByDescription(String keyword){
        if(taskList.isEmpty()){
            System.out.println("Task List is empty!");
            return;
        }
        List<Task> matchedTasks = taskList.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if(matchedTasks.isEmpty()){
            System.out.println("No results found. Please check your keyword is correct sir?");
        } else {
            printMatchedTasks(matchedTasks);
        }
    }

    public void printMatchedTasks(List<Task> matchedTasks) {
        System.out.println("Here are tasks that matched your search sir:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            int indexNum = i + 1;
            System.out.println("    " + indexNum + "." + matchedTasks.get(i));
        }
        System.out.println("~~ End of Search list ~~ ");
    }
    public void searchList(String input) {
        final String SEARCH_USAGE_MESSAGE = "Please format your input as find [description] sir!";
        String searchKeyword;

        try {
            searchKeyword = input.split("find")[1].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("Please indicate the keywords you are searching sir");
            System.out.println(SEARCH_USAGE_MESSAGE);
            return;
        }

        if (!searchKeyword.isEmpty()) {
            searchListByDescription(searchKeyword);
        } else {
            System.out.println("Please provide any keyword to search sir");
            System.out.println(SEARCH_USAGE_MESSAGE);
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < taskList.size();
    }
}
