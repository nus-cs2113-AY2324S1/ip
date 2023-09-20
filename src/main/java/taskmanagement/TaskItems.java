package taskmanagement;

import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.Deadline;
import taskmanagement.Event;

import java.util.Scanner;
import java.util.ArrayList;

public class TaskItems {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________ ";

    public static final String DELETE_TASK_COMMAND = "delete ";
    public static final String MARK_TASK_COMMAND = "mark ";
    public static final String UNMARK_TASK_COMMAND = "unmark ";
    public static final String LIST_TASK_COMMAND = "list";
    public static final String EXIT_BOT_COMMAND = "bye";
    public static final String TODO_TASK_COMMAND = "todo ";
    public static final String DEADLINE_TASK_COMMAND = "deadline ";
    public static final String DEADLINE_DATE_COMMAND = "/by ";
    public static final String EVENT_TASK_COMMAND = "event ";
    public static final String EVENT_TASK_START = "/from ";
    public static final String EVENT_TASK_END = "/to ";
    public static final String USER_HELP_COMMAND = "help";

    //to echo after a new task is added
    public static void echo(ArrayList<Task> items, Task task, String input){
        String output = input.startsWith(DELETE_TASK_COMMAND)? "Noted!" + "Task deleted: " + task.getDescription() + "\n" +
                "    Number of Tasks: " + items.size()
                : "Noted!" + "Task added: " + task.getDescription() + "\n" +
                "    Number of Tasks: " + items.size() ;
        System.out.printf(outputFormat + "%n", output);
        echo(items);
    }
    //to echo after a task's status is changed
    public static void echo(Task task, boolean isDone){
        String output = isDone ? "Congrats! :D " + "Task marked as done: " + task.getDescription() + " [" + task.getStatusIcon() + "]"
                : "Oopsies!" + "Task unmarked: " + task.getDescription() + " [" + task.getStatusIcon() + "]";
        System.out.printf(outputFormat + "%n", output);
    }

    //to echo when LIST command is being used
    public static void echo(ArrayList<Task> items){
        System.out.println("    ____________________________________________________________");
        System.out.println("    List of Tasks:");
        int index=0;
        for(Task item : items){
            if((item != null)) {
                System.out.print("    "+ ++index + ". ");
                System.out.println(item.toString());
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    //The function 'addItems' is used to accept user's input and add it to their To Do list
    public static void addItems() throws ZranExceptions {

        Scanner in = new Scanner(System.in);
        String input = "";

        ArrayList<Task> items = new ArrayList<>();
        Task task = null;

        while(!(input = in.nextLine()).equals(EXIT_BOT_COMMAND)){
            if(input.startsWith(MARK_TASK_COMMAND)) {
                try{
                    String eventIndex = input.substring(MARK_TASK_COMMAND.length()).trim();
                    if(eventIndex.isEmpty()){
                        throw new ZranExceptions(ZranErrorMessages.EMPTY_TASK_INDEX.message);
                    }
                    try{
                        task = items.get(Integer.parseInt(eventIndex) - 1);
                        task.setAsDone();
                        echo(task, task.isDone);
                    } catch(NullPointerException | NumberFormatException | IndexOutOfBoundsException e ) {
                        throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX.message);
                    }
                }
                catch(ZranExceptions e){
                    System.out.printf((outputFormat) + "%n", e.getMessage());
                }
            } else if(input.startsWith(UNMARK_TASK_COMMAND)) {
                try {
                    String eventIndex = input.substring(UNMARK_TASK_COMMAND.length()).trim();
                    if (eventIndex.isEmpty()) {
                        throw new ZranExceptions(ZranErrorMessages.EMPTY_TASK_INDEX.message);
                    }
                    try {
                        task = items.get(Integer.parseInt(eventIndex) - 1);
                        task.setAsNotDone();
                        echo(task, task.isDone);
                    } catch(NullPointerException | NumberFormatException | IndexOutOfBoundsException e ) {
                        throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX.message);
                    }
                }
                catch(ZranExceptions e){
                    System.out.printf((outputFormat) + "%n", e.getMessage());
                }
            } else if (input.startsWith(DELETE_TASK_COMMAND)) {
                try {
                    String deleteIndex = input.substring(DELETE_TASK_COMMAND.length()).trim();
                    Task deletedTask = deleteTask(items, Integer.parseInt(deleteIndex) - 1);
                    echo(items, deletedTask, input);
                } catch(NumberFormatException | NullPointerException | ZranExceptions e){
                    System.out.printf((outputFormat) + "%n", ZranErrorMessages.INVALID_TASK_INDEX_DELETE.message);
                }
            } else if(input.equals(LIST_TASK_COMMAND)){
                echo(items);
            }  else if (input.startsWith(USER_HELP_COMMAND)) {
                System.out.printf((outputFormat) + "%n",
                        "Help is here! :) \n" +
                                "    Listed below are the valid commands: \n" +
                                "    - mark \n    - unmark \n " +
                                "   - todo \n    - deadline \n    - event \n" +
                                "    For the formats below, replace *field* with your input \n" +
                                "    MARK: mark *existing task index* \n" +
                                "    UNMARK: unmark *existing task index* \n" +
                                "    TODO: todo *task name* \n" +
                                "    DEADLINE: deadline *task name* /by *deadline* \n" +
                                "    EVENT: event *event name* /from *start date* /to *end date*" +
                                "    DELETE: delete *existing task index* \n");
            } else {
                try{
                    task = addTaskByType(input);
                    items.add(task);
                    echo(items, task, input);
                }
                catch(ZranExceptions e){
                    System.out.println();
                    System.out.printf((outputFormat) + "%n", e.getMessage());
                }
            }
        }
        System.out.printf((outputFormat) + "%n", "Goodbye <3 have a great day ahead!");
    }

    // The function 'addTaskByType' is used to add an item into the task list by their task type
    public static Task addTaskByType(String input) throws ZranExceptions {
        Task task = null;
        if(input.startsWith(TODO_TASK_COMMAND)) {
            int todoIndex = input.indexOf(TODO_TASK_COMMAND);
            String description = input.substring(todoIndex + TODO_TASK_COMMAND.length()).trim();
            if(description.isEmpty()){
                throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
            }
            task = new ToDos(description);
        } else if(input.startsWith(DEADLINE_TASK_COMMAND)){
            int byIndex = input.indexOf(DEADLINE_DATE_COMMAND);
            if (byIndex == -1) {
                throw new ZranExceptions(ZranErrorMessages.INVALID_DEADLINE_FORMAT.message);
            }
            String description = input.substring(DEADLINE_TASK_COMMAND.length(), byIndex).trim();
            if(description.isEmpty()){
                throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
            }
            String by = input.substring(byIndex + DEADLINE_DATE_COMMAND.length()).trim();
            if(by.isEmpty()){
                throw new ZranExceptions(ZranErrorMessages.EMPTY_DEADLINE.message);
            }
            task = new Deadline(description, by);
        } else if (input.startsWith(EVENT_TASK_COMMAND)) {
            int fromIndex = input.indexOf(EVENT_TASK_START);
            int toIndex = input.indexOf(EVENT_TASK_END);
            if (fromIndex == -1 || toIndex == -1) {
                throw new ZranExceptions(ZranErrorMessages.INVALID_EVENT_FORMAT.message);
            }
            String description = input.substring(EVENT_TASK_COMMAND.length(), fromIndex).trim();
            if(description.isEmpty()){
                throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
            }
            String from = input.substring(fromIndex + EVENT_TASK_START.length(), toIndex).trim();
            String to = input.substring(toIndex + EVENT_TASK_END.length()).trim();
            if(from.isEmpty() || to.isEmpty()){
                throw new ZranExceptions(ZranErrorMessages.EMPTY_EVENT_DURATION.message);
            }
            task = new Event(description, from, to);
        } else {
            throw new ZranExceptions(ZranErrorMessages.UNRECOGNISED_COMMAND.message);
        }

        return task;
    }

    public static Task deleteTask(ArrayList<Task> items, int deleteIndex) throws ZranExceptions{ //change to string or no

        Task deletedTask = null;
//        try{
            if (deleteIndex<0 || deleteIndex >= items.size()){
                throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX_DELETE.message);
            }
            deletedTask = items.remove(deleteIndex);
//        } catch(ZranExceptions e) {
//            System.out.println();
//            System.out.printf((outputFormat) + "%n", e.getMessage());
//        }
        return deletedTask;
    }

}
