import java.util.Scanner;
import java.util.ArrayList;

//UI captures user input and talks to the TasksList class to interpret command.
public class Ui {

	private final Scanner scanner;

	public Ui() {
		this.scanner = new Scanner(System.in);
	}

	// Display a welcome message to the user
	public void showWelcome() {
		System.out.println("Hello my name is DUKE. I am created by Isaiah Cerven");
		System.out.println("What can I do for you today? Enter 'help' for a list of commands. Say 'bye' to save tasks and exit");
	}

	// Display a goodbye message when the user decides to exit
	public void showGoodbye() {
		System.out.println("Fine. If you have no ideas imma head out");
	}

	// Show the list of commands to the user
	public void showCommands(ArrayList<String> commandNames) {
		System.out.println("Here is a list of commands you can use: ");
		for (String command: commandNames){
			System.out.println(command);
		}
	}

	// Capture and return user input
	public String getUserInput() {
		return scanner.nextLine();
	}

	public boolean checkUserInput(String input){

		if (input.isEmpty()) {
			System.out.println("Please input a command.");
			return false;
		}
		return true;
	}

	public void showError(String message) {
		System.out.println("Error: " + message);
	}

	public void showMessage(String message) {
		System.out.println(message);
	}
}
