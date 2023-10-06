package chatbot;

public class Ui {
    public void showGreetingMessage() {
        String greetingMsg = "____________________________________________________________\n" +
                " Hello! I'm Chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greetingMsg);
    }
    public void showByeMessage() {
        String byeMsg = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(byeMsg);
    }

    public void showError(String message, boolean addLines) {
        if( addLines ) {
            System.out.println("____________________________________________________________");
        }
        System.out.println("Unknown exception. Error message: "  + message);
        if( addLines ) {
            System.out.println("____________________________________________________________");
        }
    }
}
