/**
 * Duke is a command-line task management robot that allows users to
 * add, mark, unmark, delete, and search for tasks. It also supports
 * different types of tasks such as Todo, Deadline, and Event.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-09-30
 */

package Duke;

import Duke.dealWithFiles.GetFromFile;
import Duke.dealWithFiles.SaveToFile;
import Duke.inputProcess.TaskList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {

    private static GetFromFile getTasks;
    private static SaveToFile saveTasks;
    private static TaskList tasks;
    private static Ui ui;
    private String filePath;

    public Duke(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        getTasks = new GetFromFile(filePath);
        saveTasks = new SaveToFile(filePath);
        tasks = new TaskList();
    }

    public static void main(String[] args) {
        new Duke("Duke.txt");
        try{
            getTasks.getFromTextFile(tasks);
        } catch(FileNotFoundException e){
            System.out.println("\tOOPS!!! File not found.");
        }
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        String eventTime = "";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        while (!userInput.equals("bye")) {
            String command = "list";
            if (!userInput.equals("list")){
                try{
                    command = userInput.split(" ", 2)[0].toLowerCase();
                    userInput = userInput.split(" ", 2)[1];
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                    userInput = in.nextLine();
                    continue;
                }
            }
            switch (command){
            case "list":
                tasks.printList();
                System.out.println("\tNow you have " + tasks.size() + " in the list");
                break;
            case "unmark":
                tasks.unmark((Integer.parseInt(userInput) - 1));
                break;
            case "mark":
                tasks.mark((Integer.parseInt(userInput) - 1));
                break;
            case "deadline":
                try {
                    try {
                        LocalDateTime taskDeadline = LocalDateTime.parse(userInput.split("/by ", 2)[1], df);
                        userInput = userInput.split("/", 2)[0];
                        tasks.addDeadline(userInput, taskDeadline);
                        System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.get(tasks.size() - 1) + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                    } catch (DateTimeParseException e){
                        System.out.println("\tThe input format for deadline event need to be \"deadline deadlineEvent /by dd/MM/yyyy HHmm\"");
                    }
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The deadline need to separated by \"/by\"");
                }
                break;
            case "event":
                LocalDateTime start;
                LocalDateTime end;
                try {
                    try {
                        eventTime = userInput.split("/from ", 2)[1];
                        start = LocalDateTime.parse(eventTime.split(" /to ", 2)[0], df);
                        end = LocalDateTime.parse(eventTime.split(" /to ", 2)[1], df);
                    } catch (DateTimeParseException e) {
                        System.out.println("\tThe input format for event need to be \"event eventName /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\"");
                        break;
                    }
                    if (end.isBefore(start)){
                        System.out.println("\tStart time cannot be later than end time");
                        break;
                    }
                    userInput = userInput.split("/",2)[0];
                    tasks.addEvent(userInput, start, end);
                    System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.get(tasks.size() - 1) + "\n\tNow you have "+ tasks.size() + " tasks in the list.");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The event timing need to separated by \"/from\" and \"/to\"" );
                }
                break;
            case "todo":
                tasks.addTodo(userInput);
                System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.get(tasks.size() - 1) + "\n\tNow you have "+ tasks.size() + " tasks in the list.");
                break;
            case "delete":
                try {
                    tasks.get(Integer.parseInt(userInput) - 1);
                    tasks.deleteTask(Integer.parseInt(userInput) - 1);
                } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
                    System.out.println("OOPS!!! Need to specify which task want to delete");
                }
                break;
            case "find":
                tasks.find(userInput);
                break;
            default:
                System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            try {
                saveTasks.saveToTextFile(tasks);
            } catch(IOException e){
                System.out.println("Something wrong to save the file");
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon! ");
    }
}