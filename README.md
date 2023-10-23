# Duke Chatbot

[User Guide](#user-guide) | [About Us](#about-us) | [View on GitHub](https://github.com/your-github-link-here)

## User Guide

### Introduction
The Duke Chatbot is a desktop app optimized for managing tasks and events, making use of a Command Line Interface (CLI) whilst offering the benefits of a Graphical User Interface (GUI). If you're a fast typer, our chatbot will help you manage your tasks more efficiently than traditional GUI apps.

#### Quick Start:

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest jar file.
3. Copy the file to the folder you want to use as the home directory for your Chatbot.
4. Open a command terminal, navigate to the folder containing the jar file, and run the chatbot using the `java -jar chatbot.jar` command.

#### Features

:information_source: **Notes about the command format:**
- Commands are not case-sensitive. For instance, `list` and `LIST` will function identically.
- Parameters that need to be supplied by the user are in UPPER_CASE. For example, in the `find KEYWORD`, KEYWORD is a parameter to be provided, like `find homework`.

#### Commands

- **List**: Shows all tasks.
    - Format: `list`

- **Mark**: Marks a task as done.
    - Format: `mark TASK_INDEX`
    - Example: `mark 2` marks the second task as done.

- **Unmark**: Unmarks a task.
    - Format: `unmark TASK_INDEX`
    - Example: `unmark 2` unmarks the second task.

- **Delete**: Deletes a task.
    - Format: `delete TASK_INDEX`
    - Example: `delete 2` deletes the second task.

- **Deadline**: Adds a task with a deadline.
    - Format: `deadline TASK_NAME /by DATE_TIME`
    - Example: `deadline Submit Homework /by 2023-10-15 1800`

- **Event**: Adds an event.
    - Format: `event EVENT_NAME /at DATE_TIME`
    - Example: `event Team Meeting /at 2023-10-20 1400`

- **Todo**: Adds a to-do task.
    - Format: `todo TASK_NAME`
    - Example: `todo Read Book`

- **Find**: Searches tasks containing the keyword.
    - Format: `find KEYWORD`
    - Example: `find meeting` returns all tasks containing the word 'meeting'.

#### FAQ
- **Q: How can I backup my tasks?**
    - A: All tasks are automatically saved in the hard drive after every command that modifies data. To backup, simply copy the saved data file and store it in a safe location.

#### Command Summary
| Action    | Format, Examples                                                                 |
|-----------|-----------------------------------------------------------------------------------|
| List      | `list`                                                                            |
| Mark      | `mark TASK_INDEX` e.g., `mark 2`                                                  |
| Unmark    | `unmark TASK_INDEX` e.g., `unmark 2`                                              |
| Delete    | `delete TASK_INDEX` e.g., `delete 2`                                              |
| Deadline  | `deadline TASK_NAME /by DATE_TIME` e.g., `deadline Submit Homework /by 2023-10-15`|
| Event     | `event EVENT_NAME /at DATE_TIME` e.g., `event Team Meeting /at 2023-10-20`        |
| Todo      | `todo TASK_NAME` e.g., `todo Read Book`                                           |
| Find      | `find KEYWORD` e.g., `find meeting`                                               |

