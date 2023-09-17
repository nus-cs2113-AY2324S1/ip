import java.util.Scanner;

public class Duke {
    private static final String BYE_COMMAND = "bye";
    public static void main(String[] args) {
        String logo = "\t _           _\n"
                + "\t| |    _   _| | _____\n"
                + "\t| |   | | | | |/ / _ \\\n"
                + "\t| |___| |_| |   <  __/\n"
                + "\t|_____|\\__,_|_|\\_\\___|\n";
        System.out.println("\t" + "Hello! I'm\n" + logo);
        System.out.println("\t" + "What can I do for you?");

        Task[] taskList = new Task[100];
        Scanner userInput = new Scanner(System.in);
        int counter = 0;

        String echo = userInput.nextLine();
        int taskNumber;
        String taskDescription;
        int slashCut;

        while (!echo.equals(BYE_COMMAND)) {
            String[] words = echo.split(" "); //to identify usage of features "mark" & "unmark"
            ActionType action = ActionType.valueOf(words[0].toUpperCase());

            switch (action) {
                case LIST:
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < counter; i += 1) {
                        System.out.println("\t" + (i + 1) + "." + taskList[i]);
                    }
                    break;
                case MARK:
                    taskNumber = Integer.parseInt(words[1]) - 1;
                    System.out.println("\tWoohoo! You have accomplished:");
                    taskList[taskNumber].setDone(true);
                    System.out.println(taskList[taskNumber]);
                    break;
                case UNMARK:
                    taskNumber = Integer.parseInt(words[1]) - 1;
                    System.out.println("\tHA! You still have to complete:");
                    taskList[taskNumber].setDone(false);
                    System.out.println(taskList[taskNumber]);
                    break;

                case TODO:
                    //int spaceCut = echo.indexOf(" ");
                    taskDescription = echo.substring(4);
                    taskList[counter] = new Todo(taskDescription);
                    System.out.println("\tGot it. I've added this task:" + "\n" + taskList[counter]);
                    counter += 1;
                    System.out.println("\tNow you have " + counter + " tasks in the list.");
                    break;

                case DEADLINE:
                    taskDescription = echo.substring(8);
                    slashCut = taskDescription.indexOf("/");

                    String taskDeadline = taskDescription.substring(slashCut + 1);
                    taskDescription = taskDescription.substring(0, slashCut);

                    taskList[counter] = new Deadline(taskDescription, taskDeadline);
                    System.out.println("\tGot it. I've added this task:" + "\n" + taskList[counter]);
                    counter += 1;
                    System.out.println("\tNow you have " + counter + " tasks in the list.");
                    break;

                case EVENT:
                    taskDescription = echo.substring(5);
                    slashCut = taskDescription.indexOf("/");

                    String taskDuration = taskDescription.substring(slashCut);
                    taskDescription = taskDescription.substring(0, slashCut);

                    taskList[counter] = new Event(taskDescription, taskDuration);
                    System.out.println("\tGot it. I've added this task:" + "\n" + taskList[counter]);
                    counter += 1;
                    System.out.println("\tNow you have " + counter + " tasks in the list.");
                    break;

                default:
                    break;
            }
            echo = userInput.nextLine();
        }

        System.out.println("\tBye. Hope to see you again soon!");

        userInput.close();
    }
}
