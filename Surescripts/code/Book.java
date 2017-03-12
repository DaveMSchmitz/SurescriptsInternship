package sure;

import java.util.ArrayList;
import java.util.List;


public class Book {

    private String bookCode;
    private String title;
    private String publisherName;
    private String type;
    private Float price;
    private String paperback;
    private List<Location> location;
    private List<String> authorList;

    //book constructor, set defaults
    public Book(String bookCode, String title, Float price, String location, int onHand, String authorName, String type, String paperback, String publisherName){
        this.bookCode = bookCode;
        this.title = title;
        this.price = price;
        Location location1 = new Location(location, onHand);
        this.location = new ArrayList<>();
        this.location.add(location1);
        authorList = new ArrayList<>();
        authorList.add(authorName);
        this.type = type;
        this.paperback = paperback;
        this.publisherName = publisherName;

    }
    
    //get the title
    public String getTitle() {
        return title;
    }

    //get the publisher
    public String getPublisherName() {
        return publisherName;
    }
    
    //get the type of book
    public String getType() {
        return type;
    }

    //get the price
    public Float getPrice() {
        return price;
    }

    //get if it is a paper back
    public String isPaperback() {
        return paperback;
    }

    //return a list of locations that the book is in
    public List<Location> getLocation(){
        return location;
    }

    //check to make sure that you haven't already the location
    public void addLocation(Location location1){
            //add the location if there is no location that is the same
        if(!location.contains(location1)){
            location.add(location1);
        }

    }

    //add the author if its not in there
    public void addAuthor(String authorName){
            //add the author if it does not already contain this author
        if(!authorList.contains(authorName)){
            authorList.add(authorName);
        }
    }

    public String getAuthor(){
        
        //loop in a half
        String result = authorList.get(0);

        //get all of the author names
        for(int i = 1; i < authorList.size(); ++i){
            result = result + " and " +authorList.get(i);
        }


        return result;
    }

    //if the book equals the same thing return true
    public boolean equals(Object object){
        Book book = (Book)object;
        return (this.bookCode.equals(book.bookCode));
    }
    
    //debugging purpose
    public String toString(){
        return title;
    }
}
