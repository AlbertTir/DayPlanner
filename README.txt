****************************************************
Albert Tirtariyadi		0721345
CIS2430		Assignment 4 - Dayplanner GUI
December 2, 2012
****************************************************

Assignment 4 - a continuation of the dayplanner program with GUI implementation

************
Compilation
************
I am sending in the entire project file, so there are 2 ways to compile this.

Method 1: Just open netbeans and run it that way

Method 2: use the command line/terminal
1. After unzipping, cd to root directory
2. cd src
3. type javac dayplannera4/* to compile everything in the folder

***********************
Running the program(s)
***********************
Method 1: Using netbeans, the arguments can be set through Run Options, I provided dayplanOutput.txt here

Method 2: using command line/terminal
1. After unzipping and compiling, still in the src directory, type java dayplannera4.DayPlannerA4 <inputfilename> <outputfilename>
2. There are multiple arguments that can be made, either no arguments and no file are specified, 1 file, or 2 files
3. for no files, it will follow the auto-default path and create its own dayplanOutput.txt
4. for 1 argument file, it will use that as input and output, anything added will be added to the same input file
5. takes in an input, and whatever activities are added plus the read-in one will be written onto another copy of the output


*****************
Known Limitations
*****************
1. The search feature using the hash map is not working. I still don't understand the way hash mapping works in java, so regardless of the type of activity being searched, it will just display everything. So only keyword and time searching works
2. The time input defensive programming I did was to check for 4 commas, otherwise it will just take it in and more than likely error. I'm not sure how robust this is, obviously it will not be that great since someone could just type in 4 commas and nothing else and it will break the program; but for most cases it should handle fairly well.
3. Turning the location field invisible while home/school was selected was not doable in a neat way as far as I know. So I just disabled the textfield if anything other than Other activity was chosen. It's still a legitimate method, and this way it's much neater also.
4. Exception handling may be a bit weak in this program, though I used some other forms of defensive programming.






