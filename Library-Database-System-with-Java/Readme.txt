
Test the Database
---------------------------------------------------------------

1. Enter the CSE VPN using Putty

https://www.youtube.com/watch?v=IYPB_Z3ZxeA&feature=youtu.be
//Using CSE Linux/Windows User Name and Password

2. Enter the Linux Server //ssh linux1

3. Create a Folder CSCI3170 //mkdir csci3170 cd ./csci3170

4. Get the JDBC Library //wget http://course.cse.cuhk.edu.hk/~csci3170/php/assignment/jdbc.jar

5. Write the Program //nano helloworld.java //copy the program to here from other places you wrote it.

6. Compile the Program //javac ¡Vcp .:jdbc.jar helloworld.java //Compile in Linux VM

7. Run the Program //java -cp .:jdbc.jar helloworld

8. Enter the Database //mysql -u CSCI3170S46 -h appsrvdb.cse.cuhk.edu.hk -p //USE CSCI3170S46 //PW:CSCI3170FKFKFK

9. Import SQL //source create_table.sql 

10. Display Database Schema //SHOW DATABASES

11. Display Table Schema //DESCRIBE S1

12. Delete all records from a table //DELETE FROM TABLE

Compliation of the Project
---------------------------------------------------------------

1. javac -cp .:jdbc.jar CSCI3170/*.java (compile under ./src) 

2. java -cp .:jdbc.jar CSCI3170/csci3170_g41

Reference: https://www.developer.com/java/data/manipulating-a-database-with-jdbc.html

Java Package Management
------------------------------------------------------------------

1. Create a Root Directory

2. Compile under Root Directory 

3. Add package statement on each file and put it in the corresponding Folder under Root


