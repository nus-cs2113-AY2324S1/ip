//import scanner
import javax.lang.model.type.NullType;
import java.util.Scanner;

public class Botbot {
    public static String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    //create array for list
    public static Task[] list = new Task[100];
    public static int listSize = 0;

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
        } else {
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
    public static void createDeadlineTasks(String input) throws DukeException {
        String task;
        String deadline;
        if (!input.contains("/by")) {
            throw new DukeException("Ohno... Please check your format and include '/by'~");
        } else {
            String[] parts = input.split(" /by ");
            //check if task or deadline are null
            if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                throw new DukeException("Task or deadline cannot be empty... Please check your input again~");
            }
            task = parts[0].substring("deadline ".length());
            deadline = parts[1];
        }
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
    public static void createEventTask(String input) throws DukeException {
        String task;
        String from;
        String to;
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new DukeException("Uhoh... Please check your format and include '/from' and '/to'~");
        } else {
            String[] parts = input.split(" /");
            //check if task, to, from are null
            if (parts.length != 3 || parts[0].isEmpty() || parts[1].equals("from") || parts[2].equals("to")) {
                throw new DukeException("Task, from or to cannot be empty... Please check your input again~");
            }
            task = parts[0].substring("event ".length());
            from = parts[1].substring("from".length());
            to = parts[2].substring("to".length());
        }
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


    //main method
    public static void main(String[] args){
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
                        for (int i = 0; i < listSize; i++) {
                            System.out.print((i + 1) + ". ");
                            System.out.println(list[i]);
                        }
                        System.out.println(line);
                        break;
                    case "mark":
                        markUnmarkTask(input);
                        break;
                    case "todo":
                        createTodoTasks(input.substring(5));
                        break;
                    case "deadline":
                        createDeadlineTasks(input);
                        break;
                    case "event":
                        createEventTask(input);
                        break;
                    default:
                        return;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage() + "\n" + line);
            }
        }
    }
}