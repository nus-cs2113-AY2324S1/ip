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
    private static final String FILE_PATH = "data\\";
    private static final String FILE_NAME = "data.txt";
    private static final String TODO_USAGE_MSG = "Usage: todo <description>";
    private static final String EVENT_USAGE_MSG = "Usage: event <description> /from <start> /to <end>";
    private static final String DEADLINE_USAGE_MSG = "Usage: deadline <description> /by <end>";
    private File file;
    private ArrayList<Task> taskList;
    private Scanner input;
    

    public Bot() {
        this.taskList = new ArrayList<Task>();
        this.input = new Scanner(System.in);
        this.file = new File(FILE_PATH + FILE_NAME);
    }

    /**
     * Mark or unmark task status and updates in the ArrayList
     * 
     * @param position task number on the list.
     * @param status task marked as done or not done.
     */
    public void markList(String position, boolean status) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = taskList.get(index);
            task.setCompleted(status);
            taskList.set(index, task);
            if(task.isCompleted()) {
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("No Task found");
        } catch (NumberFormatException exception) {
            System.out.println("Index cannot be empty");
        }
    }

    /**
     * Print all the tasks stored in the List
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

    public static void printListLength(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " task in the list");
    }

    public void printTask(Task task, boolean isDelete) {
        if(isDelete) {
            System.out.println("Noted. I've removed this task:");
        } else {
            System.out.println("Got it. I've added this task:");
        }
        System.out.print("  ");
        System.out.println(task);
        printListLength(taskList);
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }

    public void printBotMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE_DIVIDER);
    }

    public void addToList(Task task) {
        taskList.add(task);
        printTask(task, false);
    }

    public void checkDescription(String description) {
        if(description.isBlank()) {
            throw new IllegalArgumentException("Description Blank");
        }
    }

    public void checkBy(String by) {
        if(by.isBlank()) {
            throw new IllegalArgumentException("Description Blank");
        }
    }

    public void checkDeadlineArgs(String description, String by) {
        if(description.isBlank() && by.isBlank()) {
            throw new ArrayIndexOutOfBoundsException(0);
        }
        checkDescription(description);
        checkBy(by);
    }

    public void addDeadlineToList(String arguments) {
        try {
            if(arguments.isBlank()) {
                throw new IllegalArgumentException("Description Blank");
            }
            String[] argumentsList = arguments.split("/by");
            String description = argumentsList[0].trim();
            String by = argumentsList[1].trim();
            checkDeadlineArgs(description, by);
            addToList(new Deadline(description, by));
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Description and time must not be empty\n" + DEADLINE_USAGE_MSG);
        } catch (IllegalArgumentException exception) {
            if(exception.getMessage().equals("Description Blank")) {
                System.out.println("Description must not be blank");
            } else {
                System.out.println("By must not be empty");
            }
            System.out.println(DEADLINE_USAGE_MSG);
        }
    }
    
    public void checkFrom(String from) {
        if(from.isBlank()) {
            throw new IllegalArgumentException("From Blank");
        }
    }

    public void checkTo(String to) {
        if(to.isBlank()) {
            throw new IllegalArgumentException("To Blank");
        }
    }

    public void checkEventArgs(String description, String from, String to) {
        checkDescription(description);
        checkFrom(from);
        checkTo(to);
    }

    public void addEventToList(String arguments) {
        try {
            if(arguments.isBlank()) {
                throw new IllegalArgumentException("Description is empty");
            }
            String[] argumentsList = arguments.split("/from");
            String description = argumentsList[0].trim();
            argumentsList = argumentsList[1].split("/to");
            String from = argumentsList[0].trim();
            String to = argumentsList[1].trim();
            checkEventArgs(description, from, to);
            addToList(new Event(description, from, to));
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("To must not be empty\n" + EVENT_USAGE_MSG);
        } catch (IllegalArgumentException exception) {
            if(exception.getMessage().equals("Description Blank")) {
                System.out.println("Description must not be empty");
            } else if(exception.getMessage().equals("From Blank")) {
                System.out.println("From must not be be empty");
            } else {
                System.out.println("To must not be empty");
            }
            System.out.println(EVENT_USAGE_MSG);
        }
    }

    public void addToDoToList(String arguments) {
        try {
            if(arguments.isBlank()){
                throw new IllegalArgumentException("Description is empty");
            } else {
                addToList(new ToDo(arguments));
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("The description of a todo cannot be empty.\n" + TODO_USAGE_MSG);
        }
    }
    
    public void deleteTaskFromList(String arguments) {
        try {
            if(arguments.isBlank()){
                throw new IllegalArgumentException("Index cannot be empty");
            } else {
                int index = Integer.parseInt(arguments) - 1;
                Task deletedTask = taskList.get(index);
                taskList.remove(index);
                printTask(deletedTask, true);
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Task does not exist");
        } catch (IllegalArgumentException exception) {
            System.out.println("Index cannot be empty");
        }
    }

    public void writeToFile(String data) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH + FILE_NAME, true);
            fw.write(data + "\n");
            fw.close();
        } catch (IOException exception) {
            System.out.println("Cannot write to file");
        }
    }

    public void refreshData() {
        try {
            PrintWriter writer = new PrintWriter(FILE_PATH +FILE_NAME);
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }
        Iterator<Task> taskListIter = taskList.iterator();
        while(taskListIter.hasNext()) {
            Task task = taskListIter.next();
            if(task.getClass().getSimpleName().equals("ToDo")) {
                ToDo todo = (ToDo)task;
                writeToFile(todo.toFile());
            } else if(task.getClass().getSimpleName().equals("Deadline")) {
                Deadline deadline = (Deadline)task;
                writeToFile(deadline.toFile());
            } else {
                Event event = (Event)task;
                writeToFile(event.toFile());
            }
        }
    }
    
    public void dataToList(Task task, String isCompleted) {
        if(isCompleted.equals("1")) {
            task.setCompleted(true);
        } else {
            task.setCompleted(false);
        }
        taskList.add(task);
    }

    public void getFileData() {
        try{
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String[] dataSplit = (reader.nextLine()).split(",");
                String isCompleted = dataSplit[1];
                if(dataSplit[0].equals("T")) {
                    ToDo todoTask = new ToDo(dataSplit[2]);
                    dataToList(todoTask, isCompleted);
                } else if(dataSplit[0].equals("D")) {
                    Deadline deadlineTask = new Deadline(dataSplit[2], dataSplit[3]);
                    dataToList(deadlineTask, isCompleted);
                } else {
                    Event eventTask = new Event(dataSplit[2], dataSplit[3], dataSplit[4]);
                    dataToList(eventTask, isCompleted);
                }
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        } catch (ArrayIndexOutOfBoundsException exception) {
            
        }
    }
    
    public void importToArrayList() {
        try {
            File directory = new File(FILE_PATH);
            directory.mkdir();
            if(file.exists()) {
                getFileData();
            } else {
                file.createNewFile();
            }
        } catch (IOException exception) {
            System.out.println("An error occurred.");
        }
    }

    public void runBot() {
        String userInput, command, arguments;
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
                deleteTaskFromList(arguments);
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
}