package duke;
// ASS1, ASS2 INPUT, REMOVE 1, PRINT TO HAVE 1....
import task.Event;
import task.Task;
import task.Deadline;
import task.Todo;

import exception.DukeException;

import java.util.Scanner;

public class Duke {

    public static int taskCount = 0;
    private static Task[] list = new Task[100];

    public static void main(String[] args) throws DukeException {
        printWelcomeMessage();
        Scanner keyboard = new Scanner(System.in);

        while (true) {
            int taskNo;
            String command = keyboard.nextLine().trim().toLowerCase(); //small letter and remove front and back space
            String[] commendSplits = command.split(" ");
            if (missingOrExtraTaskDescription(commendSplits)){
                continue;
            }

            try {
                switch (commendSplits[0]) {
                case "bye":
                    printByeMessage();
                    keyboard.close();
                    return;

                case "list":
                    printList(taskCount, list);
                    break;

                case "mark":
                    // command format e.g. mark 1
                    taskNo = getTaskNo(commendSplits[1]);
                    list[taskNo - 1].setDone(taskNo, taskCount, list);
                    break;

                case "unmark":
                    taskNo = getTaskNo(commendSplits[1]);
                    list[taskNo - 1].setNotDone(taskNo, taskCount, list);
                    break;

                case "delete":
                    taskNo = getTaskNo(commendSplits[1]);
                    deleteTask(taskNo);
                    break;

                case "todo":
                    // command format e.g. todo borrow book
                    list[taskCount] = Todo.newTodoTask(command);
                    createTaskSuccessMsg();
                    break;

                case "deadline":
                    // command e.g. deadline return book /by Sunday
                    list[taskCount] = Deadline.newDdl(command);
                    createTaskSuccessMsg();
                    break;

                case "event":
                    //command e.g. event project meeting /from Mon 2pm /to 4pm
                    list[taskCount] = Event.newEventTask(command);
                    createTaskSuccessMsg();
                    break;

                default:
                    throw new DukeException();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Hey, please input your command with the correct task number.");
            } catch (NullPointerException npe){
                System.out.println("Your target task doesn't exist. Please input a correct task.");
            } catch (DukeException e){
                e.incorrectFormatException(commendSplits[0]);
            }
        }

    }

    private static int getTaskNo(String taskNum){
        //exception: taskNum is not number, or containing non-numerical value
        return Integer.parseInt(taskNum);
    }

    public static Task[] getTaskList() {
        return list;
    }

    private static void deleteTask(int deleteIndex) {
        if (deleteIndex <= 0 || deleteIndex> Duke.taskCount){
            System.out.println("Oh, No! invalid index! You don't have that task. Please try again.");
            return;
        }
        deleteTaskSuccessMsg(deleteIndex);
        list = Task.updatedTaskList(deleteIndex - 1);
    }

    //To tackle cases of invalid input like 'todo', 'event', etc.
    private static boolean missingOrExtraTaskDescription(String[] cmd){
        if (cmd.length == 1){
            if(cmd[0].equals("todo") || cmd[0].equals("event") || cmd[0].equals("deadline")
                    || cmd[0].equals("mark") || cmd[0].equals("unmark") || cmd[0].equals("delete")){
                System.out.println("Please describe your target.");
                return true;
            }
        }
        else if (cmd.length>=2 && cmd[0].equals("list")){
            System.out.println("Do you mean to see the list? Please try again using 'list'.");
            return true;
        }
        return false;
    }

    private static void createTaskSuccessMsg() {
        System.out.println("Got it. I've added this task:");
        System.out.println(list[taskCount]);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static void deleteTaskSuccessMsg(int deleteIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list[deleteIndex - 1]);
        taskCount--;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Oriento.");
        System.out.println("What can I help you?");
        System.out.println("⣿⣿⣿⠟⠛⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⢋⣩⣉⢻⣿\n" +
                "⣿⣿⣿⠀⣿⣶⣕⣈⠹⠿⠿⠿⠿⠟⠛⣛⢋⣰⠣⣿⣿⠀⣿⣿\n" +
                "⣿⣿⣿⡀⣿⣿⣿⣧⢻⣿⣶⣷⣿⣿⣿⣿⣿⣿⠿⠶⡝⠀⣿⣿\n" +
                "⣿⣿⣿⣷⠘⣿⣿⣿⢏⣿⣿⣋⣀⣈⣻⣿⣿⣷⣤⣤⣿⡐⢿⣿\n" +
                "⣿⣿⣿⣿⣆⢩⣝⣫⣾⣿⣿⣿⣿⡟⠿⠿⠦⠀⠸⠿⣻⣿⡄⢻⣿\n" +
                "⣿⣿⣿⣿⣿⡄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣾⣿⣿⣿⣿⠇⣼⣿\n" +
                "⣿⣿⣿⣿⣿⣿⡄⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣰⣿\n" +
                "⣿⣿⣿⣿⣿⣿⠇⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢀⣿⣿\n" +
                "⣿⣿⣿⣿⣿⠏⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢸⣿⣿\n" +
                "⣿⣿⣿⣿⠟⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⣿⣿\n" +
                "⣿⣿⣿⠋⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⣿⣿\n" +
                "⣿⣿⠋⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢸⣿\n");
    }

    private static void printList(int count, Task[] list) {
        if (taskCount == 0){
            System.out.println("You don't have any tasks now. Do you want to add a new task?");
            return;
        }
        for (int i = 0; i < count; i++) {
            //example 1.[T][X] read book
            System.out.println((i + 1) + "." + list[i]);
        }
    }

}
