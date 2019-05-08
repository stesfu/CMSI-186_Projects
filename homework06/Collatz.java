/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Collatz.java
 * Purpose    :  Takes in a large brobint and generate its collatz sequence
 * @author    :  Salem Tesfu 
 * Date       :  2019-04-12
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Collatz{

    BrobInt bint;
    String internalValue;
    String result = "";

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Constructor allows for typed in arguments to be used as BrobInts
   *  @param  String array taken in from arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public Collatz(String[] args){
            this.bint = new BrobInt( args[0] );
            this.internalValue = args[0];
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method for validating the numbers in argument.
   *  @param   String values from argument 
   *  @throws  IllegalArgumentException if there are no arguments or multiple numbers are typed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public boolean validateArgs(String[] args){
        boolean valid = true;
        for (int i = 0; i < args[0].length() - 1; i++) {
            if (args.length > 1) {
                valid = false;
                throw new IllegalArgumentException("Can implement one number");
            }
            if (args.length == 0) {
                valid = true;
                throw new IllegalArgumentException("Must type in a number to function");
            }
            if ((i != 0) && (!Character.isDigit( args[0].charAt(i)))){
                valid = true;
                throw new IllegalArgumentException("Non number character exists besides indicating sign");
            }
        }
        return valid;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method that takes a BrobInt and returns its collatz sequence 
   *  @param none
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public void sequence(){
        int steps = 0;
        int parityNum=0;
        BrobInt answer = new BrobInt(this.bint.internalValue);
        parityNum = Integer.parseInt(answer.toString().substring(answer.toString().length() - 2, answer.toString().length() - 1));
        System.out.println( "\n Hello, world, from the Collatz program!!\n");
        System.out.println("\n ==================================");
    
        result += this.bint.toString() + " \n";
    
        while (!answer.equals(BrobInt.ONE)){ 
        	
            if ((parityNum %2 != 0)){
            	answer = answer.multiply(BrobInt.THREE).add(BrobInt.ONE);
                steps++;
                result += answer.toString() + " \n";
                
            }else{
            	answer = answer.divide(BrobInt.TWO);
                steps++;
                result += answer.toString() + " \n";
        } 
        parityNum = Integer.valueOf(answer.toString());
    }



        System.out.println( "\n The string sequence is:       \n\n " + result);
        System.out.println(steps + " steps for sequence to converge.\n");
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * Main creates instace of collatz in order to calucalte desired collatz sequence
   *  @param  args  String array which contains BrobInt to calculate
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public static void main (String[] args) {
        Collatz collatzSim = new Collatz(args);
        collatzSim.validateArgs(args);
        collatzSim.sequence();

    }
}