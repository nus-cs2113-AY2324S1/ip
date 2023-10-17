# User Guide
Chat0PT is a **desktop command-line application to keep track of a user's tasks**

* [Quick Start](#quick-start)
* [Features](#features)
  * [List all tasks `list`](#list-all-tasks-list)
  * [Add a Todo task `todo`](#add-a-todo-task-todo)
  * [Add a Deadline task `deadline`](#add-a-deadline-task-deadline)
  * [Add a Event task `event`](#add-a-event-task-event)
  * [Deleting a task `delete`](#deleting-a-task-delete)
  * [Marking a task as done `done`](#marking-a-task-as-done-done)
  * [Marking a task as not done `unmark`](#marking-a-task-as-not-done-unmark)
  * [Searching for tasks by name `find`](#searching-for-tasks-by-name-find)
  * [Terminating the program `bye`](#terminating-the-program-bye)
* [Command Summary](#command-summary)

## Quick Start
1. Ensure that you have Java `11` installed on your computer.
2. Download the latest `ip.jar` from [here](https://github.com/hooami/ip/releases)
3. Copy the file to the folder you want to use as the home folder for Chat0PT.
4. Open a command terminal, cd to your JAR file location and use `java -jar ip.jar` to run the application.
5. Type the command in the terminal and press Enter to execute it. Refer to [Features](#features) below for the details of each command.

## Features
> **Note**
> - Words in UPPER_CASE are the parameters to be supplied by the user.<br>
    e.g. in `delete NUMBER`, `NUMBER` is a parameter which can be as `delete 1`
> - Extraneous parameters for commands that do not take in parameters (such as `list`) will be ignored.<br>
    e.g. if the command specifies `list abc`, it will be interpreted as `list`.
> - A task file `chat0pt.txt` would be created in the directory that JAR file is executed from. In the event the text file gets modified with an invalid input, the entry would be ignored.

### List all tasks `list`
Lists all currently active tasks

Format: `list`

### Add a Todo task `todo`
Creates a Todo task and adds it to the list.

Format: `todo DESCRIPTION`

Example:
- `todo homework today`
- `CS2113 IP`

### Add a Deadline task `deadline`
Creates a Deadline task and adds it to the list

Format: `deadline DESCRIPTION /by DATETIME`
- `DATETIME` must be in the format `YYYY-MM-DD HHMM`

Example:
- `deadline homework /by 2023-10-03 0700`

### Add a Event task `event`
Creates a Event task and adds it to the list

Format: `event DESCRIPTION /from FROM /to TO`

Example:
- `event Christmas /from 25 Dec /to 26 Dec`

### Deleting a task `delete`
Deletes an existing task

Format: `delete INDEX`
- `INDEX` must exist in the list and it has to be a number.

Example:
- `delete 1` to delete the first task in the list

### Marking a task as done `done`
Marks an existing task as done

Format: `mark INDEX`
- `INDEX` must exist in the list and it has to be a number.

Example:
- `mark 2` to mark the second task in the list as done
  - If task is already marked, the command would have no effect

### Marking a task as not done `unmark`
Marks an existing task as not done

Format: `unmark INDEX`
- `INDEX` must exist in the list and it has to be a number.

Example:
- `unmark 2` to unmark the second task in the list as done
  - If task is already unmarked, the command would have no effect

### Searching for tasks by name `find`
Find tasks based on a given keyword

Format: `find KEYWORD`

Example:
- `find homework` to find tasks containing the word homework.
- `find CS2113 project` to find tasks containing the sentence `CS2113 project`

### Terminating the program `bye`
Ends the program

Format: `bye`

## Command Summary

| Action   | Format, Examples                                                                            |
|----------|---------------------------------------------------------------------------------------------|
| list     | `list`                                                                                      |
| todo     | `todo DESCRIPTION` `todo homework today`                                                    |
| deadline | `deadline /by DATETIME` `deadline homework /by 2023-10-03 0700`                             |
| event    | `event DESCRIPTION /from FROM /to TO` `event Christmas /from 25 Dec /to 26 Dec`             |
| delete   | `delete INDEX` `delete 1`                                                                   |
| mark     | `mark INDEX` `mark 2`                                                                       |
| unmark   | `unmark INDEX` `unmark 2`                                                                   |
| find     | `find KEYWORD` `find homework`                                                              |
| bye      | `bye`                                                                                       |
