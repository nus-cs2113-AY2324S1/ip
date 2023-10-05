# Tum ChatBot

The Tum ChatBot is an application that can be used as a memorandum through the Command Line Interface or JAR Files. If you have anything to do or some important appointments, you can store them in Tum Chat and a text file regarding your tasks will be automatically generated so that you can be aware of what has been done and what should be done.

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
____________________________________________________________
 Hello! I'm TUM
 What can I do for you?
____________________________________________________________
   ```
## Quick Start
1. Ensure you have Java 11 installed in your Computer.
2. Download the latest IP.jar from here.
3. Copy the file to the folder you want to use as the home folder for your Tum ChatBot.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar IP.jar command to run the application.
   In a few seconds, it should show that:
![Screenshot of the display in terminal](https://github.com/Haoyuli2002/ip/assets/139958049/cade8150-21a1-43be-8d82-6e59374f4c7f))

5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
Some example commands you can try:

-list: Lists all contacts.

-todo return book: add the task todo "return book" in your task list

-delete 1: Deletes the first task shown in the current list.

-clear: Deletes all tasks.

-bye: Exits the app.

## Features

### Adding a task: todo/deadline/event
Add a task to your TaskList. We have 3 kinds of tasks available: todo, deadline, event
Format: todo DESCRIPTION_OF_TODO
Format: deadline DESCRIPTION_OF_DEADLINE /by DEADLINE
Format: event DESCRIPTION_OF_EVENT /from START_TIME /to END_TIME

Examples:
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm

### Listing all tasks:
Shows a list of all tasks that have been stored in the task list
Format: list

### Editing the status of a task : mark or unmark
Set the status of a task based on a given index to "DONE" by using "mark" or
Set the status of a task based on a given index to "NOT_DONE" by using "unmark"

Format: mark INDEX
Format: unmark INDEX

Examples:
mark 1
unmark 2

### Finding all relative tasks by keywords: find
Search for all the tasks in the task list that contain the keyword given by the user

Format: find DESCRIPTION
Examples:
find book

### Deleting a task: delete
Deletes the specified task from the task list based on the index
Format: delete INDEX

Deletes the person at the specified INDEX.
The index refers to the index number shown in the displayed person list.
The index must be a positive integer 1, 2, 3, …​
Examples:

delete 2: deletes the 2nd person in the address book.
find Betsy followed by delete 1 deletes the 1st person in the results of the find command.

### Clearing all entries: clear
Clears all entries from the address book.

Format: clear

### Exiting the program: bye
Exits the program.

Format: bye

### Command summary
Action	 Format,                                                            Examples
todo      todo DESCRIPTION                                                   todo return book
deadline  deadline DESCRIPTION /by DEADLINE                                  deadline return book /by Sunday
event     event DESCRIPTION_OF_EVENT /from START_TIME /to END_TIME           event project meeting /from Mon 2pm /to 4pm
delete	 delete INDEX                                                       delete 3
mark	    mark INDEX                                                         mark 2
unmark	 unmark INDEX                                                       unmark 2
Find	    find KEYWORD                                                       find book
List	    list
Clear     clear
Help	    help

















