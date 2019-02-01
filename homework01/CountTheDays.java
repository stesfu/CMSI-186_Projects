public class CountTheDays{
	public static void main (String [] args ){
		
		System.out.println("\n Want to know how manys days are between two dates? Input your days in the following format: MM DD YYYY MM DD YYYY \n ");
	try {
		long  day1 = Long.parseLong( args[1] );
		long  day2 = Long.parseLong( args[4] );
		long  month1 = Long.parseLong( args[0] );
		long  month2 = Long.parseLong( args[3] );
		long  year1 = Long.parseLong( args[2] );
		long  year2 = Long.parseLong( args[5] );
		
		if (CalendarStuff.isValidDate(month1, day1, year1) == true && CalendarStuff.isValidDate(month2, day2, year2) == true){
			System.out.println("\n There are " + CalendarStuff.daysBetween( month1, day1, year1, month2, day2, year2 ) + " days in between your dates \n");
		}else {
			System.out.println("\n Invalid input, please try again \n");
		}
	}
	catch( Exception e ) { System.out.println( "Invalid input" ); }
	
		
	}
	
}
