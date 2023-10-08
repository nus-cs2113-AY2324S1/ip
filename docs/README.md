# User Guide
The Chatbot name is very intuitive - Chatbot.

## Features 
1. list
2. mark
3. unmark
4. todo
5. deadline
6. event
7. delete
8. find

### Feature-list
List all tasks
### Feature-mark
Mark a task as done
### Feature-unmark
Unmark a task as done
### Feature-todo
Add a todo
### Feature-deadline
Add a deadline
### Feature-event
Add an event
### Feature-delete
Delete a task
### Feature-find
Find a task





## Usage

### `list` - list all tasks
List all the tasks recorded

Command: `list`

Argument: Nil

Example: `list`

Example of outcome:

    Tasks are listed
```
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read book
     2.[D][ ] return book (by: June 6th)
     3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     4.[T][X] join sports club
     5.[T][ ] borrow book
    ____________________________________________________________
```

### `mark` - mark a task
Command: `mark`

Argument: <int> ID of the task

Example: `mark 1`

Example of outcome:

    Task is marked as done

```
____________________________________________________________
 Nice! I've marked this task as done:
   [X] borrow new book
____________________________________________________________
```

### `unmark` - unmark a task
Mark a task as done

Command: `unmark`

Argument: <int> ID of the task

Example: `unmark 1`

Example of outcome:

    Task is unmarked

```
____________________________________________________________
 OK, I've marked this task as not done yet:
   [ ] borrow new book
____________________________________________________________
```

### `todo` - add a todo

Command: `todo`

Argument: <String> description of the task

Example: `todo borrow book`

Example of outcome:

    Todo is added

```
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] borrow book
     Now you have 5 tasks in the list.
    ____________________________________________________________
```

### `deadline` - add a deadline

Command: `deadline`

Argument: <String> description of the deadline

Example: `deadline return book /by Sunday`

Example of outcome:

    Deadline is added

```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: Sunday)
     Now you have 6 tasks in the list.
    ____________________________________________________________
```

### `event` - add an event

Command: `event`

Argument: <String> description of the event

Example: `event project meeting /from Mon 2pm /to 4pm`

Example of outcome:

    Event is added

```
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 7 tasks in the list.
    ____________________________________________________________
```
### `delete` - delete a task

Command: `delete`

Argument: <int> ID of the task to delete

Example: `delete 1`

Example of outcome:

    Task is deleted

```
    ____________________________________________________________
     Noted. I've removed this task:
       [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

### `find` - find a task
Command: `find`

Argument: `String` What to search for

Example: `find book`

Example of outcome:

    book is found

```
____________________________________________________________
 Here are the matching tasks in your list:
   [T][ ] borrow book
   [T][ ] borrow new book
____________________________________________________________
```