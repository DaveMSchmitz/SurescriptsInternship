package schmitdm4798;

import javax.swing.*;
import java.awt.*;

public class Henry implements Runnable{

    public static void main(String[] args) {
            //create a new Henry
        new Henry();
    }

    public Henry(){
            //fire off a new thread
        Thread thread = new Thread(this);
        thread.start();


    }

    public void run(){
            //create a new frame with default values
        JFrame frame = new JFrame();
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

            //create a new DAO
        HenryDAO dao = new HenryDAO();

            //Safety check to make sure there is a connection to the database
        if (dao.getIsConnected()){
                //create a TabbedPane
            JTabbedPane tabbedPane = new JTabbedPane();

                //create a SearchPanel of type AUTHOR
            SearchPanel author = new SearchPanel(dao, HenryDAO.AUTHOR);
            author.setBackground(new Color(33,150,243));
            tabbedPane.addTab("Search by Author", author);

                //create a SearchPanel of type CATEGORY
            SearchPanel category = new SearchPanel(dao, HenryDAO.CATEGORY);
            category.setBackground(new Color(100,255,218));
            tabbedPane.addTab("Search by Category", category);

                //create a SearchPanel of type PUBLISHER
            SearchPanel publisher = new SearchPanel(dao, HenryDAO.PUBLISHER);
            publisher.setBackground(new Color(0,230,118));
            tabbedPane.addTab("Search by Publisher", publisher);

                //add the TabbedPane to the frame
            frame.add(tabbedPane);

                //listen for the ShutdownHook to terminate the connection to the database
            Runtime.getRuntime().addShutdownHook(new Thread()
            {
                @Override
                public void run()
                {
                    System.out.println("Initiating Shutdown");
                    dao.closeConnection();
                }
            });
        }else{
            
            //if you are not connected to the server 
            JLabel notConnected = new JLabel();
            notConnected.setFont(new Font(notConnected.getFont().getName(), notConnected.getFont().getStyle(), 20));
            notConnected.setText("Sorry, you are not connected to the server.");

            frame.add(notConnected);

        }



        frame.setVisible(true);
    }

}
