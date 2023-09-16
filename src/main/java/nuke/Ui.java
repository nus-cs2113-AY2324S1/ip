package nuke;

public class Ui {
    public static final String LOGO =
            "     _.-^^---....,,--      \n" +
            " _--                  --_  \n" +
            "<                        >)\n" +
            "|                         |\n" +
            " \\._                   _./ \n" +
            "    ```--. . , ; .--'''    \n" +
            "          | |   |          \n" +
            "       .-=||  | |=-.       \n" +
            "       `-=#$%&%$#=-'       \n" +
            "          | ;  :|          \n" +
            " _____.,-#%&$@%#&#~,._____ ";

    public static void printWelcome() {
        System.out.println(LOGO);
        System.out.println();
        System.out.println("[@] Hello! I'm Nuke.");
    }

    public static void printWelcomeAfter() {
        System.out.println("[@] What can I do for you?");
        System.out.println();
    }

    public static void printBlankLine() {
        System.out.println();
    }

    public static void printBye() {
        System.out.println("[@] Bye. Hope to see you again soon!");
    }

    public static void printAddedTask(String addedTask, int taskCnt) {
        System.out.println("[@] Got it. I've added this task:");
        System.out.println("  " + addedTask);
        System.out.printf("[@] Now you have %d task%s in the list.\n", taskCnt, taskCnt == 1? "": "s");
    }

    public static void printListOfTasks(String[] tasks) {
        if(tasks.length == 0) {
            System.out.println("[@] There are no tasks in your list.");
            return;
        }
        System.out.println("[@] Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.printf("%d.%s\n", i + 1, tasks[i]);
        }
    }

    public static void printMarkedTask(String markedTask) {
        System.out.println("[@] Nice! I've marked this task as done:");
        System.out.println("  " + markedTask);
    }

    public static void printUnmarkedTask(String unmarkedTask) {
        System.out.println("[@] OK, I've marked this task as not done yet:");
        System.out.println("  " + unmarkedTask);
    }

    public static void printCommandError(String description, String detail) {
        System.out.println("[@] Wrong input; " + description);
        System.out.println("[@] " + detail);
    }

    public static void printTaskLoadError(String backupFilePath) {
        System.out.println();
        System.out.println("[@] Error occurred while loading the tasks!");
        System.out.println("[@] I backed up your save file.");
        System.out.println("[@] Path: " + backupFilePath);
        System.out.println();
    }
    public static void printTaskFileCopyError(String filePath) {
        System.out.println();
        System.out.println("[@] Error occurred while loading the tasks!");
        System.out.println("[@] I tried to back up your save file, but it failed as well.");
        System.out.println("[@] You can back up your save file manually.");
        System.out.println("[@] Path: " + filePath);
        System.out.println();
        System.out.println("[@] Ignore and continue to run by entering 'ignore'.");
        System.out.println();
    }
    public static void printTaskSaveError(String[] tasks) {
        System.out.println("[@] Error occurred while saving the tasks!");
        System.out.println("[@] I will show all the tasks you have added.");
        printListOfTasks(tasks);
        System.out.println();
        System.out.println("[@] Please back up your save file manually.");
        System.out.println("[@] Continue to quit by entering non-empty input.");
    }
}
