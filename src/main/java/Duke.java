import java.util.Scanner;
import java.util.Arrays;

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}

public class Duke {
    public static void main(String[] args) {
        String LINE = "__________________________________________\n";
        System.out.println(LINE + "Hello I'm MatinBot\n" + "What can I do for you?\n" + LINE);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;
        String userInput = "hi";

        while (true) {
            // Get the user input first
            userInput = scanner.nextLine();
            System.out.println(LINE);

            // Checks if input is bye, if not proceed
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1)
                                        + ". "
                                        + tasks[i].toString());
                }
            } else if (userInput.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n"
                                   + tasks[taskIndex]);
            } else if (userInput.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                tasks[taskIndex].markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n"
                                   + tasks[taskIndex]);
            } else {
                tasks[count] = new Task(userInput);
                System.out.println("added: "
                                   + userInput);
                count++;
            }
            System.out.println(LINE);
        }

        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
        scanner.close();
    }
}

