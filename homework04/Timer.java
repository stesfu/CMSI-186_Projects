import java.text.DecimalFormat;

public class Timer {
    double timeSlice = 1.0; 
    double elapsedTimeSeconds = 0.0; 
    double hours = 0.0;
    double seconds = 0.0;
    double minutes = 0.0;

    public Timer(){

    }

    public double validateTimeSliceArg(String argValue) {
        Double timeSliceInput = Double.parseDouble(argValue);
        timeSlice = timeSliceInput;
        if (timeSlice <= 0 || timeSlice > 1800) { //clock solver
         timeSlice = -1.0;
         //		     return -1.0;
         //          throw new NumberFormatException("Your angle is out of range");
        }
        return timeSlice;
       }

    public double tick(){
        elapsedTimeSeconds += timeSlice;
        return elapsedTimeSeconds;
    }

    public void stop(){
        //add in ball class
    }

    public String toString(){
        DecimalFormat secFormatter = new DecimalFormat("00.00");
        DecimalFormat hourFormatter = new DecimalFormat("00");
        DecimalFormat minFormatter = new DecimalFormat("00");

        double roughHours = elapsedTimeSeconds / 3600;
        hours = Math.floor(roughHours);
 
        double roughMinutes = ((roughHours - hours) * 3600) / 60;
        minutes = Math.floor(roughMinutes);
        seconds = ((roughMinutes - minutes) * 60);
  
        if(hours == 0){
            hours = 12;
        }

        String strHours = String.valueOf(hourFormatter.format(hours));
        String strMin = String.valueOf(minFormatter.format(minutes));
        String strSec = String.valueOf(secFormatter.format(seconds));
        return strHours + ":" + strMin + ":" + strSec;

    }


}
