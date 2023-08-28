
public class commandResponse {
    public static void respond(String userInput) {
        String line = "____________________________________________________________\n";
        switch (userInput) {
        case "bye":
            System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
            notChatGPT.isRunning = false;
            break;
        case "list":
            System.out.println(line + "Here are the tasks in your list:\n");
            String[] tasks = notChatGPT.taskList.getTasks();
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] != null) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            }
            System.out.println(line);
            break;
        default:
            System.out.println(line + "\n" + "added: " + userInput + "\n" + line);
            notChatGPT.taskList.addTask(userInput);
            break;
        }

    }
}
