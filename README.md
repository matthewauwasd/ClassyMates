# ClassyMates
## Studying-Based Social Application

### Introduction

Finding people to study with can be a daunting task. 

Public class discussion boards do not offer an environment in which students can simultaneously create connections as
well as discuss relevant coursework. 

On top of that, lecture settings limit the amount of people you can interact with, since you cannot sit and talk to
many people. This is especially the case for introverts like me.

Currently, there are no solutions designed to link people together based on their classes in order
to create a comfortable social setting. This application aims to provide a comfortable experience to students by
creating the ability to link them together through the classes they are taking.

### Application's Abilities

This application allows students to post questions and interact with each other in **classrooms**.

Classrooms have both posts, and something called **subgroups**.

These subgroups, which students can create, are where more intimate social interactions can 
take place. Subgroups are designed to foster a more casual environment, that encourages non-course related discussions.

Classrooms will contain **posts** with the ability for users to **comment** in those posts, 
while subgroups will contain messages.

### Target Audience

This app is designed for students, especially those who want to connect with classmates.

### My Interest in this Project

Currently, official class-discussion boards lack the ability to form any social connections with other students in
those classes. On top of that, forming connections in-person is challenging, especially for introverts like me. I want
to make this application to help students connect with each other while also having an environment where they can
discuss class-related topics. This will be a project that I will work on for the foreseeable future, as I would
like to see this concept be fully fleshed out.

*Aside: I noticed that in the classes in which I have friends I can talk to, I have enjoyed the class more, and also
done better academically. Having friends in your classes really helps to create a better learning environment.*

## User Stories

* As a user, I want to post a question in a classroom.
* As a user, I want to view multiple posts in a classroom.
* As a user, I want to add a comment in a post.
* As a user, I want to view multiple comments in a class post.
* As a user, I want to create a subgroup.
* As a user, I want to post and view messages in the subgroup.
* As a user, I want to view subgroups in a classroom.
* As a user, I want to save all classrooms, posts, comments, subgroups, and messages.
* As a user, I want to be able to load the saved classrooms, posts, comments, subgroups, and messages.

# Instructions for Grader

- You can add Posts to a Classroom by clicking a classroom, and then
  clicking the "Create post" button to the left, and entering the post title and body.
- You can add Comments to a Post by clicking a post, and then
  clicking the "Create comment" button to the left, and entering the comment body.
- You can add Subgroups to a Classroom by clicking a classroom, and then
  clicking the "View subgroups" button to the left, clicking the "Create subgroup" button to the left,
  and entering the subgroup name.
- You can add Messages to a Subgroup by clicking a subgroup after clicking the "View subgroups" button in the
  classroom, clicking the "Create message" button to the left, and entering the message body.
- You can save the state of my application by going to the first screen and clicking the save button in the 
  top menu bar.
- You can reload the state of my application by going to the first screen and clicking the load button in the
  top menu bar.

## Phase 4: Task 2

Example of logging events in ClassyMates.

- User first creates a post with title "PostA", and it is added to classroom "CPSC 210". 
- User then creates a comment with body "CommentA", and it is added to post "PostA".
- User then creates a subgroup with name "SubgroupA", and it is added to classroom "CPSC 210".
- User then creates a message with body "MessageA", and it is added to subgroup "SubgroupA".


- **User then exits the program, and the following log is printed:**


- Mon Nov 27 18:12:59 PST 2023 
  - Created post: PostA
- Mon Nov 27 18:12:59 PST 2023
  - Added post "PostA" to "CPSC 210"
- Mon Nov 27 18:13:03 PST 2023
  - Created comment: CommentA
- Mon Nov 27 18:13:03 PST 2023
  - Added comment "CommentA" to "PostA"
- Mon Nov 27 18:13:10 PST 2023
  - Created subgroup: SubgroupA
- Mon Nov 27 18:13:10 PST 2023
  - Added subgroup "SubgroupA" to "CPSC 210"
- Mon Nov 27 18:13:15 PST 2023
  - Created message: MessageA
- Mon Nov 27 18:13:15 PST 2023
  - Added message "MessageA" to "SubgroupA"
- Main window has closed