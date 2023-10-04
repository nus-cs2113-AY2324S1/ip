# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

# Duke project User guide
1. **Introduction**
	This is a task management robot that use to manage different types of tasks of user.
	This user guide is to guide the user how to use this robot on command line input
	This robot can identify in 3 different tasks, todo, event and deadline
	Also it can record and display the tasks user input, modify the status, search task by name and retrieve tasks from the disk.
2. **Using Duke**
	Create a new todo task: enter the command "todo taskName"
	Create a new deadline task: enter the command "deadline taskName /by dd/MM/yyyy hhmm"
	Create a new event task: enter the command "event taskName /from dd/MM/yyyy hhmm /to dd/MM/yyyy hhmm"
	Delete a task: enter the command "delete indexOfTheTask"
	Mark a task as done: enter the command "mark indexOfTheTask"
	Unmark a task: enter the command "unmark indexOfTheTask"
	Show what's in the list: enter the command "list"
	Search for tasks by name: enter the command "find taskName"
	Quit: Enter the command "bye"


	


