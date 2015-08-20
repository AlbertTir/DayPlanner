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
public class SchoolActivity extends Activity{
    /**
     * Default constructor for School Activity, no input, default inputs
     */
    public SchoolActivity(){
        super("schoolAct", new Time(2012,9,8,7,6,30), new Time(9998,9,7,6,31), "aComment");
    }
    
    /**
     * Constructor for School Activity that takes in input and sets it to the specifications
     * @param otherTitle as the input title
     * @param otherStartingTime as the input starting time
     * @param otherEndingTime as the input ending time
     * @param comment as the input comment
     */
    public SchoolActivity(String otherTitle, Time otherStartingTime, Time otherEndingTime, String comment){
        super(otherTitle, otherStartingTime, otherEndingTime, comment);
    }
    
    /**
     * Constructor that takes in another object and sets this one to that's one
     * @param otherAct as the input object
     */
    public SchoolActivity(Activity otherAct){
        super(otherAct);
    }
}
