# ACM_netBeans
# Loyds Access Control
____________________________________________

## Intro: 

This program is a basic access control mechanism that implements the given table in Java code. 

## Type of Access Control: 

This is an implementation of the role based access control

## Note: 

This program has only been tested in a Mac terminal and in Netbeans 9.0

## The Code:

When you run the program it will automatically create 3 users, you can log in as any one of them. 

1.
username: admin
password: password
lvl: 3
2.
username: sec
password: password
lvl: 2
3.
username: reg
password: password
lvl: 1

Only a level 2 (security) or a level 3 (administrator) can print the full access control matrix out, a regular level 1 user can only view their own objects and their rights. 

## Using the Code:

1) To run the code you have 3 options: a & c are the best ways!

a) run the .jar file that my NetBeans created, open a terminal window and run the command —>

java -jar ~/loydStewart_assignment2/accessControl.jar

b) import the project into NetBeans and run from there (the matrix won’t print right in netBeans)

c) do step b and use NetBeans to clean and build the project and then run from Mac terminal

2) Make sure your terminal is zoomed out enough to make matrix print correctly.

3) If you make a new subject with a name longer than 6 characters it will mess up the spacing on the matrix print.

4) Created users can either be a level 3 - administrator, a level 2 - security officer, or a lvl 1 - regular user.

5) Note that when printing the matrix, X means they have access to an object or are the owner or a subject, and a 1 means they have that specific right. 
￼
6) For the specific use case of this application, the create and destroy object work with an object (class) that is simulating a database. This object has a name and an owner attribute. When you create one It will ask you to enter a name as well as decide whether to grant all users access now or keep it private and grant access individually. When you create a “database” it will automatically make the extension “.db”. Specific users can be given access to these objects and ownership can be transferred.




