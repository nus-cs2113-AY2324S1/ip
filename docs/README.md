# BOTBOT's User Guide
Welcome to the user guide of your personal planner, BotBot~ 
(Bop!)

## Features 

### Adding tasks to your planner
BotBot allows you to store 3 different types of tasks, namely: Todo tasks, Deadline tasks and Event tasks. 
- [T] Todo: Basic tasks with title
- [D] Deadline: Tasks that comes with a specific deadline
- [E] Event: Tasks that occur within a specific period

### Deleting tasks
Made an error? BotBot is forgiving and provides you with a second opportunity to redeem yourself with the delete function.

### Marking tasks as done
Completed your task? BotBot enables you to mark a task as completed `[X]` or incomplete `[ ]`

### Finding tasks
Too busy with too many tasks? BotBot allows you to search for your task using keyword!

### Saving tasks
Upon closure, BotBot automatically saves your tasks!

## Usage

### `todo` - adds Todo task
Creates and adds Todo tasks to list

Format: 
`todo TASK_NAME`

Example of usage:
`todo Attend Lecture`

Expected outcome:
```
Got it. I've added this task:
[T] [ ] Attend Lecture
Now you have X tasks in the list.
```

### `deadline` - adds Deadline task
Creates and adds Deadline tasks to list 

Format:
`deadline TASK_NAME /by [DEADLINE]`

Example of usage:
`deadline Assignment /by 9th August`

Expected outcome:
```
Got it. I've added this task:
[D] [ ] Assignment (by: 9th August)
Now you have X tasks in the list.
```

### `event` - adds Event task
Creates and adds Deadline tasks to list

Format:
`event TASK_NAME /from [START] /to [END]`

Example of usage:
`event NDP Parade /from 9th August 1300 /to 9th August 1600`

Expected outcome:
```
Got it. I've added this task:
[E] [ ] NDP Parade (from:  9th August 1300 to:  9th August 1600)
Now you have X tasks in the list.
```

### `delete` - deletes task
removes task from list
Format:
`delete TASK_INDEX`

Example of usage:
`delete 1`

Expected outcome:
```
Noted. I've removed this task:
[T] [ ] TASK_NAME
Now you have X tasks in the list.
```

### `mark` - marks task as done
Marks task as done with `[X]`

Format:
`mark TASK_INDEX`

Example of usage:
`mark 1`

Expected outcome:
```
Nice! I've marked this task as done: 
[T] [X] TASK_NAME
```

### `unmark` - marks task as not done
Marks task as not done with `[ ]`

Format:
`unmark TASK_INDEX`

Example of usage:
`unmark 1`

Expected outcome:
```
Nice! I've marked this task as not done yet: 
[T] [ ] TASK_NAME
```

### `list` - list all tasks
Prints full list of tasks

Example of usage:
`list`

Expected outcome:
```
1. [T] [ ] Attend Lecture
2. [D] [ ] Assignment (by: 9th August)
3. [E] [ ] NDP Parade (from:  9th August 1300 to:  9th August 1600)
```

### `find` - search function
Looks for task that contains the input string

Format:
`find TASK_NAME`

Example of usage:
`find NDP`

Expected outcome:
```
Here are the matching tasks in your list:
3. [E] [ ] NDP Parade (from:  9th August 1300 to:  9th August 1600)
```

### `bye` - ends bot
Closes planner

Example of usage:
`bye`

Expected outcome:
```
Bye! Hope to see you again soon! 

───────────────────────────────────────────────────────────────────────────────────────────────────
─██████████████───████████──████████─██████████████─██████████████───██████████████─██████████████─
─██░░░░░░░░░░██───██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██─
─██░░██████░░██───████░░██──██░░████─██░░██████████─██░░██████░░██───██░░██████░░██─██████░░██████─
─██░░██──██░░██─────██░░░░██░░░░██───██░░██─────────██░░██──██░░██───██░░██──██░░██─────██░░██─────
─██░░██████░░████───████░░░░░░████───██░░██████████─██░░██████░░████─██░░██──██░░██─────██░░██─────
─██░░░░░░░░░░░░██─────████░░████─────██░░░░░░░░░░██─██░░░░░░░░░░░░██─██░░██──██░░██─────██░░██─────
─██░░████████░░██───────██░░██───────██░░██████████─██░░████████░░██─██░░██──██░░██─────██░░██─────
─██░░██────██░░██───────██░░██───────██░░██─────────██░░██────██░░██─██░░██──██░░██─────██░░██─────
─██░░████████░░██───────██░░██───────██░░██████████─██░░████████░░██─██░░██████░░██─────██░░██─────
─██░░░░░░░░░░░░██───────██░░██───────██░░░░░░░░░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░██─────██░░██─────
─████████████████───────██████───────██████████████─████████████████─██████████████─────██████─────
───────────────────────────────────────────────────────────────────────────────────────────────────
```