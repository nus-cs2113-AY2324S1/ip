import java.util.Arrays;

public class CommandResponse {
    public static void respond(String[] userInput) {
        String line = "____________________________________________________________\n";
        switch (userInput[0]) {
        case "bye":
            System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
            NotChatGPT.isRunning = false;
            break;
        case "list":
            System.out.println(line + "Here are the tasks in your list:\n");
            String[] tasks = NotChatGPT.taskList.getTasks();
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] != null) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            }
            System.out.println(line);
            break;
        case "mark":
            NotChatGPT.taskList.markTaskAsDone(Integer.parseInt(userInput[1]));
            System.out.println(line + "\nNice! I've marked this task as done:\n");
            System.out.println(NotChatGPT.taskList.getTasks()[Integer.parseInt(userInput[1]) - 1] + "\n" + line);
            break;
        case "unmark":
            NotChatGPT.taskList.unmarkTaskAsDone(Integer.parseInt(userInput[1]));
            System.out.println(line + "\nAight! I've marked this task as not done yet.\n");
            System.out.println(NotChatGPT.taskList.getTasks()[Integer.parseInt(userInput[1]) - 1] + "\n" + line);
            break;      
        case "todo":
            NotChatGPT.taskList.addTodo(String.join(" ", Arrays.copyOfRange(userInput, 1, userInput.length)));
            System.out.println(line + "\nGot it. I've added this task:\n");
            System.out.println(NotChatGPT.taskList.getTasks()[NotChatGPT.taskList.getTaskCount() - 1] + "\n" + line);
            break;
        case "deadline":
            String deadlineName = "";
            int deadlineIndex = 0; 
            String deadlineTime; 
            for (int i = 1; i < userInput.length; i++) {
                if (userInput[i].equals("/by")) {
                    deadlineIndex = i + 1;
                    break;
                }
                deadlineName += userInput[i] + " ";
            }
            deadlineName = deadlineName.trim();
            deadlineTime = String.join(" ", Arrays.copyOfRange(userInput, deadlineIndex, userInput.length));
            NotChatGPT.taskList.addDeadline(deadlineName, deadlineTime);
            System.out.println(line + "\nGot it. I've added this task:\n");
            System.out.println(NotChatGPT.taskList.getTasks()[NotChatGPT.taskList.getTaskCount() - 1] + "\n" + line);
            break;
        case "event":
            String eventName = "";
            int eventStartTimeIndex = 0;
            int eventEndTimeIndex = 0;
            String eventStartTime = "";
            String eventEndTime = "";
            for (int i = 1; i < userInput.length; i++) {
                if (userInput[i].equals("/from")) {
                    eventStartTimeIndex = i + 1;
                    break;
                }
                eventName += userInput[i] + " ";
            }
            eventName = eventName.trim();
            for (int j = eventStartTimeIndex; j < userInput.length; j++) {
                if (userInput[j].equals("/to")) {
                    eventEndTimeIndex = j + 1;
                    break;
                }
                eventStartTime += userInput[j] + " ";
            }
            eventStartTime = eventStartTime.trim();
            eventEndTime = String.join(" ", Arrays.copyOfRange(userInput, eventEndTimeIndex, userInput.length));
            NotChatGPT.taskList.addEvent(eventName, eventStartTime, eventEndTime);
            System.out.println(line + "\nGot it. I've added this task:\n");
            System.out.println(NotChatGPT.taskList.getTasks()[NotChatGPT.taskList.getTaskCount() - 1] + "\n" + line);
            break;
        default:
            System.out.println(line + "\nI'm sorry, but I don't know what that means :-(\n" + line);
            break;
        }
    }
}
