public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String startMessage = "____________________________________________________________\n"+
                 "Hello! I'm [YOUR CHATBOT NAME]\n"+
                 "What can I do for you?\n"+
                "____________________________________________________________";
        String endMessage = "Bye. Hope to see you again soon!\n"+
                "____________________________________________________________";

        System.out.println("Hello from\n" + logo);
        System.out.println(startMessage);
        System.out.println(endMessage);


    }
}
