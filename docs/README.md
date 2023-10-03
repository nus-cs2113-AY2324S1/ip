# Jarvis User Guide

Welcome to Jarvis TaskBot!
Jarvis is a Java app for managing your daily tasks, optimized for use via a Command Line Interface (CLI). Given below are instructions on how to use it.


❗ `Prerequisites:` Ensure you have Java 11 installed in your Computer.

On launch, you should see this interface:

    Hello from
          /$$$$$                                /$$          
         |__  $$                               |__/          
            | $$  /$$$$$$   /$$$$$$  /$$    /$$ /$$  /$$$$$$$
            | $$ |____  $$ /$$__  $$|  $$  /$$/| $$ /$$_____/
       /$$  | $$  /$$$$$$$| $$  \__/ \  $$/$$/ | $$|  $$$$$$
      | $$  | $$ /$$__  $$| $$        \  $$$/  | $$ \____  $$
      |  $$$$$$/|  $$$$$$$| $$         \  $/   | $$ /$$$$$$$/
      \______/  \_______/|__/          \_/    |__/|_______/
    ____________________________________________________________
    Hello Sir! I am JARVIS, your personal assistant!

    Task-list created: tasklist.txt
    ____________________________________________________________

## Features
Users may **add**, **view**, **delete** and **search** for tasks as needed.

Jarvis supports 3 types of tasks: `ToDo`, `Deadline` and `Event`.

`ToDo:` Task to be completed

`Deadline:` Task to be completed by a deadline.

`Event:` Task to start at a start time and end at an end time.

<br/>

### Add ToDo Task
Adds a new `ToDo` task to the task list.

**Format**: todo [TASKNAME]

**Examples**

- todo Linear Algebra Homework 1
- todo UROP data cleaning
- todo buy 50 apples

**Expected Outcome**:

    Got it. I've added this task:
      [T][] [TASK NAME]
    Now you have [NUMBER OF TASKS] tasks in the list sir.

❗**Invalid Todo error**

      Invalid format. Description cannot be empty for todo sir.
<br/>

### Add Deadline Task
Adds a new `Deadline` task to the task list.

**Format**: deadline [TASK NAME] /by [DEADLINE in dd-MM-yyyy HH:mm]

**Examples**

- deadline finish CS2113 Increment Level-10 /by 03-10-2023 23:59
- deadline complete CS2113 tP /by 03-01-2024 08:00

**Expected Outcome**:

    Got it. I've added this task:
      [D][] [TASK NAME] (by: [DAY, MMM dd yyyy HH:mm])
    Now you have [NUMBER OF TASKS] tasks in the list sir.

❗**Invalid Deadline error**

      Invalid format. Use: deadline <description> /by <time> sir
<br/>

### Add Event Task
Adds a new `Event` task to the task list.

**Format**: event [TASK NAME] /from [START TIME in dd-MM-yyyy HH:mm] /to [END TIME in dd-MM-yyyy HH:mm]

**Examples**

- event CS2113 Mid-Terms /from 03-10-2023 08:00 /to 03-10-2023 10:00
- event EE2211 Mid-Terms /from 08-10-2023 15:00 /to 08-10-2023 16:00

**Expected Outcome**:

      Got it. I've added this task:
         [E][] [TASK NAME] (from: [DAY, MMM dd, yyyy HH AM/PM] to: [DAY, MMM dd, yyyy HH AM/PM])
      Now you have [NUMBER OF TASKS] tasks in the list sir.

❗**Invalid Event error**

     Invalid format. Use: event <description> /from <start_time> /to <end_time> sir
<br/>

### List Tasks
Lists all tasks in the task list.

**Format**: list

**Expected Outcome**:

    1: [D][] [TASK NAME] (by: [DAY, MMM dd yyyy HH:mm])
    2: [E][] [TASK NAME] (from: [DAY, MMM dd, yyyy HH AM/PM] to: [DAY, MMM dd, yyyy HH AM/PM])
    3: [T][] [TASK NAME]
    ...

❗**Empty List Error**

    Task list is empty! Please add a task sir.

### Mark Task
Mark a task as done.

**Format**: mark [TASK INDEX]

**Examples**

- mark 1
- mark 2

❗**TASK INDEX must be within the size of the task list**

**Expected Outcome**:

    Nice! I've marked this task as done sir:
        [T][X] [TASK NAME] 

### Unmark Task
Mark a task as undone.

**Format**: unmark [TASK INDEX]

**Examples**

- unmark 1
- unmark 2

❗**TASK INDEX must be within the size of the task list**

**Expected Outcome**:

    Oh NO! I've unmarked this task as undone sir:
        [T][] [TASK NAME] 
    Get Grinding SIR

### Delete Task
Delete a task from the task list.

**Format**: delete [TASK INDEX]

**Examples**

- delete 1
- delete 2

❗**TASK INDEX must be within the size of the task list**

**Expected Outcome**:

    Noted sir. I've removed this task sir:
        [TASK DETAILS]
    Now you have [NUMBER OF TASKS] tasks in the list! 

### Search Task
Search for a task from the task list via description

**Format**: find [TASK DESCRIPTION]

**Examples**

- find /description homework

💡**Search will return all tasks which description contains the search keyword**

- find /description homework will return tasks with descriptions "homework", "linear algebra homework", "science homework"

**Expected Outcome**:

    Here are tasks that matched your search sir:
    [TASK INDEX]: [TASK DESCRIPTION]
    [TASK INDEX]: [TASK DESCRIPTION]
    ...

❗**If no matches are found, this will be the error prompt:**

    No results found. Please check your keyword is correct sir?

### Exit Program
Close the app.

**Format**: bye

**Expected Outcome**:

    Good bye sir! Have a good day
