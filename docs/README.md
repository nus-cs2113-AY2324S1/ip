# User Guide


<h2> Introduction </h2>
Ahnge is a chatbot that utilises a <strong>Command Line Interface (CLI) to help you to manage tasks.</strong>


## Table of Content
* **[Quick Start](#quick-start)**

* **[Features](#features)**
    * **[Adding a todo: `todo`](#adding-todo)**
    * **[Adding a deadline: `deadline`](#adding-deadline)**
    * **[Adding an event: `event`](#adding-event)**
    * **[Listing all tasks: `list`](#listing-all-tasks)**
    * **[Marking task as done: `mark`](#marking-a-task-as-done)**
    * **[Finding task: `find`](#finding-a-task)**
    * **[Deleting task: `delete`](#deleting-a-task)**
    * **[Exiting the program: `bye`](#exiting-the-program)**
* **[Command summary](#command-summary)**


<h2 id="quick-start"> Quick Start</h2>

1. Ensure you have `Java 11` installed on your Computer.

2. Download the latest `ip.jar`.

3. Copy the `ip.jar` file to any folder that you wish to use as the home folder.

4. Open your command terminal with the `windows key + r`.

5. `cd` into the folder where you copied the jar file.

6. Enter `java -jar ip.jar` to run the program.
7. If everything is done correctly, an output that looks like this will show up:
   ```
    ==============================
    Hello! I'm Ahnge
    What can I do for you?
    ==============================
   ```

<h2 id="features"> Features</h2>

<h2 id="adding-todo"> Adding a todo: todo </h2>

Adds a todo to Ahnge.<br>
**Command**: `todo DESCRIPTION`<br>
**Constraints**: `DESCRIPTION` must be a non-empty string.<br>
**Example**:

```
todo submit work
==============================
Ahnge: Yessir. I've added this task:
  [T][ ] submit work
Ahnge: Now you have 1 tasks in the list.
==============================
```

<h2 id="adding-deadline"> Adding a deadline: deadline </h2>

Add a deadline to Ahnge.<br>
**Command**: `deadline DESCRIPTION /by DATE`<br>
**Constraints**: `DESCRIPTION` must be a non-empty string.  <br>
**Example**:

```
You: deadline submit chatbot /by Wednesday
==============================
Ahnge: Yessir. I've added this task:
  [D][ ] submit chatbot (by: Wednesday)
Ahnge: Now you have 1 tasks in the list.
==============================
```

<h2 id="adding-event"> Adding an event: event</h2>

Adds an event to the Ahnge.<br>
**Format**: `event DESCRIPTION /from START_DATE /to END_DATE`<br>
**Constraints**: `DESCRIPTION` must be a non-empty string.<br>
**Example**:

```
You: event cry about midterms /from Friday /to Sunday
==============================
Ahnge: Yessir. I've added this task:
  [E][ ] cry about midterms (from: Friday to: Sunday)
Ahnge: Now you have 1 tasks in the list.
==============================
```

<h2 id="listing-all-tasks"> Listing all tasks: list </h2>

Have Ahnge list all the tasks that you have given it.<br>
**Format**: `list`<br>
**Example**:

```
You: list
==============================
Ahnge: Here are the tasks in your list:
 1.[T][ ] submit midterms
 2.[T][ ] submit assignments
==============================
```


<h2 id="marking-a-task-as-done"> Marking a task as done: mark</h2>

Mark a task as done.<br>
**Format**: `mark INDEX`<br>
**Constraints**: `INDEX` must be a positive integer and must be within the range of the task list. <br>
**Example**:

```
You: list
==============================
Ahnge: Here are the tasks in your list:
 1.[T][ ] submit
 2.[T][ ] submit
 3.[T][ ] submit
 4.[T][ ] cry
==============================
You: mark 2
==============================
Ahnge: I've marked this task as done:
  [T][X] submit
==============================
You: list
==============================
Ahnge: Here are the tasks in your list:
 1.[T][ ] submit
 2.[T][X] submit
 3.[T][ ] submit
 4.[T][ ] cry
==============================
```

<h2 id="finding-a-task"> Finding a task: find</h2>

Find all the tasks that contain the keyword.<br>
**Format**: `find KEYWORD`<br>
**Constraints**: `KEYWORD` must be a non-empty string.<br>
**Example**:

```
You: find midterms
==============================
Ahnge: Here are the matching tasks in your list:
 1.[T][ ] submit midterms
==============================
```

<h2 id="deleting-a-task"> Deleting a task: delete</h2>

Delete a task from the task manager by its index.

**Format**: `delete INDEX`<br>
**Constraints**: `INDEX` must be a positive integer and must be within the range of the task list. <br>
**Example**:

```
You: delete 4
==============================
Ahnge: Noted. I've removed this task:
  [T][ ] cry
Ahnge: Now you have 3 tasks in the list.
```

<h2 id="exiting-the-program"> Exiting the program: bye</h2>

Exit the program.<br>
**Format**: `bye`<br>
**Example**:

```
You: bye
==============================
Ahnge: Bye! Have a nice day.
==============================
```

<h2 id='command-summary'> Command Summary </h2>

| Action                          | Format                                    | Example                                     |
|---------------------------------|-------------------------------------------|---------------------------------------------|
| [todo](#adding-todo)            | **todo** DESCRIPTION                      | todo eat                                    |
| [deadline](#adding-deadline)    | **deadline** DESCRIPTION /by DATE         | deadline submit assignment /by 2023-02-07   |
| [event](#adding--event)         | **event** DESCRIPTION /from DATE /to DATE | event midterms /from 2023-02-07 1pm /to 3pm |
| [list](#listing-all-tasks)      | **list**                                  | list                                        |
| [mark](#marking-a-task-as-done) | **mark** INDEX                            | mark 3                                      |
| [find](#finding-a-task)         | **find** KEYWORD                          | find homework                               |
| [delete](#deleting-a-task)      | **delete** INDEX                          | delete 3                                    |
| [bye](#exiting-the-program)     | **bye**                                   | bye                                         |


