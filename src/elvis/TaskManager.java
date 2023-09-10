package elvis;

import elvis.exception.*;
import elvis.task.Deadline;
import elvis.task.Event;
import elvis.task.Task;
import elvis.task.ToDo;

import java.util.Scanner;

public class TaskManager {
    public static Scanner in = new Scanner(System.in);  //Scanner for Input
    private static int listCount = 0;                   //Counter for the list filled up
    private static final Task[] list = new Task[100];   //Keeps track of all Task Instances made

    public static void inputTask() {
        Miscellaneous.bootUp();
        while (true) {
            String inputBuffer = in.nextLine().trim(); //Scans I/O and all input stored in inputBuffer
            Miscellaneous.printHorizontalLines();

            if (inputBuffer.contains("bye")) {  //Program exit
                break;
            } else if (inputBuffer.equals("help")) {
                System.out.println("No worries, I'm here to help!");
                Miscellaneous.printHorizontalLines();
                Help.help();
                continue;
            } else {
                errorHandler(inputBuffer);      //Checks for any errors and handles them
            }
        }
        Miscellaneous.shutDown();
    }

    public static void errorHandler(String inputBuffer) {
        try {
            errorChecker(inputBuffer);
            taskFunctionManager(inputBuffer);
        } catch (EmptyInputException exception) {
            System.out.println("☹ OOPS!!! The description cannot be empty.");
        } catch (EmptyListException exception) {
            System.out.println("☹ OOPS!!! Nothing to list.");
        } catch (EmptyToDoException exception) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (EmptyMarkException exception) {
            System.out.println("☹ OOPS!!! The description of a mark cannot be empty.");
        } catch (EmptyUnmarkException exception) {
            System.out.println("☹ OOPS!!! The description of an unmark cannot be empty.");
        } catch (EmptyDeadlineException exception) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        } catch (EmptyEventException exception) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        } catch (UnknownInputException exception) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } finally {
            Miscellaneous.printHorizontalLines();
            Help.help();
        }
    }

    public static void errorChecker(String inputBuffer) throws EmptyInputException, EmptyListException,
            EmptyToDoException, EmptyMarkException, EmptyUnmarkException, EmptyDeadlineException,
            EmptyEventException {

        Scanner bufferScanner = new Scanner(inputBuffer);   //Scanner for the buffer
        String firstWord;
        if (!bufferScanner.hasNext()) {                     //Checks for the case when there is no input
            throw new EmptyInputException();
        } else {
            firstWord = bufferScanner.next();
        }
        if (firstWord.equals("list") && listCount == 0) {
            throw new EmptyListException();
        } else if (firstWord.equals("todo") && !bufferScanner.hasNext()) {
            throw new EmptyToDoException();
        } else if (firstWord.equals("mark") && !bufferScanner.hasNext()) {
            throw new EmptyMarkException();
        } else if (firstWord.equals("unmark") && !bufferScanner.hasNext()) {
            throw new EmptyUnmarkException();
        } else if (firstWord.equals("deadline") && !bufferScanner.hasNext()) {
            throw new EmptyDeadlineException();
        } else if (firstWord.equals("event") && !bufferScanner.hasNext()) {
            throw new EmptyEventException();
        }
    }

    public static void taskFunctionManager(String inputBuffer) throws UnknownInputException {
        Scanner bufferScanner = new Scanner(inputBuffer);   //Scanner for the buffer
        String firstWord = bufferScanner.next().trim();     //Stores first word in the input
        boolean hasInteger = bufferScanner.hasNextInt();    //Indicates that some integer was input
        int numberInput = -1;                               //Stores the number input
        if (hasInteger) {
            numberInput = bufferScanner.nextInt();
        }

        //Functionalities
        if (inputBuffer.equals("list")) {
            listOut();
        } else if (firstWord.equals("todo")) {
            insertToDo(inputBuffer);
        } else if (firstWord.equals("mark") && hasInteger && !bufferScanner.hasNext()) {
            markTask(numberInput);
        } else if (firstWord.equals("unmark") && hasInteger && !bufferScanner.hasNext()) {
            unmarkTask(numberInput);
        } else if (firstWord.equals("deadline")) {
            insertDeadline(inputBuffer);
        } else if (firstWord.equals("event")) {
            insertEvent(inputBuffer);
        } else {
            throw new UnknownInputException();
        }
    }

    //Print out everything in the list
    public static void listOut() {
        Miscellaneous.printHorizontalLines();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < listCount; i++) {
            System.out.print(i + 1 + "." + "[" + list[i].getTaskType() + "]");
            System.out.print("[" + list[i].getStatus() + "] " + list[i].getDescription());

            // Additional details required for Deadline and Event
            if (list[i] instanceof Deadline) {
                Deadline deadlineTask = (Deadline) list[i];
                System.out.println(" (by: " + deadlineTask.getDate() + ")");
            } else if (list[i] instanceof Event) {
                Event eventTask = (Event) list[i];
                System.out.println(" (from: " + eventTask.getStartTime() + " to: " + eventTask.getEndTime() + ")");
            }
        }
        Miscellaneous.printHorizontalLines();
    }

    public static void markTask(int numberInput) {
        int nthTask = numberInput - 1;
        if (numberInput > listCount || nthTask < 0 || listCount == 0) {
            System.out.println("No such Task!");
            return;
        }
        list[nthTask].setStatus(true);
        Miscellaneous.printHorizontalLines();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(nthTask + 1 + "." + "[" + list[nthTask].getTaskType() + "]" +
                "[" + list[nthTask].getStatus() + "] " + list[nthTask].getDescription());
        Miscellaneous.printHorizontalLines();
    }

    public static void unmarkTask(int numberInput) {
        int nthTask = numberInput - 1;  //Remember that index of list starts from 0
        if (numberInput > listCount || nthTask < 0 || listCount == 0) {
            System.out.println("No such Task!");
            return;
        }
        list[nthTask].setStatus(false);
        Miscellaneous.printHorizontalLines();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(nthTask + 1 + "." + "[" + list[nthTask].getTaskType() + "]" +
                "[" + list[nthTask].getStatus() + "] " + list[nthTask].getDescription());
        Miscellaneous.printHorizontalLines();
    }

    public static void insertToDo(String inputBuffer) {
        Miscellaneous.printHorizontalLines();
        list[listCount] = new ToDo(inputBuffer.trim().replace("todo ", ""));
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + 'T' + "]" +
                "[" + list[listCount].getStatus() + "] " + list[listCount].getDescription());
        System.out.println("Now you have " + (listCount+1) + " task(s) in the list.");
        Miscellaneous.printHorizontalLines();
        listCount++;
    }

    public static void insertDeadline(String inputBuffer) {
        Miscellaneous.printHorizontalLines();
        String date = inputBuffer.substring(inputBuffer.indexOf("/by") + 3).trim();
        String deadlineWithDate = inputBuffer.replace("deadline ", "").trim();
        String deadline = deadlineWithDate.substring(0, deadlineWithDate.indexOf("/by")).trim();

        list[listCount] = new Deadline(deadline, date);
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + 'D' + "]" +
                "[" + list[listCount].getStatus() + "] " + list[listCount].getDescription() +
                " (by: " + date + ")");
        System.out.println("Now you have " + (listCount+1) + " task(s) in the list.");
        Miscellaneous.printHorizontalLines();
        listCount++;
    }

    public static void insertEvent(String inputBuffer) {
        Miscellaneous.printHorizontalLines();
        String startTime = inputBuffer.substring(inputBuffer.indexOf("/from") + 5, inputBuffer.indexOf("/to")).trim();
        String endTime = inputBuffer.substring(inputBuffer.indexOf("/to") + 3).trim();
        String eventWithTime = inputBuffer.replace("event ", "").trim();
        String event = eventWithTime.substring(0, eventWithTime.indexOf("/from")).trim();

        list[listCount] = new Event(event, startTime, endTime);
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + 'D' + "]" +
                "[" + list[listCount].getStatus() + "] " + list[listCount].getDescription() +
                " (from: " + startTime + " to: " + endTime + ")");
        System.out.println("Now you have " + (listCount+1) + " task(s) in the list.");
        Miscellaneous.printHorizontalLines();
        listCount++;
    }
}