/*
 * Albert Tirtariyadi
 * 0721345
 * CIS2430
 * Assignment 4 - Dayplanner based off A3 with GUI implementation, little changes aside from the scanner input 
 */
package dayplannera4;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author albert710
 */
public class DayPlanner extends DayPlannerA4{

    protected ArrayList<Activity> acts = new ArrayList<Activity>();
    protected HashMap<String, Activity> actsHash = new HashMap<String, Activity>(100);
    public static final String[] types = new String[]{"home", "school", "other", "h", "s", "o", "Home", "School", "Other"};

    /**
     * Function to add a new Home Activity child to the arraylist of activities
     * @param home as a child class of activity Home Activity
     * @return true if added successfully
     */
    private boolean addHomeActivity(HomeActivity home) {
        for (int i = 0; i < acts.size(); i++) {
            /* checks all the entries and see if the newly added activity's time is the same as any of the previous ones */
            int result = home.getStartingTime().compareTo(acts.get(i).getStartingTime());
            if (result == 0) {
                /* if it finds one that's the same, doesn't add, and returns false */
                return false;
            } else if (result < 0) {
                /* if it finds that the newly added one happens before, add it before the found one */
                acts.add(i, home);
                return true;
            }
        }
        /* otherwise just add it to the end */
        acts.add(home);
        return true;
    }

    /**
     * Function to add a new School Activity child to the arraylist of activities
     * @param school as a child class of activity School Activity
     * @return true if added successfully
     */
    private boolean addSchoolActivity(SchoolActivity school) {
        for (int i = 0; i < acts.size(); i++) {
            /* checks all the entries and see if the newly added activity's time is the same as any of the previous ones */
            int result = school.getStartingTime().compareTo(acts.get(i).getStartingTime());
            if (result == 0) {
                /* if it finds one that's the same, doesn't add, and returns false */
                return false;
            } else if (result < 0) {
                /* if it finds that the newly added one happens before, add it before the found one */
                acts.add(i, school);
                return true;
            }
        }
        /* otherwise just add it to the end */
        acts.add(school);
        return true;
    }

    /**
     * Function to add a new Other Activity child to the arraylist of activities
     *
     * @param other as a child class of activity Other Activity
     * @return true if added successfully
     */
    private boolean addOtherActivity(OtherActivity other) {
        for (int i = 0; i < acts.size(); i++) {
            /* checks all the entries and see if the newly added activity's time is the same as any of the previous ones */
            int result = other.getStartingTime().compareTo(acts.get(i).getStartingTime());
            if (result == 0) {
                /* if it finds one that's the same, doesn't add, and returns false */
                return false;
            } else if (result < 0) {
                /* if it finds that the newly added one happens before, add it before the found one */
                acts.add(i, other);
                return true;
            }
        }
        /* otherwise just add it to the end */
        acts.add(other);
        return true;
    }

    /**
     * The core adding function to add the new activity, also contains the user
     * prompting sequence
     *
     * @param input as a scanner from the main program
     */
    public int AddActivity(String typeString, String titleString, String startTimeString, String endTimeString, String locationString, String commentString) {
        /* this is where everything will happen for adding */
        String type = "";
        Time startingTime = null;
        Time endingTime = null;
        String title = "";
        String location = "";
        String comment = "";
        boolean validTimes = true;
        boolean result = true;

        int startTimeComma = 0;
        int endTimeComma = 0;

        /* this is my file appending bit of my code, yes I append because overwriting can get really messy sometimes */
        /* gets user input for the activity type, then title, start time, end time, (location), comment 
         */
        do {
            //System.out.print("Enter an activity type (home, school, or other)\n>");
            type = new String(typeString);

            if (type.equalsIgnoreCase("h")) {
                type = new String("home");
            } else if (type.equalsIgnoreCase("s")) {
                type = new String("school");
            } else if (type.equalsIgnoreCase("o")) {
                type = new String("other");
            }

        } while (!matchedKeyword(type, types));

        //System.out.print("Enter your activity title:\n>");
        title = new String(titleString);

        //do {
        startTimeComma = commaCheck(new String(startTimeString));
        endTimeComma = commaCheck(new String(endTimeString));
        validTimes = true;

        // do { /* keep doing this until startingTime is filled properly */
        //System.out.print("Enter your starting time: (year, month, day, hour, minute)\n>");
        if (startTimeComma == 4) { 
            /* all this does is check how many commas are in the input time string, if it's not 5, return -1 which means the adding failed due to incorrect time input*/
            startingTime = getTime(new String(startTimeString));
        } else {
            return -1;
        }
        //} while (startingTime == null);
        //do { /* keep doing this until endingTime is filled properly */
        //System.out.print("Enter your ending time: (year, month, day, hour, minute)\n>");
        if (endTimeComma == 4) {
            endingTime = getTime(new String(endTimeString));
        } else {
            return -1;
        }
        //} while (endingTime == null);

        if (startingTime.compareTo(endingTime) >= 0) {
            /* so this basically checks if the startingTime happens before endingTime or not, if not, makes validTimes false and restarts the entire loop */
            //System.out.println("Starting time can't be after your ending time, try again.");
            validTimes = false;
            return -1;
        }
        /* on the getgo, this loop should run once, but if the user messes up, it will restart the entire thing */
        //} while (!validTimes);

        if (type.equalsIgnoreCase("other") || type.equalsIgnoreCase("o")) {
            //System.out.print("Enter a location for your activity:\n>");
            location = new String(locationString);
        }

        //System.out.print("Enter any additional comment about your activity: \n>");
        comment = new String(commentString);

        /* here we add our activity to our arraylist<activity>, 
         * at the same time we append our tempString which held individual values of the user input to the output file
         */
        if (type.equalsIgnoreCase("home") || type.equalsIgnoreCase("h")) {
            /* add to home activity */
            result = addHomeActivity(new HomeActivity(title, startingTime, endingTime, comment));
            actsHash.put(title, new HomeActivity(title, startingTime, endingTime, comment));

        } else if (type.equalsIgnoreCase("school") || type.equalsIgnoreCase("s")) {
            /* add to school activity */
            result = addSchoolActivity(new SchoolActivity(title, startingTime, endingTime, comment));
            System.out.println("title is " + title);
            actsHash.put(title, new SchoolActivity(title, startingTime, endingTime, comment));
        } else {
            /* can't be anything else, so add to other activity */
            result = addOtherActivity(new OtherActivity(title, startingTime, endingTime, location, comment));
            actsHash.put(title, new OtherActivity(title, startingTime, endingTime, location, comment));
        }


        if (!result) {
            /* if the result is false from when adding the activity, it won't add and print out the error message */
            DayPlannerA4.resultsArea.setText("Add failed. Cannot have two activities with the same starting time.");
        }
        return 1;
    }

    /**
     * Function to search for Home Activities in the arraylist of activities
     * @param startingTime as the starting time to search for
     * @param endingTime as the ending time to search for
     * @param keywords as any keywords to search for
     */
    private void searchHomeActivities(Time startingTime, Time endingTime, String[] keywords) {
        for (int i = 0; i < acts.size(); i++) {
            if ((startingTime == null || acts.get(i).getStartingTime().compareTo(startingTime) >= 0)
                    && (endingTime == null || acts.get(i).getEndingTime().compareTo(endingTime) <= 0)
                    && (keywords == null || matchedKeywords(keywords, acts.get(i).getTitle()))) {
                //System.out.println(acts.get(i));
                DayPlannerA4.resultsArea.append(acts.get(i).toString() + newLine);
                
            }
        }
    }

    /**
     * Function to search for School Activities in the arraylist of activities
     * @param startingTime as the starting time to search for
     * @param endingTime as the ending time to search for
     * @param keywords as the keywords to search for
     */
    private void searchSchoolActivities(Time startingTime, Time endingTime, String[] keywords) {
        for (int i = 0; i < acts.size(); i++) {
            if ((startingTime == null || acts.get(i).getStartingTime().compareTo(startingTime) >= 0)
                    && (endingTime == null || acts.get(i).getEndingTime().compareTo(endingTime) <= 0)
                    && (keywords == null || matchedKeywords(keywords, acts.get(i).getTitle()))) {
                //System.out.println(acts.get(i));
                DayPlannerA4.resultsArea.append(acts.get(i).toString() + newLine);
            }
        }
    }

    /**
     * Function to search for Other Activities in the arraylist of activities
     * @param startingTime as the starting time to search for
     * @param endingTime as the ending time to search for
     * @param keywords as the keywords to search for
     */
    private void searchOtherActivities(Time startingTime, Time endingTime, String[] keywords) {
        for (int i = 0; i < acts.size(); i++) {
            if ((startingTime == null || acts.get(i).getStartingTime().compareTo(startingTime) >= 0)
                    && (endingTime == null || acts.get(i).getEndingTime().compareTo(endingTime) <= 0)
                    && (keywords == null || matchedKeywords(keywords, acts.get(i).getTitle()))) {
                //System.out.println(acts.get(i));
                DayPlannerA4.resultsArea.append(acts.get(i).toString() + newLine);  
            }
        }
    }

    /**
     * The core adding function to search the activity, also contains the user prompting sequence
     * @param input as a scanner for the main program
     */
    public int SearchActivity(String typeString, String titleString, String startTimeString, String endTimeString) {
        /* this is where everything will happen for searching */
        String type = "";
        do {
            //System.out.print("Enter an activity type (home, school, or other)\n>");
            type = new String(typeString);
        } while (!type.equals("") && !matchedKeyword(type, types));

        
        Time startingTime = null;
        Time endingTime = null;
        boolean validTimes = true;
        int startTimeComma = 0;
        int endTimeComma = 0;

        startTimeComma = commaCheck(new String(startTimeString));
        endTimeComma = commaCheck(new String(endTimeString));

        //System.out.print("Enter a starting time (year, month, day, hour, minute)\n>");
        if (startTimeComma == 4) {/* same as in adding activity, if the number of commas is not 4, just handle the defensive programming */
            String startLine = new String(startTimeString);
            if (startLine.equals("")) {
                startingTime = null;
            } else {
                startingTime = getTime(startLine);
            }
        }else{
            startingTime = null;
            DayPlannerA4.resultsArea.append("Time input is invalid or blank. To try again, follow the proper format: yyyy,mm,dd,hh,min" + newLine);
        }

        if (endTimeComma == 4) {
            //System.out.print("Enter an ending time (year, month, day, hour, minute)\n>");
            String endLine = new String(endTimeString);
            if (endLine.equals("")) {
                endingTime = null;
            } else {
                endingTime = getTime(endLine);
            }
        }else{
            endingTime = null;
            DayPlannerA4.resultsArea.append("Time input is invalid or blank. To try again, follow the proper format: yyyy,mm,dd,hh,min" + newLine);
        }
        if (startingTime != null && endingTime != null && startingTime.compareTo(endingTime) >= 0) {
            //System.out.println("Starting time should happen before ending time");
            validTimes = false;
        }

        //System.out.print("Enter title keywords:\n>");
        String[] keywords = null;
        String line = new String(titleString);
        if (!line.equals("")) {
            keywords = line.split("[ ,\n]+");
        }

        // search for matched activities
        //System.out.println("Matched activities: ");
        resultsArea.append("Search results: " + newLine);
        if (type.equals("") || type.equalsIgnoreCase("home")) {
            searchHomeActivities(startingTime, endingTime, keywords);
        }

        if (type.equals("") || type.equalsIgnoreCase("school")) {
            searchSchoolActivities(startingTime, endingTime, keywords);
        }

        if (type.equals("") || type.equalsIgnoreCase("other")) {
            searchOtherActivities(startingTime, endingTime, keywords);
        }
        return 1;
    }

    /**
     * Overloading matchedKeyword function for when a keyword is given and the title has been tokenized
     * @param keyword as the search keyword
     * @param tokens as the tokenized title
     * @return true if found
     */
    private boolean matchedKeyword(String keyword, String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            if (keyword.equalsIgnoreCase(tokens[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * matchedKeyword function that takes in an array of keywords, and the search title
     * @param keywords as the search keyword
     * @param title as the title being searched for
     * @return true if found
     */
    private boolean matchedKeywords(String[] keywords, String title) {
        String[] tokens = title.split("[ ,\n]+");
        for (int i = 0; i < keywords.length; i++) {
            if (!matchedKeyword(keywords[i], tokens)) {
                return false;
            }
        }
        return true;
    }

    /**
     * getTime function to split up the inputted time and make sure everything is in the correct format
     * @param line as the user input time
     * @return a new time class with the user inputted string
     */
    private Time getTime(String line) {
        String[] tokens = line.split("[ ,\n]+");
        if (tokens.length != 5) {
            return null;
        }
        int year = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int day = Integer.parseInt(tokens[2]);
        int hour = Integer.parseInt(tokens[3]);
        int minute = Integer.parseInt(tokens[4]);
        if (Time.timeOK(year, month, day, hour, minute)) {
            return new Time(year, month, day, hour, minute);
        } else {
            return null;
        }
    }

    /**
     * Function to read in the input file and store things into the arraylist of activities, also adds to the hashmap
     * @param inputFileName as the name of the input file to be read
     */
    public void readInPlanner(String inputFile) {
        FileIO myFile = new FileIO();
        acts = new ArrayList<Activity>(myFile.readFile(inputFile, acts));
        /* add to activities, then add from the arraylist of activities to the hashmap whatever stuff was just read in. Hash maps don't take duplicate key values */
        for (int i = 0; i < acts.size(); i++) {
            actsHash.put(acts.get(i).getTitle(), new Activity(acts.get(i).getTitle(), acts.get(i).getStartingTime(), acts.get(i).getEndingTime(), acts.get(i).getLocation(), acts.get(i).getComment()));
        }
    }

    /**
     * Function to write to the output file.
     * @param outputFileName as the name of the output file to be written to
     */
    public void writeToPlanner(String outputFile) {
        FileIO myFile = new FileIO();
        myFile.writeFile(outputFile, acts);
    }
    
    /**
     * 
     * @param timeString as the time in String to be checked for commas
     * @return number of commas in the string
     */
    public int commaCheck(String timeString){
        int numOfCommas = 0;
        for(int i = 0; i < timeString.length(); i++){
            if(timeString.charAt(i) == ','){
                numOfCommas++;
            }
        }
        return numOfCommas;
        
    }
}
