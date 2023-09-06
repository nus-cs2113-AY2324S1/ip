package cn.yfshadaow.cs2113.ip;

import cn.yfshadaow.cs2113.ip.XiaoAiBot;

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
        switch (splitCommand[0]) {
            case "bye":
                bot.setShouldQuit(true);
                break;
            case "list":
                List<String> tasks = bot.getTasks();
                for (int i = 0; i < tasks.size(); i += 1) {
                    bot.sendMessageWithoutSplit(i + ". " + tasks.get(i));
                }
                bot.sendSplit();
                break;
            default:
                bot.getTasks().add(command);
                bot.sendMessage("added " + command);
        }
    }
}
