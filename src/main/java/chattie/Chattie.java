package chattie;

import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Deadline;
import chattie.tasks.Event;
import chattie.tasks.Task;
import chattie.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Chattie {

    private static void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    private static void startChat() {
        int count = 0;
        ArrayList<Task> list = new ArrayList<>();
        String line;
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("\tHello! I'm Chattie! How was your day?");
        printLine();

        line = in.nextLine();
        greetUser(line);

        line = in.nextLine();
        while(!line.equals("bye")) {
            printLine();
            count = readCommands(line, list, count);
            printLine();
            line = in.nextLine();
        }

        printLine();
        System.out.println("\tByeeeee. Hope to see you again soon! :)");
        printLine();
    }

    public static void greetUser(String line) {
        line = line.toLowerCase();

        printLine();
        if(line.contains("good")) {
            System.out.println("\tGreat to hear that! :D");
        } else if (line.contains("bad")) {
            System.out.println("\tI'm sorry to hear that :(");
        } else {
            System.out.println("\tSounds like you had quite a day");
        }
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    private static int readCommands(String line, ArrayList<Task> list, int count) {
        try {
            if(line.equals("list")) {
                listTasks(list, count);
            } else if(line.startsWith("mark") || line.startsWith("unmark")) {
                String[] command = line.split(" ");
                markTask(list, command);
            } else if (line.startsWith("todo")) {
                count = addTodo(list, line, count);
            } else if (line.startsWith("deadline")) {
                count = addDeadline(list, line, count);
            } else if (line.startsWith("event")) {
                count = addEvent(list, line, count);
            }
        } catch (ChattieException e) {
            System.out.println("\tPlease try again");
        }
        return count;
    }

    public static void listTasks(ArrayList<Task> list, int count) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.print("\t" + (i+1) + ". ");
            if(list.get(i).isDone()) {
                System.out.println(list.get(i));
            } else {
                System.out.println(list.get(i));
            }
        }
    }

    public static int addTodo(ArrayList<Task> list, String line, int count) throws ChattieException{
        if (line.trim().length() < 5) {
            throw new ChattieException(ErrorType.TODO);
        }
        String todo = line.substring(5);
        list.add(new Todo(todo));
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + list.get(count));
        count++;
        System.out.println("\tNow you have " + count + " tasks in the list.");
        return count;
    }

    public static int addDeadline(ArrayList<Task> list, String line, int count) throws ChattieException {
        if (line.trim().length() < 9) { //check if deadline is empty
            throw new ChattieException(ErrorType.EMPTY_DEADLINE);
        }

        int slashIndex = line.indexOf("/by");
        if (slashIndex < 0) {
            throw new ChattieException(ErrorType.INVALID_DEADLINE); //check if '/by' is present
        }

        String task = line.substring(9, slashIndex);
        String by = line.substring(slashIndex + 3);
        if (task.isEmpty() || by.isEmpty()) {
            throw new ChattieException(ErrorType.INVALID_DEADLINE); //check if [task] or [by] empty
        }

        list.add(new Deadline(task, by));
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + list.get(count));
        count++;
        System.out.println("\tNow you have " + count + " tasks in the list.");
        return count;
    }

    public static int addEvent(ArrayList<Task> list, String line, int count) throws ChattieException{
        if (line.trim().length() < 6) {
            throw new ChattieException(ErrorType.EMPTY_EVENT); //check if event is empty
        }

        int firstSlash = line.indexOf("/from");
        int secondSlash = line.indexOf("/to");
        if (firstSlash < 0 || secondSlash < 0) {
            throw new ChattieException(ErrorType.INVALID_EVENT); //check if '/from' or '/to' present
        }

        String task = line.substring(6, firstSlash);
        String from = line.substring(firstSlash + 5, secondSlash);
        String to = line.substring(secondSlash + 3);
        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new ChattieException(ErrorType.INVALID_EVENT); //check if [task], [from], or [to] is empty
        }

        list.add(new Event(task, from, to));
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + list.get(count));
        count++;
        System.out.println("\tNow you have " + count + " tasks in the list.");
        return count;
    }

    public static void markTask(ArrayList<Task> list, String[] command) {
        int taskNum = Integer.parseInt(command[1]) - 1;
        if(command[0].equalsIgnoreCase("mark")) {
            list.get(taskNum).setDone(true);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + list.get(taskNum));
        } else {
            list.get(taskNum).setDone(false);
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t" + list.get(taskNum));
        }
    }
    
    public static void main(String[] args) {
        startChat();
    }
}
