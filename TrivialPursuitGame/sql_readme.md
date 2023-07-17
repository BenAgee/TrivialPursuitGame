MySQL windows installer
https://dev.mysql.com/downloads/installer/

Linux 
sudo apt install mysql-server


I used wsl running on my windows computer to do this, if you dont' have wsl setup you can use the windows download. 

You have to create a user within your sql server for the application to connect with. Here are my commands in a linux terminal.

sudo apt install mysql-server

sudo systemctl mysql start

sudo mysql

//in mysql prompt
> CREATE USER trivia@localhost IDENTIFIED BY 'poiulkjh'; 
> CREATE DATABASE TRIVIA_COMPUTE;
> USE TRIVIA_COMPUTE;
> GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT, REFERENCES, RELOAD on *.* TO 'trivia'@'localhost' WITH GRANT OPTION;
create some account
> exit
//in terminal run the following to create tables and add some entries

sudo mysql < SQL_SCHEMA

sudo mysql < SQL_DATA

//verify entries
sudo mysql
> USE TRIVIA_COMPUTE
> SHOW TABLES;
> SELECT * FROM QUESTIONS;

// You may want to turn off the database when you are done. `sudo systemctl mysql stop`


