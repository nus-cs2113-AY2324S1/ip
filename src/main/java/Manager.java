import java.util.Scanner;

class Manager {
    List list;

    public Manager() {
        this.list = new List();
    }

    public void readInput() {
            Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String input = s.nextLine();
            this.execute(input);
        }
    }
    void execute(String input) {

        try {
            if (input.startsWith("bye")) {
                printByeMessage();
                exit();
            } else if (input.startsWith("list")) {
                printListMessage();
            } else if (input.startsWith("mark")) {
                this.list.mark(input);
                printMarkMessage(this.list.getSize());
            } else if (input.startsWith("unmark")) {
                this.list.unmark(input);
                printUnmarkMessage(this.list.getSize());
            } else if (input.startsWith("todo")) {
                this.list.addTodo(input.substring("todo".length()));
                printAddMessage();
            } else if (input.startsWith("deadline")) {
                this.list.addDeadline(input.substring("deadline".length()));
                printAddMessage();
            } else if (input.startsWith("event")) {
                this.list.addEvent(input.substring("event".length()));
                printAddMessage();
            } else {
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DescriptionFormatException | InvalidCommandException e) {
            System.out.println(e);
        }
    }

    private static void exit() {
        System.exit(0);
    }

    private void printAddMessage() {
        printStraightLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(this.list.getTask(this.list.getSize()) + "\n");
        System.out.println(String.format("Now you have %d tasks in the list.", this.list.getSize()));
        printStraightLine();
    }

    private void printUnmarkMessage(int taskId) {
        printStraightLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.list.getTask(taskId));
        printStraightLine();
    }

    private void printMarkMessage(int taskId) {
        printStraightLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.list.getTask(taskId));
        printStraightLine();
    }

    private void printListMessage() {
        printStraightLine();
        System.out.println("Here are the tasks in your list: ");
        System.out.print(this.list);
        printStraightLine();
    }

    private void printByeMessage() {
        printStraightLine();
        System.out.println("Bye. Hope to see you again soon!");
        printStraightLine();
    }
    private void printStraightLine() {
        System.out.println("-----------------------------------------------------");
    }
}
