# NOXIN

## User Guide

NOXIN is a `CLI` chat-bot that can help users record their tasks into a list. There are different types of Tasks that can 
be inputted:
- **ToDo**: Represents a task without a specific deadline.
- **Deadline**: Represents a task with a deadline.
- **Event**: Represents an event with a specific time range.

Users are also able to mark their tasks as complete or incomplete.

The format of using the features are listed within the feature descriptions, where those keywords encapsulated by `" "`
are user provided arguments.

## Features

### Adding Tasks

You can add different types of tasks to your list:

Format to add different types of tasks:
- **ToDo**: `todo` "TaskName"
```
todo CS2113 Homework
___________________________________

Got it. I've added this task:
[T][ ] CS2113 Homework
Now you have 1 tasks in the list.
___________________________________
```

- **Deadline**: `deadline` "TaskName" /from "DueDate"
```
deadline read book /by Friday 2359
___________________________________

Got it. I've added this task:
[D][ ] read book (by: Friday 2359)
Now you have 2 tasks in the list.
___________________________________
```

- **Event**: `event` "TaskName" /from "StartTime" /to "EndTime"
```
event CS2113 Lecture /from Friday 1200 /to Friday 1600
___________________________________

Got it. I've added this task:
[E][ ] CS2113 Lecture(from: Friday 1200 to: Friday 1600)
Now you have 3 tasks in the list.
___________________________________
```


### Listing Tasks

You can list all your tasks to see an overview of what needs to be done.

Just input `list` into the command line to view the task list.

```
list
___________________________________

Here are the tasks in your list:

1.[T][ ] CS2113 Homework

2.[D][ ] read book (by: Friday 2359)

3.[E][ ] CS2113 Lecture(from: Friday 1200 to: Friday 1600)

___________________________________
```

### Marking Tasks as Done

You can mark tasks as done to keep track of your progress.

Just input `mark` "IndexNumber of task" to mark the task that you wish to be marked as complete.

```
mark 1
___________________________________

Nice! I've marked this task as done:

[T][X] CS2113 Homework

___________________________________
```

### Unmarking Tasks as Undone
You can mark tasks as done to keep track of your progress.

Just input `unmark` "IndexNumber of task" to unmark the task that you wish to be marked as incomplete.
```
unmark 1
___________________________________

OK, I've marked this task as not done yet:

[T][ ] CS2113 Homework

___________________________________
```

### Deleting Tasks

You can delete tasks that are no longer needed.

Just input `delete` "IndexNumber of task" to delete the task you wish to be deleted.
```
delete 2
___________________________________

Noted. I've removed this task:

___________________________________

[D][ ] read book (by: Friday 2359)

___________________________________
```

### Finding Tasks

You can search for tasks containing specific keywords in their descriptions.

Just input `find` "Keyword" to find tasks that contains the keyword you are searching for.

```
find Homework
___________________________________

Here are the matching tasks in your list:

 1.[T][ ] CS2113 Homework
___________________________________
```

### Exiting the Chat-bot

You can exit the chat-bot after finishing the actions that you desire.

Just input `bye` to end the chat-bot.

```
bye
Bye. Hope to see you again soon!

___________________________________


Process finished with exit code 0
```


