package chatbot;

public class ChatbotUnknownCommandException extends Exception {
    public ChatbotUnknownCommandException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
