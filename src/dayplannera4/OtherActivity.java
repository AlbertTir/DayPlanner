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
public class OtherActivity extends Activity{
    /**
     * Default constructor for Other Activity class
     */
    public OtherActivity(){
        super("otherAct", new Time(2012,9,8,7,6,30), new Time(9998,9,7,6,31), "aPlace", "aComment");
    }
    
    /**
     * Constructor for Other Activity that takes in other inputs
     * @param otherTitle as input title
     * @param otherStartingTime as input starting time
     * @param otherEndingTime as input ending time
     * @param location as input location
     * @param comment as input comment
     */
    public OtherActivity(String otherTitle, Time otherStartingTime, Time otherEndingTime, String location, String comment){
        super(otherTitle, otherStartingTime, otherEndingTime, location, comment);
    }
    
    /**
     * Constructor for Other Activity that takes in other inputs except location
     * @param otherTitle as input title
     * @param otherStartingTime as input starting time
     * @param otherEndingTime as input ending time
     * @param comment as input comment
     */
    public OtherActivity(String otherTitle, Time otherStartingTime, Time otherEndingTime, String comment){
        super(otherTitle, otherStartingTime, otherEndingTime, "aPlace", comment);
    }
    
    /**
     * Constructor that takes in another instance to set this instance to
     * @param otherAct as the input object
     */
    public OtherActivity(Activity otherAct){
        super(otherAct);
    }
}
