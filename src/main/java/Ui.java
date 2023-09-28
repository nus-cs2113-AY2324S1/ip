public class Ui {
    public static void printWelcomeMessage() {
        printLines();
        System.out.println("Hello! I'm En\nWhat can I do for you?");
        printLines();
    }
    public static void printLines() {
        for (int i = 0; i < 30; i++) {
            System.out.print('=');
        }
        System.out.println();
    }
}
