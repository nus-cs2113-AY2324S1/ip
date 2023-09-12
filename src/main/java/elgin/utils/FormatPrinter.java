package elgin.utils;


public class FormatPrinter {
    final static String BOT_NAME = "Elgin";
    final static String SEPARATOR = "____________________________________________________________";


    public static void sayGreeting() {
        String[] toPrint = new String[]{
                "Hello! I'm " + BOT_NAME,
                "What can I do for you?"
        };
        formatPrint(toPrint);
    }

    public static void sayBye() {
        formatPrint("Bye. Hope to see you again soon!");
    }

    public static void formatPrint(String[] lines) {
        System.out.println("\t" + SEPARATOR);
        for (String line : lines) {
            System.out.println("\t" + line);
        }
        System.out.println("\t" + SEPARATOR);
    }

    public static void formatPrint(String line) {
        System.out.println("\t" + SEPARATOR);
        System.out.println("\t" + line);
        System.out.println("\t" + SEPARATOR);
    }
}
