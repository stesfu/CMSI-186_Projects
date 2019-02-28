/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson (protype)
 *  Date written  :  2017-02-28 (prototype)
 *  @author       :  Salem Tesfu
 *  Date written  :  2019-02-14
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  Got DecimalFormat information from https://examples.javacodegeeks.com
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Clock {
 /**
  *  Class field definintions go here
  */

 private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
 private static final double INVALID_ARGUMENT_VALUE = -1.0;
 private static final double MAXIMUM_DEGREE_VALUE = 360.0;
 private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
 private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
 public static double elapsedTimeSeconds = 0.0;
 public static double angle = 0.0;
 public static double timeSlice = 60.0;
 public static double hourHandAngle = 0.0;
 public static double minuteHandAngle = 0.0;
 public static double handAngle = 0.0;
 public static double hours = 0.0;
 public static double minutes = 0.0;
 public static double seconds = 0.0;


 /**
  *  Constructor goes here
  */
 public Clock() {
  // this.timeSlice = timeSlice;
  // this.hours = hours;
  // this.minutes = minutes;
  // this.seconds = seconds;
 }

 /**
  *  Methods go here
  *
  *  Method to calculate the next tick from the time increment
  *  @return double-precision value of the current clock tick
  */
 public double tick() {
  elapsedTimeSeconds += timeSlice;
  getHourHandAngle();
  getMinuteHandAngle();
  getHandAngle();
  return elapsedTimeSeconds;
 }

 /**
  *  Method to validate the angle argument
  *  @param   argValue  String from the main programs args[0] input
  *  @return  double-precision value of the argument
  *  @throws  NumberFormatException
  */
 public static double validateAngleArg(String argValue) throws NumberFormatException {
  angle = Double.parseDouble(argValue);
  if (angle < 0 || angle >= MAXIMUM_DEGREE_VALUE) {
   angle = -1.0;
   //          throw new NumberFormatException("Your angle is out of range");
  }
  return angle;
 }

 /**
  *  Method to validate the optional time slice argument
  *  @param  argValue  String from the main programs args[1] input
  *  @return double-precision value of the argument or -1.0 if invalid
  *  note: if the main program determines there IS no optional argument supplied,
  *         I have elected to have it substitute the string "60.0" and call this
  *         method anyhow.  That makes the main program code more uniform, but
  *         this is a DESIGN DECISION, not a requirement!
  *  note: remember that the time slice, if it is small will cause the simulation
  *         to take a VERY LONG TIME to complete!
  */
 public static double validateTimeSliceArg(String argValue) {
  Double timeSliceInput = Double.parseDouble(argValue);
  timeSlice = timeSliceInput;
  if (timeSlice <= 0 || timeSlice > 1800) {
   timeSlice = -1.0;
   //		     return -1.0;
   //           throw new NumberFormatException("Your angle is out of range");
  }
  return timeSlice;
 }

 /**
  *  Method to calculate and return the current position of the hour hand
  *  @return double-precision value of the hour hand location
  */
 public double getHourHandAngle() {
  hourHandAngle = HOUR_HAND_DEGREES_PER_SECOND * elapsedTimeSeconds;
  return hourHandAngle;
 }

 /**
  *  Method to calculate and return the current position of the minute hand
  *  @return double-precision value of the minute hand location
  */
 public double getMinuteHandAngle() {
  minuteHandAngle = (MINUTE_HAND_DEGREES_PER_SECOND * elapsedTimeSeconds) % 360;
  return minuteHandAngle;
 }

 /**
  *  Method to calculate and return the angle between the hands
  *  @return double-precision value of the angle between the two hands
  */
 public double getHandAngle() {
  handAngle = Math.abs(hourHandAngle - minuteHandAngle);
  return handAngle;
 }

 /**
  *  Method to fetch the total number of seconds
  *   we can use this to tell when 12 hours have elapsed
  *  @return double-precision value the total seconds private variable
  */
 public double getTotalSeconds() {
  return elapsedTimeSeconds;
 }

 /**
  *  Method to return a String representation of this clock
  *  @return String value of the current clock
  */
 public String toString() {
  DecimalFormat secFormatter = new DecimalFormat("00.00");
  DecimalFormat hourFormatter = new DecimalFormat("00");
  DecimalFormat minFormatter = new DecimalFormat("00");

  double roughHours = elapsedTimeSeconds / 3600;
  hours = Math.floor(roughHours);
  if(angle == 0 && hours == 0){
   hours = 12;
  }
  double roughMinutes = ((roughHours - hours) * 3600) / 60;
  minutes = Math.floor(roughMinutes);
  seconds = ((roughMinutes - minutes) * 60);

  String strHours = String.valueOf(hourFormatter.format(hours));
  String strMin = String.valueOf(minFormatter.format(minutes));
  String strSec = String.valueOf(secFormatter.format(seconds));
  return strHours + ":" + strMin + ":" + strSec;
 }

 /**
  *  The main program starts here
  *  remember the constraints from the project description
  *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
  *  be sure to make LOTS of tests!!
  *  remember you are trying to BREAK your code, not just prove it works!
  */
 public static void main(String args[]) {

  System.out.println("\nCLOCK CLASS TESTER PROGRAM\n" +
   "--------------------------\n");
  System.out.println("  Creating a new clock: ");
  Clock clock = new Clock();
  System.out.println("    New clock created: " + clock.toString());
  try {
   System.out.println("    Testing validateAngleArg()... ");
   System.out.println((-1.0 == clock.validateAngleArg("-250")) ? " Pass " : " Fail ");
   System.out.println((-1.0 == clock.validateAngleArg("-0.1111111")) ? " Pass " : " Fail ");
   System.out.println((-1.0 == clock.validateAngleArg("361.0")) ? " Pass " : " Fail ");
   System.out.println((359.0 == clock.validateAngleArg("359.0")) ? " Pass " : " Fail ");
   System.out.println((1.0 == clock.validateAngleArg("1.0")) ? " Pass " : " Fail ");
   System.out.println((45.0 == clock.validateAngleArg("45.0")) ? " Pass " : " Fail ");
   System.out.println("    Testing validateTimeSliceArg()....");
   System.out.println((-1.0 == clock.validateTimeSliceArg("0.0")) ? " Pass " : " Fail ");
   System.out.println((-1.0 == clock.validateTimeSliceArg("-60.0")) ? " Pass " : " Fail ");
   System.out.println((-1.0 == clock.validateTimeSliceArg("1801")) ? " Pass " : " Fail ");
   System.out.println((1800 == clock.validateTimeSliceArg("1800")) ? " Pass " : " Fail ");
   System.out.println((60.0 == clock.validateTimeSliceArg("60.0")) ? " Pass " : " Fail ");
   System.out.println((.001 == clock.validateTimeSliceArg(".001")) ? " Pass " : " Fail ");
  } catch (Exception e) {
   System.out.println(" - Exception thrown: " + e.toString());
  }
 }
}
