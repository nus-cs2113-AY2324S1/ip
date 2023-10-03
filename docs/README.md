# User guide
Andrew Tate is a **CLI tool** to help you manage your tasks.

## Contents
* [Quick start](#quick-start--return-to-contents)
* [Features](#features--return-to-contents)
  -  [List current tasks: `list`](#list-current-tasks--list--return-to-contents)
  -  [Exiting the program: `bye`](#exiting-the-program-bye--return-to-contents)
  -  [Add a ToDo: `todo`](#add-a-ToDo-todo--return-to-contents)
  -  [Add a Deadline: `deadline`](#add-a-Deadline-deadline--return-to-contents)
  -  [Add an Event: `event`](#add-an-Event-event--return-to-contents)
  -  [Mark a task as done: `mark`](#mark-a-task-as-done-mark--return-to-contents)
  -  [Unmark a task as done: `unmark`](#unmark-a-task-from-done-unmark--return-to-contents)
  -  [Delete a task: `delete`](#delete-a-task-delete--return-to-contents)
  -  [Locate a task by name: `find`](#locate-a-task-by-name-find--return-to-contents)
* [Command summary](#command-summary--return-to-contents)


## Quick start | [Return to contents](#contents)
1. Make sure that you have Java 11 or above installed on your computer.
   1. [How do I check the version of my Java](https://www.java.com/en/download/help/version_manual.html)
2. Download the latest ip.jar release from [GitHub](https://github.com/000verflow/ip/releases)
3. Create a `data` folder within the same folder as `ip.jar`
4. Using a CLI prompt like `command prompt` for Windows users and `Terminal` for Mac users
5. Use `cd` command to move to the folder where you put `ip.jar`
6. Run the program with `java -jar ip.jar`

## Features | [Return to contents](#contents)

> Note:
> - Andrew Tate saves your tasks locally using relative path `./data/history.txt`, please create the `./data` directory
    for the program
> - **UPPERCASE** parameters are required by the user.
    >   - e.g `todo DESCRIPTION`
> - If a command doesn't take any parameters, it will be ignored.
    >   - e.g `bye 1234`

## List current tasks : `list` | [Return to contents](#contents)
Lists all current tasks, marked done or undone.

Format: `list`

## Exiting the program: `bye` | [Return to contents](#contents)
Exits from the program. Any current tasks would've been saved in the `history.txt` file.

Format: `bye`

## Add a ToDo: `todo` | [Return to contents](#contents)
Creates a ToDo task and add it into the task list.

Format: `todo DESCRIPTION`

Example:
- `todo assignment for cs2113`
- `todo revise notes`

## Add a Deadline: `deadline` | [Return to contents](#contents)
Creates a Deadline task and add it into the task list.

Format: `deadline DESCRIPTION /by DEADLINE`
> Note: DEADLINE is in YYYY-MM-DD HHMM format

Example:
- `deadline assignment submission /by 2023-10-15 1800`
- `deadline create notes /by 2023-09-12 1330 `

## Add an Event: `event` | [Return to contents](#contents)
Creates an Event task and add it into the task list.

Format: `event DESCRIPTION /from EVENTSTART /to EVENTEND`
> Note: EVENTSTART/EVENTEND is in YYYY-MM-DD HHMM format

Example:
- `event tiktok hackathon /from 2023-10-15 1800 /to 2023-10-17 0900`


## Mark a task as done: `mark` | [Return to contents](#contents)
Mark a task as done, displayed with a `[X]`.

Format: `mark INDEX`

>Note: Marks the task at the specified `INDEX`, where index starts from 1,2,3...

Examples:
- `mark 1` will mark the 1st task as done in the task list.

## Unmark a task from done: `unmark` | [Return to contents](#contents)
Unmark a task from done to undone, displayed with a `[ ]`.

Format: `unmark INDEX`

>Note: Marks the task at the specified `INDEX`, where index starts from 1,2,3...

Examples:
- `unmark 1` will unmark the 1st task from done to undone in the task list.


## Delete a task: `delete` | [Return to contents](#contents)
Deletes a task from the task list.

Format: `delete INDEX`

>Note: Deletes the task at the specified `INDEX`, where index starts from 1,2,3...

Examples:
- `delete 1` deletes the 1st task in the task list.

## Locate a task by name: `find` | [Return to contents](#contents)
Find any tasks in the task list that match given name.

Format: `find KEYWORD`

> Note:
> - Only the description is searched.
> - Partial words will be matched. e.g. `cs` will match `cs2113`.
> - The search is case-insensitive. e.g. `cs2113` will match `CS2113`

Examples:
- `find submissions` returns `cs2113 project submission deadline`


## Command summary | [Return to contents](#contents)

| Action   | Command                                                            |
|----------|--------------------------------------------------------------------|
| list     | `list`                                                             |
| bye      | `bye`                                                              |
| todo     | `todo DESCRIPTION` `todo powerpoint slides for cs2113`             |
| deadline | `deadline assignment submission /by 2023-10-15 1800`               |
| event    | `event tiktok hackathon /from 2023-10-15 1800 /to 2023-10-17 0900` |
| mark     | `mark INDEX` `mark 1` `mark 2`                                     |
| unmark   | `unmark INDEX` `unmark 1` `unmark 2`                               |
| delete   | `delete INDEX` `delete 1`                                          |
| find     | `find KEYWORD` `find submissions`                                  |                     

