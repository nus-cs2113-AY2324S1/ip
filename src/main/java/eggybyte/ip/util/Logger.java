package eggybyte.ip.util;

import eggybyte.ip.data.exception.TipsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An util used for showing log with a more standard way.
 */
public class Logger {
    public static Boolean debugMode = true;

    /**
     * Simply show greeting.
     */
    public static void showGreeting() {
        showLog(" Hello! I'm EggyByte!\n What can I do for you?", true);
    }

    public enum LogLevel {
        DEBUG, WARNING, INFO, ERROR, FATAL, IMPORTANT
    }

    /**
     * Simply showing an object and choose whether to show 2 lines to clamp it.
     * 
     * @param object   The object you want to show.
     * @param showLine Indicate whether to show lines.
     */
    public static void showLog(Object object, Boolean showLine) {
        LogLevel logLevel = debugMode ? LogLevel.DEBUG : LogLevel.INFO;
        String content = object.toString();
        if (object instanceof Exception) {
            logLevel = LogLevel.ERROR;
            content = ((Exception) object).getMessage();
        }
        if (object instanceof TipsException) {
            logLevel = LogLevel.ERROR;
            content = object.toString();
        }
        showLog(content, logLevel, showLine);
    }

    /**
     * Showing a message and choose whether to show 2 lines to clamp it, and also
     * need to choose the LogLevel of this information.
     * 
     * @param content  The message you want to show.
     * @param logLevel The essence level of your message.
     * @param showLine Indicate whether to show lines.
     */
    public static void showLog(String content, LogLevel logLevel, Boolean showLine) {
        if (showLine) {
            customPrint("____________________________________________________________");
        }
        customPrint(
                "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
                        "][" + logLevel + "]");
        String[] lines = content.split("\n");
        for (String line : lines) {
            customPrint(" " + line);
        }
        if (showLine) {
            customPrint("____________________________________________________________\n");
        }
    }

    /**
     * Printing with a tab in front of the content to make it more easy to
     * distinguish the different lines and meaning.
     */
    public static void customPrint(String content) {
        System.out.println("    " + content);
    }

    /**
     * Printing all elements in an array.
     * It's usually used in debuging.
     */
    public static <Type> void printArray(Type[] array) {
        int length = array.length;
        String result = "Array Length : " + length + "\n[";
        for (int i = 0; i < length; i++) {
            result += array[i].toString() + (i < length - 1 ? ", " : "");
        }
        result += "]";
        showLog(result, false);
    }
}
