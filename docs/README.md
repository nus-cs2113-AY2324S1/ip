# Ken: Barbie-Themed Task Manager User Guide

Welcome, fashionista! Ken is your personal dream planner extraordinaire. 
Whether you are ready to conquer the runway or just keep track of your daily tasks, Ken has got you covered.
This guide will walk you through using Ken's fabulous features.

## Features

### Add tasks
Ken allows you to add different types of tasks to your task list!

Some of the characteristics of these are as follows:
- Todo: Description of task
- Deadline: Description of task + Deadline date
- Event: Description of task + Event start & end date

### Edit and update tasks
When you've successfully accomplished a task, you can **mark** it as done, darling! 
Other ways to manage your list of fabulous tasks include **unmarking** a task and **deleting** tasks. Stay fabulous!

### Find tasks
When things start getting CRAZY in Ken's world, it's easy to lose track of your tasks, just like a fashion faux pas! 
That's why, with Ken the fabulous task manager, we're here to help you get back on the glamorous track. 
You can search for tasks with a specific keyword or list out all your tasks, darling!

## Usage
### `todo` - add a todo task

This command adds a todo task to your task list.

Format: `todo DESCRIPTION`

Example of usage: `todo Attend Barbie's party`

Expected outcome:

```
Barbie-approved! You've added this glamorous task:
    [T][ ] Attend Barbie's party
Now your list is sparkling with (x) glamorous tasks, darling!
```

### `deadline` - add a deadline task

This command adds a deadline task to your task list.

Format: `deadline DESCRIPTION /by DEADLINE_DATE`

> [!NOTE]
> - DEADLINE_DATE must be in the format `d/M/yyyy HHmm`.

Example of usage: `deadline buy Barbie's present /by 3/10/2023 1530`

Expected outcome:

```
Barbie-approved! You've added this glamorous task:
    [D][ ] buy Barbie's present (by: Oct 3 2023 3:30 PM)
Now your list is sparkling with (x) glamorous tasks, darling!
```

### `event` - add a event task

This command adds an event task to your task list, with the start and end date indicated in the format
d/M/yyyy HHmm.

Format: `event DESCRIPTION /from FROM_DATE /to TO_DATE`

> [!NOTE]
> - `FROM_DATE` and `TO_DATE` must be in the format `d/M/yyyy HHmm`.
> - `FROM_DATE` must be before `TO_DATE`.

Example of usage: `event Barbie's Glamorous Party /from 12/10/2023 1800 /to 12/10/2023 2200`

Expected outcome:

```
Barbie-approved! You've added this glamorous task:
    [E][ ] Barbie's Glamorous Party (from: Oct 12 2023 6:00 PM to: Oct 12 2023 10:00 PM)
Now your list is sparkling with (x) glamorous tasks, darling!
```
### `mark` - mark task as done

This command marks a task in your task as done, using the item number
of the task in that list.

Example of usage: `mark 2`

Expected outcome:

```
Barbie-tastic! You've completed this task with glamour!
	[D][X] buy Barbie's present (by: Oct 3 2023 3:30 PM)
```
### `unmark` - unmark task as done

This command unmarks a task as done, using that item number of the task
in that list

Example of usage: `unmark 2`

Expected outcome:

```
Back to the runway, darling! This task needs more Barbie magic!
	[D][ ] buy Barbie's present (by: Oct 3 2023 3:30 PM)
```
### `delete` - delete task

This command deletes a task from the list, using that item number of the task
in that list

Example of usage: `delete 2`

Expected outcome:

```
Noted, darling! I've given that task the boot, just like a fashion faux pas!
	[D][ ] buy Barbie's present (by: Oct 3 2023 3:30 PM)
Now your list is sparkling with (x) glamorous tasks, darling!
```
### `find` - find tasks

This command finds a task using a specified keyword.

Example of usage: `find Barbie`

Expected outcome:

```
These are the tasks that match the keyword, hun!
	1.	[T][ ] Attend Barbie's party
	2.	[E][ ] Barbie's Glamorous Party (from: Oct 12 2023 6:00 PM to: Oct 12 2023 10:00 PM)
```

### `list` - list tasks

This command lists all tasks in the task list.

Example of usage: `list`

Expected outcome:

```
Behold, your list of enchanting tasks!
	1.	[T][ ] Attend Barbie's party
	2.	[E][ ] Barbie's Glamorous Party (from: Oct 12 2023 6:00 PM to: Oct 12 2023 10:00 PM)
```

### `bye` - exit app

This command closes the application and saves the current list into a file.

Example of usage: `bye`

Expected outcome:

```
Until we meet again, my fellow dream chaser! Keep
	  ___________________  _____ __________ ____  __.____    .___ _______    ________
	/   _____/\______   \/  _  \\______   \    |/ _|    |   |   |\      \  /  _____/
	 \_____  \  |     ___/  /_\  \|       _/      < |    |   |   |/   |   \/   \  ___
	 /        \ |    |  /    |    \    |   \    |  \|    |___|   /    |    \    \_\  \
	/_______  / |____|  \____|__  /____|_  /____|__ \_______ \___\____|__  /\______  /
	        \/                  \/       \/        \/       \/           \/        \/
```
