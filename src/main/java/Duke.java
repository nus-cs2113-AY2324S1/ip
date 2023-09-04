import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        private static int numberOfTasks = 0;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
            numberOfTasks += 1;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return this.description;
        }

        public void markStatus() {
            this.isDone = true;
        }

        public void unmarkStatus() {
            this.isDone = false;
        }

        public int getTotalNumberOfBicycles() {
            return numberOfTasks;
        }
    }

    /**
     * Prints a list of tasks with their status icons and descriptions.
     *
     * @param storageArray An array of Task objects representing the list of tasks.
     * @param num          The number of tasks to be printed.
     */

    public static void printList(Task[] storageArray, int num) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < num; i++) {
            System.out.println(
                    i + 1 + "." + "[" + storageArray[i].getStatusIcon() + "]" + storageArray[i].getDescription());
        }
    }

    public static void main(String[] args) {

        String logoRyan = " ____  _          \n"
                + "|  _ \\| | ___   __\n"
                + "| |_) | |/ _ \\ / _|\n"
                + "|  __/| | (_) | (__\n"
                + "|_|   |_|\\___/ \\___|\n";

        System.out.println("Hello from\n" + logoRyan);

        Task[] storageArray = new Task[100];
        int numOfItems = 0;

        String line = "Hello! I'm Ryan Loh \nWhat can I do for you?\n";

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        while (!userInput.equals("Bye")) {
            String[] words = userInput.split(" ");

            String initialWord = words[0];

            if (initialWord.equals("list")) {

                printList(storageArray, numOfItems);

            } else if (initialWord.equals("mark")) {
                System.out.println("mark " + words[1]);
                int index = Integer.parseInt(words[1]);
                // we need to check if its out of bounds first
                if (index > numOfItems || index <= 0) {
                    System.out.println("index out of bounds, try again later.");
                } else {
                    Task currTask = storageArray[index - 1];
                    currTask.markStatus();
                    System.out.println("Nice! I've marked this task as done: ");
                    String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
                    System.out.println(message);
                }

            } else if (initialWord.equals("unmark")) {
                // unmark an item
                System.out.println("unmark " + words[1]);
                int index = Integer.parseInt(words[1]);
                // we need to check if its out of bounds first
                if (index > numOfItems || index <= 0) {
                    System.out.println("index out of bounds, try again later.");
                } else {
                    Task currTask = storageArray[index - 1];
                    currTask.unmarkStatus();
                    System.out.println("Nice! I've marked this task as done: ");
                    String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
                    System.out.println(message);
                }

            } else {
                Task t = new Task(userInput);

                storageArray[numOfItems] = t;
                System.out.println("added: " + userInput);
                numOfItems += 1;
            }

            userInput = scanner.nextLine();

        }
        String lineBreak = "------------------------------ \n";

        System.out.println(line + lineBreak + "Bye. Hope to see you again soon!\n");

        // Close the scanner when you're done with it
        scanner.close();

    }
}
