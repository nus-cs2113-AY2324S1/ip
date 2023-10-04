# Wenny Task Manager User Guide
Wenny is your friendly task manager that helps you keep track of your endless numbers of to do tasks, deadlines and events.  
## Features 
Notes about the command format:
1. Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in todo DESCRIPTION, DESCRIPTION is a parameter which can be used as todo Homework.

2. Extraneous parameters for commands that do not take in parameters (such as list and bye) will be ignored.
e.g. if the command specifies list 123, it will be interpreted as list.

### List current tasks `list`
List all current tasks.
Format: `list`

### Exit the program `bye`
Exit the program.
Format: `bye`

### Add a to-do task `todo`
Add to-do task into the list.
Format: `list DESCRIPTION`
Example:
- `todo complete week 7 tasks`
- `todo sleep`

### Add a deadline task `deadline`
Add a task with a deadline
Format: `deadline DESCRIPTION /by DEADLINE`
Example:
- `deadline complete week 8 tasks /by 12 Oct`

### Add an event task `event`
Add an event into the task list
Format: `event DESCRIPTION /from START_TIME /to END_TIME`
Example:
- `event IA interview /from 7 Oct 2pm /to 7 Oct 4pm`
- 
### Mark a task as done `mark`
Mark a task on the task list as done
Format: `mark TASK_NUMBER`
Exmaple:
- `mark 1`

### Mark a task as undone `unmark`
Mark a task on the task list as undone
Format: `unmark TASK_NUMBER`
Exmaple:
- `unmark 1`


### Delete a task `delete`
Delete a task from the task list
Format: `delete TASK_NUMBER`
Exmaple:
- `delete 1`

### Find the tasks that matches a certain keyword `find`
Find the tasks that matches a certain keyword 
Format: `find KEYWORD`
Exmaple:
- `find John`
