# Spaceman Task Manager

Welcome to Spaceman, your personal assistance chat-bot!
Spaceman is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI).
This app is suitable for users who can type fast.

## Prerequisites

Make sure you have Java `11` or above installed on your system.

## Quick Start

1. Download the latest JAR file (`ip.jar`).
2. Copy the JAR file to the folder you want to use as the **home folder** for your Task Manager.
3. Open a command terminal, `cd` into the folder you put the jar file in.
4. Enter the following command to run the program:

   ```
   java -jar ip.jar
   ```

5. You should see a welcome message as shown below:

   ```
   Hello from
     ____  _____   ___    _____ _____ __    __   ___   __   __
   /     /|  __ \ /   \  /   __|     |  \  /  | /   \ |  \ |  |
   \   __\| |__) |  _  \|   /  |   __|   \/   |/  _  \|   \|  |
    \__   |  ___/  |_|  |  |   |   __|        |  |_|  |       |
   /      | |   |   _   |   \__|     |   __   |   _   |       |
   |____ /|_|   |__| |__|\_____|_____|__|  |__|__| |__|__|\___|

   ________________________________________________________________________
   What can I do for you?
   ________________________________________________________________________
   ```

6. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:

   - `list` : Lists all tasks.
   - `todo buy groceries` : Adds a todo buy groceries to the list of tasks.
   - `delete 2` : Deletes the 2nd task shown in the current list.
   - `mark 2` : Marks the 2nd task shown in the current list as done.
   - `find book` : Finds tasks that contains the keyword `book` and displays it.
   - `bye` : Exits the app.

7. Refer to the Features below for details of each command.

## Features

### Adding a todo : `todo`

Adds a todo to the current list of tasks.
Format: `todo DESCRIPTION​`
Examples:

- `todo buy coffee`

### Adding a deadline : `deadline`

Adds a deadline to the current list of tasks.
Format: `deadline DESCRIPTION /by DATETIME`
Examples:

- `deadline return books /by 16/10/2023 1200`
- `deadline submit first draft /by 06-10-2023 2359`

### Adding an event : `event`

Adds an event to the current list of tasks.
Format: `event DESCRIPTION /from START_DATETIME /to END_DATETIME​`
Examples:

- `event Orientation Camp /from 10/09/2023 0800 /to 13/09/2023 1800`

### Listing all tasks : `list`

Shows a list of all tasks.
Format: `list​`

### Filtering tasks by keyword : `find`

Finds tasks whose description contain the given keyword.
Format: `find KEYWORD`

- The search is case-insensitive. e.g Books will match books
- Only full words will be matched e.g. books will not match book

### Deleting a task : `delete`

Deletes the specified task from the list of tasks.
Format: `delete INDEX`

- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed list of tasks.

Examples:

- `list` followed by `delete 2` deletes the 2nd task in the list of tasks.

### Marking a task as done : `mark`

Marks the specified task from the list of tasks as done.
Format: `mark INDEX`

- Marks the task at the specified INDEX as done.
- The index refers to the index number shown in the displayed list of tasks.

Examples:

- `list` followed by `mark 2` marks the 2nd task in the list of tasks as done.

### Marking a task as not done : `unmark`

Marks the specified task from the list of tasks as not done.
Format: `unmark INDEX`

- Marks the task at the specified INDEX as not done.
- The index refers to the index number shown in the displayed list of tasks.

Examples:

- `list` followed by `unmark 2` marks the 2nd task in the list of tasks as not done.

### Exiting the program : `bye`

Exits the program.

Format: `bye`
