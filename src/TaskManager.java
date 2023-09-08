import java.util.Scanner;

public class TaskManager {

    public static Scanner in = new Scanner(System.in);  //Scanner for Input
    private static int listCount = 0;                   //Counter for the list filled up
    private static final Task[] list = new Task[100];   //Keeps track of all Task Instances made

    public static void listOut() {
        if (listCount == 0) {   //If there's nothing the list
            Elvis.printHorizontalLines();
            System.out.println("Nothing to list");
            Elvis.printHorizontalLines();
        } else {                //Print out everything in the list
            Elvis.printHorizontalLines();
            System.out.println("Here are the tasks in your list: ");

            for (int i = 0; i < listCount; i++) {
                System.out.print(i + 1 + "." + "[" + list[i].getTaskType() + "]");
                System.out.print("[" + list[i].getStatus() + "] " + list[i].getDescription());

                // Check if the task is of type 'D' (Deadline) and cast it to Deadline if so
                if (list[i] instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) list[i];
                    System.out.println(" (by: " + deadlineTask.getDate() + ")");
                } else if (list[i] instanceof Event) {
                    Event eventTask = (Event) list[i];
                    System.out.println(" (from: " + eventTask.getStartTime() + " to: " + eventTask.getEndTime() + ")");
                } else {
                    System.out.println("");
                }
            }
            Elvis.printHorizontalLines();
        }
    }

    public static void markTask(int numberInput) {
        int nthTask = numberInput - 1;
        if (numberInput > listCount || nthTask < 0 || listCount == 0) {
            System.out.println("No such Task!");
            return;
        }
        list[nthTask].setStatus(true);
        Elvis.printHorizontalLines();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(nthTask + 1 + "." + "[" + list[nthTask].getTaskType() + "]" +
                "[" + list[nthTask].getStatus() + "] " + list[nthTask].getDescription());
        Elvis.printHorizontalLines();
    }

    public static void unmarkTask(int numberInput) {
        int nthTask = numberInput - 1;  //Remember that index of list starts from 0
        if (numberInput > listCount || nthTask < 0 || listCount == 0) {
            System.out.println("No such Task!");
            return;
        }
        list[nthTask].setStatus(false);
        Elvis.printHorizontalLines();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(nthTask + 1 + "." + "[" + list[nthTask].getTaskType() + "]" +
                "[" + list[nthTask].getStatus() + "] " + list[nthTask].getDescription());
        Elvis.printHorizontalLines();
    }

    public static void insertToDo(String inputBuffer) {
        Elvis.printHorizontalLines();
        list[listCount] = new ToDo(inputBuffer.trim().replace("todo ", ""));
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + 'T' + "]" +
                "[" + list[listCount].getStatus() + "] " + list[listCount].getDescription());
        System.out.println("Now you have " + (listCount+1) + " task(s) in the list.");
        Elvis.printHorizontalLines();
        listCount++;
    }

    public static void insertDeadline(String inputBuffer) {
        Elvis.printHorizontalLines();
        String date = inputBuffer.substring(inputBuffer.indexOf("/by") + 3).trim();
        String deadlineWithDate = inputBuffer.replace("deadline ", "").trim();
        String deadline = deadlineWithDate.substring(0, deadlineWithDate.indexOf("/by")).trim();

        list[listCount] = new Deadline(deadline, date);
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + 'D' + "]" +
                "[" + list[listCount].getStatus() + "] " + list[listCount].getDescription() +
                " (by: " + date + ")");
        System.out.println("Now you have " + (listCount+1) + " task(s) in the list.");
        Elvis.printHorizontalLines();
        listCount++;
    }

    public static void insertEvent(String inputBuffer) {
        Elvis.printHorizontalLines();
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
        Elvis.printHorizontalLines();
        listCount++;
    }

    public static void manageTask() {
        Elvis.bootUp();

        while (true) {
            //Preparation stage
            String inputBuffer = in.nextLine(); //Scans I/O and all input stored in inputBuffer
            Scanner bufferScanner = new Scanner(inputBuffer);   //Scanner for the buffer
            if (!bufferScanner.hasNext()) { //Checks for the case when there is no input
                System.out.println("Invalid input!");
                continue;
            }
            String firstWord = bufferScanner.next().trim();     //Stores first word in the input
            boolean hasInteger = bufferScanner.hasNextInt();    //Indicates that some integer was input
            int numberInput = -1;    //Stores the number input
            if (hasInteger) {
                numberInput = bufferScanner.nextInt();
            }

            //Functionalities
            if (inputBuffer.contains("bye")) {  //Program exit
                break;
            } else if (inputBuffer.equals("list")) {
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
                System.out.println("Invalid input!");
            }
        }
        Elvis.shutDown();
    }
}
