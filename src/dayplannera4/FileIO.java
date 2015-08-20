/*
 * Albert Tirtariyadi
 * 0721345
 * Simple program for reading and writing to files, lets you read from file, (over)write to file, append to file
 */
package dayplannera4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author albert710
 */
public class FileIO {

    /**
     * Function read in a line from a file
     * @param inputFileName as the name of the input file to be read from
     * @return the string that comes up from the file
     */
    public String readFile(String inputFileName) {
        String fileString = "";
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream(inputFileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the input file");
            System.exit(0);
        }

        if (inputStream.hasNextLine()) {
            fileString = inputStream.nextLine();
        } else {
            System.out.println("No lines to read from the file");
        }
        inputStream.close();
        return fileString;
    }

    /**
     * Function to read in lines from a textfile and store them into an arraylist of activities, customized for this assignment
     * @param inputFileName as the name of the input file
     * @param acts as the arraylist to store the inputs
     * @return the filled arraylist
     */
    public ArrayList<Activity> readFile(String inputFileName, ArrayList<Activity> acts) {
        Scanner inputStream = null;
        String fileString = "";
        String delimiter = "=";
        String timeDelimiter = ", ";
        String myString = "";
        String[] temp = new String[6];
        int numberOfActivities = 0;

        try {
            inputStream = new Scanner(new FileInputStream(inputFileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the input file");
            System.exit(0);
        }

        while (inputStream.hasNextLine() != false) {
            
            myString = inputStream.nextLine();

            StringTokenizer token = new StringTokenizer(myString, delimiter);
            /* if the first keyword is "Type", it adds to numberOfAnimals and create a new animal in the arraylist */
            if (myString.contains("type")) {
                token.nextToken();
                temp[0] = token.nextToken();
                numberOfActivities++;
                

                if (temp[0].equalsIgnoreCase("home") == true) {
                    acts.add(new HomeActivity());
                } else if (temp[0].equalsIgnoreCase("school")) {
                    acts.add(new SchoolActivity());
                } else if (temp[0].equalsIgnoreCase("other")) {
                    acts.add(new OtherActivity());
                } else {
                    System.out.println("Fail to read, unknown activity type.");
                }

            }

            if (myString.contains("title")) {
                token.nextToken();
                if (token.hasMoreTokens() == true) {
                    temp[1] = token.nextToken();
                } else {
                    temp[1] = "anAct";
                }
                /* if the first keyword is "Name", it uses a mutator for that specific type of animal and sets its name accordinly */
                if (temp[0].equalsIgnoreCase("home") == true) {
                    acts.get(numberOfActivities - 1).setTitle(temp[1]);
                } else if (temp[0].equalsIgnoreCase("school") == true) {
                    acts.get(numberOfActivities - 1).setTitle(temp[1]);
                } else if (temp[0].equalsIgnoreCase("other") == true) {
                    acts.get(numberOfActivities - 1).setTitle(temp[1]);
                } else {
                    System.out.println("Fail to read, unknown activity type.");
                }
            }

            if (myString.contains("start")) {
                int year = 0;
                int month = 0;
                int day = 0;
                int dayOfWeek = 0;
                int hour = 0;
                int minute = 0;
                String myTemp = "";
                token.nextToken();
                if (token.hasMoreTokens() == true) {
                    temp[2] = token.nextToken();
                } else {
                    temp[2] = "2012, 9, 8, 0, 7, 30";
                }

				/* break up the time input to their appropriate variables */
                StringTokenizer timeToken = new StringTokenizer(temp[2], timeDelimiter);
                year = Integer.parseInt(timeToken.nextToken());
                month = Integer.parseInt(timeToken.nextToken());
                day = Integer.parseInt(timeToken.nextToken());
                dayOfWeek = Integer.parseInt(timeToken.nextToken());
                hour = Integer.parseInt(timeToken.nextToken());
                minute = Integer.parseInt(timeToken.nextToken());
                
                acts.get(numberOfActivities - 1).setStartingTime(new Time(year, month, day, hour, minute));
            }

            if (myString.contains("end")) {
                int year = 0;
                int month = 0;
                int day = 0;
                int dayOfWeek = 0;
                int hour = 0;
                int minute = 0;
                token.nextToken();
                if (token.hasMoreTokens() == true) {
                    temp[3] = token.nextToken();
                } else {
                    temp[3] = "2012, 9, 8, 0, 8, 30";
                }

                StringTokenizer timeToken = new StringTokenizer(temp[3], timeDelimiter);

                year = Integer.parseInt(timeToken.nextToken());
                month = Integer.parseInt(timeToken.nextToken());
                day = Integer.parseInt(timeToken.nextToken());
                dayOfWeek = Integer.parseInt(timeToken.nextToken());
                hour = Integer.parseInt(timeToken.nextToken());
                minute = Integer.parseInt(timeToken.nextToken());

                acts.get(numberOfActivities - 1).setEndingTime(new Time(year, month, day, hour, minute));
            }

            if (myString.contains("comment")) {
                token.nextToken();
                /* if the first keyword is "FoodEaten", it uses the eat function for that specific type of animal and sets its new weight accordingly
                 * there's the try catch block to stop any non numeric inputs
                 */
                if (token.hasMoreTokens() == true) {
                    temp[4] = token.nextToken();
                } else {
                    temp[4] = "n/a";
                }

                acts.get(numberOfActivities - 1).setComment(temp[4]);
            }

            if (myString.contains("location")) {
                token.nextToken();
                /* if the first keyword is "DaysOfRoaming", it uses the roam function for that specific type of animal and sets its roaming accordingly
                 * there's the try catch block to stop any non numeric inputs
                 */
                if (token.hasMoreTokens() == true) {
                    temp[5] = token.nextToken();

                } else {
                    temp[5] = "n/a";

                }

                acts.get(numberOfActivities - 1).setLocation(temp[5]);
            }
        }
        inputStream.close();
        return acts;
    }

    /**
     * Function to write to an output file, customized for this assignment
     * @param outputFileName as the name of the output file to be written on
     * @param acts as the arraylist containing the data to write
     */
    public void writeFile(String outputFileName, ArrayList<Activity> acts) {
        PrintWriter outputStream = null;
        String[] type = new String[acts.size()];
        try {
            outputStream = new PrintWriter(new FileOutputStream(outputFileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the output file");
            System.exit(0);
        }
        
        for(int j = 0; j < acts.size(); j++){
            if(acts.get(j).getClass().toString().contains("Home")){
                type[j] = new String("home");
            }else if(acts.get(j).getClass().toString().contains("School")){
                type[j] = new String("school");
            }else{
                type[j] = new String("other");
            }
        }
        
        for (int i = 0; i < acts.size(); i++) {
            //outputStream.println(acts.get(i).toString());
            outputStream.println("type=" + type[i]);
            outputStream.println("title=" + acts.get(i).getTitle());
            outputStream.println("start=" + acts.get(i).getStartingTime());
            outputStream.println("end=" + acts.get(i).getEndingTime());
            if(type[i].equalsIgnoreCase("other")){
                outputStream.println("location=" + acts.get(i).getLocation());
            }
            outputStream.println("comment=" + acts.get(i).getComment());
            outputStream.println();
        }
        outputStream.close();
    }

    /**
     * Function to append to output file
     * @param outputFileName as the name of the output file to be written on to
     * @param appendString as the string to append
     */
    static public void appendToFile(String outputFileName, String appendString) {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream(outputFileName, true));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the output file");
            System.exit(0);
        }

        outputStream.print(appendString + "\n");
        outputStream.close();
    }
}
