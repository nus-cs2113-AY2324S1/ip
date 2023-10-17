# 📜 <span style="color:#36bed6"> TORCHIE User Guide</span> 

Torchie is an interactive and intuitive Command Line Interface based application for keeping track of your tasks.

___

<!-- Table of Contents -->
##  <span style="color:#1d2f80">Table of Contents</span>
- [Quick Start](#quick-start)
- [Features](#features)
    + [Adding a Task](#adding-a-task)
        1. [Todo: `todo`](#todo)
        2. [Event: `event`](#event)
        3. [Deadline: `deadline`](#deadline)
    + [List all tasks: `list`](#list)
    + [Mark a task: `mark`](#mark)
    + [Unmark a task: `unmark`](#unmark)
    + [Delete a task: `delete`](#delete)
    + [Find a task: `find`](#find)
    + [Exit the program: `bye`](#bye)
    + [Saving the data](#saving)
    + [Editing the data file](#editing)
- [FAQ](#faq)
- [Command Summary](#command-list)

___

<!-- Quick Start guide -->
## <span id="quick-start" style="color:#1d2f80">Quick Start</span>
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `Torchie.jar` [here].
3. Open a command terminal, change into the directory the jar file is in using `cd <path>`, and use the `java -jar Torchie.jar` command to run the application.
4. Type any command in the correct format and press ENTER key on your computer to execute it. Refer to the full list of commands here [Command Summary](#command-list) and check out details of each command here[Features](#features)

___

<!-- Feature List -->
## <span id="features" style="color:#1d2f80">Features</span>

### Adding a Task<a id='adding-a-task'></a>:
_Multiple tasks are supported:_

### 1. Todo: `todo`<a id='todo'></a>
Adds a todo task to the task list.<br/>
Format: `todo TASK_NAME`
- `TASK_NAME` accepts a String of multiple words -- compulsory

Examples:
```js
todo memorise abc
todo fly kite
```

### 2. Deadline: `deadline`<a id='deadline'></a>
Adds a deadline task to the task list. <br/>
Format: `deadline TASK_NAME /by DEADLINE`
- `TASK_NAME` accepts a String of multiple words -- compulsory
- `DEADLINE` accepts a string in the format of YYYY-MM-DDTHH:mm.
    - `YYYY` represents the year (eg. `2023`)
    - `MM` represents the month (eg. `04` for April)
    - `DD` represents the day (eg. `08`)
    - `HH:mm` represents the time in 24-hour format (eg. `05:30` or `18:30`)
- `DEADLINE` **must** be entered in the correct format.

Examples:
```js
deadline CS2113 homework /by 2024-01-01T23:59
deadline fly kite /by 2025-08-01T12:00
```

### 3. Event: `event`<a id='event'></a>
Adds an event task to the task list.<br/>
Format: `event TASK_NAME /from START_TIME /to END_TIME
- `TASK_NAME` accepts a String of multiple words -- compulsory
- `/START_TIME` and `END_TIME` is **compulsory** and format needs to be correct
- `/START_TIME` and `END_TIME` accepts a string in the format of YYYY-MM-DD HHmm.
    - `YYYY` represents the year (eg. `2023`)
    - `MM` represents the month (eg. `04` for April)
    - `DD` represents the day (eg. `08`)
    - `HH:mm` represents the time in 24-hour format (eg. `05:30` or `18:30`)

Examples:
```js
event play with cats /from 2023-07-01 1000 /to 2023-07-01 1600
event fly kite /from 2025-04-01 0800 /to 2025-04-01 1600
```


<br/>

### List all tasks: `list`<a id='list'></a>
Shows a list of all tasks in the task list.<br/>
Format: `list`

<br/>

### Mark a task: `mark`<a id='mark'></a>
Marks a specific task as done.<br/>
Format: `mark TASK_NUMBER`
- `TASK_NUMBER` accepts positive integer from 1 to N (the number of tasks in the list) -- compulsory
- The task number refers to the index number shown in the displayed `list`

Examples:
- `mark 1` marks the 1st task in the list as done.

<br/>

### Unmark a task: `unmark`<a id='unmark'></a>
Unmarks a specific task as not done.<br/>
Format: `unmark TASK_NUMBER`
- `TASK_NUMBER` accepts positive integer from 1 to N (the number of tasks in the list)  -- compulsory
- The task number refers to the index number shown in the displayed `list`

Examples:
- `unmark 3` unmarks the 3rd task in the list as undone.


<br/>

### Delete a task: `delete`<a id='delete'></a>
Deletes a specific task in the task list.<br/>
Format: `delete TASK_NUMBER`
- `TASK_NUMBER` accepts positive integer from 1 to N (the number of tasks in the list)  -- compulsory
- The task number refers to the index number shown in the displayed `list`

Examples:
- `delete 2` deletes the 2nd task in the list


<br/>

### Find a task: `find`<a id='find'></a>
Searches the task list for all tasks containing the entered keyword.<br/>
Format: `find KEYWORD`
- `KEYWORD` keywords to match task  -- compulsory
- `KEYWORD` is not case-sensitive
- Only exact matches will be found

Examples:
```js
find kite
find fly kite
```


<br/>

### Exit the program: `bye`<a id='bye'></a>
Exits the program.<br/>
Format: `bye`

<br/>

### Saving the data<a id='saving'></a>
List of tasks are saved in local hard disk automatically after exiting the program. <br/>
<br/>

### Editing the data file<a id='editing'></a>
Data of saved Task List are saved in a .txt file in `[JAR file location]/data/torchie.txt`. <br/>
Edits must be made according to the formatting of the data.

<br/>

___
<!-- FAQs -->
## <span id="faq" style="color:#1d2f80">FAQ</span> 💻
> Q: How do I transfer my data to another Computer?
>
> A: In your new application folder in the new Computer, paste the copied `/data/torchie.txt` file from your old Computer into the same folder as the new `torchie.jar`.

___
<!-- Summary of Commands -->
## <span id="command-list" style="color:#1d2f80">Command Summary</span> 🔑

| Command      | Format                                        |
|--------------|-----------------------------------------------|
| **todo**     | `todo TASK_NAME`                              |
| **deadline** | `deadline TASK_NAME /by DEADLINE`              |
| **event**    | `event TASK_NAME /from START_DATE /to END_DATE` |
| **list**     | `list`                                        |
| **mark**     | `mark TASK_NUMBER`                            |
| **unmark**   | `unmark TASK_NUMBER`                          |
| **delete**   | `delete TASK_NUMBER`                          |
| **find**     | `find KEYWORD`                                |
| **exit**     | `bye`                                         |
