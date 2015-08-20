/*
 * Albert Tirtariyadi
 * 0721345
 * CIS2430
 * Assignment 4 - Dayplanner with Inheritance, hashmaps and file IO
 */
package dayplannera4;

/**
 *
 * @author albert710
 */
public class HomeActivity extends Activity{
    /**
     * Default constructor for home activity child class that calls the superclass
     */
    public HomeActivity(){
        super("homeAct", new Time(2012,9,8,7,6,30), new Time(9998,9,7,6,31), "aComment");
    }
    
    /**
     * Constructor for home activity that takes in inputs and assigns the activity those values
     * @param otherTitle as the input title
     * @param otherStartingTime as the input starting time
     * @param otherEndingTime as the input ending time
     * @param comment as the input comment
     */
    public HomeActivity(String otherTitle, Time otherStartingTime, Time otherEndingTime, String comment){
        super(otherTitle, otherStartingTime, otherEndingTime, comment);
    }
    
    /**
     * Constructor for home activity that takes in another object Activity and assigns those values to this instance
     * @param otherAct as the input object
     */
    public HomeActivity(Activity otherAct){
        super(otherAct);
    }
    
}
