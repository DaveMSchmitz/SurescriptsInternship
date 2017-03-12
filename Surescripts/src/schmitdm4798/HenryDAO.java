package schmitdm4798;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HenryDAO {

        //the types of searches you can perform
    public static final int AUTHOR = 0;
    public static final int CATEGORY = 1;
    public static final int PUBLISHER = 2;

        //JDBC driver identifier and database URL
    private static final String DB_URL = "jdbc:oracle:thin:@dario.cs.uwec.edu:1521:csdev";

        //Database credentials
    private static final String USER = "username";
    private static final String PASS = "password";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private boolean isConnected;


    public HenryDAO(){

            //connect to the database
        try{
                //Open the connection to the database
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            isConnected = true;


        }catch(SQLException ex){
            ex.printStackTrace();
            isConnected = false;
        }

    }

    public List<String> getList(int type){

        String sql = "";
        String cData = "";
            //create the sql based on what type you are trying to get
        switch(type){
            case AUTHOR: sql = "SELECT distinct (TRIM(author_first) || ' ' || TRIM(author_last)) as author_name FROM henry_author INNER JOIN henry_wrote ON henry_author.author_num = henry_wrote.author_num INNER JOIN henry_book ON henry_book.book_code = henry_wrote.book_code ORDER BY author_name";
                         cData = "author_name";
                break;
            case CATEGORY: sql = "SELECT DISTINCT type FROM henry_book ORDER BY type";
                           cData = "type";
                break;
            case PUBLISHER: sql = "SELECT distinct publisher_name " +
                                  "FROM henry_publisher INNER JOIN HENRY_BOOK " +
                                  "ON henry_publisher.PUBLISHER_CODE = HENRY_BOOK.PUBLISHER_CODE " +
                                  "ORDER BY publisher_name";
                            cData = "publisher_name";
                break;
            default: System.err.println("Unknown Type");
        }

            //create a list for the data
        List<String> dataList = new ArrayList<>();

            //try to execute the query
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

                //add all of the data to the list
            while(resultSet.next()){
               
                    //get the data you were trying to get and add it to the list
                dataList.add(resultSet.getString(cData).trim());
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return dataList;


    }
        //get the book data based on the type needed
    public List<Book> getBookData(String data, int type){
        String sql = "SELECT (TRIM(author_first) || ' ' || TRIM(author_last)) as author_name, book_code, paperback, publisher_name, type, title, price, branch_name, on_hand \n" +
                "FROM henry_book INNER JOIN henry_wrote \n" +
                "   ON henry_book.book_code = henry_wrote.book_code\n" +
                "   INNER JOIN henry_inventory \n" +
                "   ON henry_book.book_code = henry_inventory.book_code\n" +
                "   INNER JOIN henry_branch \n" +
                "   ON henry_inventory.branch_num = henry_branch.branch_num \n" +
                "   INNER JOIN henry_author \n" +
                "   ON henry_author.author_num = henry_wrote.author_num\n" +
                "   INNER JOIN henry_publisher\n" +
                "   ON henry_book.PUBLISHER_CODE = henry_publisher.publisher_code\n";


            //get the book data based on the type needed
        switch(type){
            case AUTHOR: data = data.replace("'","''");

                         sql =    sql + "WHERE book_code in (SELECT book_code\n" +
                                 "                    FROM(select(TRIM(author_first) || ' ' || TRIM(author_last)) as author_name, henry_book.book_code  \n" +
                                 "                         FROM henry_book INNER JOIN henry_wrote \n" +
                                 "                           ON henry_book.book_code = henry_wrote.book_code\n" +
                                 "                           INNER JOIN henry_author \n" +
                                 "                           ON henry_author.author_num = henry_wrote.author_num)\n" +
                                 "                    WHERE author_name = '"+data+"')";
                break;

            case CATEGORY: sql = sql +" WHERE type = '" + data + "' order by title";
                break;

            case PUBLISHER: sql = sql + "  WHERE publisher_name = '"+data+"'";
                break;

            default: System.err.println("Unknown Type");
        }

            //create a list for the books
        List<Book>  bookList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

                //get all of the book data the comes back and create a book with all of the data
            while(resultSet.next()){

                String authorName = resultSet.getString("author_name");



                String bookCode = resultSet.getString("book_code");
                String paperBack = resultSet.getString("paperback");
                String publisherName = resultSet.getString("publisher_name");
                String bookType = resultSet.getString("type");
                String title = resultSet.getString("title");
                Float price = resultSet.getFloat("price");
                String location = resultSet.getString("branch_name");
                int onHand = resultSet.getInt("on_hand");

                    //create a book with the data
                Book book = new Book(bookCode,title,price,location,onHand, authorName, bookType, paperBack, publisherName);




                boolean isDone = false;
                int i = 0;

                    //loop the the book list and check if a book is already in there, if it
                    //is add the location and the author of the current book to the book already in the list
                if(bookList.size() != 0){


                    while(!(isDone || bookList.size() <= i)){
                        
                            //if this book in the list is the same as another book
                        if(bookList.get(i).equals(book)){

                                //add the location of this book to the book
                            bookList.get(i).addLocation(book.getLocation().get(0));
                            bookList.get(i).addAuthor(book.getAuthor());
                            isDone = true;

                        }else{

                            ++i;
                        }
                    }

                        //if its the last book, add it in
                    if(i == bookList.size()){

                        bookList.add(book);
                    }
                }else{
                        //if there was only one book, add it in
                    bookList.add(book);
                }


            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return bookList;
    }

    public void closeConnection(){
            //close the connection
        try {
            connection.close();
            System.out.println("Connection Closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getIsConnected(){
        return isConnected;
    }

}
