import java.util.Scanner;
import java.util.ArrayList;
public class Ui {

	private final Scanner scanner;

	public Ui() {
		this.scanner = new Scanner(System.in);
	}

	// Display a welcome message to the user
	public void showWelcome() {
		System.out.println("Hello my name is DUKE");
		System.out.println("What can I do for you today? Enter 'help' for a list of commands");
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

	public void showError(String message) {
		System.out.println("Error: " + message);
	}

	public void showMessage(String message) {
		System.out.println(message);
	}
}
