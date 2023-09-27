# Rene User Guide

Welcome to Rene TaskBot!
Rene is a Java app for managing your daily tasks, optimized for use via a Command Line Interface (CLI). Given below are instructions on how to use it.


❗ `Prerequisites:` Ensure you have Java 11 installed in your Computer.

On launch, you should see this interface:

    Hello from
     _____ 
    |  __  \  ____  __   _   ____ 
    | |__  | /  _  \|  \| |/  _  \
    | |  \ \|   ___/| \ | |   ___/
    |_|   \_\\____| |_|\__|\____|

    ____________________________________________________________
    今日は! I am Rene Kokoro!
    Let me record your tasks!! *blushes*

    Task-list created: tasklist.txt
    You currently have no saved tasks uWu
    ____________________________________________________________

## Features
Users may **add**, **view**, **delete** and **search** for tasks as needed.

Rene supports 3 types of tasks: `ToDo`, `Deadline` and `Event`.

`ToDo:` Task to be completed, no timing constraints.

`Deadline:` Task to be completed by a fixed deadline.

`Event:` Task to start at a start time and end at an end time.

<br/>

### Add ToDo Task
Adds a new `ToDo` task to the current task list.

**Format**: todo [TASKNAME]

**Examples**

- todo buy 22 groceries
- todo binge watch one piece to finish Wano Arc

**Expected Outcome**:

    I have added the following task OwO:
      [T][] [TASK NAME]
    Now you have [NUMBER OF TASKS] tasks in the list! UWU

<br/>

### Add Deadline Task
Adds a new `Deadline` task to the current task list.

**Format**: deadline [TASK NAME] /by [DEADLINE in dd-MM-yyyy HH:mm]

**Examples**

- deadline finish lecture videos /by 23-09-2018 23:00
- deadline complete project proposal /by 17-01-2024 08:00

**Expected Outcome**:

    I have added the following task OwO:
      [D][] [TASK NAME] (by: [DAY, MMM dd yyyy HH:mm])
    Now you have [NUMBER OF TASKS] tasks in the list! UWU

<br/>

### Add Event Task
Adds a new `Event` task to the current task list.

**Format**: event [TASK NAME] /from [START TIME in dd-MM-yyyy HH:mm] /to [END TIME in dd-MM-yyyy HH:mm]

❗**END TIME must be later than START TIME, if not user will be prompted to re-enter the timings.**

**Examples**

- event girlfriend's birthday party /from 17-12-2020 18:00 /to 18-12-2020 10:00
- event finals weekend /from 07-10-2023 08:30 /to 07-10-2023 17:00

**Expected Outcome**:

    I have added the following task OwO:
      [E][] [TASK NAME] (from: [DAY, MMM dd, yyyy HH AM/PM] to: [DAY, MMM dd, yyyy HH AM/PM])
    Now you have [NUMBER OF TASKS] tasks in the list! UWU

### List Tasks
Lists all tasks in the current task list.

**Format**: list

**Expected Outcome**:

    1: [D][] [TASK NAME] (by: [DAY, MMM dd yyyy HH:mm])
    2: [D][] [TASK NAME] (by: [DAY, MMM dd yyyy HH:mm])
    3: [E][] [TASK NAME] (from: [DAY, MMM dd, yyyy HH AM/PM] to: [DAY, MMM dd, yyyy HH AM/PM])
    4: [D][] [TASK NAME] (by: [DAY, MMM dd yyyy HH:mm])
    5: [E][] [TASK NAME] (from: [DAY, MMM dd, yyyy HH AM/PM] to: [DAY, MMM dd, yyyy HH AM/PM])
    6: [T][] [TASK NAME]
    ...

❗**If task list is empty, the following is printed:**

    No tasks found! Time to add some OWO

### Mark Task
Mark a task as done.

**Format**: mark [TASK INDEX]

**Examples**

- mark 1
- mark 3

❗**TASK INDEX must be within the total number of current tasks, if not user will be prompted to re-enter the index**

**Expected Outcome**:

    Roger that! I have marked the following task as done >w< !
        [T][X] [TASK NAME] OR [D][X] [TASK NAME] (by: [DAY, MMM dd yyyy HH:mm]) OR [E][X] [TASK NAME] (from: [DAY, MMM dd, yyyy HH AM/PM] to: [DAY, MMM dd, yyyy HH AM/PM])

### Unmark Task
Mark a task as not done.

**Format**: unmark [TASK INDEX]

**Examples**

- unmark 2
- unmark 4

❗**TASK INDEX must be within the total number of current tasks, if not user will be prompted to re-enter the index**

**Expected Outcome**:

    Roger that! I have unmarked the following task as done >w< !
        [T][] [TASK NAME] OR [D][] [TASK NAME] (by: [DAY, MMM dd yyyy HH:mm]) OR [E][] [TASK NAME] (from: [DAY, MMM dd, yyyy HH AM/PM] to: [DAY, MMM dd, yyyy HH AM/PM])

### Delete Task
Delete a task from the task list.

**Format**: delete [TASK INDEX]

**Examples**

- delete 1
- delete 5

❗**TASK INDEX must be within the total number of current tasks, if not user will be prompted to re-enter the index**

**Expected Outcome**:

    Roger that! I have deleted the following task >w< !
        [TASK DETAILS]
    Now you have [NUMBER OF REMAINING TASKS] tasks in the list! UWU

### Search Task
Search for a task from the task list using either description or timing.

**Format**: find /description [TASK DESCRIPTION] OR find /time [TASK DATETIME]

**Examples**

- find /description grocery
- find /time 21-04-2021

💡**Search will return all tasks which description contains the search keyword or which datetime contains the search datetime.**

- find /description grocery will return tasks with descriptions "grocery", "buy grocery", "grocery stock check"
- find /time 21-04  will return tasks with datetime "21-04-2021 18:00", "21-04-2009 05:00", "21-04-2023 03:35"

**Expected Outcome**:

    Here are tasks that matched your search:
    [TASK INDEX]: [TASK DETAILS]
    [TASK INDEX]: [TASK DETAILS]
    ...

❗**If no matches are found, the following is printed:**

    Here are tasks that matched your search:
    No results found :< Check your keyword is correct?
    
    OR
    
    Here are tasks that matched your search:
    No results found :< Check your time format is in dd-MM-yyyy HH:mm?

### Exit Program
Close the app.

**Format**: bye

**Expected Outcome**:

    Aww you are leaving? *sniffs*
    Well... hope to see you again soon!

