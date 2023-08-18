public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLines();
        System.out.println("Hello! I'm Remy");
        System.out.println("What can I do for you?");
        printLines();
        System.out.println("Bye. Hope to see you again soon!");
        printLines();
    }
    
    static void printLines() {
    	System.out.println("____________________________________________________________");
    }
}
