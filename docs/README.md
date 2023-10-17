# AMY User Guide

AMY is a text based Java app for managing your daily
tasks. Given below is the features list and your user guide.

## Features 

Here is your features list:

+ **todo**, **event**, 
+ **deadline**, **mark**, 
+ **unmark**, **delete**, 
+ **find**, **list**, **bye**

Users can use these features to handle their tasks

### ToDo 

Adds a new task to do in the task list.

#### Usage

**Format**: todo [task]

**Examples**

+ `todo finish my homework`

**Expected Output**:

```
+ Got it. I've added this task: 
+ [T][ ] [finish my homework]
```

### Deadline 

Adds a new task with deadline to do in the task list.

#### Usage

**Format**: deadline [task] /by [deadline]

**Example**

+ `deadline finish my homework /by Sunday`

**Expected Output**:

```
+ Got it. I've added this task: <br>
+ [D][ ] [finish my homework] (by: [Sunday])
```

### Event 

Adds a new Event to the task list.

#### Usage

**Format**: event [task] /from [start] /to [end]

**Example**

+`finish homework /from 12:00 /to 18:00`

**Expected Output**:

```
+ Got it. I've added this task:
+ [E][ ] [finish homework] (from: [12:00] to: [18:00])
```

### List 

Lists all tasks in the task list.

#### Usage

**Format**: list

**Expected Output**:

```
1. [D][ ] [task] (by: [Deadline])
2. [T][ ] [task]
```

### Mark Task

Mark the task as done in the task list.

#### Usage

**Format**: mark [TASK INDEX]

**Example**

+ `mark 1`

**Expected Output**:

```
+ Nice! I've marked this task as done: 
+ [T][X] [finish homework] 
```
### Unmark 

Mark the task as not done in the task list.

#### Usage

**Format**: unmark [index]

**Example**

+ ```unmark 1```

**Expected Output**:

```
+ Nice! I've marked this task as not done.
+ [T][ ] [task] 
```

### Delete Task

Delete a task from the task list.

#### Usage

**Format**: delete [index]

**Example**

+ ```delete 2```

**Expected Output**:

```
Noted. I've removed this task:
[task] 
Now you have [#] tasks in your list.
```

### Find Task

Find tasks from the task list using either description or timing.

#### Usage

**Format**: find [keyword] 

**Example**

+ ```find /description exam```
+ ```find /time 21-04-2021 14:30```

**Expected Output**:

```
Here are the matching tasks in your list:
+ [index]. [task]
+ [index]. [task]
```

### bye

Exit from the application.

#### Usage

**Format**: bye

**Example**

+ ```bye```

**Expected Output**:

```
+ Bye. Hope to see you again soon!
```
