# User Guide

## Features
Frank is a CLI todo list that is capable of creating, 
displaying and saving your tasks, deadlines and events.


## Usage
### Command Parser
When you load into Frank you will be prompted by the command parser:
```
Available Commands:
todo, deadline, event,
mark <index>, unmark <index>, delete <index>, search <terms>,
list, clear, bye
```

### `todo`, `deadline`, `event` - Create Task

Just type "todo", "deadline" or "event" to start the Task wizard.
All dates (deadline, event) must be typed in 11pm/25/12/23 format (TT/DD/MM/YY)

Deadline will inform you how many days until your deadline

Event will inform you how many hours the event lasts

#### Example of usage: 

```
todo
Example Todo
```

```
deadline
Example Deadline
11PM/4/10/23
```

```
event
Example Event
10AM/3/10/23
11PM/3/10/23
```

#### Expected outcome:

`Added: [T][ ] Example Todo`

`Added: [D][ ] Example Deadline (by: 11PM Wed, 4 Oct 23, 2 days from now!)`

`Added: [E][ ] Example Event (from: 10AM Tue, 3 Oct 23 to: 11PM Tue, 3 Oct 23, 13 hours long.)`

### `Mark`, `Unmark`, `Delete`

Just type `mark/unmark/delete 1` to perform the action on the first Task

```
mark 1

------------------------------------------------------------------------------------------------------------------------------
Tres Bien! I have marked this as done: 
[X] Touch some grass
------------------------------------------------------------------------------------------------------------------------------
```
```
unmark 1

------------------------------------------------------------------------------------------------------------------------------
Tres Bien! I have marked this as not done yet: 
[ ] Touch some grass
------------------------------------------------------------------------------------------------------------------------------
```
```
delete 1

------------------------------------------------------------------------------------------------------------------------------
Deleted: [T][ ] Touch some grass
------------------------------------------------------------------------------------------------------------------------------
```

### `Search`

If you want to find all the terms with "EE2026 Exam"

Just type `search Ee2026 exam` (case insensitive)

#### Example of Usage:
`search EE2026 exam`

#### Expected outcome:
```
------------------------------------------------------------------------------------------------------------------------------
Here are the selected tasks: 
1. [T][ ] EE2026 Exam Prep
2. [E][ ] EE2026 Exam (from: 5PM Sat, 7 Oct 23 to: 7PM Sat, 7 Oct 23, 2 hours long.)
------------------------------------------------------------------------------------------------------------------------------
```

### List, Clear, Bye

Type "list" to show all Tasks and their status
```
list

------------------------------------------------------------------------------------------------------------------------------
1. [T][ ] Touch some grass
2. [T][ ] Hit the gym
3. [T][ ] Get a girlfriend

------------------------------------------------------------------------------------------------------------------------------
```

Type "clear" to clear everything from the current list and memory (you will be prompted to confirm this action)
```
clear

This will remove all tasks! Are you sure? + Type Y to confirm, another key to cancel. 
y

------------------------------------------------------------------------------------------------------------------------------
Clearing tasks...
------------------------------------------------------------------------------------------------------------------------------
```

Type "bye" to close the program
```
bye

------------------------------------------------------------------------------------------------------------------------------

Goodbye User, zai jian yong ze, sampai jumpa lagi user 

------------------------------------------------------------------------------------------------------------------------------
```

### Save

There is no formal command to save your work, but everything is automatically saved to /data/tasklist.txt after every command (except bye). 
It is formatted in the same way as `list` so it can be easily printed
for offline use.
