public class Duke {
    public static void printHorizontalLines() {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //ChatBot Starting
        printHorizontalLines();
        System.out.println("Hello! I'm ELVIS");
        System.out.println("What can I do for you?");
        printHorizontalLines();

        //Functionalities of ChatBot
        //Echo.Echo();
        List.List();

        //ChatBot Ending
        printHorizontalLines();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLines();
    }
}
