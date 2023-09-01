import java.util.Scanner;

//Task Class that stores the descriptions of task and wherther it has been done
public class Task {
    private static int listCount = 0;
    private static Task[] list = new Task[100]; //Keeps track of all Task Instances made
    protected String toDo;
    protected boolean isDone;

    //Constructor of the Task Class
    public Task(String toDo) {
        this.toDo = toDo;
        this.isDone = false;
    }

    /**
     * Adds and mark/unmark tasks to be done.
     * Uses static variable Task[] list to store all the tasks as an array
     *
     */
    public static void manageTask() {
        Scanner in = new Scanner(System.in);    //Scanner for standard input
        Duke.bootUp();
        while (true) {
            String inputBuffer = in.nextLine(); //Scans for the input
            Scanner bufferScanner = new Scanner(inputBuffer);   //All inputs first stored in inputBuffer
            if (!bufferScanner.hasNext()) {
                System.out.println("Please input a valid input");
                continue;
            }
            String firstWord = bufferScanner.next();
            boolean hasInteger = bufferScanner.hasNextInt();
            int numberInput = -1;
            if (hasInteger) {
                numberInput = bufferScanner.nextInt();
            }

            if (inputBuffer.contains("bye")) {
                break;
            } else if (inputBuffer.equals("list")) {
                if (listCount == 0) {
                    Duke.printHorizontalLines();
                    System.out.println("Nothing to list");
                    Duke.printHorizontalLines();
                    continue;
                }
                Duke.printHorizontalLines();
                for (int i = 0; i < listCount; i++) {
                    System.out.println(i + 1 + "." + "[" + list[i].getStatus() + "] " + list[i].getToDo());
                }
                Duke.printHorizontalLines();
            } else if (firstWord.equals("unmark") && hasInteger && !bufferScanner.hasNext()) {
                int nthTask = numberInput - 1;
                if (numberInput > listCount || nthTask < 0 || listCount == 0) {
                    System.out.println("No such Task!");
                    continue;
                }
                list[nthTask].setStatus(false);
                Duke.printHorizontalLines();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(nthTask + 1 + "." + "[" + list[nthTask].getStatus() + "] " + list[nthTask].getToDo());
                Duke.printHorizontalLines();
            } else if (firstWord.equals("mark") && hasInteger && !bufferScanner.hasNext()) {
                int nthTask = numberInput - 1;
                if (numberInput > listCount || nthTask < 0 || listCount == 0) {
                    System.out.println("No such Task!");
                    continue;
                }
                list[nthTask].setStatus(true);
                Duke.printHorizontalLines();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(nthTask + 1 + "." + "[" + list[nthTask].getStatus() + "] " + list[nthTask].getToDo());
                Duke.printHorizontalLines();
            } else {
                Duke.printHorizontalLines();
                list[listCount] = new Task(inputBuffer.trim());
                System.out.println("added: " + list[listCount].getToDo());
                Duke.printHorizontalLines();
                listCount++;
            }
        }
        Duke.shutDown();
        return;
    }

    //Getting the To-Do attribute of the task instance
    public String getToDo() {
        return this.toDo;
    }

    //Getting the isDone attribute of the task instance
    public String getStatus() {
        return (this.isDone ? "X" : " ");    // mark done task with X
    }

    //Setting the status of the isDone
    public void setStatus(boolean truthValue) {
        this.isDone = truthValue;
    }
}
