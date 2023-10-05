# User Guide

## Features 

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
1. Remember to comply with each format rules for the specific class.

> [!IMPORTANT]
> Crucial information necessary for users to succeed.

1. When meet with format problems, please check your input.If you don't know how to write a standard command, just modify the example input.
#### Classes
##### String
No any restriction.Just remind don't input '/', which will make misunderstanding.
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



### Valid Keywords and Arguments:
#### Todo
- Keywords: todo
- Arguments:
    - Length: 1
    - Contents: [description]
        - description: (String) used for describe the todo.
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
- Keywords: deadline
- Arguments:
    - Length: 2
    - Contents: [description, by]
        - description: (String) used for describe the deadline.
        - by: (DateTime) the end of the deadline.
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
- Keywords: event
- Arguments:
    - Length: 3
    - Contents: [description, from, to]
        - description: (String) used for describe the event.
        - from: (DateTime) the begin of the event.
        - to: (DateTime) the end of the event.
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