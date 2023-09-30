# NotChatGPT User Guide

NotChatGPT is a Java app for managing your daily tasks, optimized for use via a Command Line Interface (CLI).
Below are instructions on how to use it.


❗ `Prerequisites:` Java 11 installed.

## Features
Users may **add**, **view**, **delete** and **find**  tasks which are stored inside a task list.

NotChatGPT supports 3 types of tasks: `ToDo`, `Deadline` and `Event`.

`ToDo:` Task with no timing constraints.

`Deadline:` Task with a fixed deadline.

`Event:` Task with a designated starting time and ending time.

At any time, the user can use the **help** command to summon a list of all the available commands and their respective formats.

<br/>

### Add ToDo Task
Adds a new `ToDo` task to the current task list.

**Format**: todo [TASK NAME]

**Examples**

- todo buy food from canteen

**Expected Output**: 

   Got it. I've added this task: <br>
   [T][ ] [TASK NAME]

<br/>

### Add Deadline Task
Adds a new `Deadline` task to the current task list.

**Format**: deadline [TASK NAME] /by [DEADLINE in dd-MM-yyyy HH:mm]

**Example**

- deadline finish revising for exam /by 23-09-2023 23:59 

**Expected Output**: 

   Got it. I've added this task: <br>
   [D][ ] [TASK NAME] (by: [dd-MM-yyyy HH:mm])

<br/>

### Add Event Task
Adds a new `Event` task to the current task list.

**Format**: event [TASK NAME] /from [START TIME in dd-MM-yyyy HH:mm] /to [END TIME in dd-MM-yyyy HH:mm]

**Example**

- event career fair /from 10-08-2022 12:00 /to 11-08-2020 18:00

**Expected Output**: 

   Got it. I've added this task: <br>
   [E][ ] [TASK NAME] (from: [DAY, MMM dd, yyyy HH AM/PM] to: [DAY, MMM dd, yyyy HH AM/PM])

### List Tasks
Lists all tasks in the current task list.

**Format**: list

**Expected Output**: 

   1. [D][ ] [TASK NAME] (by: [dd-MM-yyyy HH:mm])
   2. [D][ ] [TASK NAME] (by: [dd-MM-yyyy HH:mm]) 
   3. [E][ ] [TASK NAME] (from: [dd-MM-yyyy HH:mm] to: [dd-MM-yyyy HH:mm]) 
   4. [T][ ] [TASK NAME]
   ...

### Mark Task
Mark a task as done.

**Format**: mark [TASK INDEX]

**Example**

- mark 2 

**Expected Output**: 

   Nice! I've marked this task as done: <br>
   [T][X] [TASK NAME] OR <br>
   [D][X] [TASK NAME] (by: [dd-MM-yyyy HH:mm]) OR <br>
   [E][X] [TASK NAME] (from: [dd-MM-yyyy HH:mm] to: [dd-MM-yyyy HH:mm]) 
        
### Unmark Task
Mark a task as not done.

**Format**: unmark [TASK INDEX]

**Example**

- unmark 2 

**Expected Output**: 

   Aight! I've marked this task as not done yet. <br>
   [T][ ] [TASK NAME] OR <br>
   [D][ ] [TASK NAME] (by: [dd-MM-yyyy HH:mm]) OR <br>
   [E][ ] [TASK NAME] (from: [dd-MM-yyyy HH:mm] to: [dd-MM-yyyy HH:mm])
        
### Delete Task
Delete a task from the task list.

**Format**: delete [TASK INDEX]

**Example**

- delete 2  

**Expected Output**: 

   Noted. I've removed this task: <br>
   [TASK DETAILS] <br>
   Now you have [NUMBER OF REMAINING TASKS] tasks in your list. 
        
### Find Task
Find tasks from the task list using either description or timing.

**Format**: find /description [TASK DESCRIPTION] OR find /time [TASK DATETIME]

**Example**

- find /description exam  
- find /time 21-04-2021 14:30

💡**All tasks which description contains the search keyword or which datetime contains the search datetime will be displayed.**

**Expected Output**: 

   Here are the matching tasks in your list:
   [TASK INDEX]. [TASK DETAILS]
   [TASK INDEX]. [TASK DETAILS] 
   ...
    
### Exit Program
Exit from the application.

**Format**: bye

**Expected Output**: 

   Bye Broski! Thank you for choosing notChatGPT :)

