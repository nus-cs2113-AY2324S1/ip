package duke;

import task.Task;

public abstract class HerbertUI {

    private static void println() {
        System.out.println("___________________________________________________________________________");
    }

    private static void newln() {
        System.out.print(System.lineSeparator());
    }

    public static void sayHello() {
        String logo = " __   __  _______  ______    _______  _______  ______    _______ \n"
                + "|  | |  ||       ||    _ |  |  _    ||       ||    _ |  |       |\n"
                + "|  |_|  ||    ___||   | ||  | |_|   ||    ___||   | ||  |_     _|\n"
                + "|       ||   |___ |   |_||_ |       ||   |___ |   |_||_   |   |  \n"
                + "|       ||    ___||    __  ||  _   | |    ___||    __  |  |   |  \n"
                + "|   _   ||   |___ |   |  | || |_|   ||   |___ |   |  | |  |   |  \n"
                + "|__| |__||_______||___|  |_||_______||_______||___|  |_|  |___|  ";

        System.out.println(logo);
        println();
        System.out.println("\tHello! I'm Herbert.");
        System.out.println("\tWhat can I do for you?");
        newln();
        System.out.println("\tYou may choose from the following commands:");
        for (Command s : Command.values()) {
            System.out.println("\t- " + s.name().toLowerCase());
        }
        println();
    }

    public static void sayGoodbye() {
        println();
        System.out.println("\tBye. Hope to see you again soon!");
        println();
    }

    public static void listTasks(TaskList taskList) {
        println();

        if (taskList.size() == 0) {
            System.out.println("\tThere are no tasks in your list! Sit back, relax, and enjoy.");
            println();
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("\t%d. [%s][%s] %s\n",
                    (i + 1),
                    taskList.get(i).getCode(),
                    taskList.get(i).getStatusIcon(),
                    taskList.get(i)
            );
        }

        println();
    }

    public static void displayHelp() {
        System.out.println("###########################################################################");
        System.out.println("\tWelcome to the Herbert Helpline!" + System.lineSeparator());
        System.out.println("\tCOMMANDS");
        for (Command s : Command.values()) {
            System.out.println("\t* " + s.name().toLowerCase());
            System.out.println(s);
            System.out.println();
        }
        System.out.println("###########################################################################");
    }

    //region Message methods
    public static void printMessageInvalidInput(String errorMessage) {
        println();
        System.out.printf("\t%s\n", errorMessage);
        System.out.println("\tUse 'help' for usage instructions.");
        println();
    }

    public static void printMessageInvalidInput() {
        println();
        System.out.println("\tInvalid input. Use 'help' for usage instructions.");
        println();
    }

    public static void printMessageUnknownCommand(String userInput) {
        println();
        System.out.printf("\tUnknown command '%s'. Use 'help' for a full list of user commands.\n", userInput);
        println();
    }

    public static void printMessageMarkTask(Task task, boolean completed) {
        println();
        if (completed) {
            System.out.println("\tLovely! I've marked this task as done:");
        } else {
            System.out.println("\tOkay, I've unmarked this task for you:");
        }
        System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getDescription());
        println();
    }

    public static void printMessageAddTask(Task t, TaskList taskList) {
        println();
        System.out.println("\tOkay, I've added this to your task list:");
        System.out.printf("\t\t[%s][%s] %s\n", t.getCode(), t.getStatusIcon(), t);
        System.out.printf("\tNow you have %d task(s) in your list.\n", taskList.size());
        println();
    }

    public static void printMessageDeleteTask(Task t, TaskList taskList) {
        println();
        System.out.println("\tNoted. I've removed this task from your list:");
        System.out.printf("\t\t[%s][%s] %s\n", t.getCode(), t.getStatusIcon(), t);
        System.out.printf("\tNow you have %d task(s) in your list.\n", taskList.size());
        println();
    }
    //endregion

}
