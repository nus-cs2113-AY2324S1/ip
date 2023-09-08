public class Elvis {
    //Logo for ELVIS the Chatbot
    public static final String LOGO =
            "\n" +
            "          _____                      _____            _____                      _____                      _____          \n" +
            "         /\\    \\                    /\\    \\          /\\    \\                    /\\    \\                    /\\    \\         \n" +
            "        /::\\    \\                  /::\\____\\        /::\\____\\                  /::\\    \\                  /::\\    \\        \n" +
            "       /::::\\    \\                /:::/    /       /:::/    /                  \\:::\\    \\                /::::\\    \\       \n" +
            "      /::::::\\    \\              /:::/    /       /:::/    /                    \\:::\\    \\              /::::::\\    \\      \n" +
            "     /:::/\\:::\\    \\            /:::/    /       /:::/    /                      \\:::\\    \\            /:::/\\:::\\    \\     \n" +
            "    /:::/__\\:::\\    \\          /:::/    /       /:::/____/                        \\:::\\    \\          /:::/__\\:::\\    \\    \n" +
            "   /::::\\   \\:::\\    \\        /:::/    /        |::|    |                         /::::\\    \\         \\:::\\   \\:::\\    \\   \n" +
            "  /::::::\\   \\:::\\    \\      /:::/    /         |::|    |     _____      ____    /::::::\\    \\      ___\\:::\\   \\:::\\    \\  \n" +
            " /:::/\\:::\\   \\:::\\    \\    /:::/    /          |::|    |    /\\    \\    /\\   \\  /:::/\\:::\\    \\    /\\   \\:::\\   \\:::\\    \\ \n" +
            "/:::/__\\:::\\   \\:::\\____\\  /:::/____/           |::|    |   /::\\____\\  /::\\   \\/:::/  \\:::\\____\\  /::\\   \\:::\\   \\:::\\____\\\n" +
            "\\:::\\   \\:::\\   \\::/    /  \\:::\\    \\           |::|    |  /:::/    /  \\:::\\  /:::/    \\::/    /  \\:::\\   \\:::\\   \\::/    /\n" +
            " \\:::\\   \\:::\\   \\/____/    \\:::\\    \\          |::|    | /:::/    /    \\:::\\/:::/    / \\/____/    \\:::\\   \\:::\\   \\/____/ \n" +
            "  \\:::\\   \\:::\\    \\         \\:::\\    \\         |::|____|/:::/    /      \\::::::/    /              \\:::\\   \\:::\\    \\     \n" +
            "   \\:::\\   \\:::\\____\\         \\:::\\    \\        |:::::::::::/    /        \\::::/____/                \\:::\\   \\:::\\____\\    \n" +
            "    \\:::\\   \\::/    /          \\:::\\    \\       \\::::::::::/____/          \\:::\\    \\                 \\:::\\  /:::/    /    \n" +
            "     \\:::\\   \\/____/            \\:::\\    \\       ~~~~~~~~~~                 \\:::\\    \\                 \\:::\\/:::/    /     \n" +
            "      \\:::\\    \\                 \\:::\\    \\                                  \\:::\\    \\                 \\::::::/    /      \n" +
            "       \\:::\\____\\                 \\:::\\____\\                                  \\:::\\____\\                 \\::::/    /       \n" +
            "        \\::/    /                  \\::/    /                                   \\::/    /                  \\::/    /        \n" +
            "         \\/____/                    \\/____/                                     \\/____/                    \\/____/         \n" +
            "                                                                                                                                \n";

    public static void printHorizontalLines() {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.print(System.lineSeparator());
    }

    public static void bootUp() {
        //ChatBot BootUp
        System.out.println(LOGO);
        printHorizontalLines();
        System.out.println("Hello! I'm ELVIS");
        System.out.println("What can I do for you?");
        printHorizontalLines();
    }

    public static void shutDown() {
        //ChatBot Ending
        printHorizontalLines();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLines();
    }

    public static void main(String[] args) {

        //Functionalities of ChatBot
        //Echo.shoutEcho(); (WEEK 1)
        //List.makeList();  (WEEK 2)
        TaskManager.manageTask();
    }
}
