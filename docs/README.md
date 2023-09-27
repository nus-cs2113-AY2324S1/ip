# User Guide 

Duke provides a command-line tool for managing your tasks. It helps you track the completion status of your todos, deadlines and event, and makes managing your life so much easier! 

- [Quick Start](#quick-start)
- [Features](#features)
    - [Listing Items](#listing-items-list): `list`
    - [Adding todo](#adding-todo-todo): `todo`
    - [Adding deadline](#adding-deadline-deadline): `deadline`
    - [Adding event](#adding-event-event): `event`
    - [Marking Items](#marking-items-mark): `mark`
    - [Unmarking Items](#unmarking-items-unmark): `unmark`
    - [Finding Items](#finding-items-find): `find`
    - [Deleting Items](#deleting-items-delete): `delete`
    - [Exiting the program](#exiting-the-program-bye): `bye`
    - [Saving the data](#saving-the-data)
- [Command reference](#command-reference)
    
<br />

## Quick Start
1. Ensure you have Java `11` or above installed in your computer. 
2. Download the latest `ip.jar` from [here](#).
3. Copy the file to the folder you want to use as the *home folder* for Duke. 
4. Open a command terminal, locate the path to the folder you put the jar file in, and `cd` (change directory) into that folder. For example, if your file path is `C:\Users\potato\duke\ip.jar`, the full command will be 
    ```
    cd C:\Users\potato\duke\ip.jar
    ```
5. Once you're in the directory, use the following command to run the application.
    ```
    java -jar ip.jar
    ```
    If the application starts successfully, you should see the following
    ```
    ____________________________________________________________
    Hello! I'm
       ___    ___  ___
      / __\  /___\/ __\
     /__\// //  //__\/\
    / \/  \/ \_// \/  \
    \_____/\___/\_____/

    What can I do for you?
    ____________________________________________________________
    ```
    If you encounter the error message "Unable to find file. Defaulting to empty list...", this means that Duke is unable to find a save file at the default path `./data/duke.txt`. To remedy this, you may first type `bye` and press Enter to exit the program, and then execute the following. 
    
    For **Windows**: 
    ```
    New-Item -ItemType Directory -Path .\data; New-Item -ItemType File -Path .\data\duke.txt
    ```
    For **Unix**: 
    ```
    mkdir -p ./data && touch ./data/duke.txt
    ```
    Restart the program with the same `java -jar ip.jar` command. Note that the error message should no be present. 
6. Now that the application is running, here are some sample commands you can try: 
    * [`todo water plants`](#): Creates a new todo "water plants". 
    * [`list`](#): List all tasks. 
    * [`mark 1`](#): Marks task at index 1 as complete. This task should be the first item in the `list`.  
    * [`bye`](#): Exits the app.
7. To find out more about each command, view [Features](#). To get an overview of all available commands, see the [Table of Contents](#) above or the [Command reference](#) below. 

<br />

## Features

### Listing Items: `list`
Lists all items currently in the task list. 

Format: `list` 

### Adding todo: `todo`
Adds new todo to the task list, with an accompanying description of the todo. 

Format: `todo <description>`
Example: `todo water dog`

### Adding deadline: `deadline`
Adds new deadline to the task list, with an accompanying description of the task and the deadline. 

Format:`deadline <description> /by <deadline>`
> :bulb: **Note**: The deadline should be in the form yyyy-mm-dd. 

Example: `deadline pet dog /by 2023-10-06`

### Adding event: `event`
Adds a new event to the task list, with an accompanying description of the event, as well as the start time and the end time of the event. 

Format: `event <description> /from <start> /to <end>`
Example: `event give dog food /from 1000 /to 1030`

### Marking Items: `mark`
Marks item in task list as complete. When item is marked as complete, an "X" should show up in the second checkbox preceding the task description when the `list` command is executed. 

Format: `mark <index>`
> :bulb: **Note**: `<index>` follows the index of the task as per displayed by the `list` command. For instance, `mark 1` marks the first item in the list as complete. 

Example: `mark 1`

### Unmarking Items: `unmark`
Marks item in task list as incomplete.  When item is marked an incomplete, an "X" previously in the second checkbox preceding the task description when the `list` command is executed will be replaced by a space " ".

Format: `unmark <index>`
> :bulb: **Note**: `<index>` follows the index of the task as per displayed by the `list` command. For instance, `unmark 1` marks the first item in the list as incomplete. 

Example: `unmark 1`

### Finding Items: `find`
Finds item in the task list with the corresponding keyword. 

Format: `find <keyword>`
Example: `find dog`

### Deleting Items: `delete`
Deletes an item from the task list. 

Format: `delete <index>`
> :bulb: **Note**: `<index>` follows the index of the task as per displayed by the `list` command. For instance, `delete 1` marks the first item in the list as complete. The item that was previously second in the list will now have index 1, the third index 2, and so on. 

Example: `delete 1`

### Exiting the program: `bye`
Exits the program. If the save file `./data/duke.txt` exists, contents of the task list will be written into the save file. View [Saving the data](#) for more information on creating the save file. 

### Saving the data
If the save file `.\data\duke.txt` exists, items remaining in the task list will be saved in the save file when the app exits, and will be reloaded the next time Duke is launched. 

If the save file cannot be found, upon starting up Duke will prepend to the welcome message the following error: "Unable to find file. Defaulting to empty list...". 

To remedy this, you may first type `bye` and press Enter to exit the program, and then execute the following. 
    
- For **Windows**: 
    ```
    New-Item -ItemType Directory -Path .\data; New-Item -ItemType File -Path .\data\duke.txt
    ```
- For **Unix**: 
    ```
    mkdir -p ./data && touch ./data/duke.txt
    ```
Note that when the program is restarted with the `java -jar ip.jar` command, the error message should no longer be present. 

<br />

## Command Reference 

| Action       | Format                                        |
| ------------ | --------------------------------------------- |
| List items   | `list`                                        |
| Add todo     | `todo <description>`                          |
| Add deadline | `deadline <description> /by <deadline>`       |
| Add event    | `event <description> /from <start> /to <end>` |
| Mark items   | `mark <index>`                                |
| Unmark items | `unmark <index>`                              |
| Find items   | `find <keyword>`                              |
| Delete items | `delete <index>`                              |
| Exit         | `bye`                                         |

