# User Guide for Itay

## Features 

### Add tasks

You can add three types of task for Itay to remember:
- <b>Todo</b>, which has a description
- <b>Deadline</b>, which has a description and a finish by date and time
- <b>Event</b>, which has a description, a start and an end date and time

### View the list of tasks

You can ask Itay to show you the entire list of tasks.

### Mark and unmark tasks as done

You can tell Itay to mark or unmark some task as done.

### Find tasks

You can get Itay to show you all the tasks containing a certain keyword.

### Delete tasks

You can ask Itay to forget any of the tasks by deleting it.

## Usage

### `todo` - Add a todo

Adds a todo to the list of tasks.

Format:
```
todo DESC

where

DESC: description of the todo
```

Example of usage: 

```
todo finish documentation
```

Expected outcome:

```
Got it. I've added this task:
[T][ ] finish documentation
Now you have 8 tasks in the list.
```

### `deadline` - Add a deadline

Add a deadline to the list of tasks.

Format:
```
deadline DESC /by FIN_BY_DT

where

DESC: description of the deadline
FIN_BY_DATE: finish by date and time of the deadline
```

Example of usage:

```
deadline submit ip /by 25 Sep 2023 - 23:59`
```

Expected outcome:

```
Got it. I've added this task:
[D][ ] submit ip (by: 25 Sep 2023 - 23:59)
Now you have 9 tasks in the list.
```

### `event` - Add an event

Add an event to the list of tasks.

Format:
```
event DESC /from FROM_DT /to TO_DT

where

DESC: description of the event
FROM_DT: start date and time of the event
TO_DT: end date and time of the event
```

Example of usage:

```
event trading seminar /from 21 Sep 2023 - 10:00 /to 21 Sep 2023 - 12:00
```

Expected outcome:

```
Got it. I've added this task:
[E][ ] trading seminar (from: 21 Sep 2023 - 10:00 to: 21 Sep 2023 - 12:00)
Now you have 10 tasks in the list.
```

### `list` - Display list of tasks

Displays the list of all tasks.

Example of usage:

```
list
```

Expected outcome:

```
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: 06 Jun 2023 - 16:00)
3.[E][ ] project meeting (from: 06 Aug 2023 - 14:00 to: 06 Aug 2023 - 16:00)
4.[T][X] join sports club
5.[T][ ] borrow book
6.[D][ ] return another book (by: 02 Sep 2023 - 16:00)
7.[E][ ] another project meeting (from: 30 Aug 2023 - 21:00 to: 30 Aug 2023 - 22:00)
```

### `mark` - Mark task as done

Mark a task as done by referencing its index in the list.

Format:
```
mark INDEX

where

INDEX: index of task to be marked as done
```

Example of usage:

```
mark 8
```

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] finish documentation
```

### `unmark` - Unmark task as done

Unmark a task as done by referencing its index in the list.

Format:
```
unmark INDEX

where

INDEX: index of task to be unmarked as done
```

Example of usage:

```
unmark 8
```

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] finish documentation
```

### `find` - Find tasks with keyword

Find all the tasks containing a specific keyword.

Format:
```
find KEYWORD

where

KEYWORD: keyword to find the tasks
```

Example of usage:

```
find book
```

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: 06 Jun 2023 - 16:00)
3.[T][ ] borrow book
4.[D][ ] return another book (by: 02 Sep 2023 - 16:00)
```

### `delete` - Delete task

Delete a task by referencing its index in the list.

Format:
```
delete INDEX

where

INDEX: index of task to be marked as done
```

Example of usage:

```
delete 8
```

Expected outcome:

```
Got it. I've removed this task:
[T][ ] finish documentation
Now you have 9 tasks in the list.
```

### `bye` - Exit the Itay bot

Exit the Itay bot and save all the current tasks.

Example of usage:

```
bye
```

Expected outcome:

```
Bye. Hope to see you again soon!
```
