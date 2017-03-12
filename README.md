# Surescripts Internship
Hello Surescripts, the project I chose to submit was for my Databases class.  The goal of the project was to incorporate retrieving information from a database with a Object-Oriented Language.  This project touches base with SQL, Object-Oriented design, and GUI development.  This project uses a private University of Wisconsin Server, so it will not be able to run, without being on a UWEC server or with proper credentials. 

# Completed Project
 This project simulated what an online book database might look like in Java.  There where 3 tabs that the user could search from, Author name, Category of books, and Publisher.
 
### Author Tab 
![alt tag](https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Images/author1.png)
![alt tag](https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Images/author2.png)
<br>  
### Category Tab 
![alt tag](https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Images/cat1.png)
![alt tag](https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Images/cat2.png)
<br>  
### Publisher Tab 
![alt tag](https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Images/pub1.png)
![alt tag](https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Images/pub2.png)
<br>

# Code

### Henry.java
This class is responsible for starting the program. It constructs the basics for the GUI and tries to connect to the database.  If it does connect to the database then it will start to populate the fields that the user can select from. If it cannot connect, it will tell the user that it is not connected to the server

https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Surescripts/code/Henry.java

### SearchPanel.java
This class is responsible for all of the content on the panel. When making a SearchPanel you need to give it a type (EX. Author, Category, Publisher) and the Database Access Object. With the type, when ever it asks the Database Access Object for book information, it will pass it this type so that the DAO will know what information to give back. The main way to search for books is with ComboBoxes so when one of the ComboBoxes is switched it will ask the DAO for type and then load the defaults.

https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Surescripts/code/SearchPanel.java
