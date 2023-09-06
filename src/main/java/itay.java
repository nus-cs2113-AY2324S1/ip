import java.util.Scanner;

public class Itay {

    static Task[] tasks = new Task[100];
    static int numTasks = 0;
    static String DIVIDER = "------------------------------------------------------------";
    
    public static void Respond(String input) {
        System.out.println(DIVIDER);
        String splitInput[] = input.split(" ");
        String indicator = splitInput[0];
        int taskIdx;

        switch(indicator) {

            case("list"):
                printList();
                break;

            case("mark"):
                taskIdx = Integer.parseInt(splitInput[1]) - 1;
                handleMark(taskIdx);
                break;

            case("unmark"):
                taskIdx = Integer.parseInt(splitInput[1]) - 1;
                handleUnmark(taskIdx);
                break;

            case("todo"):
                handleTodo(input);
                break;
            
            case("deadline"):
                handleDeadline(input);
                break;
            
            case("event"):
                handleEvent(input);
                break;
            
            default:
                System.out.println("Please enter a valid Task or request");
                System.out.println(DIVIDER);
                break;
        }
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < numTasks ; i++) {
                System.out.println(tasks[i].toString());
            }
        System.out.println(DIVIDER);
    }

    public static void handleMark(int idx) {
        if(idx < 0 || idx >= numTasks) {
            System.out.println("Please enter a valid task number");
            System.out.println(DIVIDER);
            return;
        }
        tasks[idx].setStatus(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[idx].toString());
        System.out.println(DIVIDER);
    }

    public static void handleUnmark(int idx) {
        if(idx < 0 || idx >= numTasks) {
            System.out.println("Please enter a valid task number");
            System.out.println(DIVIDER);
            return;
        }
        tasks[idx].setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[idx].toString());
        System.out.println(DIVIDER);
    }

    public static void handleTodo(String input) {
        String description = input.substring(input.indexOf(' ') + 1);
        Task task = new Task(description, 'T');
        tasks[numTasks] = task;
        PrintAddTask();
    }

    public static void handleDeadline(String input) {
        int firstSlashIdx = input.indexOf('/');

        String description = input.substring(input.indexOf(' ') + 1, firstSlashIdx - 1);
        Task task = new Task(description, 'D');

        String temp = input.substring(firstSlashIdx + 1);
        String deadline = temp.substring(temp.indexOf(' ') + 1);
        task.setDeadlineTime(deadline);

        tasks[numTasks] = task;
        PrintAddTask();
    }

    public static void handleEvent(String input) {
        int firstSlashIdx = input.indexOf('/');
        int secondSlashIdx = input.indexOf('/', firstSlashIdx + 1);

        String description = input.substring(input.indexOf(' ') + 1, firstSlashIdx - 1);
        Task task = new Task(description, 'E');

        String temp = input.substring(firstSlashIdx);
        firstSlashIdx = temp.indexOf('/');
        secondSlashIdx = temp.indexOf('/', firstSlashIdx + 1);
        String startTime = temp.substring(temp.indexOf(' ') + 1, secondSlashIdx - 1);
        temp = temp.substring(secondSlashIdx);
        String endTime = temp.substring(temp.indexOf(' ') + 1);
        
        task.setEventTime(startTime, endTime);

        tasks[numTasks] = task;
        PrintAddTask();
    }
    
    public static void PrintAddTask() {
        System.out.println("Got it. I've added this task:");   
        System.out.println(tasks[numTasks].toString());   
        numTasks++;
        System.out.println("Now you have " + numTasks + " tasks in the list.");
        System.out.println(DIVIDER);   
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Itay");
        System.out.println("What can i do for you?");
        System.out.println(DIVIDER);

        try (Scanner in = new Scanner(System.in)) {
            String input = in.nextLine();
            input = input.trim();

            while(! input.equals("bye")) {
                Respond(input);
                input = in.nextLine();
                input = input.trim();
            }
        }

        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
    
}