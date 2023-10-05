# Dukey Chatbot
# User Guide



## Usage of Features

### `bye` - Exit Program

This command is used to exit the program.

Example of usage:

`bye`

Expected outcome: Bye. Hope to see you again soon!


### `list` - List Tasks

This command is used to list all tasks.

Example of usage:

`list`

Expected outcome:
1.[T][ ] homework 
2.[T][ ] go to the beach
3.[E][ ] cycle (from: 5am to: 8am)


### `mark` - Mark Task as Done

This command is used to mark a task as done.

Example of usage:

`mark (task number)`

Expected outcome: Nice! I've marked this task as done:
[T][X] hi


### `unmark` - Unmark Task

This command is used to unmark a task (mark it as not done).

Example of usage:

`unmark (task number)`

Expected outcome: Nice! I've marked this task as done:
[T][ ] hi



### `deadline` - Add Deadline Task

This command is used to add a deadline task.

Example of usage:

`deadline (description) /by (due date)`

Expected outcome: [D][ ] Submit assignment (by: 9am)


### `event` - Add Event Task

This command is used to add an event task.

Example of usage:

`event (description) /from (start time) /to (end time)`

Expected outcome: [E][ ] Rohit attends cs2113 lecture (from: 4pm to: 6pm)


### `todo` - Add Todo Task

This command is used to add a todo task.

Example of usage:

`todo (description)`

Expected outcome: [T][ ] buy groceries


### `delete` - Delete Task

This command is used to delete a task.

Example of usage:

`delete (task number)`

Expected outcome: 
"Noted. I have removed this task:
[D][ ] bro (by:tmr)
Now you have 3 tasks in the list."

