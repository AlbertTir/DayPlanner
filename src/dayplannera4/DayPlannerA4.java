/*
 * Albert Tirtariyadi
 * 0721345
 * CIS2430
 * Assignment 4 - Demo file for A4: Dayplanner with GUI
 * I still can't get the search feature to use the hashmap properly, so that's something that's incomplete.
 * The program still works wonderfully without it though
 */
package dayplannera4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author albert710
 */
public class DayPlannerA4 extends JFrame implements ActionListener {
    
    /* These are all the global variables I use all over the place. Seems extensive but some are necessary to simplify things */
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    public static final int LINES = 15;
    public static final int CHAR_PER_LINE = 30;
    public static final String newLine = "\n";
    public static final String[] HOMESCHOOL = {"Home", "School"};
    public static final String OTHER = "Other";
    public static String fileOutputName = "";
    public static String fileInputName = "";
    
    private Border border = BorderFactory.createLineBorder(Color.BLACK);
    
    private JPanel generalPanel = new JPanel();
    private JPanel userPanel = new JPanel();
    
    private JPanel introPanel = new JPanel();
    private JLabel introLabel = new JLabel(); //for the initial label
    
    private JPanel actionPanel = new JPanel(); //for all the textfields
    private JPanel buttonPanel = new JPanel(); //for the 2 buttons
    private JPanel resultsPanel = new JPanel(); //for the massive results box
    
    private JLabel actionLabel = new JLabel(); //for the label saying whether we're adding or searching
    private JLabel resultsLabel = new JLabel();
    
    public static JTextArea resultsArea = new JTextArea(LINES, CHAR_PER_LINE); /* for the text area */
    private JScrollPane resultsScroll = new JScrollPane(resultsArea); /* to let the text area be scrollable */
    
    private JTextField titleField = new JTextField();
    private JTextField startTimeField = new JTextField();
    private JTextField endTimeField = new JTextField();
    private JTextField locationField = new JTextField();
    private JTextField commentField = new JTextField();
    
    private JPanel locationPanel = new JPanel();
    private JPanel commentPanel = new JPanel();
    private JLabel locationLabel = new JLabel("Location: \t");
    private JLabel commentLabel = new JLabel("Comment: \t");
    
    private JComboBox typeBox;
    
    public static DayPlanner planner = new DayPlanner();

    public DayPlannerA4(){
        super("DayPlanner");
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* This chunk of code sets up the menu bar for Command, which has Home, Add, Search and Exit items*/
        JMenu fileMenu = new JMenu("Commands");
        
        JMenuItem homeItem = new JMenuItem("Home");
        homeItem.addActionListener(this);
        fileMenu.add(homeItem);
        
        JMenuItem openItem = new JMenuItem("Add");
        openItem.addActionListener(this);
        fileMenu.add(openItem);
        
        JMenuItem saveItem = new JMenuItem("Search");
        saveItem.addActionListener(this);
        fileMenu.add(saveItem);
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        /* introPanel is the welcome screen when the program starts up */
        introPanel.setLayout(new BorderLayout());
        introPanel.setVisible(true);
        introLabel.setHorizontalAlignment(JLabel.CENTER);
        introLabel.setVerticalAlignment(JLabel.CENTER);
        introLabel.setText(convertToMultiline("Welcome to the DayPlanner!\n\nChoose a command from the 'Commands' menu above to return to the home screen, add activities, search activities, or quit the program."));
        introPanel.add(new JPanel(), BorderLayout.WEST); /* this is just to make it look pretty, adds a "buffer space" to the west and east side */
        introPanel.add(introLabel, BorderLayout.CENTER);
        introPanel.add(new JPanel(), BorderLayout.EAST);
        
        /* userPanel contains the actionPanel and the buttonPanel */
        userPanel.setLayout(new BorderLayout());
        userPanel.add(actionPanel, BorderLayout.CENTER);
        userPanel.add(buttonPanel, BorderLayout.EAST);

        /* where all the textfield for inputs goes */
        actionPanel.setLayout(new GridLayout(7,1));
        actionPanel.setBorder(border);
        
        /* set up all the things needed here before adding it onto the cards */
        String[] comboBoxItems = {"Home", "School", "Other"};
        typeBox = new JComboBox(comboBoxItems);
        typeBox.setEditable(false);
        typeBox.addActionListener(this);
        
        /* typePanel holds the label and combobox for the type*/
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BorderLayout());
        JLabel typeLabel = new JLabel("Type: \t");
        typePanel.add(typeLabel, BorderLayout.WEST);
        typePanel.add(typeBox, BorderLayout.CENTER);
        
        /* titlePanel holds the label and textfield for the title */
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Title: \t");
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(titleField, BorderLayout.CENTER);
        
        /* startTimeLabel holds the label and textfield for the starting time */
        JPanel startTimePanel = new JPanel();
        startTimePanel.setLayout(new BorderLayout());
        JLabel startTimeLabel = new JLabel("Starting Time: ");
        startTimeField.setText("");
        startTimeField.setToolTipText("yyyy,mm,dd,hh,min");
        startTimePanel.add(startTimeLabel, BorderLayout.WEST);
        startTimePanel.add(startTimeField, BorderLayout.CENTER);
        
        /* endTimePanel holds the label and textfield for the ending time */
        JPanel endTimePanel = new JPanel();
        endTimePanel.setLayout(new BorderLayout());
        JLabel endTimeLabel = new JLabel("Ending Time: ");
        endTimeField.setText("");
        endTimeField.setToolTipText("yyyy,mm,dd,hh,min");
        endTimePanel.add(endTimeLabel, BorderLayout.WEST);
        endTimePanel.add(endTimeField, BorderLayout.CENTER);
        
        /* locationPanel for the locationLabel and textfield */
        locationPanel.setLayout(new BorderLayout());
        locationPanel.add(locationLabel, BorderLayout.WEST);
        locationPanel.add(locationField, BorderLayout.CENTER);
        locationField.setEnabled(false);
        
        /* commentPanel for the commentLabel and textfield */
        commentPanel.setLayout(new BorderLayout());
        commentPanel.add(commentLabel, BorderLayout.WEST);
        commentPanel.add(commentField, BorderLayout.CENTER);
        
        /* the actionPanel holds the textfields and such, whatever the user interacts with for sending input */
        actionPanel.add(actionLabel);
        actionPanel.add(typePanel);
        actionPanel.add(titlePanel);
        actionPanel.add(startTimePanel);
        actionPanel.add(endTimePanel);
        actionPanel.add(locationPanel);
        actionPanel.add(commentPanel);

        /* buttonPanel will hold the 2 buttons to the side */
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.setBorder(border);
        /* a reset button for resetting the valeus in the textFields */
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        
        /* an enter button for confirming the action and either adding or searching */
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(this);
        buttonPanel.add(enterButton);
        
        /* resultsPanel will hold the results label and any error message/search results box */
        resultsPanel.setLayout(new BorderLayout());
        resultsPanel.setBorder(border);
        resultsPanel.add(resultsLabel, BorderLayout.NORTH);
        resultsArea.setLineWrap(true);
        resultsArea.setWrapStyleWord(true);
        resultsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        resultsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultsPanel.add(resultsScroll, BorderLayout.CENTER);
        resultsPanel.add(new JPanel(), BorderLayout.WEST);
        resultsPanel.add(new JPanel(), BorderLayout.EAST);
        resultsPanel.add(new JPanel(), BorderLayout.SOUTH);
        resultsArea.setEditable(false); 
        /* this makes it so the user can't edit the stuff in the text area. 
         * They can still copy and paste the (error) messages like any other error report textareas, they just can't change stuff in it*/
        
        /* add up all the things into a generalPanel that will fit nicely into the frame */
        generalPanel.setLayout(new GridLayout(2,1));
        generalPanel.add(userPanel);
        generalPanel.add(resultsPanel);
        generalPanel.setVisible(false);
        
        /* starts off with the welcome screen to the program */
        add(introPanel, BorderLayout.CENTER);

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        String command = "";
        int i = 0;
        
        DayPlannerA4 myCalendar = new DayPlannerA4();
        myCalendar.setVisible(true);
        
        /* this is the part for file I/O, takes 0, 1 or 2 arguments from command line */
        if(args.length == 0){
            /* if no files are given during run command */
            fileInputName = new String("dayplanOutput.txt");
            fileOutputName = new String("dayplanOutput.txt");
        }else if (args.length == 1){
            /* so if input is given but no output specified */
            fileInputName = new String(args[0]);
            fileOutputName = new String(fileInputName);
            planner.readInPlanner(fileInputName);
        }else if (args.length == 2){
            /* if both input and output are given */
            fileInputName = new String(args[0]);
            fileOutputName = new String(args[1]);
            planner.readInPlanner(fileInputName);
        }else{
            System.out.println("Too many arguments, specify <input>, <input> <output> or leave it blank for default settings.");
            System.exit(0);
        }
        
        planner.writeToPlanner(fileOutputName);

    }

    /**
     * The method where it determines what happens when various actions by buttons are performed
     * @param ae as the action command
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String buttonCommand = ae.getActionCommand();
        
        if(buttonCommand.equalsIgnoreCase("Home")){
            introPanel.setVisible(true);
            generalPanel.setVisible(false);
            DayPlannerA4.this.add(introPanel, BorderLayout.CENTER);
            
        }else if(buttonCommand.equalsIgnoreCase("Add")){
            /* sets things up if the Add menu is pressed, goes into the appropriate interface */
            locationLabel.setVisible(true);
            locationField.setVisible(true);
            commentLabel.setVisible(true);
            commentField.setVisible(true);
            introPanel.setVisible(false);
            generalPanel.setVisible(true);
            resetAllFields();
            actionLabel.setText("Adding an activity: ");
            resultsLabel.setText("Messages:");
            DayPlannerA4.this.add(generalPanel, BorderLayout.CENTER);
            
        }else if(buttonCommand.equalsIgnoreCase("Search")){
            /* sets things up if the Search menu is pressed, goes into the appropriate interface */
            locationLabel.setVisible(false);
            locationField.setVisible(false);
            commentLabel.setVisible(false);
            commentField.setVisible(false);
            introPanel.setVisible(false);
            generalPanel.setVisible(true);
            resetAllFields();
            actionLabel.setText("Searching activities: ");
            resultsLabel.setText("Search results:");
            DayPlannerA4.this.add(generalPanel, BorderLayout.CENTER);
            
        }else if(buttonCommand.equalsIgnoreCase("Exit")){
            /* exit menu is pressed, writes to file and quits the program */
            planner.writeToPlanner(fileOutputName);
            System.exit(0);
        }else if(buttonCommand.equalsIgnoreCase("Reset")){
            /* resets all the text area fields */
            resetAllFields();
        }else if(buttonCommand.equalsIgnoreCase("Enter")){
            /* Sets up the Enter button depending on what interface is being used at the moment */
            if(actionLabel.getText().equalsIgnoreCase("Adding an activity: ")){
                /* When adding an activity, Enter calls for AddActivity */
                String typeString = typeBox.getSelectedItem().toString();
                String titleString = titleField.getText();
                String startTimeString = startTimeField.getText();
                String endTimeString = endTimeField.getText();
                String locationString = locationField.getText();
                String commentString = commentField.getText();

                int addResult = planner.AddActivity(typeString, titleString, startTimeString, endTimeString, locationString, commentString);
                if(addResult > 0){
                    resultsArea.setText("Add successful!");
                }else if(addResult < 0){
                    resultsArea.setText("Add failed. Make sure to follow proper time formatting: yyyy,mm,dd,hh,min");
                }
                
                
            }else if(actionLabel.getText().equalsIgnoreCase("Searching activities: ")){
                /* When searching an activity, Enter calls for search Activity */
                resultsArea.setText("");
                locationPanel.setVisible(false);
                commentPanel.setVisible(false);
                String typeString = typeBox.getSelectedItem().toString();
                String titleString = titleField.getText();
                String startTimeString = startTimeField.getText();
                String endTimeString = endTimeField.getText();

                int searchResult = planner.SearchActivity(typeString, titleString, startTimeString, endTimeString);
                
                if(searchResult > 0){
                    /* I do the search result line in the DayPlanner class */
                }else if(searchResult < 0){
                    resultsArea.append("Search failed. Make sure to follow proper time formatting for any time inputs: yyyy,mm,dd,hh,min");
                }
            }
        }else if (buttonCommand.equalsIgnoreCase("comboBoxChanged")) {
            /* this makes it  so when the combobox has Home or School selected, location is disabled, and enabled when Other is selected */
            JComboBox tempBox = (JComboBox)ae.getSource();
            String tempString = (String)tempBox.getSelectedItem();
            if (tempString.equalsIgnoreCase("Home")) {
                locationField.setEnabled(false);
            } else if (tempString.equalsIgnoreCase("School")) {
                locationField.setEnabled(false);
            } else if (tempString.equalsIgnoreCase("Other")) {
                locationField.setEnabled(true);
            }
        }
    }
    
    /**
     * A method to split a string into 2 multi line strings
     * @param orig as the original string that will be broken up
     * @return a multi line string
     */
    public static String convertToMultiline(String orig) {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
    
    /**
     * This method is used to reset all the fields to empty
     */
    public void resetAllFields(){
        typeBox.setSelectedIndex(0);
        titleField.setText("");
        startTimeField.setText("");
        endTimeField.setText("");
        locationField.setText("");
        commentField.setText("");
        resultsArea.setText("");
    }
    
}
