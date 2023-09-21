import luke.constants.*;
import luke.errors.LukeTimeError;;
import luke.tasks.*;
import luke.files.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Luke {
    private static final String BYE_COMMAND = "bye";

    public static void main(String[] args) {
        String logo = "\t _           _        \n"
                + "\t| |    _   _| | _____ \n"
                + "\t| |   | | | | |/ / _ \\\n"
                + "\t| |___| |_| |   <  __/\n"
                + "\t|_____|\\__,_|_|\\_\\___|\n";

        System.out.println("\t" + "Hello! I'm\n" + logo);

        File taskListFile = new File("./luke/files/memory.txt");
        if (taskListFile.exists()) {
            //Memory.readMemory("./memory.txt");
        } else {
            //taskListFile.createNewFile();
        }
        //try {

       // } catch (FileNotFoundException e) {
            System.out.println("No existing memory.");
            File newMemory = new File("./luke/files/memory.txt");
            //Memory taskListFile = new Memory();
        //}


        System.out.println("\t" + "What can I do for you?");

        Task[] taskList = new Task[100];
        Scanner userInput = new Scanner(System.in);
        int counter = 0;
        int taskNumber;

        String echo = userInput.nextLine();

        while (!echo.equals(BYE_COMMAND)) {
            String[] words = echo.split(" "); //to identify usage of features "mark" & "unmark"

            try {
                ActionType action = ActionType.valueOf(words[0].toUpperCase());

                try {
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
                            taskList[counter] = new Todo(echo);

                            System.out.println("\tGot it. I've added this task:" + "\n" + taskList[counter]);
                            counter += 1;
                            System.out.println("\tNow you have " + counter + " tasks in the list.");
                            break;

                        case DEADLINE:
                            try {
                                taskList[counter] = new Deadline(echo);

                                System.out.println("\tGot it. I've added this task:" + "\n" + taskList[counter]);
                                counter += 1;
                                System.out.println("\tNow you have " + counter + " tasks in the list.");
                            } catch (LukeTimeError e) {
                                System.out.println("OOPS!!! There's an error in the deadline's 'do by' date.");
                            }
                            break;

                        case EVENT:
                            try {
                                taskList[counter] = new Event(echo);

                                System.out.println("\tGot it. I've added this task:" + "\n" + taskList[counter]);
                                counter += 1;
                                System.out.println("\tNow you have " + counter + " tasks in the list.");
                            } catch (LukeTimeError e) {
                                System.out.println("OOPS!!! There's an error in the event's start and end time.");
                            }
                            break;

                        default:
                            assert false: "This line should never be reached";
                            break;
                    }
                } catch (IndexOutOfBoundsException e) { //empty for MARK, UNMARK, TO DO description, DEADLINE description, EVENT description
                    System.out.println("OOPS!!! You have missing arguments for " + words[0] + ".");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            echo = userInput.nextLine();
        }

        //store in memory.txt
        try {
            Memory.storeMemory("./src/main/java/luke/files/memory.txt", taskList, counter);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        System.out.println("\tBye. Hope to see you again soon!");

        userInput.close();
    }
}

