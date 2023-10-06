# User Guide

## Contents
> [Feature](#Feature)  
[Usage](#Usage)

## Feature

### Local Data Saving

All the data that produced by the program is going to save in the 'AppData\LocalLow\EggyByte\iP\' under the current user folder.
In this way, previous data can be used and no need for duplicated input.

### Error Handling

All errors are handled with a customized type 'TipsException', which enables user to get both the description of the error and the possible solution.It's easy to solve errors by following the tips.

### Cross Platform

User of any platform that supports jar file can use it.

### High Stability

We have tested it for many extreme situation and fix numerous potential problems.Users can enjoy themselves using this jar.

## Usage
### `Keyword` - Describe action
### `Arguments` - Optional words that customize 
### Input: 
Mode:
```
keyword (arguments)
```
Example:
```
todo Read Books
```
### Outcome:
Mode:
```
    ____________________________________________________________
    [time][log level]
     content
    ____________________________________________________________
```
Example:
```
    ____________________________________________________________
    [23:44:19][INFO]
     Nice! I've marked this task as done:
       [E][X] return books (from: Mar 12, 2019 13:34, to: Feb 13, 2024 22:12)
    ____________________________________________________________
```
### Format
#### Basic Rules
1. Use '/' to split each arguments.
2. Remember to comply with each format rules for the specific class.

> [!IMPORTANT]  
> 3. When meet with format problems, please check your input.If you don't know how to write a standard command, just modify the example input.
#### Classes
> [String](#String)  
[DateTime](#DateTime)  
[Date](#Date)

##### String
No any restriction.  
Just remind don't input '/', which will make misunderstanding.
##### DateTime
Only following format is supported:
```
yyyy-M-d HHmm
yyyy-M-d H:m
M-d-yyyy HHmm
M-d-yyyy H:m
MMM d, yyyy HH:mm
```
1. yyyy stands for year, which must be 4 characters.  
```2020```
2. M stands for month, which can be 1 or 2 characters.  
```1```
```11```
3. MMM stands for month in English, which must be 3 characters.  
```Oct```
```Apr```
4. d stands for day, which can be 1 or 2 characters.  
```5```
```28```
5. H stands for hour, which can be 1 or 2 characters.  
```3```
```18```
6. HH stands for hour, which must be 2 characters.  
```03```
```18```
7. m stands for minute, which can be 1 or 2 characters.  
```6```
```55```
8. mm stands for minute, which must be 2 characters.  
```06```
```55```

##### Date
Only following format is supported:
```
yyyy-M-d
M-d-yyyy
MMM d, yyyy
```
1. yyyy stands for year, which must be 4 characters.  
```2020```
2. M stands for month, which can be 1 or 2 characters.  
```1```
```11```
3. MMM stands for month in English, which must be 3 characters.  
```Oct```
```Apr```
4. d stands for day, which can be 1 or 2 characters.  
```5```
```28```

### Valid Keywords and Arguments:

> [Todo](#Todo)  
[Deadline](#Deadline) 
[Event](#Event)  
[List](#List)  
[Mark](#Mark)  
[Unmark](#Unmark)  
[Delete](#Delete)  
[Activated](#Activated)  
[Find](#Find)  
[Bye](#Bye)

#### Todo
> [!NOTE]
> Add a task into the current list with type of `Todo`.
- Keywords: `todo`
- Arguments:
    - Length: 1
    - Contents: [`description`]
        - `description`: (String) used for describe the todo.
- Example Input:
```
todo Read Books
```
- Example Outcome:
```
    ____________________________________________________________
    [01:03:03][INFO]
     Got it. I've added this task:
       [T][ ] Read Books
     Now you have 3 tasks in the list.
    ____________________________________________________________
```

#### Deadline
> [!NOTE]
> Add a task into the current list with type of `Deadline`.
- Keywords: `deadline`
- Arguments:
    - Length: 2
    - Contents: [`description`, `by`]
        - `description`: (String) used for describe the deadline.
        - `by`: (DateTime) the end of the deadline.
- Example Input:
```
deadline Read Books /by 1928-10-1 13:2
```
- Example Outcome:
```
    ____________________________________________________________
    [01:06:02][INFO]
     Got it. I've added this task:
       [D][ ] Read Books (by: Oct 1, 1928 13:02)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

#### Event
> [!NOTE]
> Add a task into the current list with type of `Event`.
- Keywords: `event`
- Arguments:
    - Length: 3
    - Contents: [`description`, `from`, `to`]
        - `description`: (String) used for describe the event.
        - `from`: (DateTime) the begin of the event.
        - `to`: (DateTime) the end of the event.
- Example Input:
```
event Read Bookss /from 1925-10-1 13:2 /to 1928-10-1 13:2
```
- Example Outcome:
```
    ____________________________________________________________
    [01:06:02][INFO]
     Got it. I've added this task:
       [D][ ] Read Books (by: Oct 1, 1928 13:02)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

#### List
> [!NOTE]
> Show all tasks in the current list.
- Keywords: `list`
- Arguments:
    - Length: 0
    - Contents: []
- Example Input:
```
list
```
- Example Outcome:
```
    ____________________________________________________________
    [13:41:45][INFO]
     Here are the tasks in your list:
      1.[E][ ] return books (from: Mar 12, 2019 13:34, to: Feb 13, 2024 22:12)
      2.[T][X] yes!
      3.[T][ ] Read Books
      4.[D][ ] Read Books (by: Oct 1, 1928 13:02)
    ____________________________________________________________
```

#### Mark
> [!NOTE]
> Mark an existing task in the current list as done.
- Keywords: `mark`
- Arguments:
    - Length: 1
    - Contents: [`index`]
        - `index`: (Integer) used for locate the task in the list.
- Example Input:
```
mark 1
```
- Example Outcome:
```
    ____________________________________________________________
    [13:43:08][INFO]
     Nice! I've marked this task as done:
       [E][X] return books (from: Mar 12, 2019 13:34, to: Feb 13, 2024 22:12)
    ____________________________________________________________
```

#### Unmark
> [!NOTE]
> Mark an existing task in the current list as undone.
- Keywords: `unmark`
- Arguments:
    - Length: 1
    - Contents: [`index`]
        - `index`: (Integer) used for locate the task in the list.
- Example Input:
```
mark 1
```
- Example Outcome:
```
    ____________________________________________________________
    [13:43:33][INFO]
     OK, I've marked this task as not done yet:
       [T][ ] yes!
    ____________________________________________________________
```

#### Delete
> [!NOTE]
> Delete an existing task in the current list.
- Keywords: `delete`
- Arguments:
    - Length: 1
    - Contents: [`index`]
        - `index`: (Integer) used for locate the task in the list.
- Example Input:
```
delete 3
```
- Example Outcome:
```
    ____________________________________________________________
    [13:44:17][INFO]
     Noted. I've removed this task:
       [T][ ] Read Books
     Now you have 3 tasks in the list.
    ____________________________________________________________
```

#### Activated
> [!NOTE]
> Search all tasks in the current list with a filter of date.  
> Those tasks whose type are `Deadline` or `Event` that are still available on the input date will be shown.
- Keywords: `activated`
- Arguments:
    - Length: 1
    - Contents: [`date`]
        - `date`: (Date) the date that you want to check.
- Example Input:
```
activated 2020-1-1
```
- Example Outcome:
```
    ____________________________________________________________
    [13:55:14][INFO]
     Here are the tasks that are still activated on Jan 1, 2020:
      1.[E][X] return books (from: Mar 12, 2019 13:34, to: Feb 13, 2024 22:12)
    ____________________________________________________________
```

#### Find
> [!NOTE]
> Find all tasks in the current list with a filter of string.  
> Those tasks whose description contains the input string will be shown.
- Keywords: `find`
- Arguments:
    - Length: 1
    - Contents: [`partial description`]
        - `partial description`: (String) used for targeting tasks.
- Example Input:
```
find book
```
- Example Outcome:
```
    ____________________________________________________________
    [13:57:43][INFO]
     Here are the tasks that have partial description with 'book':
      1.[E][X] return books (from: Mar 12, 2019 13:34, to: Feb 13, 2024 22:12)
    ____________________________________________________________
```


#### Bye
> [!NOTE]
> Terminate the program and automatically save the date locally.
- Keywords: `bye`
- Arguments:
    - Length: 0
    - Contents: []
- Example Input:
```
bye
```
- Example Outcome:
```
    ____________________________________________________________
    [13:58:52][INFO]
      Bye. Hope to see you again soon!
    ____________________________________________________________

    [13:58:52][IMPORTANT]
     Your data has been saved at the path:
       C:\Users\fDu.0ppa1\AppData\LocalLow\EggyByte\iP\Tasks.json
```