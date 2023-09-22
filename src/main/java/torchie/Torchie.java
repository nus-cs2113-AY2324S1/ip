package torchie;

<<<<<<< HEAD
import torchie.storage.DataManager;
import torchie.exception.TorchieException;
=======
import torchie.exception.DukeException;
>>>>>>> branch-Level-6
import torchie.task.Deadline;
import torchie.task.Event;
import torchie.task.Task;
import torchie.task.TaskList;
import torchie.task.ToDo;

import java.util.Scanner;
public class Torchie {

<<<<<<< HEAD
//    private static Task[] taskStore = new Task[100];
//private static TaskList taskList = new TaskList();
    private static DataManager dataManager = new DataManager();
    private static TaskList taskList = dataManager.retrieveData();
=======
    //    private static Task[] taskStore = new Task[100];
    private static TaskList taskList = new TaskList();
>>>>>>> branch-Level-6
//    private static int numOfTasks = 0; // keep track of next null space in array

//    public static Task[] getTaskStore() {
//        return taskStore;
//    }

    public static TaskList getTaskList() {
        return taskList;
    }

    public static void addTask(Task t) {
        taskList.addTask(t);
    }
<<<<<<< HEAD
//    public static void setTaskStore(Task t) {
//        taskStore[numOfTasks] = t;
//        numOfTasks += 1;
//    }

    public static void showList() {
        taskList.showTasks();
        /*System.out.println("Here are the tasks in your list: ");
        for (int i=0; i<taskStore.length; i++){
            if (taskStore[i] == null){
                // only print non-null tasks
                break;
            }
=======

    /*public static void setTaskStore(Task t) {
        taskStore[numOfTasks] = t;
        numOfTasks += 1;
    }*/

    public static void showList() {
        taskList.showTasks();
       /* System.out.println("Here are the tasks in your list: ");
        for (int i=0; i<taskList.length; i++){
//            if (taskStore[i] == null){
//                // only print non-null tasks
//                break;
//            }
>>>>>>> branch-Level-6
            System.out.print( (i+1) + ".");
            taskStore[i].printTask(taskStore[i].toString());
        }*/
    }

    public static void announceListSize() {
        System.out.println("Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    public static String getContent(String s) {
        // split sentence into 2 parts, first word and everything else
        String[] words = s.split(" ", 2);

        // making sure content stops before the key characters such as /
        String content = words[1];

        if (content.indexOf('/') != -1) {
            int keyWordIndex = content.indexOf('/');
            content = content.substring(0, keyWordIndex - 1);
        }

        return content;
    }

    public static String getDeadlineDate(String s) throws TorchieException {
        int SIZE_OF_BUFFER = 4;
        int keyWordIndex = s.indexOf('/');

        if (keyWordIndex == -1) {
            throw new TorchieException();
        }
        return s.substring(keyWordIndex + SIZE_OF_BUFFER);
    }

    public static String getEventStart(String s) throws TorchieException {
        int SIZE_OF_BUFFER = 6;

        // first occurrence of '/' character
        int startTimeIndex = s.indexOf('/');
        if (startTimeIndex == -1) {
            throw new TorchieException();
        }

        // second occurrence of '/' character
        int endTimeIndex = s.indexOf('/', startTimeIndex + 1);
        return s.substring(startTimeIndex + SIZE_OF_BUFFER, endTimeIndex - 1);
    }

    public static String getEventEnd(String s) throws TorchieException {
        int SIZE_OF_BUFFER = 4;

        // first occurrence of '/' character
        int startTimeIndex = s.indexOf('/');

        // second occurrence of '/' character
        int endTimeIndex = s.indexOf('/', startTimeIndex + 1);
        if (endTimeIndex == -1) {
            throw new TorchieException();
        }
        return s.substring(endTimeIndex + SIZE_OF_BUFFER);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Torchie!");
        System.out.println("What can I do for you?");
        System.out.println("Let's play storetorchie today! You say something and I ll store it!");

        String input;
        int itemNum;

        do {
            input = scanner.nextLine();
            String firstWord = input.split(" ")[0];

            switch (firstWord) {
            case "list":
                showList();
                break;
            case "mark":
//                itemNum = Integer.parseInt(input.split(" ")[1]) - 1;

                try {
                    itemNum = Integer.parseInt(getContent(input)) - 1;
                    taskList.markTask(itemNum);
<<<<<<< HEAD
                    dataManager.save(taskList);
=======
>>>>>>> branch-Level-6
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Format! Correct format: \"mark <index>\" where" +
                            " index is an integer ");
                } catch (NullPointerException e) {
                    System.out.println("Task number cannot exceed: <" + taskList.getSize() + ">");
                }
                break;
            case "unmark":
                try {
                    itemNum = Integer.parseInt(getContent(input)) - 1;
                    taskList.unmarkTask(itemNum);
<<<<<<< HEAD
                    dataManager.save(taskList);
=======
>>>>>>> branch-Level-6
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Format! Correct format: \"mark <index>\" where" +
                            " index is an integer ");
                } catch (NullPointerException e) {
                    System.out.println("Task number cannot exceed: <" + taskList.getSize() + ">");
                }
                break;
            case "bye":
                System.out.println("Awww bye :( Let's play again soon!");
                break;
            case "todo":
                ToDo td;
                try {
                    td = new ToDo(getContent(input));
                    addTask(td);
<<<<<<< HEAD
                    td.announceTaskAdd();
=======
//                    td.announceTaskAdd();
>>>>>>> branch-Level-6
                    announceListSize();
                    dataManager.save(taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing <task name>: Example: todo <read>");
                }
                break;
            case "deadline":
                try {
                    Deadline d = new Deadline(getContent(input), getDeadlineDate(input));
                    addTask(d);
<<<<<<< HEAD
                    d.announceTaskAdd();
=======
//                    d.announceTaskAdd();
>>>>>>> branch-Level-6
                    announceListSize();
                    dataManager.save(taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing <task name>: Example: deadline <read> /by Aug 1st");
                } catch (TorchieException e) {
                    System.out.println("Missing <deadline>: Example: deadline read </by Aug 1st>");
                }
                break;
            case "event":
                try {
                    Event e = new Event(getContent(input), getEventStart(input), getEventEnd(input));
                    addTask(e);
<<<<<<< HEAD
                    e.announceTaskAdd();
=======
//                    e.announceTaskAdd();
>>>>>>> branch-Level-6
                    announceListSize();
                    dataManager.save(taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing <task name>: Example: event <read> /from Aug 1st 4pm /to 6pm");
                } catch (TorchieException e) {
                    System.out.println("Missing keyword </from start time> or </by end time> " +
                            "Example: event read </from Aug 1st 4pm> </to 6pm>");
                }
                break;
            case "delete":
                try {
                    itemNum = Integer.parseInt(getContent(input)) - 1;
                    taskList.deleteTask(itemNum);
                    announceListSize();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Format! Correct format: \"mark <index>\" where" +
                            " index is an integer ");
                } catch (NullPointerException e) {
                    System.out.println("Task number cannot exceed: <" + taskList.getSize() + ">");
                }
                break;
            default:
                Task t = new Task(input);
                addTask(t);
                System.out.println("added: " + input);
            }

        } while (!input.equals("bye"));

    }
}