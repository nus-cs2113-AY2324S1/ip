import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|_____|\\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        Task[] theList = new Task[100];
        Scanner userInput = new Scanner(System.in);
        int counter = 0;

        String echo = userInput.nextLine();
        int taskNumber;

        while (!echo.equals("bye")) {
            String[] words = echo.split(" "); //to identify usage of features "mark" & "unmark"

            switch (words[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i += 1) {
                        System.out.print((i + 1) + ".");
                        System.out.println(theList[i]);
                    }
                    break;
                case "mark":
                    taskNumber = Integer.parseInt(words[1]) - 1;
                    System.out.println("Woohoo! You have accomplished:");
                    theList[taskNumber].setDone(true);
                    System.out.println(theList[taskNumber]);
                    break;
                case "unmark":
                    taskNumber = Integer.parseInt(words[1]) - 1;
                    System.out.println("HA! You still have to complete:");
                    theList[taskNumber].setDone(false);
                    System.out.println(theList[taskNumber]);
                    break;
                default:
                    int spaceCut = echo.indexOf(" ");
                    String taskType = echo.substring(0, spaceCut);
                    String taskDescription = echo.substring(spaceCut);
                    int slashCut = taskDescription.indexOf("/");

                    //TaskType type = TaskType.words[0];

                    switch (taskType) {
                        case "todo":
                            theList[counter] = new Todo(taskDescription);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(theList[counter]);
                            counter += 1;
                            System.out.println("Now you have " + counter + " tasks in the list.");
                            break;
                        case "deadline":
                            String taskDeadline = taskDescription.substring(slashCut);
                            taskDescription = taskDescription.substring(0, slashCut);

                            theList[counter] = new Deadline(taskDescription, taskDeadline);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(theList[counter]);
                            counter += 1;
                            System.out.println("Now you have " + counter + " tasks in the list.");
                            break;
                        case "event":
                            String taskDuration = taskDescription.substring(slashCut);
                            taskDescription = taskDescription.substring(0, slashCut);

                            theList[counter] = new Event(taskDescription, taskDuration);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(theList[counter]);
                            counter += 1;
                            System.out.println("Now you have " + counter + " tasks in the list.");
                            break;
                        default:
                            break;
                    }
            }
            echo = userInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

        userInput.close();
    }
}
