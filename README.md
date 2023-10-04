# Hi there! I am KenergeticBot 

I am a personal Task managing chatbot, able to store tasks such as To dos, Deadlines and Events.

## Here are some of the things I can do!

1) Create 3 types of Tasks:
<details>
1. Todo: Tasks which are not time sensitive.
2. Deadline: Tasks which are time sensitive, able to indicate deadline using /by.
3. Event: Tasks which are happening at a specific time frame, using /from and /to.
</details>

2) Keep track of Task completion.
<details>
 1. [ ] Indicates Tasks that are not completed
 2. [X] Indicates Tasks that are completed
</details>
      
3) Store and retreive your tasks into a dedicated save file.

4) Search for keyword using the Find function.

<details>
  ### find [keyword]
```
<summary>Useage Example</summary>
find book
____________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
____________________________________________________________
```
</details>

5) Remove stored Tasks using the Delete function.

<details>
<summary>Useage Example</summary>
 ### delete [task index]
```
delete 2
____________________________________________________________
Noted. I've removed this task:
[D][ ] return book (by: Sunday)
Now you have 1 tasks in the list.
____________________________________________________________
```
```ruby
   puts "Hello World"
```
</details>

<details>

<summary>Tips for collapsed sections</summary>

### You can add a header

You can add text within a collapsed section. 

You can add an image or a code block, too.

```ruby
   puts "Hello World"
```

</details>

## User Guide
To find out more details on how to interact with KenergeticBot, please refer to *(http://Cazh1.github.io/ip/)*.

