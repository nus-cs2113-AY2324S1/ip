package elvis;

import elvis.task.Task;
import elvis.task.ToDo;
import elvis.task.Deadline;
import elvis.task.Event;
import elvis.exception.EmptyDeadlineException;
import elvis.exception.EmptyDeleteException;
import elvis.exception.EmptyEventException;
import elvis.exception.EmptyInputException;
import elvis.exception.EmptyListException;
import elvis.exception.EmptyMarkException;
import elvis.exception.EmptyToDoException;
import elvis.exception.EmptyUnmarkException;
import elvis.exception.UnknownInputException;
import java.util.Scanner;
import java.util.ArrayList;

//For adding, removing, marking, unmarking of Tasks
public class TaskManager {
    public static Scanner in = new Scanner(System.in);  //Scanner for Input
    private static ArrayList<Task> tasks = new ArrayList<>();   //Keeps track of all Task Instances made

    public static void inputTask() {
        SystemOperation.bootUp();
        while (true) {
            //System.out.println("Size of tasks is: " + tasks.size());
            String inputBuffer = in.nextLine().trim(); //Scans I/O and all input stored in inputBuffer

            if (inputBuffer.contains("bye")) {  //Program exit
                break;
            } else if (inputBuffer.equals("help")) {
                SystemOperation.printHorizontalLines();
                System.out.println("No worries, I'm here to help!");
                SystemOperation.printHorizontalLines();
                Help.helper();
            } else {
                SystemOperation.printHorizontalLines();
                errorHandler(inputBuffer);      //Checks for any errors and handles them
                SystemOperation.printHorizontalLines();
            }
        }
        SystemOperation.shutDown();
    }

    public static void errorHandler(String inputBuffer) {
        boolean validInput = false;
        try {
            errorChecker(inputBuffer);
            functionalityManager(inputBuffer);
            validInput = true;
        } catch (EmptyInputException exception) {
            System.out.println("☹ OOPS!!! The description cannot be empty.");
        } catch (EmptyListException exception) {
            System.out.println("☹ OOPS!!! Nothing to list.");
        } catch (EmptyDeleteException exception) {
            System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
        } catch (EmptyMarkException exception) {
            System.out.println("☹ OOPS!!! The description of a mark cannot be empty.");
        } catch (EmptyUnmarkException exception) {
            System.out.println("☹ OOPS!!! The description of an unmark cannot be empty.");
        } catch (EmptyToDoException exception) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (EmptyDeadlineException exception) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        } catch (EmptyEventException exception) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        } catch (UnknownInputException exception) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } finally {
            if (!validInput) {
                SystemOperation.printHorizontalLines();
                Help.helper();
            }
        }
    }

    public static void errorChecker(String inputBuffer) throws EmptyInputException, EmptyListException,
            EmptyToDoException, EmptyMarkException, EmptyUnmarkException, EmptyDeadlineException,
            EmptyEventException, EmptyDeleteException {

        Scanner bufferScanner = new Scanner(inputBuffer);   //Scanner for the buffer
        String firstWord;
        if (!bufferScanner.hasNext()) {                     //Checks for the case when there is no input
            throw new EmptyInputException();
        } else {
            firstWord = bufferScanner.next();
        }

        if (firstWord.equals("list") && tasks.isEmpty()) {
            throw new EmptyListException();
        }  else if (firstWord.equals("delete") && !bufferScanner.hasNext()) {
            throw new EmptyDeleteException();
        }else if (firstWord.equals("mark") && !bufferScanner.hasNext()) {
            throw new EmptyMarkException();
        } else if (firstWord.equals("unmark") && !bufferScanner.hasNext()) {
            throw new EmptyUnmarkException();
        } else if (firstWord.equals("todo") && !bufferScanner.hasNext()) {
            throw new EmptyToDoException();
        } else if (firstWord.equals("deadline") && !bufferScanner.hasNext()) {
            throw new EmptyDeadlineException();
        } else if (firstWord.equals("event") && !bufferScanner.hasNext()) {
            throw new EmptyEventException();
        }
    }

    public static void functionalityManager(String inputBuffer) throws UnknownInputException {
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
        } else if (firstWord.equals("delete") && hasInteger && !bufferScanner.hasNext()) {
            taskRemover(numberInput);
        } else if (firstWord.equals("mark") && hasInteger && !bufferScanner.hasNext()) {
            taskMarker(numberInput);
        } else if (firstWord.equals("unmark") && hasInteger && !bufferScanner.hasNext()) {
            taskUnmarker(numberInput);
        } else if (firstWord.equals("todo")) {
            insertToDo(inputBuffer);
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
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            print(i);
        }
        printTaskCount();
    }

    public static void taskRemover(int numberInput) {
        int nthTask = numberInput - 1;
        if (nthTask > tasks.size()-1 || nthTask < 0 || tasks.isEmpty()) {
            System.out.println("No such Task!");
            return;
        }

        System.out.println("Noted. I've removed this task:");
        print(nthTask);
        tasks.remove(nthTask);
        printTaskCount();
    }

    public static void taskMarker(int numberInput) {
        int nthTask = numberInput - 1;
        if (nthTask > tasks.size()-1 || nthTask < 0 || tasks.isEmpty()) {
            System.out.println("No such Task!");
            return;
        }
        tasks.get(nthTask).setStatus(true);
        System.out.println("Nice! I've marked this task as done:");
        print(nthTask);
    }

    public static void taskUnmarker(int numberInput) {
        int nthTask = numberInput - 1;  //Remember that index of list starts from 0
        if (nthTask > tasks.size()-1 || nthTask < 0 || tasks.isEmpty()) {
            System.out.println("No such Task!");
            return;
        }
        tasks.get(nthTask).setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        print(nthTask);
    }

    public static void insertToDo(String inputBuffer) {
        tasks.add(new ToDo(inputBuffer.trim().replace("todo ", "")));
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + 'T' + "]" +
                "[" + tasks.get(tasks.size()-1).getStatus() + "] " + tasks.get(tasks.size()-1).getDescription());
        System.out.println("Now you have " + (tasks.size()) + " task(s) in the list.");
    }

    public static void insertDeadline(String inputBuffer) {
        String date = inputBuffer.substring(inputBuffer.indexOf("/by") + 3).trim();
        String deadlineWithDate = inputBuffer.replace("deadline ", "").trim();
        String deadline = deadlineWithDate.substring(0, deadlineWithDate.indexOf("/by")).trim();

        tasks.add(new Deadline(deadline, date));
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + 'D' + "]" +
                "[" + tasks.get(tasks.size()-1).getStatus() + "] " + tasks.get(tasks.size()-1).getDescription() +
                " (by: " + date + ")");
        System.out.println("Now you have " + (tasks.size()) + " task(s) in the list.");
    }

    public static void insertEvent(String inputBuffer) {
        String startTime = inputBuffer.substring(inputBuffer.indexOf("/from") + 5, inputBuffer.indexOf("/to")).trim();
        String endTime = inputBuffer.substring(inputBuffer.indexOf("/to") + 3).trim();
        String eventWithTime = inputBuffer.replace("event ", "").trim();
        String event = eventWithTime.substring(0, eventWithTime.indexOf("/from")).trim();

        tasks.add(new Event(event, startTime, endTime));
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + 'D' + "]" +
                "[" + tasks.get(tasks.size()-1).getStatus() + "] " + tasks.get(tasks.size()-1).getDescription() +
                " (from: " + startTime + " to: " + endTime + ")");
        System.out.println("Now you have " + (tasks.size()) + " task(s) in the list.");
    }

    //Prints task eg "[T][X] read book" without the preceding index eg"1."
    public static void print(int i) {
        boolean isToDo = false; //If "todo" is printed last, need additional line separator
        System.out.print("[" + tasks.get(i).getTaskType() + "]");
        System.out.print("[" + tasks.get(i).getStatus() + "] " + tasks.get(i).getDescription());
        isToDo = true;

        // Additional details required for Deadline and Event
        if (tasks.get(i) instanceof Deadline) {
            Deadline deadlineTask = (Deadline) tasks.get(i);
            System.out.println(" (by: " + deadlineTask.getDate() + ")");
            isToDo = false;
        } else if (tasks.get(i) instanceof Event) {
            Event eventTask = (Event) tasks.get(i);
            System.out.println(" (from: " + eventTask.getStartTime() + " to: " + eventTask.getEndTime() + ")");
            isToDo = false;
        }
        if (isToDo) {
            System.out.print(System.lineSeparator());
        }
    }

    public static void printTaskCount() {
        System.out.println("Now you have " + (tasks.size()) + " task(s) in the list.");
    }
}