import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        printLines(30, '-');
        System.out.println("Hello! I'm En\nWhat can i do for you?");
        printLines(30, '-');

        Scanner scanner = new Scanner(System.in);
        String userInput;
        List<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();
            printLines(30, '-');

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Have a nice day.");
                printLines(30, '-');
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("En: Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i));
                }
                printLines(30,'-');
            } else if (userInput.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markNotDone();
                    System.out.println(tasks.get(taskIndex));
                    printLines(30,'-');
                } else {
                    printLines(30, '-');
                    System.out.println("Invalid");
                }
            } else {
                tasks.add(new Task(userInput));
                System.out.println("En: added: " + userInput);
                printLines(30,'-');
            }
        }
        scanner.close();
    }

    public static class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markDone() {
            isDone = false;
        }

        public void markNotDone() {
            isDone = true;
        }

        public String toString() {
            return "[" + (isDone ? "X" : " ") + "] " + description;
        }
    }


    public static void printLines(int l, char c) {
        for (int i = 0; i < l; i++) {
            System.out.print(c);
        }
        System.out.println();
    }
}
