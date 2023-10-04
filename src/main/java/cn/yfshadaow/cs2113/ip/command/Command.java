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

    public static Command parseCommand(String s) throws IllegalArgumentException{
        Iterator<String> iterator = Arrays.stream(s.split(" ")).iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Command cannot be empty!");
        }
        String cmdString = iterator.next();
        List<String> arguments = new ArrayList<>();
        Map<String, String> extraArguments = new HashMap<>();

        // currentPart stores which part of argument the parser is reading
        // For example, if currentPart is null, the parser is reading main arguments
        // If it is not null, the parser is reading extra arguments associated with that string
        String currentPart = null;
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.startsWith("-")) {
                if (next.length() == 1) {
                    throw new IllegalArgumentException("Extra argument identifier cannot be empty!");
                }
                currentPart = next.substring(1);
                if (extraArguments.containsKey(currentPart)) {
                    throw new IllegalArgumentException("Duplicate argument identifier!");
                }
                extraArguments.put(currentPart, "");
            } else {
                if (currentPart == null) {
                    arguments.add(next);
                } else {
                    extraArguments.put(currentPart, extraArguments.get(currentPart) + " " + next);
                }
            }
        }
        return new Command(cmdString, arguments, extraArguments);
    }
}
