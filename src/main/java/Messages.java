import listWhisper.task.List;
import listWhisper.task.Task;

public class Messages {
    public static void printAddMessage(List list) {
        ListWhisper.printStraightLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(list.getTask(list.getSize()) + "\n");
        System.out.println(String.format("Now you have %d tasks in the list.", list.getSize()));
        ListWhisper.printStraightLine();
    }

    public static void printDeleteMessage(List list, Task task) {
        ListWhisper.printStraightLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", list.getSize()));
        ListWhisper.printStraightLine();
    }


    public static void printUnmarkMessage(Task task) {
        ListWhisper.printStraightLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        ListWhisper.printStraightLine();
    }

    public static void printMarkMessage(Task task) {
        ListWhisper.printStraightLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        ListWhisper.printStraightLine();
    }

    public static void printListMessage(List list) {
        ListWhisper.printStraightLine();
        System.out.println("Here are the tasks in your list:");
        System.out.println(list.toString());
        ListWhisper.printStraightLine();
    }

    public static void printByeMessage() {
        ListWhisper.printStraightLine();
        System.out.println("Bye. Hope to see you again soon!");
        ListWhisper.printStraightLine();
    }
}
