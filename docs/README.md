# Dukey Chatbot
# User Guide



## Usage of Features

### `bye` - Exit Program

This command is used to exit the program.

Format:

`bye`

Expected outcome: Bye. Hope to see you again soon!


### `list` - List Tasks

This command is used to list all tasks.

Format:

`list`


Expected outcome:
1.[T][ ] homework 
2.[T][ ] go to the beach
3.[E][ ] cycle (from: 5am to: 8am)


### `mark` - Mark Task as Done

This command is used to mark a task as done.

Format:

`mark (task number)`


Expected outcome: Nice! I've marked this task as done:
[T][`X`] hi


### `unmark` - Unmark Task

This command is used to unmark a task (mark it as not done).

Format:

`unmark (task number)`

Expected outcome: Nice! I've marked this task as done:
[T][ ] hi



### `deadline` - Add Deadline Task

This command is used to add a deadline task.

Format:

`deadline (description) /by (due date)`

Example of input :
`Submit assignment /by 9am`

Expected outcome: [D][ ] Submit assignment (by: 9am)


### `event` - Add Event Task

This command is used to add an event task.

Format:

`event (description) /from (start time) /to (end time)`

Example of input :
`Rohit attends cs2113 lecture /from 4pm /to 6pm`

Expected outcome: [E][ ] Rohit attends cs2113 lecture (from: 4pm to: 6pm)


### `todo` - Add Todo Task

This command is used to add a todo task.

Format:

`todo (description)`

Example of input :
`todo buy groceries`

Expected outcome: [T][ ] buy groceries


### `delete` - Delete Task

This command is used to delete a task.

Format:

`delete (task number)`

Expected outcome: 
"Noted. I have removed this task:
[D][ ] bro (by:tmr)
Now you have 3 tasks in the list."

### 'find' - Find a task/tasks

This command is used to search for tasks that contain a particular
keyword in the description.

Format:

`find (task number)`

Example of input:
`find hohoho`

Excpected Outcome:

"Here are the matching tasks in your list:

[T][ ] hohoho
[T][ ] hohoho
[E][ ] hohoho (from: 9am to: 5pm)"


### Additional Notes For Users 

- Do not input whitespaces before your command 
- e.g. (  `todo homework` is invalid), (`todo homework` is valid)