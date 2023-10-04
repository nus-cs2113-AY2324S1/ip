package BotBuddy;

public class Parser {
    public String[] parseInput(String userInput) {
        String[] inputArr = userInput.split(" ", 2);
        String command = "";
        String parameters = "";
        command = inputArr[0];
        if (inputArr.length == 2) {
            parameters = inputArr[1];
        } else {
            parameters = "";
        }
        return inputArr;
    }

    public void validateInput(String command, String parameters) throws BotBuddyException {
        switch (command) {
        case "todo":
            if (parameters.isEmpty()) {
                throw new BotBuddyException("The description of a todo cannot be empty!");
            }
            break;

        case "event":
            String[] eventDetails = parameters.split("/from");
            if (eventDetails.length < 2) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "event 'Event Name' /from 'Start Time' /to 'End Time'");
            }
            String eventName = eventDetails[0].trim();
            eventDetails = eventDetails[1].split("/to");
            if (eventDetails.length < 2) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "event 'Event Name' /from 'Start Time' /to 'End Time'");
            }
            String eventFrom = eventDetails[0].trim();
            String eventTo = eventDetails[1].trim();
            if (eventName.isEmpty() || eventTo.isEmpty() || eventFrom.isEmpty()) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "event 'Event Name' /from 'Start Time' /to 'End Time'");
            }
            break;

        case "deadline":
            String[] deadlineDetails = parameters.split("/by");
            if (deadlineDetails.length < 2) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "deadline 'Deadline Name' /by 'Due Date'");
            }
            String deadlineName = deadlineDetails[0].trim();
            String deadlineBy = deadlineDetails[1].trim();
            if (deadlineName.isEmpty() || deadlineBy.isEmpty()) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "deadline 'Deadline Name' /by 'Due Date'");
            }
            break;

        case "list":
            break;

        case "bye":
            break;

        case "mark":
            // Fallthrough

        case "unmark":
            // Fallthrough

        case "delete":
            if (parameters.isEmpty()) {
                throw new BotBuddyException("You did not specify which task!");
            }
            int taskToModify;
            try {
                taskToModify = Integer.parseInt(parameters);
            } catch (NumberFormatException e) {
                throw new BotBuddyException("The task specified should be a number!");
            }
            if (taskToModify > Task.getNoOfTasks() || taskToModify < 1) {
                throw new BotBuddyException("There is no such task!");
            }
            break;

        case "find":
            if (parameters.isEmpty()) {
                throw new BotBuddyException("You cannot search for nothing! Please try again.");
            }
            break;

        default:
            // invalid command
            throw new BotBuddyException("Invalid command! Supported commands are: " +
                    "todo, event, deadline, list, mark, unmark, find, bye");
        }
    }

    public String[] parseEventDetails(String parameters) {
        String[] eventDetails = parameters.split("/from");
        String eventName = eventDetails[0].trim();
        eventDetails = eventDetails[1].split("/to");
        String eventFrom = eventDetails[0].trim();
        String eventTo = eventDetails[1].trim();

        String[] parsedEventDetails = {eventName, eventFrom, eventTo};
        return parsedEventDetails;
    }

    public String[] parseDeadlineDetails(String parameters) {
        String[] deadlineDetails = parameters.split("/by");
        String deadlineName = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();

        String[] parsedDeadlineDetails = {deadlineName, deadlineBy};
        return parsedDeadlineDetails;
    }
}
