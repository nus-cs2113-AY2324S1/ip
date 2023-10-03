# User Guide: Axel

Axel is a chatbot designed to help the user in managing tasks, for use in the CLI. 

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest Axel.jar from here
3. Copy the file to the folder you want to use as the home folder for the program.
4. Open a command terminal, `cd` into the chosen folder, and run the app by entering `java -jar Axel.jar`.
5. Type a command into the CLI and press enter to execute it.
6. Once done, type `bye` to end the application.

## Features

### *Notes about the command format*
 - Words in square brackets, eg `[BY]`, `[FROM]` refer to user input parameters
 - Words in curly brackets, eg `{TO}` refer to optional parameters
 - Parameters have to be entered in the order as specified by this guide.
 - Extra parameters for commands will be ignored.


### *Adding a task:* `todo`, `deadline` and `event`
Adds a `Task` to the list.

Format: `todo/deadline/event [NAME] {Additional parameters}`

There are 3 sub-types of task, `ToDo`, `Deadline` and `Event`.
 - `ToDo` is added by entering `todo [NAME]`
 - `Deadline` is added by entering `deadline [NAME] /by [TIME]`
 - `Event` is added by entering `event [NAME] /from [START] /to [END]`

Each type of task has different parameters for time to make it easier to keep track of important dates and times.
Times entered by the user will be stored as-is, and should not include any forward slash characters.

Examples:
 - `todo homework`
 - `deadline submit group project /by 2230 hours`
 - `event cs2113 lecture /from 1600 /to 1800`


### *Deleting a task:* `delete`
Deletes a `Task`.

Format: `delete [INDEX]`
 - Deletes the `Task` at the specified index. Indices start from 1.
 - Indices must be a positive integer.

Example:
 - `delete 1` Deletes the task at index 1


### *Listing all tasks:* `list`
Lists all existing tasks.


### *Locating tasks by keyword* `find`
Finds a task by searching for a single keyword in its name.

Format: `find [KEYWORD]`


### *Marking tasks as done* `mark`,`unmark`
Adds or removes a mark on a task which indicates it as done.

Format: `mark [INDEX]`, `unmark[INDEX]`
- Marks/unmarks the `Task` at the specified index. Indices start from 1.
- Indices must be a positive integer.
  Example:
- `mark 1` Marks the task at index 1
- `unmark 10` Unmarks the task at index 10


### *Saving the list of tasks*
Axel will save the current list of tasks in `data/duke.txt` after each command is completed.
If the file does not exist, Axel will automatically create one.


### *Loading list of tasks from save data*
Whenever Axel is started, it will automatically find `data/duke.txt` and import the data in it.
If the file does not exist, Axel will create a new, empty list of tasks.