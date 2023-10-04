package cn.yfshadaow.cs2113.ip.command;

import cn.yfshadaow.cs2113.ip.XiaoAiBot;
import cn.yfshadaow.cs2113.ip.task.Deadline;
import cn.yfshadaow.cs2113.ip.task.Event;
import cn.yfshadaow.cs2113.ip.task.Task;
import cn.yfshadaow.cs2113.ip.task.Todo;

import java.util.List;

/**
 * Represents a command handler.
 */
public class CommandHandler {

    private final XiaoAiBot bot;

    /**
     * Instantiates a new Command handler.
     *
     * @param bot the bot
     */
    public CommandHandler(XiaoAiBot bot) {
        this.bot = bot;
    }

    /**
     * Handle command.
     *
     * @param cmd the command
     */
    public void handleCommand(Command cmd) {
        bot.getUi().sendSplit();
        switch (cmd.getName()) {
            case "bye": {
                bot.setShouldQuit(true);
                break;
            }
            case "todo": {
                Todo todo;
                try {
                    todo = Todo.parseTodo(cmd);
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error parsing task: %s", e.getMessage()));
                    break;
                }
                bot.getTaskList().tasks.add(todo);
                bot.getUi().sendMessageWithoutSplit("Got it. I've added this task:");
                bot.getUi().sendMessageWithoutSplit(todo.toStringWithIsDone());
                bot.getUi().sendMessage("Now you have " + bot.getTaskList().tasks.size() + " tasks in the list.");
                try {
                    bot.getStorage().saveData(bot.getTaskList());
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error saving data: %s", e.getMessage()));
                    break;
                }
                break;
            }
            case "deadline": {
                Deadline deadline;
                try {
                    deadline = Deadline.parseDeadline(cmd);
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error parsing task: %s", e.getMessage()));
                    break;
                }
                bot.getTaskList().tasks.add(deadline);
                bot.getUi().sendMessageWithoutSplit("Got it. I've added this task:");
                bot.getUi().sendMessageWithoutSplit(deadline.toStringWithIsDone());
                bot.getUi().sendMessage("Now you have " + bot.getTaskList().tasks.size() + " tasks in the list.");
                try {
                    bot.getStorage().saveData(bot.getTaskList());
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error saving data: %s", e.getMessage()));
                    break;
                }
                break;
            }
            case "event": {
                Event event;
                try {
                    event = Event.parseEvent(cmd);
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error parsing task: %s", e.getMessage()));
                    break;
                }
                bot.getTaskList().tasks.add(event);
                bot.getUi().sendMessageWithoutSplit("Got it. I've added this task:");
                bot.getUi().sendMessageWithoutSplit(event.toStringWithIsDone());
                bot.getUi().sendMessage("Now you have " + bot.getTaskList().tasks.size() + " tasks in the list.");
                try {
                    bot.getStorage().saveData(bot.getTaskList());
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error saving data: %s", e.getMessage()));
                    break;
                }
                break;
            }
            case "mark": {
                if (cmd.args.size() != 1) {
                    bot.getUi().sendMessage("Incorrect arguments");
                    break;
                }
                int index;
                try {
                    index = Integer.parseInt(cmd.args.get(0));
                } catch (NumberFormatException e) {
                    bot.getUi().sendMessage(String.format("Error parsing int: %s", e.getMessage()));
                    break;
                }
                try {
                    bot.getTaskList().tasks.get(index - 1).setDone(true);
                } catch (IndexOutOfBoundsException e) {
                    bot.getUi().sendMessage(String.format("Error getting task: %s", e.getMessage()));
                    break;
                }
                bot.getUi().sendMessageWithoutSplit("Nice! I've marked this task as done:");
                bot.getUi().sendMessage(bot.getTaskList().tasks.get(index - 1).toStringWithIsDone());
                try {
                    bot.getStorage().saveData(bot.getTaskList());
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error saving data: %s", e.getMessage()));
                    break;
                }
                break;
            }
            case "unmark": {
                if (cmd.args.size() != 1) {
                    bot.getUi().sendMessage("Incorrect arguments");
                    break;
                }
                int index;
                try {
                    index = Integer.parseInt(cmd.args.get(0));
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error parsing int: %s", e.getMessage()));
                    break;
                }
                try {
                    bot.getTaskList().tasks.get(index - 1).setDone(false);
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error getting task: %s", e.getMessage()));
                    break;
                }
                bot.getUi().sendMessageWithoutSplit("OK, I've marked this task as not done yet:");
                bot.getUi().sendMessage(bot.getTaskList().tasks.get(index - 1).toStringWithIsDone());
                try {
                    bot.getStorage().saveData(bot.getTaskList());
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error saving data: %s", e.getMessage()));
                    break;
                }
                break;
            }
            case "delete": {
                if (cmd.args.size() != 1) {
                    bot.getUi().sendMessage("Incorrect arguments");
                    break;
                }
                int index;
                try {
                    index = Integer.parseInt(cmd.args.get(0));
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error parsing int: %s", e.getMessage()));
                    break;
                }
                Task task;
                try {
                    task = bot.getTaskList().tasks.get(index - 1);
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error getting task: %s", e.getMessage()));
                    break;
                }
                bot.getUi().sendMessageWithoutSplit("Noted. I've removed this task:");
                bot.getUi().sendMessage(task.toStringWithIsDone());
                bot.getTaskList().tasks.remove(index - 1);
                try {
                    bot.getStorage().saveData(bot.getTaskList());
                } catch (Exception e) {
                    bot.getUi().sendMessage(String.format("Error saving data: %s", e.getMessage()));
                    break;
                }
                break;
            }
            case "list": {
                List<Task> tasks = bot.getTaskList().tasks;
                for (int i = 0; i < tasks.size(); i += 1) {
                    Task task = tasks.get(i);
                    bot.getUi().sendMessageWithoutSplit((i + 1) + "." + task.toStringWithIsDone());
                }
                bot.getUi().sendSplit();
                break;
            }
            default: {
                bot.getUi().sendMessage("Unknown command");
            }
        }
    }
}
