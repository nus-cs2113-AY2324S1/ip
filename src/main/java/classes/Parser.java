package classes;

public class Parser {
	
	/**
	 * Gets the user's input and returns it as an array to be parsed and processed
	 * 
	 * @param userCommand the user's input
	 * @return an array to be parsed to determine the command to be executed
	 */
	public static String[] parse(String userCommand) {
		return userCommand.strip().split(" ");
	}
}
