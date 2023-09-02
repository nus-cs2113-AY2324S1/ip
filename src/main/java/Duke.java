import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];

    private static int taskCount = 0;
    public static void printTasks(){

        System.out.println("____________________________________________________________");

        for(int i =0; i< taskCount; i++) {

            Task item = tasks[i];
            System.out.println(i+1 + ". " + item.toString());
        }

        System.out.println("____________________________________________________________\n");
    }

    public static void addTask(Task t){

        tasks[taskCount] = t;
        taskCount++;

        System.out.println("____________________________________________________________\n");
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + taskCount + " task(s) in the list.");
        System.out.println("____________________________________________________________\n");

    }

    public static void readInput() {


        String input = "";
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        String[] splitInput = input.split(" ");

        String command = splitInput[0];
        String arguments = input.substring(command.length());



        int index;


        while(!(command.equals("bye"))){

            switch(command){

            case "list":
                printTasks();
                break;

            case "todo":
                Task newTask = new Todo(arguments);
                addTask(newTask);
                break;

            case "deadline":


                int byIndex = arguments.indexOf("/by");

                String by = arguments.substring(byIndex+3);
                String description = arguments.substring(0,byIndex);

                newTask = new Deadline(description, by.trim());
                addTask(newTask);
                break;
            case "event":


                int fromIndex = arguments.indexOf("/from");
                int toIndex = arguments.indexOf("/to");

                String from = arguments.substring(fromIndex+5, toIndex);
                String to = arguments.substring(toIndex+3);
                String eventName = arguments.substring(0,fromIndex);

                newTask = new Event(eventName,from.trim(),to.trim());
                addTask(newTask);
                break;

            case "mark":
                index = Integer.parseInt(splitInput[1]) - 1;
                Task item = tasks[index];
                System.out.println("____________________________________________________________\n");
                System.out.print("Nice! I've marked this task as done:\n");
                item.setDone(true);
                System.out.println(item.toString());
                System.out.println("____________________________________________________________\n");
                break;
            case "unmark":
                index = Integer.parseInt(splitInput[1]) - 1;
                item = tasks[index];
                System.out.println("____________________________________________________________\n");
                System.out.print("OK, I've marked this task as not done yet:\n");
                item.setDone(false);
                System.out.println(item.toString());
                System.out.println("____________________________________________________________\n");
                break;
            }

            input = in.nextLine();
            splitInput = input.split(" ");
            command = splitInput[0];
            arguments = input.substring(command.length());
        }


        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");



    }



    public static void main(String[] args) {


        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Magpie");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");

        readInput();

    }

}