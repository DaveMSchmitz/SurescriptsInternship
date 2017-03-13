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
This class is responsible for starting the program. It constructs the basics for the GUI and tries to connect to the database.  If it does connect to the database, then it will start to populate the fields that the user can select from. If it cannot connect, it will tell the user that it is not connected to the server

https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Surescripts/code/Henry.java

### SearchPanel.java
This class is responsible for all of the content on the panel. When making a SearchPanel, you need to give it a type (EX. Author, Category, Publisher) and the Database Access Object. With the type, whenever it asks the Database Access Object for book information, it will pass it this type so that the DAO will know what information to give back. The main way to search for books is with ComboBoxes so, when one of the ComboBoxes is switched, it will ask the DAO for type and then load the defaults.

https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Surescripts/code/SearchPanel.java

### HenryDAO.java
This class is the Database Access Object (DAO) and is responsible for recieving requests from the SearchPanel and sending queries to the Database. When making a HenryDAO, it will try to connect to the server.  Then, it will stay connected unitl the user terminates the session.
#### getList(int type)
getList(int type) is responsible for loading the first ComboBox on the SearchPanel.  It retrieves the type requested (EX. author name, category of book, or publisher of book), submits a query for all of that type in the database, and returns a list of strings of that type. After it is done, SearchPanel will then ask for the first book that the other has with getBookData(String data, int type).

#### getBookData(String data, int type)
getBookData(String data, int type) is responsible for populating the information at the bottom of the SearchPanel (EX. title, author(s), price, etc). Using data (book title), it will query the database for the book and create a book with the information needed.

https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Surescripts/code/HenryDAO.java

### Book.java
This class is responsible for being a data structure that can hold all of the information that a book would need (EX. book code, title, book location, etc). It is very basic with having variables that you can set and get.  The different cases is for author and location.  Since there can be more than one author per book, this holds a list of authors.

https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Surescripts/code/Book.java

### Location.java
This class is responsible for being a data structure that can hold location data. It stores the name of the location and the number of books that the location has on hand of that book.

https://github.com/DaveMSchmitz/SurescriptsInternship/blob/master/Surescripts/code/Location.java
