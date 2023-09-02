import java.util.Scanner;
public class Duke {

    public static void drawLine() {  // Draw horizontal lines
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void hiDude() {  // Print logo and hello message
        String logo = "###            #        \n"
                + "#  #           #        \n"
                + "#  #  #  #   ###   ##   \n"
                + "#  #  #  #  #  #  # ##  \n"
                + "#  #  #  #  #  #  ##    \n"
                + "###    ###   ###   ##   \n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("Hello! I'm your best Dude:)");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public static void storeDude() {  // Add and list some tasks
        Scanner scan = new Scanner(System.in);
        String input = null;
        String[] list = new String[100];
        int curPos = 0;  // Initialise current position
        int index = 1;  // Set index for the list

        while(true) {
            input = scan.nextLine();
            drawLine();
            if(input.equals("bye")) {
                byeDude();
                break;
            } else if(input.equals("list")) {
                for(int i = 0; i < curPos; i++) {
                    System.out.println(index + ". " + list[index-1]);
                    index++;
                }
                drawLine();
            } else {
                list[curPos] = input;
                curPos++;
                System.out.println("added: " + input);
                drawLine();
            }
        }
    }

    public static void byeDude() {  // Print goodbye message
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    public static void main(String[] args) {
        hiDude();
        storeDude();
    }
}
