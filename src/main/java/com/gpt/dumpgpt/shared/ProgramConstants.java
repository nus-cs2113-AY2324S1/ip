package com.gpt.dumpgpt.shared;

import com.gpt.dumpgpt.task.TaskManager;

public final class ProgramConstants {
    public static final int INVALID_POS_NUM = -1;
    private static boolean isEnded = false;

    /**
     * Parses a string into number if is valid
     *
     * @param number string to be parsed
     * @return a positive integer if parsed string was valid
     * otherwise, will return {@link #INVALID_POS_NUM}
     */
    public static int parsePositiveNumber(String number) {
        int taskNumber = INVALID_POS_NUM;

        try {
            taskNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return taskNumber;
        }

        if (taskNumber <= 0) {
            return INVALID_POS_NUM;
        }
        return taskNumber;
    }


    public static void setIsEnded(boolean newIsEnded) {
        isEnded = newIsEnded;
    }

    public static boolean getIsEnded() {
        return isEnded;
    }

    private ProgramConstants() {
    }
}
