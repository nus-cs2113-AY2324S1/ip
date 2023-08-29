import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class Doli {

    // method to print a horizontal dashed line to separate fields
    public static void printLine() {
        for (int i = 50; i > 0; i--) {
            System.out.print("_");
        }
        System.out.println("");
    }
    public static void main(String[] args) {

        // Welcome design and introduction to the chatbot

        String design = " ____       _    \n" +
                "|  _  \\    | | [_]\n" +
                "| | | |____| |  _\n" +
                "| |_| | [] | | | | \n" +
                "|____/|____|__||_| \n\n";
        printLine();
        System.out.println("\nHello! My name is\n" + design + "What can I do for you?");
        printLine();

        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        int numberOfItems = 0;
        Task[] tasks = new Task[100];

        while (line.trim().equalsIgnoreCase("bye") == false){

            if (line.trim().toLowerCase().contains("mark")){
                int taskNumber = Integer.parseInt(line.substring(line.indexOf(" ")+1));
                if (taskNumber > numberOfItems) {
                    System.out.println("The specific task has not yet been added to your list.");
                    line = in.nextLine();
                    continue;
                }
                if (line.trim().toLowerCase().contains("unmark")) {
                    tasks[taskNumber - 1].markTaskAsNotDone();
                } else {
                    tasks[taskNumber - 1].markTaskAsDone();
                }
                line = in.nextLine();
                continue;
            }

            if (line.trim().equalsIgnoreCase("list")){
                Task[] array = Arrays.copyOf(tasks, numberOfItems);
                for (int i = 0; i < array.length; i++){
                    System.out.println(String.format("%d. [%c] ", i + 1, array[i].getStatusIcon()) + array[i].getDescription());
                }
            } else {
                tasks[numberOfItems] = new Task(line);
                numberOfItems++;
                System.out.println("added: " + line);
            }
            printLine();
            line = in.nextLine();
        }
        printLine();
        System.out.println("\nIt was a pleasure, bye. See you later!");

    }
}
