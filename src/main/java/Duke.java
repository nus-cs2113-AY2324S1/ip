import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[1000];
    static int amount = 0;

    public static void showGreeting() {
        showLog("Hello! I'm EggyByte!\nWhat can I do for you?");
    }

    public static void showBye() {
        showLog("Bye. Hope to see you again soon!");
    }

    public static void showLog(Object content) {
        System.out.println("____________________________________________________________");
        System.out.println(content);
        System.out.println("____________________________________________________________");
    }

    public static void insertTask(Task newTask) {
        if (amount >= tasks.length) {
            showLog("Too many tasks!");
            return;
        }
        tasks[amount] = newTask;
        amount++;
        showLog("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " + amount
                + " tasks in the list.");
    }

    public static void showTasks() {
        String log = "Here are the tasks in your list:";
        for (int i = 0; i < amount; i++) {
            log += "\n" + (i + 1) + "." + tasks[i].toString();
        }
        showLog(log);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showGreeting();
        Scanner scanner = new Scanner(System.in);
        String input;
        Boolean running = true;

        while (running) {
            input = scanner.nextLine();
            int[] firstDevider = new int[] { input.indexOf(" ") };
            String firstCommand;
            if (firstDevider.length == 0 || firstDevider[0] == -1) {
                firstCommand = input;
            } else {
                firstCommand = input.substring(0, firstDevider[0]);
            }
            showLog(firstCommand);
            switch (firstCommand) {
                case "bye": {
                    running = false;
                    break;
                }
                case "todo": {
                    Task newTask = new Todo(input.substring(5, input.length()));
                    insertTask(newTask);
                    break;
                }
                case "deadline": {
                    int[] dividerPositions = new int[] { input.indexOf("/") };
                    Task newTask = new Deadline(input.substring(9, dividerPositions[0] - 1),
                            input.substring(dividerPositions[0] + 4, input.length()));
                    insertTask(newTask);
                    break;
                }
                case "event": {
                    int[] dividerPositions = new int[] { input.indexOf("/"), 0 };
                    // Try to find the second "/"
                    String remainingString = input.substring(dividerPositions[0] + 1, input.length());
                    dividerPositions[1] = dividerPositions[0] + 1 + remainingString.indexOf("/");
                    Task newTask = new Event(input.substring(6, dividerPositions[0] - 1),
                            input.substring(dividerPositions[0] + 6, dividerPositions[1] - 1),
                            input.substring(dividerPositions[1] + 3, input.length()));
                    insertTask(newTask);
                    break;
                }
                case "list": {
                    showTasks();
                    break;
                }
                default: {
                    showLog(input);
                }
            }
        }

        scanner.close();
        showBye();
    }
}
