package eggybyte.ip;

import eggybyte.ip.data.task.Task;
import eggybyte.ip.command.Command;
import eggybyte.ip.command.CommandResult;
import eggybyte.ip.data.RunningState;
import eggybyte.ip.util.Parser;
import eggybyte.ip.util.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static RunningState runningState = new RunningState(new ArrayList<Task>());
    public static Boolean debugMode = true;

    private static void initialize() {
        Command.setRunningState(runningState);
        Logger.debugMode = Parser.debugMode = debugMode;
        Logger.showGreeting();
    }

    public static void main(String[] args) {
        initialize();
        runCommandLoopUntilByeCommand();
    }

    /**
     * Reads the user command and executes it, until the user issues the bye
     * command.
     */
    private static void runCommandLoopUntilByeCommand() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                String userCommandText = scanner.nextLine();
                Command command = new Parser().parseCommand(userCommandText);
                CommandResult commandResult = command.execute();
                switch (commandResult.state) {
                    case success: {
                        Logger.showLog(commandResult.result, true);
                        break;
                    }
                    case fail: {
                        // Logger.showLog(commandResult.result, Logger.LogLevel.ERROR, true);
                        // break;
                        throw commandResult.exception;
                    }
                }
            } catch (Exception exception) {
                Logger.showLog(exception, true);
            }
        } while (runningState.isRunning());
        scanner.close();
    }
}
