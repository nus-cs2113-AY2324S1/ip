package cn.yfshadaow.cs2113.ip.command;

import java.util.*;

public class Command {
    private String cmd;

    @SuppressWarnings("unused")
    public String getCmd() {
        return cmd;
    }

    @SuppressWarnings("unused")
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public final List<String> args = new ArrayList<>();
    public final Map<String, String> extraArgs = new HashMap<>();

    public Command(String cmd, List<String> args, Map<String, String> extraArgs) {
        this.cmd = cmd;
        this.args.addAll(args);
        this.extraArgs.putAll(extraArgs);
    }

}
