public class notChatGPT {
    public static boolean isRunning;
    public static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        isRunning = true;
        introMessage.displayIntroMessage();
        while (isRunning) {
            String[] userInput = UserInput.getUserInput();
            commandResponse.respond(userInput);
        }
    }
}
