# User Guide for Ken

```
K   K  EEEEE   N   N
K  K   E       NN  N
KKK    EEEE    N N N
K  K   E       N  NN
K   K  EEEEE   N   N
```
## Features 
Ken is a CLI chatbot that helps you manage your tasks efficiently. Easily add and delete tasks, deadline and events, save them, and use Ken to manage them easily. 

- Add tasks of different types: Todo, Deadline, Event
- List all your tasks
- Mark tasks as done
- Delete tasks
- Find tasks

## Usage

### `todo, deadline, event` - Add a Task 

To add a task, use one of the following commands:

- `todo [task description]`: Adds a todo task.
- `deadline [task description] /by [deadline]`: Adds a deadline task, with when it is due by.
- `event [task description] /from [start date] /to [end date]`: Adds an event task, with the duration of the event.

#### Example of usage :

`todo activity`

`deadline project /by Friday`

`event lunch /from 2pm /to 4pm`


#### Expected outcome: 

```
Got it. I've added this task:
   [T][ ] activity
Now you have 1 tasks in the list.
```
```
Got it. I've added this task:
[D][ ] project  by: Friday
Now you have 4 tasks in the list.
```
```
Got it. I've added this task:
[E][ ] lunch  from: 2pm to: 4pm
Now you have 5 tasks in the list.
```


### `list` - Viewing all tasks

To view a list of all your tasks and their corresponding information, use this command
 
`list`

#### Expected outcome:


```
Here are the tasks in your list:
 1.[T][ ] studying for test
 2.[D][ ] project by: Friday
 3.[E][ ] lunch from: 2pm to: 4pm
```


### `delete` - Removing tasks

To delete a task, use the delete command followed by the task number:
 
`delete 2`

#### Expected outcome:


```
Got it. I've removed this task:
   [D][ ] project by: Friday
Now you have 2 tasks in the list.
```
### `find` - Searching for tasks by keywords

To find a particular task and their corresponding information from the list by using any part of description, use this command
 
`find lunch`

#### Expected outcome:


```
Here are the matching tasks in your list:
2. [E][ ] lunch ( from: 2pm to: 4pm )
```


### `mark` - Searching for tasks by keywords

To mark a task as done, use the mark command followed by the task number:

#### Example of usage :

`mark 2`

#### Expected outcome:

```
Nice! I've marked this task as done:
 [X] lunch from: 2pm to: 4pm
```


