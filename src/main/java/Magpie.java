import java.util.Scanner;

public class Magpie {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void printTasks(){

        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list: ");
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

    public static void markTask(int index, boolean isDone){

        Task item = tasks[index];
        item.setDone(isDone);
        System.out.println("____________________________________________________________\n");
        if (isDone == true){
            System.out.print("Nice! I've marked this task as done:\n");
        }
        else{
            System.out.print("OK, I've marked this task as not done yet:\n");

        }
        System.out.println("  " + item.toString());
        System.out.println("____________________________________________________________\n");
    }


    public static void main(String[] args) {


        System.out.println("____________________________________________________________");
        System.out.println(" __  __          _____ _____ _____ ______ ");
        System.out.println("|  \\/  |   /\\   / ____|  __ \\_   _|  ____|");
        System.out.println("| \\  / |  /  \\ | |  __| |__) || | | |__   ");
        System.out.println("| |\\/| | / /\\ \\| | |_ |  ___/ | | |  __|  ");
        System.out.println("| |  | |/ ____ \\ |__| | |    _| |_| |____ ");
        System.out.println("|_|  |_/_/    \\_\\_____|_|   |_____|______|");
        System.out.println("\nHello! I'm Magpie :)");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");


        Scanner in = new Scanner(System.in);
        String input = "";
        input = in.nextLine();

        while(input.equals("bye") == false){

            String[] splitInput = input.split(" ");
            String command = splitInput[0];
            String arguments = input.substring(command.length());


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
                int markIndex = Integer.parseInt(splitInput[1]) - 1;
                markTask(markIndex,true);
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(splitInput[1]) - 1;
                markTask(unmarkIndex, false);
                break;
            default:

            }

            input = in.nextLine();


        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");

    }

}