/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Salem Tesfu
 *  Date          :  2019-01-31
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 */

public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;
	
  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      if(year % 4 == 0 && (year % 400 == 0 || year % 100 != 0)){
		   return true; 
	  }else{
		   return false;   
	   }
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
	   if (month == 2 && isLeapYear(year) == true){
		   return 29;
	   }else{
		   return days[(int) month - 1];
	   }
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
	  if (year1 != year2 || month1 != month2 || day1 != day2){
		  return false; 
	  }else{
		  return true;
	  }
     
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if (year1 < year2 || (year1 == year2 && month1 < month2) || (year1 == year2 && month1 == month2 && day1 < day2)){
		  return -1;
	  }else if(dateEquals( month1, day1,  year1,  month2,  day2,  year2 ) == true){
		  return 0;
	  }else{
		  return 1;
	  }
	   
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      if(month > 12 || month < 1 || day < 1 || ( (long) daysInMonth(month, year)) < day){
		  return false;
	  }else{
		  return true; 
	  }
	   
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) { 
	  String monthName = "";
      switch( month - 1 ) {
		  case 0 : monthName = "January"; break;
		  case 1 : monthName = "February"; break;
		  case 2 : monthName = "March"; break;
		  case 3 : monthName = "April"; break;
		  case 4 : monthName = "May"; break;
		  case 5 : monthName = "June"; break;
		  case 6 : monthName = "July"; break;
	      case 7 : monthName = "August"; break;
		  case 8 : monthName = "September"; break;
		  case 9 : monthName = "October"; break;
		  case 10 : monthName = "November"; break;
		  case 11 : monthName = "December"; break;
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
	   return monthName;
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
       String dayName = "";
	   switch( day - 1 ) {
		  case 0 : dayName = "Sunday"; break; 
		  case 1 : dayName = "Monday"; break; 
		  case 2 : dayName = "Tuesday"; break; 
		  case 3 : dayName = "Wednesday"; break;
		  case 4 : dayName = "Thursday"; break;
		  case 5 : dayName = "Friday"; break;
		  case 6 : dayName = "Saturday"; break; 
         default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
	   return dayName; 
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      long dayCount = 0;
	  long daysBetweenYears = 0;
	  long daysBetweenMonths = 0;
	  long largerYear = year2;
	  long smallerYear = year1;
	  long largerMonth = month2;
	  long smallerMonth = month1; 
	  long leapYearCount = 0;
	  long daysDifference = Math.abs(day2 - day1);
	   
	   if(year2 < year1){
		   largerYear = year1;
		   smallerYear = year2;
	   }
	   
	   if(month2 < month1){
		  largerMonth = month1;
	      smallerMonth = month2;
	   }
	   
	   for(long i = smallerYear; i < largerYear + 1; i++ ){
		   if(isLeapYear(i) == true){ //need to add the condition that specifies bounds for the leap year date 2/29/ly to 12/31/ly
			   leapYearCount += 1;
		   }
	   }
	   
	   
	   if(year1 == year2){
		   daysBetweenYears = 0; 
	   }else{
		   daysBetweenYears = ((365 * (largerYear - smallerYear)) + leapYearCount); 
	   }
	   
	   for(int i = ((int) smallerMonth - 1); i < ((int) largerMonth - 1); i++){
		   daysBetweenMonths += ((long) days[i]);
	   }
	   
	   if (month1 == month2){
		   daysBetweenMonths = 0;
	   }
	   
	   if((day1 > day2 && month1 == month2) || (day1 > day2 && month1 < month2) ){ 
		   daysBetweenMonths = daysBetweenMonths - daysDifference;
	   }else{
		   daysBetweenMonths += daysDifference;
	   }
	   
	   
	   if( month2 != month1){ //largerMonth == month2 &&
		   dayCount = Math.abs(daysBetweenYears - daysBetweenMonths);
	   }
	   
	   	else if(month1 == month2 && year1 == year2){
		   dayCount = daysDifference;
	   }
	   
	   else if(month1 == month2){
		   dayCount = daysBetweenYears + daysBetweenMonths;
	   }
	   

						  
      return dayCount;
   }

}
