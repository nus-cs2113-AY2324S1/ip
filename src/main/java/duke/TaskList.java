package duke;

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

    public void addTodoInList(String[] arguments) throws DukeException{
        if (arguments.length == 0){ // empty argument
            throw new DukeException("The description of a todo cannot be empty!");
        }
        String taskDescription = String.join(" ", arguments);
        ToDo newToDo = new ToDo(taskDescription);
        this.TASKS.add(newToDo);
        printRemark(newToDo, "Done, I've added this task: ");
    }

    public void addDeadlineInList(String[] arguments) throws DukeException{
        try{
            String argumentsString = String.join(" ", arguments);
            String[] argumentsList = argumentsString.split(" /by ");
            String deadlineDescription = argumentsList[FIRST_INDEX];
            String deadlineEndTime = argumentsList[SECOND_INDEX];
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineEndTime);
            this.TASKS.add(newDeadline);
            printRemark(newDeadline, "Done, I've added this task: ");
        } catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Insufficient arguments provided, try this (deadline submission /by date)");
        }

    }

    public void addEventInList(String[] arguments) throws DukeException{
        try {
            String argumentsString = String.join(" ", arguments);
            String[] argumentsList = argumentsString.split(" /from ");
            String eventDescription = argumentsList[FIRST_INDEX];
            argumentsList = argumentsList[SECOND_INDEX].split(" /to ");
            String eventStartTime = argumentsList[FIRST_INDEX];
            String eventEndTime = argumentsList[SECOND_INDEX];
            Event newEvent = new Event(eventDescription, eventStartTime, eventEndTime);
            this.TASKS.add(newEvent);
            printRemark(newEvent, "Done, I've added this task: ");
        } catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Insufficient arguments provided, try this (event tiktok hackathon /from date /to date)");
        }
    }

    /* Marks the task from {@code arguments} as complete */
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

    /* Marks the task from {@code arguments} as incomplete */
    public void unmarkTaskIncomplete(String[] arguments) throws DukeException{
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
