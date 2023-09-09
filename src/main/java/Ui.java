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
            " _____.,-#%&$@%#&#~,._____ \n";

    public static void printWelcome() {
        System.out.println(LOGO);
        System.out.println();
        System.out.println("[@] Hello! I'm Nuke.");
        System.out.println("[@] What can I do for you?");
        System.out.println();
    }

    public static void printBye() {
        System.out.println("[@] Bye. Hope to see you again soon!");
    }

    public static void printAddedTask(String addedTask, int taskCnt) {
        System.out.println("[@] Got it. I've added this task:");
        System.out.println("  " + addedTask);
        System.out.printf("[@] Now you have %d tasks in the list.\n", taskCnt);
    }

    public static void printListOfTasks(String[] tasks) {
        System.out.println("[@] Here are the tasks in you list:");
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

    public static void printCommandError(String description) {
        System.out.println("[@] OOPS!!! " + description);
    }
}
