import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

   public static void main( String args[] ) {
	  
	  int gameCount = Integer.parseInt( args[0] );
	  int gameSides = Integer.parseInt( args[1] );
	  int highScore = 0;
	   
      DiceSet gameSet = new DiceSet(gameCount, gameSides);
	   
      System.out.println("\n Welcome to the the game of HighRoll. Follow the menu to play!\n [1] ROLL ALL THE DIE \n [2] ROLL A SINGLE DIE \n [3] CALCULATE THE SCORE FOR THIS SET \n [4] SAVE THIS SCORE AS A HIGH SCHORE \n [5] DISPLAY THE HIGH SCORE \n [6] ENTER 'Q' TO QUIT THE PROGRAM");

      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         try {
            inputLine = input.readLine();
            if( 0 == inputLine.length() ) {
               System.out.println("enter some text!:");
            }
			 if('1' == inputLine.charAt(0) ){
				 gameSet.roll();
		         System.out.println( "The dice are rolled! \n" + gameSet.toString());
				 
	         }if('2' == inputLine.charAt(0) ){
				 System.out.println( "Enter the a number for the index of the die you want to roll"); //fix
		   
	         }if('3' == inputLine.charAt(0) ){
				 System.out.println("Your score is " + gameSet.sum());
		   
	         }if('4' == inputLine.charAt(0) ){
				 highScore = gameSet.sum();
				 System.out.println("Your high score is saved!");
		   
	         }if('5' == inputLine.charAt(0) ){
				 System.out.println("Your current high score is " + highScore);
		   
	        }if( 'q' == inputLine.charAt(0) ) {
               break;
            }
//			 else{
//				System.out.println("Invalid input. Please type in a number 1-5 to play or 'Q' to quit"); 
//			 }        
		 }
         catch( IOException ioe ) {
            System.out.println( "Invalid input! Although you may have wanted to play the game, you played yourself." );
         }
      }
	
}


}
