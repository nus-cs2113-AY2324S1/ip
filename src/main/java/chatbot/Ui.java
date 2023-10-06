package chatbot;

public class Ui {
    /**
     * Show greeting message when app first launches.
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void showGreetingMessage() {
        String greetingMsg = "____________________________________________________________\n" +
                " Hello! I'm Chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greetingMsg);
    }
    /**
     * Show bye message when app is closing
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void showByeMessage() {
        String byeMsg = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(byeMsg);
    }
    /**
     * Show an error message, with additional formatting possible
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
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
