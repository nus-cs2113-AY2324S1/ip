
public class commandResponse {
    public static void respond(String userInput) {
        String line = "____________________________________________________________\n";
        if (userInput.toUpperCase().equals("BYE")) {
            System.out.println(line + "\n" + "notChatGPT: Bye. Hope to see you again soon!" + "\n" + line);
            notChatGPT.isRunning = false;
        } else {
            System.out.println(line + "\n" + "notChatGPT: " + userInput + "\n" + line);
        }
    }
}
