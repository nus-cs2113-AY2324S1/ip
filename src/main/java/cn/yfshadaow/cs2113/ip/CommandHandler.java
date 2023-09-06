package cn.yfshadaow.cs2113.ip;

import java.util.List;

public class CommandHandler {

    private final XiaoAiBot bot;
    public CommandHandler(XiaoAiBot bot) {
        this.bot = bot;
    }

    public void handleCommand(String command){
        if (command == null) {
            bot.sendMessage("Command is null!");
            return;
        }
        String[] splitCommand = command.split(" ");
        if (splitCommand.length < 1) {
            bot.sendMessage("No command detected!");
            return;
        }
        String[] args = new String[splitCommand.length - 1];
        System.arraycopy(splitCommand, 1, args, 0, splitCommand.length - 1);
        bot.sendSplit();
        switch (splitCommand[0]) {
            case "bye": {
                bot.setShouldQuit(true);
                break;
            }
            case "mark": {
                if (args.length != 1) {
                    bot.sendMessage("Incorrect arguments");
                }
                int index = 0;
                try {
                    index = Integer.parseInt(args[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                try {
                    bot.getTasks().get(index - 1).setDone(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                bot.sendMessageWithoutSplit("Nice! I've marked this task as done:");
                bot.sendMessage(bot.getTasks().get(index - 1).toStringWithIsDone());
                break;
            }
            case "unmark": {
                if (args.length != 1) {
                    bot.sendMessage("Incorrect arguments");
                }
                int index = 0;
                try {
                    index = Integer.parseInt(args[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                try {
                    bot.getTasks().get(index - 1).setDone(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                bot.sendMessageWithoutSplit("OK, I've marked this task as not done yet:");
                bot.sendMessage(bot.getTasks().get(index - 1).toStringWithIsDone());
                break;
            }
            case "list": {
                List<Task> tasks = bot.getTasks();
                for (int i = 0; i < tasks.size(); i += 1) {
                    Task task = tasks.get(i);
                    bot.sendMessageWithoutSplit((i + 1) + "." + task.toStringWithIsDone());
                }
                bot.sendSplit();
                break;
            }
            default: {
                bot.getTasks().add(new Task(command));
                bot.sendMessage("added " + command);
            }
        }
    }
}
