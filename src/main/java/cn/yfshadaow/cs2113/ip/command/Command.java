package cn.yfshadaow.cs2113.ip.command;

import java.util.*;

/**
 * Represents a command. A command must have a name, but might have empty arguments and extra arguments.
 */
public class Command {
    private String name;

    /**
     * Gets name.
     *
     * @return the name
     */
    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The Args.
     */
    public final List<String> args = new ArrayList<>();
    /**
     * The Extra args.
     */
    public final Map<String, String> extraArgs = new HashMap<>();

    /**
     * Instantiates a new Command.
     *
     * @param name      the name
     * @param args      the args
     * @param extraArgs the extra args
     */
    public Command(String name, List<String> args, Map<String, String> extraArgs) {
        this.name = name;
        this.args.addAll(args);
        this.extraArgs.putAll(extraArgs);
    }

}
