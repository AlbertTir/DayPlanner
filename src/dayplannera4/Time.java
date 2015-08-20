/*
 * Albert Tirtariyadi
 * 0721345
 * CIS2430
 * Assignment 4 - Dayplanner with Inheritance, hashmaps and file IO with GUI implementation
 */
package dayplannera4;

/**
 * @author albert710
 */
public class Time {
    private int year;
    private int month;
    private int dayOfMonth;
    private int dayOfWeek;
    private int hour;
    private int minute;
    
    /**
     * Default constructor with specified values
     */
    public Time(){
        this.year = 2012;
        this.month = 6;
        this.dayOfMonth = 6;
        this.dayOfWeek = 6;
        this.hour = 6;
        this.minute = 36;
    }
    
    /**
     * Constructor that takes in inputs and assigns them onto the new Time object
     * @param otherYear as the input year
     * @param otherMonth as the input month
     * @param otherDayOfMonth as the input day of the month
     * @param otherHour as the input hour
     * @param otherMinute as the input minute
     */
    public Time(int otherYear, int otherMonth, int otherDayOfMonth, int otherHour, int otherMinute){
        this(otherYear, otherMonth, otherDayOfMonth, 0, otherHour, otherMinute); /* if no dayOfWeek is provided, just default it to 0 */
    }

    /**
     * Constructor that takes in all the possible inputs and assigns them onto the new Time object
     * @param otherYear as the input year
     * @param otherMonth as the input month
     * @param otherDayOfMonth as the input day of the month
     * @param otherDayOfWeek as the input day of the week
     * @param otherHour as the input hour
     * @param otherMinute as the input minute
     */
    public Time(int otherYear, int otherMonth, int otherDayOfMonth, int otherDayOfWeek, int otherHour, int otherMinute) {
        if (timeOK(otherYear, otherMonth, otherDayOfMonth, otherDayOfWeek, otherHour, otherMinute)) {
            this.year = otherYear;
            this.month = otherMonth;
            this.dayOfMonth = otherDayOfMonth;
            this.dayOfWeek = otherDayOfWeek;
            this.hour = otherHour;
            this.minute = otherMinute;
        } else {
            DayPlannerA4.resultsArea.setText("Fatal error, invalid time input");
            System.exit(0);
        }
    }

    /**
     * Constructor that takes in another Time object and stores its values onto this instance
     * @param otherTime as the input Time object
     */
    public Time(Time otherTime) {
        if (otherTime == null) {
            DayPlannerA4.resultsArea.setText("Fatal error, invalid time input");
            System.exit(0);
        } else {
            this.year = otherTime.year;
            this.month = otherTime.month;
            this.dayOfMonth = otherTime.dayOfMonth;
            this.dayOfWeek = otherTime.dayOfWeek;
            this.hour = otherTime.hour;
            this.minute = otherTime.minute;
        }
    }

    /**
     * Overriding toString() function for this Time class
     * @return a string with the formatted time
     */
    public String toString() {
        return (this.year + ", " + this.month + ", " + this.dayOfMonth + ", " + this.dayOfWeek + ", " + this.hour + ", " + this.minute);
    }

    /**
     * Overriding equals() method to compare if two time objects have the same values
     * @param otherTime as the object to be compared to
     * @return true if the two objects matches
     */
    public boolean equals(Time otherTime) {
        if (otherTime == null) {
            return false;
        } else {
            return this.year == otherTime.year
                    && this.month == otherTime.month
                    && this.dayOfMonth == otherTime.dayOfMonth
                    && this.dayOfWeek == otherTime.dayOfWeek
                    && this.hour == otherTime.hour
                    && this.minute == otherTime.minute;
        }
    }

    /**
     * Method to compare two Time objects and see which one is greater, lesser or equals to
     * @param other as the other Time object
     * @return 1 if greater, -1 if smaller, 0 if equals
     */
    public int compareTo(Time other) {
        if (year < other.year) {
            return -1;
        } else if (year > other.year) {
            return 1;
        } else if (month < other.month) {
            return -1;
        } else if (month > other.month) {
            return 1;
        } else if (dayOfMonth < other.dayOfMonth) {
            return -1;
        } else if (dayOfMonth > other.dayOfMonth) {
            return 1;
        } else if (hour < other.hour) {
            return -1;
        } else if (hour > other.hour) {
            return 1;
        } else if (minute < other.minute) {
            return -1;
        } else if (minute > other.minute) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Method to check if the time is valid in terms of years, month, day, minute, hour, etc
     * @param year as the year to check
     * @param month as the month to check
     * @param day as the day of month to check
     * @param dayOfWeek as the day of week to check
     * @param hour as the hour to check
     * @param minute as the minute to check
     * @return true if all are OK, false otherwise
     */
    static public boolean timeOK(int year, int month, int day, int dayOfWeek, int hour, int minute) {
        return (year >= 1000 && year <= 9999)
                && (month > 0 && month <= 12)
                && (day > 0 && day <= 31)
                && (dayOfWeek >= 0 && dayOfWeek <= 7)
                && (hour >= 0 && hour < 24)
                && (minute >= 0 && minute < 60);
    }

    /**
     * Method to check if time is OK without the day of the week
     * @param year as the year to check
     * @param month as the month to check
     * @param day as the day to check
     * @param hour as the hour to check
     * @param minute as the minute to check
     * @return true if all OK, false otherwise
     */
    static public boolean timeOK(int year, int month, int day, int hour, int minute) {
        return timeOK(year, month, day, 0, hour, minute);
    }
    
    /**
     * Accessor method for year
     * @return this object's year
     */
    public int getYear(){
        return this.year;
    }
    
    /**
     * Accessor method for month
     * @return this object's month
     */
    public int getMonth(){
        return this.month;
    }
    
    /**
     * Accessor method for day of month
     * @return this object's day of month
     */
    public int getDayOfMonth(){
        return this.dayOfMonth;
    }
    
    /**
     * Accessor method for day of week
     * @return this object's day of week
     */
    public int getDayOfWeek(){
        return this.dayOfWeek;
    }
    
    /**
     * Accessor method for hour
     * @return this object's hour
     */
    public int getHour(){
        return this.hour;
    }
    
    /**
     * Accessor method for minute
     * @return this object's minute
     */
    public int getMinute(){
        return this.minute;
    }
    
    /**
     * Mutator method for year
     * @param otherYear as the new year
     */
    public void setYear(int otherYear){
        this.year = otherYear;
    }
    
    /**
     * Mutator method for month
     * @param otherMonth as the new month
     */
    public void setMonth(int otherMonth){
        this.month = otherMonth;
    }
    
    /**
     * Mutator method for day of month
     * @param otherDayOfMonth as the new day of month
     */
    public void setDayOfMonth(int otherDayOfMonth){
        this.dayOfMonth = otherDayOfMonth;
    }
    
    /**
     * Mutator method for day of week
     * @param otherDayOfWeek as the new day of week
     */
    public void setDayOfWeek(int otherDayOfWeek){
        this.dayOfWeek = otherDayOfWeek;
    }
    
    /**
     * Mutator method for hour
     * @param otherHour as the new hour
     */
    public void setHour(int otherHour){
        this.hour = otherHour;
    }
    
    /**
     * Mutator method for minute
     * @param otherMinute as the new minute
     */
    public void setMinute(int otherMinute){
        this.minute = otherMinute;
    }
}
