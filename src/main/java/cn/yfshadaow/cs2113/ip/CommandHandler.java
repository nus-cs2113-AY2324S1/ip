package cn.yfshadaow.cs2113.ip;

import cn.yfshadaow.cs2113.ip.XiaoAiBot;

public class CommandHandler {

    private final XiaoAiBot bot;
    public CommandHandler(XiaoAiBot bot) {
        this.bot = bot;
    }

    public void handleCommand(String command){
        if (command == null) {
            bot.sendMessages("Command is null!");
            return;
        }
        String[] splitCommand = command.split(" ");
        if (splitCommand.length < 1) {
            bot.sendMessages("No command detected!");
            return;
        }
        String[] args = new String[splitCommand.length - 1];
        System.arraycopy(splitCommand, 1, args, 0, splitCommand.length - 1);
        switch (splitCommand[0]) {
            case "bye":
                bot.setShouldQuit(true);
                break;
            default:
                bot.sendMessages(command);
        }
    }
}
