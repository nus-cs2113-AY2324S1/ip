# User Guide
Elgin is a **desktop app for keeping track of the status of tasks, optimised for use via Command-Line Interface (CLI)**.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Adding a Todo: `todo`](#adding-a-todo-todo)
    * [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
    * [Adding an Event: `event`](#adding-an-event-event)
    * [Marking task as done: `mark`](#marking-task-as-done-mark)
    * [Unmarking task as done: `unmark`](#unmarking-task-as-done-unmark)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Finding tasks: `find`](#finding-tasks-find)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
* [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or above installed on your computer.
2. Download the latest `ip.jar` from [here](https://github.com/ChoonSiang/ip/releases).
3. Copy the `ip.jar` to any folder you wish to use as the home folder of the application.
4. Open a command terminal, `cd` into the folder you placed your `ip.jar` in and enter `java -jar ip.jar` to run the program. The output from the startup of the program will look like this.
   ```
    ____________________________________________________________
    Hello! I'm Elgin
    What can I do for you?
    ____________________________________________________________
   
   ```
5. Refer to the [Features](#features) below for more details about each command.

## Features
> **NOTES**
> - Words in UPPER_CASE are the arguments to be supplied by user. <br>
    e.g. `deadline DESCRIPTION /by DATETIME`, `DESCRIPTION` and `DATETIME` are arguments to be specified by user.
> - Parameters need to be in the exact same format as specified. <br>
    e.g. `event DESCRIPTION /from DATETIME /to DATETIME`, `/from` must come before `/to`.
> - Redundant parameters supplied by the user will be ignored. <br>
    e.g. `bye 123` will only be interpreted as `bye`.

### Listing all tasks: `list`

Prints all the tasks.

Format: `list`

Example:
- `list`

### Adding a Todo: `todo`

Adds a Todo task.

Format: `todo DESCRIPTION`

Example:
- `todo study for midterms`
- `todo submit assignment`

### Adding a Deadline: `deadline`

Adds a deadline task which shows when the task is due.

Format: `deadline DESCRIPTION /by BY_DATETIME`
- `BY_DATETIME` must be in the format of `dd/mm/yyyy hhmm` where
  - `dd` - Day of month (`01`to`31`)
  - `mm` - Month of year (`01`to`12`)
  - `yyyy` - Year
  - `hhmm` - 24 hours format (e.g.`2359`)

Example:
- `deadline lab report /by 29/09/2023 2359`
- `deadline assignment /by 01/10/2023 1000`

### Adding an Event: `event`

Adds a event which tracks the duration.

Format: `event DESCRIPTION /from FROM_DATETIME /to TO_DATETIME`
- `FROM_DATETIME` and `TO_DATETIME` must be in the format of `dd/mm/yyyy hhmm` where
    - `dd` - Day of month (`01`to`31`)
    - `mm` - Month of year (`01`to`12`)
    - `yyyy` - Year
    - `hhmm` - 24 hours format (e.g.`2359`)
- `FROM_DATETIME` must be before `TO_DATETIME`

Example:
- `event party /from 30/10/2023 1900 /to 30/10/2023 2359`

### Marking task as done: `mark`

Marks the status of the task as done.

Format: `mark TASK_INDEX`
- `TASK_INDEX` is the position of the task in the tasks list.
- `TASK_INDEX` must be a valid positive integer.

Example:
- `mark 5` <br>
    Marks the fifth task in the task list as done.

### Unmarking task as done: `unmark`

Unmarks the status of the task to be not done.

Format: `unmark TASK_INDEX`
- `TASK_INDEX` is the position of the task in the tasks list.
- `TASK_INDEX` must be a valid positive integer.

Example:
- `unmark 5` <br>
  Unmarks the fifth task in the task list to be not done.

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete TASK_INDEX`
- `TASK_INDEX` is the position of the task in the tasks list.
- `TASK_INDEX` must be a valid positive integer.

Example:
- `delete 5` <br>
  Deletes the fifth task from the task list.

### Finding tasks: `find`

Finds all tasks with description containing substring of the supplied keyword.

Format: `find KEYWORD`
- `KEYWORD` is the word to be searched for in the task description.
- `KEYWORD` will match any case-insensitive task description containing it.

Example:
- `find assignment` <br>
    Example Output:
  ```
  find assignment
  _________________________________________________________
  Here the matching tasks in your list:
  1.[D][ ] assignment (by: 01/10/2023 10.00AM)
  _________________________________________________________
  ```

### Exiting the program: `bye`

Exits the program

Format: `bye`
- No argument is needed.

Example:
- `bye`

## Command Summary

| Action   | Format                                                  | Example                                                   |
|----------|---------------------------------------------------------|-----------------------------------------------------------|
| List     | `list`                                                  | `list`                                                    |
| Todo     | `todo DESCRIPTION`                                      | `todo study for midterms`                                 |
| Deadline | `deadline DESCRIPTION /by BY_DATETIME`                  | `deadline lab report /by 29/09/2023 2359`                 |
| Event    | `event DESCRIPTION /from FROM_DATETIME /to TO_DATETIME` | `event party /from 30/10/2023 1900 /to 30/10/2023 2359`   |
| Mark     | `mark TASK_INDEX`                                       | `mark 5`                                                  |
| Unmark   | `unmark TASK_INDEX`                                     | `unmark 5`                                                |
| Delete   | `delete TASK_INDEX`                                     | `delete 5`                                                |
| Find     | `find KEYWORD`                                          | `find assignment`                                         |
| Exit     | `bye`                                                   | `bye`                                                     |