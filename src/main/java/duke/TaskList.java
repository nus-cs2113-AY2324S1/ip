package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    public static int FIRST_INDEX=0;
    public static int SECOND_INDEX=1;
    protected ArrayList<Task> TASKS;

    public TaskList() {
        this.TASKS = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> AL) {
        this.TASKS = AL;
    }

    public ArrayList<Task> getArrayList(){
        return this.TASKS;
    }

    /**
     * Adds a new ToDo task to the task list with the specified arguments.
     * <p>
     * This method creates a new ToDo object with the description provided in the arguments array.
     * If no argument is provided for the description, it throws a DukeException.
     * After the creation of the ToDo object, it is added to the task list, and a remark is printed indicating the task has been added.
     * </p>
     *
     * @param arguments An array of Strings containing the description for the todo to be added.
     * It can contain multiple elements, which will be joined to form the task description.
     * @throws DukeException If the arguments provided are empty, indicating that no description has been provided for the todo.
     */
    public void addTodoInList(String[] arguments) throws DukeException{
        if (arguments.length == 0){ // empty argument
            throw new DukeException("The description of a todo cannot be empty!");
        }
        String taskDescription = String.join(" ", arguments);
        ToDo newToDo = new ToDo(taskDescription);
        this.TASKS.add(newToDo);
        printRemark(newToDo, "Done, I've added this task: ");
    }

    /**
     * Adds a new Deadline task to the task list with the specified arguments.
     * <p>
     * This method creates a new Deadline object with the description and end time provided in the arguments array.
     * The expected format for the arguments is: [deadlineDescription] /by [deadlineEndTime], and the expected
     * datetime format is "yyyy-MM-dd HHmm".
     * After the creation of the Deadline object, it is added to the task list, and a remark is printed indicating
     * the task has been added.
     * </p>
     * <p>
     * If the arguments are not in the expected format or are insufficient, or if the datetime is not in the expected format,
     * it throws a DukeException with an appropriate message guiding the user to provide input in the correct format.
     * </p>
     *
     * @param arguments An array of Strings containing the description and end time for the deadline to be added.
     * @throws DukeException If the arguments provided are insufficient, not in the expected format, or if the datetime is in an invalid format.
     */
    public void addDeadlineInList(String[] arguments) throws DukeException{
        try{
            String argumentsString = String.join(" ", arguments);
            String[] argumentsList = argumentsString.split(" /by ");
            String deadlineDescription = argumentsList[FIRST_INDEX];
            String deadlineEndTime = argumentsList[SECOND_INDEX];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime deadlineEndParsed = LocalDateTime.parse(deadlineEndTime, formatter);
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineEndParsed);
            this.TASKS.add(newDeadline);
            printRemark(newDeadline, "Done, I've added this task: ");
        } catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Insufficient arguments provided, try this (deadline submission /by 20-10-15 1800)");
        } catch(DateTimeParseException dtEx) {
            throw new DukeException("Invalid datetime format, try this (deadline submission /by 2023-10-15 1800)");
        }

    }

    /**
     * Adds a new Event task to the task list with the specified arguments.
     * <p>
     * The method creates a new Event object with description, start time, and end time provided in the arguments array.
     * The expected format for the arguments is: [eventDescription] /from [eventStartTime] /to [eventEndTime].
     * After the creation of the Event object, it is added to the task list and a remark is printed indicating the task has been added.
     * If the arguments are not in the expected format or are insufficient, it throws a DukeException.
     * </p>
     *
     * @param arguments An array of Strings containing the description, start time, and end time for the event to be added.
     * @throws DukeException If the arguments provided are insufficient or not in the expected format, or if the datetime is in an invalid format.
     */
    public void addEventInList(String[] arguments) throws DukeException{
        try {
            String argumentsString = String.join(" ", arguments);
            String[] argumentsList = argumentsString.split(" /from ");
            String eventDescription = argumentsList[FIRST_INDEX];
            argumentsList = argumentsList[SECOND_INDEX].split(" /to ");
            String eventStartTime = argumentsList[FIRST_INDEX];
            String eventEndTime = argumentsList[SECOND_INDEX];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime eventStartParsed = LocalDateTime.parse(eventStartTime, formatter);
            LocalDateTime eventEndParsed = LocalDateTime.parse(eventEndTime, formatter);
            Event newEvent = new Event(eventDescription, eventStartParsed, eventEndParsed);
            this.TASKS.add(newEvent);
            printRemark(newEvent, "Done, I've added this task: ");
        } catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Insufficient arguments provided, try this (event tiktok hackathon /from DATETIME /to DATETIME)");
        } catch(DateTimeParseException dtEx) {
            throw new DukeException("Invalid datetime format, try this (event tiktok hackathon /from 2023-10-15 1800 /to 2023-10-17 0900)");
        }
    }

    /**
     * Marks a task in the task list as complete based on the provided index.
     * <p>
     * This method attempts to parse the first element of the provided arguments array as an integer
     * and uses it as an index to access and mark the task at that position in the task list as complete.
     * After marking, it prints a message indicating that the task has been marked as done along with the task details.
     * If the parsed integer is not a valid index, or if the first element is not an integer, it throws a DukeException.
     * </p>
     *
     * @param arguments An array of Strings containing the index of the task to be marked as its first element.
     * @throws DukeException If the provided index is not a valid integer, or if it's out of the bounds of the task list.
     */
    public void markTaskComplete(String[] arguments) throws DukeException{
        try{
            int taskIndex = Integer.parseInt(arguments[FIRST_INDEX]);
            Task taskToMark = this.TASKS.get(taskIndex-1);
            taskToMark.markAsDone();
            System.out.println("Marked this task as done:");
            System.out.println(taskToMark);
        } catch(IndexOutOfBoundsException indexEx){
            throw new DukeException("Invalid index bro...");
        } catch(NumberFormatException numEx){
            throw new DukeException("That's not an index bro..");
        }
    }

    /**
     * Marks a task in the task list as incomplete based on the provided index.
     * <p>
     * This method attempts to parse the first element of the provided arguments array as an integer
     * and uses it as an index to access and unmark the task at that position in the task list. After unmarking,
     * it prints a message indicating that the task has been marked as undone along with the task details.
     * If the parsed integer is not a valid index, or if the first element is not an integer, it throws a DukeException.
     * </p>
     *
     * @param arguments An array of Strings containing the index of the task to be unmarked as its first element.
     * @throws DukeException If the provided index is not a valid integer, or if it's out of the bounds of the task list.
     */
    public void markTaskIncomplete(String[] arguments) throws DukeException{
        try{
            int taskIndex = Integer.parseInt(arguments[FIRST_INDEX]);
            Task taskToUnmark = this.TASKS.get(taskIndex-1);
            taskToUnmark.markAsUndone();
            System.out.println("Marked this task as undone:");
            System.out.println(taskToUnmark);
        } catch(IndexOutOfBoundsException indexEx){
            throw new DukeException("Invalid index bro...");
        } catch(NumberFormatException numEx){
            throw new DukeException("That's not an index bro..");
        }
    }

    /**
     * Deletes a task from the task list based on the provided index.
     * <p>
     * This method attempts to parse the first element of the provided arguments array as an integer to use it as an index to delete
     * the task at that position in the task list. After deletion, it prints a remark with the deleted task.
     * If the parsed integer is not a valid index or if the first element is not an integer, it throws a DukeException.
     * </p>
     *
     * @param arguments An array of Strings containing the index of the task to be deleted as its first element.
     * @throws DukeException If the provided index is not a valid integer, or if it's out of the bounds of the task list.
     */
    public void deleteTaskInList(String[] arguments) throws DukeException{
        try {
            int taskIndex = Integer.parseInt(arguments[FIRST_INDEX]);
            Task taskToDelete = this.TASKS.get(taskIndex-1);
            this.TASKS.remove(taskToDelete);
            printRemark(taskToDelete, "Done, I've deleted this task: ");
        } catch(IndexOutOfBoundsException indexEx){
            throw new DukeException("Invalid index bro...");
        } catch(NumberFormatException numEx){
            throw new DukeException("That's not an index bro..");
        }
    }

    /**
     * Searches and prints the tasks in the task list that contain the provided search term.
     * <p>
     * The method prints the matching tasks line by line with their index in the task list.
     * The search is case-insensitive and looks for the search term in the task descriptions.
     * If no arguments are provided, it throws a DukeException indicating that a term to find is required.
     * </p>
     * <p>
     * The search term is constructed by joining the elements of the provided arguments array with a space.
     * </p>
     *
     * @param arguments An array of Strings containing the words of the search term.
     * @throws DukeException If no arguments are provided to form a search term.
     */
    public void findTasksInList(String[] arguments) throws DukeException{
        if (arguments.length == 0){ // empty argument
            throw new DukeException("Provide a term to find bro....");
        }
        System.out.println("Here are the matching tasks in the list: ");
        String descriptionSubstring = String.join(" ", arguments);
        for (int i=0;i<this.TASKS.size();i++){
            Task task = TASKS.get(i);
            String taskDescription = task.getDescription().toLowerCase();
            if (taskDescription.contains(descriptionSubstring.toLowerCase())){
                System.out.println(i+1 + "." + task.toString());
            }
        }
    }

    public void printList(){
        if (!TASKS.isEmpty()){
            for (int i = 0; i < TASKS.size(); i++) {
                Task task = TASKS.get(i);
                System.out.println(i+1 + "." + task.toString());
            }
        }else{
            System.out.println("No data here bro...");
        }
    }

    public void printRemark(Task task, String remark){
        System.out.println(remark);
        System.out.println(task);
        System.out.println("Now you have " + TASKS.size() + " tasks in the list");
    }
}
