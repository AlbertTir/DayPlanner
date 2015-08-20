/*
 * Albert Tirtariyadi
 * 0721345
 * CIS2430
 * Assignment 3 - Dayplanner with Inheritance, hashmaps and file IO
 */
package dayplannera4;

/**
 * 
 * @author albert710
 */
public class Activity {
    protected String title;
    protected Time startingTime;
    protected Time endingTime;
    protected String comment;
    protected String location;
   
    /**
     * Default constructor, sets values
     */
    public Activity(){
        this.title = new String("someActivity");
        this.startingTime = new Time(2012,9,8,7,6,30);
        this.endingTime = new Time(9998,9,8,7,6,31);
        this.comment = new String("someComment");
        this.location = new String("");
    }
    
    /**
     * 
     * @param otherTitle as input title
     * @param otherStartingTime as input starting time
     * @param otherEndingTime as input ending time
     * @param otherComment as any additional comments
     */
    public Activity(String otherTitle, Time otherStartingTime, Time otherEndingTime, String otherComment){
        this.title = new String(otherTitle);
        this.startingTime = new Time(otherStartingTime);
        this.endingTime = new Time(otherEndingTime);
        this.comment = new String(otherComment);
        this.location = new String("");
    }
    
    /**
     * 
     * @param otherTitle as input title
     * @param otherStartingTime as input starting time
     * @param otherEndingTime as input ending time
     * @param otherLocation as input location
     * @param otherComment as any additional comment
     */
    public Activity(String otherTitle, Time otherStartingTime, Time otherEndingTime, String otherLocation, String otherComment){
        this.title = new String(otherTitle);
        this.startingTime = new Time(otherStartingTime);
        this.endingTime = new Time(otherEndingTime);
        this.comment = new String(otherComment);
        this.location = new String(otherLocation);
    }
    
    /**
     * 
     * @param otherTitle as input title
     * @param otherStartingTime as input starting time
     * @param otherEndingTime as input ending time
     */
    public Activity(String otherTitle, Time otherStartingTime, Time otherEndingTime){
        this(otherTitle, otherStartingTime, otherEndingTime, "");
    }
    
    /**
     * Constructor that takes in another activity for this activity to copy
     * @param otherActivity as input instance
     */
    public Activity(Activity otherActivity){
        if (otherActivity == null){
            System.out.println("Fatal error, Null activity for setting.");
            System.exit(0);
        } else {
            this.title = new String(otherActivity.title);
            this.startingTime = new Time(otherActivity.startingTime);
            this.endingTime = new Time(otherActivity.endingTime);
            this.comment = new String(otherActivity.comment);
            this.location = new String(otherActivity.location);
        }
    }
    
    /**
     * toString() override specific to this custom class
     * @return the variables in this class in a neat format
     */
    public String toString() {
        if (this.location.length() > 0) {
            return this.title + ": " + this.startingTime + " to " + this.endingTime + " at: " + this.location + ", " + this.comment;
        } else {
            return this.title + ": " + this.startingTime + " to " + this.endingTime + ", " + this.comment;
        }
    }

    /**
     * equals() override to compare this class to another instance
     * @param otherActivity as another instance of the Activity class to be compared to 
     * @return true if both class have the same values
     */
    public boolean equals(Activity otherActivity) {
        if (otherActivity == null){
            return false;
        }else{
            return (this.title.equals(otherActivity.title) && 
                    this.startingTime.equals(otherActivity.startingTime) && 
                    this.endingTime.equals(otherActivity.endingTime) && 
                    this.comment.equals(otherActivity.comment));
        }
    }
    
    /**
     * Accessor method
     * @return the title of this instance
     */
    public String getTitle(){
        return new String(this.title);
    }
    
    /**
     * Accessor method
     * @return the starting time of this instance
     */
    public Time getStartingTime(){
        return new Time(this.startingTime);
    }
    
    /**
     * Accessor method
     * @return the ending time of this instance
     */
    public Time getEndingTime(){
        return new Time (this.endingTime);
    }

    /**
     * Accessor method
     * @return the comment of this instance
     */
    public String getComment() {
        return new String(this.comment);
    }
    
    /**
     * Accessor method
     * @return the location of this instance
     */
    public String getLocation(){
        return new String(this.location);
    }

    /**
     * Mutator method
     * @param otherTitle as new title 
     */
    public void setTitle(String otherTitle) {
        this.title = new String(otherTitle);
    }

    /**
     * Mutator method
     * @param startingTime as new starting time 
     */
    public void setStartingTime(Time startingTime) {
        if (endingTime != null && startingTime.compareTo(endingTime) >= 0) {
            System.out.println("Invalid starting time: " + startingTime);
            System.exit(0);
        } else {
            this.startingTime = new Time(startingTime);
        }
    }

    /**
     * Mutator method
     * @param endingTime as new ending time 
     */
    public void setEndingTime(Time endingTime) {
        if (startingTime != null && startingTime.compareTo(endingTime) >= 0) {
            System.out.println("Invalid ending time: " + endingTime);
            System.exit(0);
        }else{
            this.endingTime = new Time(endingTime);
        }
    }
    
    /**
     * Mutator method
     * @param otherComment as new comment 
     */
    public void setComment(String otherComment){
        this.comment = new String(otherComment);
    }
    
    /**
     * Mutator method
     * @param otherLocation as new location 
     */
    public void setLocation(String otherLocation){
        this.location = new String(otherLocation);
    }
}
