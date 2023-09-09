public class NotChatGPT {
    public static boolean isRunning;
    public static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        isRunning = true;
        IntroMessage.displayIntroMessage();
        while (isRunning) {
            String[] userInput = UserInput.getUserInput();
            CommandResponse.respond(userInput);
        }
    }
}
