import java.util.Scanner;

public class Duke {

    static String[] tasks = new String[100];
    static int taskCount = 0;

    public static void addTask(String task) {
        System.out.println("added: " + task);
        tasks[taskCount] = task;
        taskCount++;
    }

    public static void listTask() {
        for(int i=0; i<taskCount; i++) {
            System.out.print(i+1);
            System.out.println(". " + tasks[i]);
        }
    }

    public static void processCommand(String line) {
        Scanner textIn = new Scanner(System.in);
        while (true) {
            String command = textIn.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(line);
                listTask();
                System.out.println(line);
            } else {
                System.out.println(line);
                addTask(command);
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm soccat!\nWhat can I do for you?");
        System.out.println(line);
        processCommand(line);
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
