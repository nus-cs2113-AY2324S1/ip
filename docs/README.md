# Chatbot: RIAS
serving you, the one and only master! <3
```
 _____  _____           _____     ______
|  __ \|_   _|   /\    / ____|   / ___  \ 
| |__) | | |    /  \  | (___    / /  __) |
|  _  /  | |   / /\ \  \___ \  < <  |__ < 
| | \ \ _| |_ / ____ \ ____) |  \ \ ___) |
|_|  \_\_____/_/    \_\_____/    \_____ / 
```
## Summary
Chatbot: RIAS is a CLI program to keep track of your existing
task while sending you ascii arts or emotes!:heart:
## Content
* [Quick Start](#Quick-start-)
* [Features](#Features-)
* [Usage](#Usage-)
  * [Get help: `help`](#Get-help-help-)
  * [Add a todo: `todo`](#todo-Add-a-todo-)
  * [Add a event: `event`](#event-Add-a-event-)
  * [Add a deadline: `deadline`](#deadline-Add-a-deadline-)
  * [List tasks: `list`](#list-List-all-task-)
  * [Mark tasks: `mark`](#mark-Mark-task-)
  * [Unmark tasks: `unmark`](#unmark-Unmark-task-)
  * [Delete a listing: `delete`](#delete-Delete-a-task-)
  * [Find by search parameter: `find`](#find-Find-string-)
  * [Exiting the program : `bye`](#bye-Exiting-the-program-)
  * [Turn on/off ascii: `ascii`](#ascii-Turn-on/off-ascii-)

## Quick Start
- Download the latest `chatbot.jar` from the releases
- Run the following command: `java -jar chatbot.jar` in the same directory as the `chatbot.jar` file
## Features [:arrow_up_small:](#Content)
> Note:
> - Items in triangle brackets `<>` are placeholders, replace the whole 
string, including `<>` with your item
> - `<name>` refers to the name/description of the task
> - `<index>` refers to the index number of the task in the task list, starting from 1
> - `<start>` and `<end>` can be string(s) or date(s)

### Core Features
| Commands                               | Description                                                                       |
|----------------------------------------|-----------------------------------------------------------------------------------|
| `todo <name>`                          | Add a todo task to the task list                                                  |
| `event <name> /from <start> /to <end>` | Add an event task to the task list with the given `<start>` time and `<end>` time |
| `deadline <name> /by <end>`            | Add a deadline task to the task list with the given `<end>` time                  |
| `list`                                 | Print out the whole task list                                                     |
| `mark <index>`                         | Mark the task at `<index>` as complete                                            |
| `unmark <index>`                       | Mark the task at `<index>` as incomplete                                          |
| `find <string>`                        | Find a `<string>` in the task list and print out all related task(s)              |
| `delete <index>`                       | Delete the task at `<index>`                                                      |
| `bye`                                  | Exit the program                                                                  |
### Side Features
| Commands       | Description                    |
|----------------|--------------------------------|
| `ascii <bool>` | Turn on/off ascii art printing |
## Usage [:arrow_up_small:](#Content)

### `todo` - Add a todo 

Add a todo task to the list: `todo <name>`

| Example          | Outcome                     |
|------------------|-----------------------------|
| `todo buy bread` | `added: [T][ ] buy bread`   |

### `event` - Add a event

Add a event task to the list: `event <name> /from <start> /to <end>`

| Example                                     | Outcome                                                   |
|---------------------------------------------|-----------------------------------------------------------|
| `event watch movie /from 1pm /to 3pm`       | `added: [E][ ] watch movie (from: 1pm to: 3pm)`           |
| `event exam /from 1/11/2023 /to 30/11/2023` | `added: [E][ ] exam  (from: Nov 01 2023 to: Nov 30 2023)` |
### `deadline` - Add a deadline

Add a deadline task to the list: `deadline <name> /by <end>`

| Example                              | Outcome                                        |
|--------------------------------------|------------------------------------------------|
| `deadline math homework /by 2359`    | `added: [D][ ] math homework (by: 2359)`       |
| `deadline return book /by 1/11/2023` | `added: [D][ ] return book  (by: Nov 01 2023)` |

### `list` - List all tasks

List all the task in the task list

| Example | Outcome                                                                                              |
|---------|------------------------------------------------------------------------------------------------------|
| `list`  | `1. [E][ ] exam  (from: Nov 01 2023 to: Nov 30 2023)`<br/>`2. [D][ ] return book  (by: Nov 01 2023)` | 

### `mark` - Mark tasks

Mark task of given index as completed

| Example  | Outcome                                            |
|----------|----------------------------------------------------|
| `mark 1` | `[E][X] exam  (from: Nov 01 2023 to: Nov 30 2023)` | 

### `unmark` - Unmark tasks

Mark task of given index as incomplete

| Example    | Outcome                                               |
|------------|-------------------------------------------------------|
| `unmark 1` | `1. [E][ ] exam  (from: Nov 01 2023 to: Nov 30 2023)` | 

### `find` - Find string

Find a string in the task list

| Example     | Outcome                                               |
|-------------|-------------------------------------------------------|
| `find exam` | `1. [E][X] exam  (from: Nov 01 2023 to: Nov 30 2023)` | 
| `find D`    | `1. [D][ ] return book  (by: Nov 01 2023)`            | 

### `bye` - Exiting the program

Exit the application

| Example | Outcome      |
|---------|--------------|
| `bye`   | `Bye masta!` | 

### `ascii` - Turn on/off ascii art

Enable or disable ascii art for the program

| Example       | Outcome                   |
|---------------|---------------------------|
| `ascii true`  | `ascii art is now true!`  |
| `ascii false` | `ascii art is now false!` | 
