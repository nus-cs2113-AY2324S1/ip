package classes;

public class Parser {
	
	public static String[] parse(String userCommand) {
		return userCommand.strip().split(" ");
	}
}
