package cn.yfshadaow.cs2113.ip.utils;

import cn.yfshadaow.cs2113.ip.command.Command;

import java.util.*;

public class Parser {

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
                    String storedString = extraArguments.get(currentPart);
                    extraArguments.put(currentPart,  storedString + (storedString.isEmpty()? "" : " ") + next);
                }
            }
        }
        return new Command(cmdString, arguments, extraArguments);
    }
}
