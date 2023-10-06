public class Ui {
    protected final static String LINE = "_______________________________________________";
    protected final static String GREET = "Hello! I'm Elwin\n" + "What can I do for you?";
    protected final static String EXIT = "Bye. Hope to see you again soon!";
    protected final static String LIST = "Here are the tasks in your list:";
    protected final static String MARK = "Nice! I've marked this task as done:";
    protected final static String UNMARK = "OK, I've marked this task as not done yet:";
    protected final static String FIND = "Here are the matching tasks in your list:";
    /**
     * Print the message
     *
     * @param message The message to be printed
     */
    public void print(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Print the list of tasks
     *
     * @param tasks the task list
     */
    public void printList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println(LIST);
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i+1) + "." + tasks.get(i+1).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Print the list of found tasks
     *
     * @param tasks the task list
     */
    public void printFoundTasks(TaskList tasks) {
        System.out.println(LINE);
        System.out.println(FIND);
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i+1) + "." + tasks.get(i+1).toString());
        }
        System.out.println(LINE);
    }
    /**
     * Print when the task is marked as done
     *
     * @param task The task which is marked as done
     */
    public void printMark(Task task) {
        print(UNMARK+"\n  "+task.toString());
    }

    /**
     * Print when the task is unmarked as done
     *
     * @param task The task which is unmarked as done
     */
    public void printUnmark(Task task) {
        print(MARK+"\n  "+task.toString());
    }

    /**
     * Print the greet message
     */
    public void printGreet() {
        print(GREET);
    }

    /**
     * Print the exit message
     */
    public void printExit() {
        print(EXIT);
    }

    /**
     * Print the message when one task is added
     *
     * @param task The task which is added
     * @param size The size of the list
     */
    public void printAddition(Task task, int size) {
        print("Got it. I've added this task:\n  "+task.toString()+"\nNow you have "+size+" tasks in the list.");
    }

    /**
     * Print the message when one task is deleted
     *
     * @param task The task which is deleted
     * @param size The size of the list
     */
    public void printDeletion(Task task, int size) {
        print("Noted. I've removed this task:\n  "+task.toString()+"\nNow you have "+size+" tasks in the list.");
    }
}
