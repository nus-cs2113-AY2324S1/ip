# User guide
Andrew Tate is a **CLI tool** to help you manage your tasks.

## Contents
* [Quick start](#Quick-start--Return-to-contents)
* [Features](#Features--Return-to-contents)
  -  [List current tasks: `list`](#List-current-tasks--list--Return-to-contents)
  -  [Exiting the program: `bye`](#Exiting-the-program-bye--Return-to-contents)
  -  [Add a ToDo: `todo`](#Add-a-ToDo-todo--Return-to-contents)
  -  [Add a Deadline: `deadline`](#Add-a-Deadline-deadline--Return-to-contents)
  -  [Add an Event: `event`](#Add-an-Event-event--Return-to-contents)
  -  [Mark a task as done: `mark`](#Mark-a-task-as-done-mark--Return-to-contents)
  -  [Unmark a task as done: `unmark`](#Unmark-a-task-from-done-unmark--Return-to-contents)
  -  [Delete a task: `delete`](#Delete-a-task-delete--Return-to-contents)
  -  [Locate a task by name: `find`](#Locate-a-task-by-name-find--Return-to-contents)
* [Command summary](#Command-summary--Return-to-contents)


## Quick start | [Return to contents](#Contents)
1. Make sure that you have Java 11 or above installed on your computer.
  - [How do I check the verison of my Java](https://www.java.com/en/download/help/version_manual.html)
2. Download the latest ip.jar release from [Github](https://github.com/000verflow/ip/releases)
3. Create a `data` folder within the same folder as `ip.jar`
4. Using a CLI prompt like `command prompt` for Windows users and `Terminal` for Mac users
5. Use `cd` command to move to the folder where you put `ip.jar`
6. Run the program with `java -jar ip.jar`

## Features | [Return to contents](#Contents)

> Note:
> - Andrew Tate saves your tasks locally using relative path `./data/history.txt`, please create the `./data` directory
    for the program
> - **UPPERCASE** parameters are required by the user.
    >   - e.g `todo DESCRIPTION`
> - If a command doesn't take any parameters, it will be ignored.
    >   - e.g `bye 1234`

## List current tasks : `list` | [Return to contents](#Contents)
Lists all current tasks, marked done or undone.

Format: `list`

## Exiting the program: `bye` | [Return to contents](#Contents)
Exits from the program. Any current tasks would've been saved in the `history.txt` file.

Format: `bye`

## Add a ToDo: `todo` | [Return to contents](#Contents)
Creates a ToDo task and add it into the task list.

Format: `todo DESCRIPTION`

Example:
- `todo assignment for cs2113`
- `todo revise notes`

## Add a Deadline: `deadline` | [Return to contents](#Contents)
Creates a Deadline task and add it into the task list.

Format: `deadline DESCRIPTION /by DEADLINE`
> Note: DEADLINE is in YYYY-MM-DD HHMM format

Example:
- `deadline assignment submission /by 2023-10-15 1800`
- `deadline create notes /by 2023-09-12 1330 `

## Add an Event: `event` | [Return to contents](#Contents)
Creates an Event task and add it into the task list.

Format: `event DESCRIPTION /from EVENTSTART /to EVENTEND`
> Note: EVENTSTART/EVENTEND is in YYYY-MM-DD HHMM format

Example:
- `event tiktok hackathon /from 2023-10-15 1800 /to 2023-10-17 0900`


## Mark a task as done: `mark` | [Return to contents](#Contents)
Mark a task as done, displayed with a `[X]`.

Format: `mark INDEX`

>Note: Marks the task at the specified `INDEX`, where index starts from 1,2,3...

Examples:
- `mark 1` will mark the 1st task as done in the task list.

## Unmark a task from done: `unmark` | [Return to contents](#Contents)
Unmark a task from done to undone, displayed with a `[ ]`.

Format: `unmark INDEX`

>Note: Marks the task at the specified `INDEX`, where index starts from 1,2,3...

Examples:
- `unmark 1` will unmark the 1st task from done to undone in the task list.


## Delete a task: `delete` | [Return to contents](#Contents)
Deletes a task from the task list.

Format: `delete INDEX`

>Note: Deletes the task at the specified `INDEX`, where index starts from 1,2,3...

Examples:
- `delete 1` deletes the 1st task in the task list.

## Locate a task by name: `find` | [Return to contents](#Contents)
Find any tasks in the task list that match given name.

Format: `find KEYWORD`

> Note:
> - Only the description is searched.
> - Partial words will be matched. e.g. `cs` will match `cs2113`.
> - The search is case-insensitive. e.g. `cs2113` will match `CS2113`

Examples:
- `find submissions` returns `cs2113 project submission deadline`


## Command summary | [Return to contents](#Contents)

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

