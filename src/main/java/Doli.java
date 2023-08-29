import java.util.Scanner;


public class Doli {
    public static void printLine() {
        for (int i = 50; i > 0; i--) {
            System.out.print("_");
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        String design = " ____       _    \n" +
                "|  _  \\    | | [_]\n" +
                "| | | |____| |  _\n" +
                "| |_| | [] | | | | \n" +
                "|____/|____|__||_| \n\n";
        printLine();
        System.out.println("\nHello! My name is\n" + design + "What can I do for you?");
        printLine();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (line.trim().equalsIgnoreCase("bye") == false){
            System.out.println(line);
            printLine();
            line = in.nextLine();
        }
        printLine();
        System.out.println("\nIt was a pleasure, bye. See you later!");

    }
}
