public class XiaoAiBot {
    private final String BOT_NAME = "XiaoAi";
    private final String SPLIT_LINE = "____________________________________________";
    private final String GREET_MESSAGE = "Welcome back, master!\n" +
            BOT_NAME + " here. What can I do for you?";

    private final String QUIT_MESSAGE = "See you next time, master!";

    private void sendMessages(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(SPLIT_LINE);
    }

    public void start() {
        sendMessages(GREET_MESSAGE);
        sendMessages(QUIT_MESSAGE);
    }
}
