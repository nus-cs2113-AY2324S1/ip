//import scanner
import java.util.Scanner;

public class Botbot {
    public static String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    //create array for list
    public static Task[] list = new Task[100];
    public static int listSize = 0;

    //method to mark or unmark task
    public static void markUnmarkTask(String command){
        int itemIndex; //int to store index of item to mark/unmark
        //for command unmark
        if(command.contains("unmark")){
            //find the given index to unmark
            itemIndex = Integer.parseInt(command.substring(7))-1;
            //if given index is out of range
            if (itemIndex>=listSize){
                System.out.println("invalid list item");
                return;
            } else {
                list[itemIndex].unmark();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(list[itemIndex]);
                System.out.println(line);
            }
        } else { //for command mark
            //find the given index to mark
            itemIndex = Integer.parseInt(command.substring(5))-1;
            //if given index is out of range
            if (itemIndex>=listSize){
                System.out.println("invalid list item");
                return;
            }else {
                list[itemIndex].mark();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list[itemIndex]);
                System.out.println(line);
            }
        }
    }

    //method to add todo tasks
    public static void createTodoTasks(String task) {
        //instantiate new todo object
        Todo todoTask = new Todo(task);
        //add to array
        list[listSize] = todoTask;
        listSize++;
        System.out.println("Got it. I've added this task:");
        System.out.println(todoTask);
        System.out.println("Now you have " + (listSize) + " tasks in the list.");
        System.out.println(line);
    }

    //method to add deadline tasks
    public static void createDeadlineTasks(String task, String deadline){
        //instantiate new deadline object
        Deadline deadlineTask = new Deadline(task, deadline);
        //add to array
        list[listSize] = deadlineTask;
        listSize++;
        System.out.println("Got it. I've added this task:");
        System.out.println(deadlineTask);
        System.out.println("Now you have " + (listSize) + " tasks in the list.");
        System.out.println(line);
    }

    //method to add eventTask
    public static void createEventTask(String task, String from, String to){
        //instantiate new event object
        Event eventTask = new Event(task, from, to);
        //add to array
        list[listSize] = eventTask;
        listSize++;
        System.out.println("Got it. I've added this task:");
        System.out.println(eventTask);
        System.out.println("Now you have " + (listSize) + " tasks in the list.");
        System.out.println(line);
    }

    //method to add task to list
    public static void addTask(String input){
        //instantiate new Task object
        Task newTask = new Task(input);
        //Echo input
        System.out.println("Added: " + input);
        System.out.println(line);
        //edit list array
        list[listSize] = newTask;
        listSize++;
    }

    public static void main(String[] args) {
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

            //for command bye
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            //for command list
            } else if(input.equals("list")) {
                for (int i = 0; i < listSize; i++) {
                    System.out.print((i + 1) + ". ");
                    System.out.println(list[i]);
                }
                System.out.println(line);
            //for marking/unmarking command
            }else if(input.contains("mark")){
                markUnmarkTask(input);
            //for todo commands
            }else if(input.contains("todo")) {
                createTodoTasks(input.substring(5));
            //for deadline commands
            }else if(input.contains("deadline")){
                if (!input.contains("/by")){
                    System.out.println("Invalid input. No deadline/invalid format.");
                }else {
                    String[] parts = input.split("/by ");
                    String task = parts[0].substring("deadline ".length());
                    String deadline = parts[1];
                    createDeadlineTasks(task, deadline);
                }
            //for event commands
            }else if(input.contains("event")){
                if (!input.contains("/from") || !input.contains("/to")){
                    System.out.println("Invalid input. No duration/invalid format.");
                }else {
                    String[] parts = input.split("/");
                    String task = parts[0].substring("event ".length());
                    String from = parts[1].substring("from ".length());
                    String to = parts[2].substring("to ".length());
                    createEventTask(task, from, to);
                }
            }else{
                    addTask(input);
            }
        }
        //close scanner
        scanner.close();
    }
}
