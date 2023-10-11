public class Parser {

	public static String[] parseInput(String input) {
		return input.split(" ", 2);
	}

	public static String getCommand(String input) {
		return parseInput(input)[0];
	}

	public static String getArgs(String input) {
		String[] parts = parseInput(input);
		if (parts.length > 1) {
			return parts[1];
		} else {
			return "";
		}
	}
}