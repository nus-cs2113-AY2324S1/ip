//import scanner
import java.util.Scanner;
//import ArrayList
import java.util.ArrayList;
//import File I/O class
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Import packages and classes
import Exceptions.DukeException;
import Storage.Storage;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import Task.TaskList;



public class Botbot {
    public static String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    //create arraylist
    public static TaskList list = new TaskList();

    //method to identify command
    public static String identifyCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            return "bye";
        } else if (command.equals("list")){
            return "list";
        } else if (command.contains("mark")){
            return "mark";
        } else if (command.contains("todo")){
            return "todo";
        } else if (command.contains("deadline")){
            return "deadline";
        } else if (command.contains("event")){
            return "event";
        } else if (command.contains("delete")){
            return "delete";
        }else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :<");
        }
    }

    //method to mark or unmark task
    public static void markUnmarkTask(String command){
        int itemIndex; //int to store index of item to mark/unmark
        //for command unmark
        if(command.contains("unmark")){
            //find the given index to unmark
            itemIndex = Integer.parseInt(command.substring(7))-1;
            //if given index is out of range
            if (itemIndex>=list.size()){
                System.out.println("invalid list item");
            } else {
                list.get(itemIndex).unmark();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(list.get(itemIndex));
                System.out.println(line);
            }
        } else { //for command mark
            //find the given index to mark
            itemIndex = Integer.parseInt(command.substring(5))-1;
            //if given index is out of range
            if (itemIndex>=list.size()){
                System.out.println("invalid list item");
                return;
            }else {
                list.get(itemIndex).mark();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list.get(itemIndex));
                System.out.println(line);
            }
        }
    }


    //method to delete tasks
    public static void deleteTasks(String command) throws DukeException {
        int taskIndex = Integer.parseInt(command.substring(7))-1;
        if (taskIndex>=list.size()) {
            throw new DukeException("Invalid task item. Check item number again~");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(taskIndex));
        list.remove(taskIndex);
        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
        System.out.println(line);
    }
//
//    //method to save list to file
//    public static void saveListToFile() throws IOException {
//        try (FileWriter fileWriter = new FileWriter("data/botbot.txt")){
//            for (int i = 0; i<list.size(); i++) {
//                fileWriter.write(list.get(i).toString() + "\n");
//            }
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//    }
//
//    //method to extract todo task
//    public static void extractTodo(String savedTask){
//        TaskList.createTodoTasks(savedTask.substring(8));
//    }
//
//    //method to extract deadline task
//    public static void extractDeadline(String savedTask) throws DukeException {
//        String[] parts = savedTask.split(" \\(by: " );
//        String task = parts[0].substring(8);
//        String deadline = parts[1].substring(0, parts[1].length() - 1);
//        if (task.isEmpty() || deadline.isEmpty()) {
//                throw new DukeException("Loaded task or deadline is empty... Please check your saved list again~");
//        } else {
//            //instantiate new deadline object
//            Deadline deadlineTask = new Deadline(task, deadline);
//            //add to array
//            list.add(deadlineTask);
//        }
//    }
//
//    //method to extract event task
//    public static void extractEvent(String savedTask) throws DukeException {
//        String[] parts = savedTask.split(" \\(");
//        String task = parts[0].substring(8);
//        String[] timeSplit = parts[1].split(" to: ");
//        String from = timeSplit[0].substring(6);
//        String to = timeSplit[1].substring(0, timeSplit[1].length() - 1);
//        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
//            throw new DukeException("Loaded task or time period is empty... Please check your saved list again~");
//        } else {
//            //instantiate new event object
//            Event eventTask = new Event(task, from, to);
//            //add to array
//            list.add(eventTask);
//        }
//    }
//
//    // method to load list from file
//    public static void loadListFromFile() throws IOException, DukeException {
//        File file = new File("data/botbot.txt");
//        if (!file.exists()){
//            try {
//                boolean createdDirectory = file.mkdirs();
//                boolean createdFile = file.createNewFile();
//            } catch (IOException e){
//                System.out.println("Something went wrong: " + e.getMessage());
//            }
//        } else {
//            Scanner fileScanner = new Scanner(file);
//            while (fileScanner.hasNext()) {
//                String savedTask = fileScanner.nextLine();
//                if (savedTask.contains("T")) {
//                    extractTodo(savedTask);
//                } else if (savedTask.contains("D")) {
//                    extractDeadline(savedTask);
//                } else if (savedTask.contains("E")) {
//                    extractEvent(savedTask);
//                }
//            }
//        }
//    }


    //main method
    public static void main(String[] args) throws DukeException, IOException {
        //load file
        Storage.loadListFromFile();
        //message
        System.out.println("Hello! I'm Botbot \n" +
                "───────────────────────────────────────────────────────────────────────────────────────────────\n" +
                "─██████████████───██████████████─██████████████─██████████████───██████████████─██████████████─\n" +
                "─██░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██─\n" +
                "─██░░██████░░██───██░░██████░░██─██████░░██████─██░░██████░░██───██░░██████░░██─██████░░██████─\n" +
                "─██░░██──██░░██───██░░██──██░░██─────██░░██─────██░░██──██░░██───██░░██──██░░██─────██░░██─────\n" +
                "─██░░██████░░████─██░░██──██░░██─────██░░██─────██░░██████░░████─██░░██──██░░██─────██░░██─────\n" +
                "─██░░░░░░░░░░░░██─██░░██──██░░██─────██░░██─────██░░░░░░░░░░░░██─██░░██──██░░██─────██░░██─────\n" +
                "─██░░████████░░██─██░░██──██░░██─────██░░██─────██░░████████░░██─██░░██──██░░██─────██░░██─────\n" +
                "─██░░██────██░░██─██░░██──██░░██─────██░░██─────██░░██────██░░██─██░░██──██░░██─────██░░██─────\n" +
                "─██░░████████░░██─██░░██████░░██─────██░░██─────██░░████████░░██─██░░██████░░██─────██░░██─────\n" +
                "─██░░░░░░░░░░░░██─██░░░░░░░░░░██─────██░░██─────██░░░░░░░░░░░░██─██░░░░░░░░░░██─────██░░██─────\n" +
                "─████████████████─██████████████─────██████─────████████████████─██████████████─────██████─────\n" +
                "───────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("What can I do for you?");
        System.out.println(line);

        //create new scanner object
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            System.out.println(line);

            try {
                //identify the command type
                String command = identifyCommand(input);

                switch (command) {
                    case "bye":
                        System.out.println("Bye! Hope to see you again soon!");
                        //close scanner
                        scanner.close();
                        return;
                    case "list":
                        for (int i = 0; i < list.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            System.out.println(list.get(i));
                        }
                        System.out.println(line);
                        break;
                    case "mark":
                        markUnmarkTask(input);
                        Storage.saveListToFile();
                        break;
                    case "todo":
                        TaskList.createTodoTasks(input.substring(5));
                        Storage.saveListToFile();
                        break;
                    case "deadline":
                        TaskList.createDeadlineTasks(input);
                        Storage.saveListToFile();
                        break;
                    case "event":
                        TaskList.createEventTask(input);
                        Storage.saveListToFile();
                        break;
                    case "delete":
                        deleteTasks(input);
                        break;
                    default:
                        return;
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage() + "\n" + line);
            }
        }
    }
}