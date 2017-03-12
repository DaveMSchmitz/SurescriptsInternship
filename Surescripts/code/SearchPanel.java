package sure;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;


public  class SearchPanel extends JPanel{

    private boolean isSetUp;

    private List<Book> bookList;
    private JComboBox<Book> comboBoxBook;
    private JPanel holderPanel;

    public SearchPanel(HenryDAO dao, int type){
            //Set a variable that will keep track of whether this is the first time the panel is set up
        isSetUp = false;

            //get the list of data that goes in the first comboBox, and get the default book list that
            //goes with that piece of data
        List<String> dataList = dao.getList(type);
        bookList = dao.getBookData(dataList.get(0), type);

            //set the panel to a grid layout
        super.setLayout(new GridLayout(0,1));

            //create a panel that will hold the combo boxes and make it transparent
        JPanel comboPanel = new JPanel();
        comboPanel.setOpaque(false);

            //create a ComboBox model that will hold all of the first
            //ComboBox data and create a ComboBox with that model
        DefaultComboBoxModel<String> dataModel = new DefaultComboBoxModel<String>(new Vector<String>(dataList));
        JComboBox<String> comboBoxData = new JComboBox<String>(dataModel);

            //add an action listener to the ComboBox
        comboBoxData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    //when the ComboBox is clicked get the String that was selected
                JComboBox tempCombo = (JComboBox) e.getSource();
                String data = (String) tempCombo.getSelectedItem();

                    //get the book list that is associated with that string and set the default values
                bookList = dao.getBookData(data, type);
                setDefault();

            }
        });
            //add the ComboBox to the ComboBox panel
        comboPanel.add(comboBoxData);

            //create a ComboBox that is in charge of selecting books
        comboBoxBook = new JComboBox<Book>();

            //create an action listener for the book ComboBox
        comboBoxBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    //get the name of the book that was clicked
                JComboBox tempCombo = (JComboBox) e.getSource();
                Book book = (Book)tempCombo.getSelectedItem();

                    //set the book information
                setBookInfo(book);
            }
        });
            //add the book ComboBox to the combo panel
        comboPanel.add(comboBoxBook);

            //add the combo panel to this SearchPanel and create a EmptyBorder to space the items away from the edges
        this.add(comboPanel);
        this.setBorder(new EmptyBorder(30,30,30,30));

            //run the default set up, and mark the SearchPanel as set up after
        setDefault();
        isSetUp = true;
    }

    private void setDefault(){
            //create a Model for the book ComboBox with the book list, and set the Model on the book ComboBox
        DefaultComboBoxModel<Book> bookComboBoxModel = new DefaultComboBoxModel<Book>(new Vector<Book>(bookList));
        comboBoxBook.setModel(bookComboBoxModel);

            //Safety check to make sure there is a book in the book list
        if(bookList.size() != 0){
            setBookInfo(bookList.get(0));
        }else{
            if(isSetUp){
                this.remove(holderPanel);
            }

            holderPanel = new JPanel();
            holderPanel.add(new JLabel("Sorry, No Books"));
            this.add(holderPanel);
            this.revalidate();
            this.repaint();
        }



    }

    private void setBookInfo(Book book){

            //if it is already set up, remove the current holderPane;
        if(isSetUp){
            this.remove(holderPanel);
        }

            //create all of the lines that hold the book information like author, price, availability, etc

        holderPanel = new JPanel(new GridLayout(0,1));
        holderPanel.setBorder(new LineBorder(Color.black, 1, false));

        JPanel titleLine = new JPanel(new GridLayout(0,2));
        titleLine.setBackground(new Color(158,158,158));
        titleLine.add(new JLabel("Title"));
        titleLine.add(new JLabel(book.getTitle()));
        holderPanel.add(titleLine);

        JPanel authorsLine = new JPanel(new GridLayout(0,2));
        authorsLine.add(new JLabel("Author(s)"));
        authorsLine.add(new JLabel(book.getAuthor()));
        holderPanel.add(authorsLine);

        JPanel priceLine = new JPanel(new GridLayout(0,2));
        priceLine.setBackground(new Color(158,158,158));
        priceLine.add(new JLabel("Price"));
        priceLine.add(new JLabel("$"+book.getPrice()));
        holderPanel.add(priceLine);

        JPanel typeLine = new JPanel(new GridLayout(0,2));
        typeLine.add(new JLabel("Genre"));
        typeLine.add(new JLabel(book.getType()));
        holderPanel.add(typeLine);

        JPanel paperbackLine = new JPanel(new GridLayout(0,2));
        paperbackLine.setBackground(new Color(158,158,158));
        paperbackLine.add(new JLabel("Paperback Version"));
        paperbackLine.add(new JLabel(book.isPaperback()));
        holderPanel.add(paperbackLine);

        JPanel pubLine = new JPanel(new GridLayout(0,2));
        pubLine.add(new JLabel("Publisher"));
        pubLine.add(new JLabel(book.getPublisherName()));
        holderPanel.add(pubLine);

        boolean even = true;

            //add all of the locations to the panel
        for(Location location:book.getLocation()){
            JPanel inventoryLine = new JPanel(new GridLayout(0,2));

            if(even){
                inventoryLine.setBackground(new Color(158,158,158));
                even = false;
            }else{
                even = true;
            }

            inventoryLine.add(new JLabel(location.getName()));
            inventoryLine.add(new JLabel(location.getOnHand()+" in Stock"));
            holderPanel.add(inventoryLine);
        }

            //add the holderPanel to the SearchPanel
        this.add(holderPanel);
        this.revalidate();
        this.repaint();

    }


}
