
public class commandResponse {
    public static void respond(String[] userInput) {
        String line = "____________________________________________________________\n";
        switch (userInput[0]) {
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
        case "mark":
            notChatGPT.taskList.markTaskAsDone(Integer.parseInt(userInput[1]));
            System.out.println(line + "\nNice! I've marked this task as done:\n");
            System.out.println(notChatGPT.taskList.getTasks()[Integer.parseInt(userInput[1]) - 1] + "\n" + line);
            break;
        case "unmark":
            notChatGPT.taskList.unmarkTaskAsDone(Integer.parseInt(userInput[1]));
            System.out.println(line + "\nAight! I've marked this task as not done yet.\n");
            System.out.println(notChatGPT.taskList.getTasks()[Integer.parseInt(userInput[1]) - 1] + "\n" + line);
            break;    
        default:
            String taskName = String.join(" ", userInput);
            System.out.println(line + "\n" + "added: " + taskName + "\n" + line);
            notChatGPT.taskList.addTask(taskName);
            break;
        }

    }
}
