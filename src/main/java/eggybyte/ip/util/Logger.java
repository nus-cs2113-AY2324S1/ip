package eggybyte.ip.util;

import eggybyte.ip.data.exception.TipsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static Boolean debugMode = true;

    public static void showGreeting() {
        showLog("Hello! I'm EggyByte!\nWhat can I do for you?", true);
    }

    public enum LogLevel {
        DEBUG, WARNING, INFO, ERROR, FATAL
    }

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

    public static void showLog(String content, LogLevel logLevel, Boolean showLine) {
        if (showLine) {
            System.out.println("____________________________________________________________");
        }
        System.out.println(
                "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "][" + logLevel + "]\n"
                        + content);
        if (showLine) {
            System.out.println("____________________________________________________________\n");
        }
    }

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
