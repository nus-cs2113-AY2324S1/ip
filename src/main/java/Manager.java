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
        String[] splitInput = input.split(" ", 2);
        String action = splitInput[0];

        switch (action) {
        case "bye":
            printByeMessage();
            exit();
            break;
        case "list":
            printListMessage();
            break;
        case "mark":
            int taskId = Integer.parseInt(splitInput[1]);
            this.list.mark(taskId);
            printMarkMessage(taskId);
            break;
        case "unmark":
            taskId = Integer.parseInt(splitInput[1]);
            this.list.unmark(taskId);
            printUnmarkMessage(taskId);
            break;
        default:
            this.list.add(input);
            printAddMessage();
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
