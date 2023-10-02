package kenergeticbot.ui;

import kenergeticbot.TaskList;
import kenergeticbot.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static kenergeticbot.common.Messages.ADD_TASK_MESSAGE;
import static kenergeticbot.common.Messages.DELETE_TASK_MESSAGE;
import static kenergeticbot.common.Messages.GOODBYE_MESSAGE;
import static kenergeticbot.common.Messages.GREETING_MESSAGE;
import static kenergeticbot.common.Messages.MARK_TASK_MESSAGE;
import static kenergeticbot.common.Messages.SEPARATING_LINE;
import static kenergeticbot.common.Messages.TASK_IN_LIST_MESSAGE_PART_1;
import static kenergeticbot.common.Messages.TASK_IN_LIST_MESSAGE_PART_2;
import static kenergeticbot.common.Messages.UNMARK_TASK_MESSAGE;

public class TextUi {

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printLine() {
        System.out.println(SEPARATING_LINE);
    }

    /**
     * Reads the text entered by the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }
    public void printGreetingMessage() {
        showToUser(SEPARATING_LINE,
                GREETING_MESSAGE,
                SEPARATING_LINE);
    }

    public void printExitMessage() {
        showToUser(SEPARATING_LINE,
                GOODBYE_MESSAGE,
                SEPARATING_LINE);
    }

    public void printAddedTaskMessage(TaskList taskList, Task newTask) {
        showToUser(SEPARATING_LINE,
                ADD_TASK_MESSAGE,
                "       " + newTask,
                TASK_IN_LIST_MESSAGE_PART_1 + taskList.getSize() + TASK_IN_LIST_MESSAGE_PART_2,
                SEPARATING_LINE);
    }

    public void printDeleteTaskMessage(TaskList taskList, int listIndex) {
        showToUser(SEPARATING_LINE,
                DELETE_TASK_MESSAGE,
                "       " + taskList.getTask(listIndex - 1),
                TASK_IN_LIST_MESSAGE_PART_1 + (taskList.getSize() - 1) + TASK_IN_LIST_MESSAGE_PART_2);
    }

    public void printMarkTaskMessage(TaskList taskList, int listIndex) {
        showToUser(SEPARATING_LINE, MARK_TASK_MESSAGE,
                "       " + taskList.getTask(listIndex - 1), SEPARATING_LINE);
    }

    public void printUnmarkTaskMessage(TaskList taskList, int listIndex) {
        showToUser(SEPARATING_LINE, UNMARK_TASK_MESSAGE,
                "       " + taskList.getTask(listIndex - 1), SEPARATING_LINE);
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
}
