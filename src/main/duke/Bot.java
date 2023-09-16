package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Bot {
    private static final String BOT_NAME = "JS";
    private static final String LINE_DIVIDER = "----------------------------------------";
    private static final String FILE_PATH = "src\\main\\data\\data.txt";
    private File f = new File(FILE_PATH);
    private ArrayList<Task> taskList;
    private Scanner input;
    

    public Bot() {
        this.taskList = new ArrayList<Task>();
        this.input = new Scanner(System.in);
    }

    /**
     * Mark or unmark task status and updates in the ArrayList
     * 
     * @param taskList contains all the tasks stored.
     * @param position task number on the list.
     * @param status task marked as done or not done.
     */
    public void markList(String position, boolean status) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task event = taskList.get(index);
            event.setCompleted(status);
            taskList.set(index, event);
            if(event.isCompleted()) {
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(event);
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(event);
            }
        } catch (Exception exception) { // Need to change to specific error
            System.out.println("Invalid Input");
        }
    }

    /**
     * Print all the tasks stored
     * 
     * @param taskList contains all the tasks stored.
     */
    public static void printList(ArrayList<Task> taskList) {
        System.out.println("Here are the task in your list:");
        Iterator<Task> taskListIter = taskList.iterator();
        for(int i = 1; taskListIter.hasNext() ; i++) {
            Task event = taskListIter.next();
            System.out.println(i + "." + event);
        }
    }

    /**
     * Prints the length of the list in a fixed format
     * 
     * @param taskList contains all the tasks stored.
     */
    public static void printListLength(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " task in the list");
    }

    /**
     * Prints out the new task added
     * 
     * @param taskList contains all the tasks stored.
     * @param task new task that is created
     */
    public void printNewTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        System.out.println(task);
        printListLength(taskList);
    }

    public void printDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.print("  ");
        System.out.println(task);
        printListLength(taskList);
    }

    public void printBotMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Add deadline to the ArrayList
     * 
     * @param taskList contains all the tasks stored.
     * @param arugments arguments of the commands that is used
     */
    public void addDeadlineToList(String arguments) {
        try {
            String[] argumentsList = arguments.split(" /by ");
            Deadline newDeadline = new Deadline(argumentsList[0], argumentsList[1]);
            if(argumentsList[0].isBlank() && argumentsList[1].isBlank()) {
                throw new ArrayIndexOutOfBoundsException(0);
            }
            if(argumentsList[0].isBlank()) {
                throw new IllegalArgumentException("Description Blank");
            }
            if(argumentsList[1].isBlank()) {
                throw new IllegalArgumentException("Time Blank");
            }
            taskList.add(newDeadline);
            addDeadlineToFile(newDeadline);
            printNewTask(newDeadline);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Description and time must not be empty");
            System.out.println("Usage: deadline <description> /by <end>");
        } catch (IllegalArgumentException exception) {
            if(exception.getMessage().equals("Description Blank")) {
                System.out.println("Description must not be blank");
            } else {
                System.out.println("Time must not be empty");
            }
            System.out.println("Usage: deadline <description> /by <end>");
        }
    }

    /**
     * Add Event to the ArrayList
     * 
     * @param taskList contains all the tasks stored.
     * @param arugments arguments of the commands that is used
     */
    public void addEventToList(String arguments) {
        try {
            if(arguments.isBlank()) {
                throw new IllegalArgumentException("Description is empty");
            }
            String[] argumentsList = arguments.split("/from");
            String description = argumentsList[0].trim();
            argumentsList = argumentsList[1].split("/to");
            if(description.isBlank()) {
                throw new IllegalArgumentException("Description Blank");
            }
            if(argumentsList[0].trim().isBlank()) {
                throw new IllegalArgumentException("From Blank");
            }
            if(argumentsList[1].trim().isBlank()) {
                throw new IllegalArgumentException("To Blank");
            }
            Event newEvent = new Event(description, argumentsList[0].trim(), argumentsList[1].trim());
            taskList.add(newEvent);
            addEventToFile(newEvent);
            printNewTask(newEvent);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("To must not be empty");
            System.out.println("Usage: event <description> /from <start> /to <end>");
        } catch (IllegalArgumentException exception) {
            if(exception.getMessage().equals("Description Blank")) {
                System.out.println("Description must not be empty");
            } else if(exception.getMessage().equals("From Blank")) {
                System.out.println("From must not be be empty");
            } else {
                System.out.println("To must not be empty");
            }
            System.out.println("Usage: event <description> /from <start> /to <end>");
        }
    }

    /**
     * Add ToDo to the ArrayList
     * 
     * @param taskList contains all the tasks stored.
     * @param arugments arguments of the commands that is used
     */
    public void addToDoToList(String arguments) {
        try {
            if(arguments.isBlank()){
                throw new IllegalArgumentException("Description is empty");
            } else {
                ToDo newToDo = new ToDo(arguments);
                taskList.add(newToDo);
                addToDoToFile(newToDo);
                printNewTask(newToDo);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
            System.out.println("Usage: todo <description>");
        }
    }

    /**
     * Delete Task from Task List
     * 
     * @param taskList contains all the tasks stored.
     * @param arugments arguments of the commands that is used
     */
    public void deleteTask(String arguments) {
        try {
            if(arguments.isBlank()){
                throw new IllegalArgumentException("Index cannot be empty");
            } else {
                int index = Integer.parseInt(arguments) - 1;
                Task deletedTask = taskList.get(index);
                taskList.remove(index);
                printDeleteTask(deletedTask);
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Task does not exist");
        } catch (IllegalArgumentException exception) {
            System.out.println("Index cannot be empty");
        }
    }

    public void importToArrayList() {
        try {
            if(f.exists()) {
                fileToArrayList(taskList, f);
            } else {
                f.createNewFile();
            }
        } catch (IOException exception) {
            System.out.println("An error occurred.");
        }
    }

    public void refreshData() {
        try {
            PrintWriter writer = new PrintWriter(FILE_PATH);
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }
        Iterator<Task> taskListIter = taskList.iterator();
        while(taskListIter.hasNext()) {
            Task task = taskListIter.next();
            if(task.getClass().getSimpleName().equals("ToDo")) {
                ToDo todo = (ToDo)task;
                addToDoToFile(todo);
            } else if(task.getClass().getSimpleName().equals("Deadline")) {
                Deadline deadline = (Deadline)task;
                addDeadlineToFile(deadline);
            } else {
                Event event = (Event)task;
                addEventToFile(event);
            }
        }
    }

    /**
     * Runs the chatbot
     * 
     * @param input scanner object that is used to get the user input
     * @param taskList contains all the tasks stored.
     */
    public void runBot() {
        String userInput, command, arguments;
        userInput = command = arguments = "";
        importToArrayList();
        printBotMessage();
        do {
            arguments = "";
            userInput = this.input.nextLine();
            command = userInput.split(" ", 2)[0];
            if(userInput.split(" ", 2).length != 1) {
                arguments = userInput.split(" ", 2)[1];
            }
            switch (command) {
            case "list":
                System.out.println(LINE_DIVIDER);
                printList(this.taskList);
                break;
            case "bye":
                break;
            case "todo":
                System.out.println(LINE_DIVIDER);
                addToDoToList(arguments);
                break;
            case "event":
                System.out.println(LINE_DIVIDER);
                addEventToList(arguments);
                break;
            case "deadline":
                System.out.println(LINE_DIVIDER);
                addDeadlineToList(arguments);
                break;
            case "unmark":
                System.out.println(LINE_DIVIDER);
                markList(arguments, false);
                break;
            case "mark":
                System.out.println(LINE_DIVIDER);
                markList(arguments, true);
                break;
            case "delete":
                System.out.println(LINE_DIVIDER);
                deleteTask(arguments);
                break;
            default:
                System.out.println(LINE_DIVIDER);
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(LINE_DIVIDER);
            refreshData();
        } while (!(command.equals("bye")));
        input.close();
        printByeMessage();
    }

    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }

    public static void addToDoToFile(ToDo newToDo) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String format = "T," + (newToDo.isCompleted() ? "1" : "0") + "," + newToDo.getName();
            fw.write(format + "\n");
            fw.close();
        } catch (IOException exception) {
            System.out.println("Cannot write to file");
        }
    }

    public static void addDeadlineToFile(Deadline newDeadline) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String format = "D," + (newDeadline.isCompleted() ? "1" : "0") + "," + newDeadline.getName() + "," + newDeadline.getBy();
            fw.write(format + "\n");
            fw.close();
        } catch (IOException exception) {
            System.out.println("Cannot write to file");
        }
    }

    public static void addEventToFile(Event newEvent) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String format = "E," + (newEvent.isCompleted() ? "1" : "0") + "," + newEvent.getName() + "," + newEvent.getFrom()  + "," + newEvent.getTo();
            fw.write(format + "\n");
            fw.close();
        } catch (IOException exception) {
            System.out.println("Cannot write to file");
        }
    }
  
     public static void fileToArrayList(ArrayList<Task> taskList, File f) {
        try{
            Scanner reader = new Scanner(f);
            while(reader.hasNextLine()) {
                String[] dataSplit = (reader.nextLine()).split(",");
                if(dataSplit[0].equals("T")) {
                    ToDo todoTask = new ToDo(dataSplit[2]);
                    if(dataSplit[1].equals("1")) {
                        todoTask.setCompleted(true);
                    } else {
                        todoTask.setCompleted(false);
                    }
                    taskList.add(todoTask);
                } else if(dataSplit[0].equals("D")) {
                    Deadline deadlineTask = new Deadline(dataSplit[2], dataSplit[3]);
                    if(dataSplit[1].equals("1")) {
                        deadlineTask.setCompleted(true);
                    } else {
                        deadlineTask.setCompleted(false);
                    }
                    taskList.add(deadlineTask);
                } else {
                    Event eventTask = new Event(dataSplit[2], dataSplit[3], dataSplit[4]);
                    if(dataSplit[1].equals("1")) {
                        eventTask.setCompleted(true);
                    } else {
                        eventTask.setCompleted(false);
                    }
                    taskList.add(eventTask);
                }
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Error");
        }
    }
}