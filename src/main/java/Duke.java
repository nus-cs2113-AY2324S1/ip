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
        System.out.println("Hello! I'm your best Dude.");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public static void echoDude() {  // Echo the input
        Scanner scan = new Scanner(System.in);
        String echo = null;

        while(true) {
            echo = scan.nextLine();
            drawLine();
            if(echo.equals("bye")) {
                byeDude();
                break;
            } else {
                System.out.println(echo);
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
        echoDude();
    }
}
