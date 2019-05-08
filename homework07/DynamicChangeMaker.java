// // ** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// //  * File name  :  DynamicChangeMaker.java
// //  * Purpose    :  Takes in a large brobint and generate its collatz sequence
// //  * @author    :  Salem Tesfu 
// //  * Date       :  2019-04-12
// //  *
// //  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class DynamicChangeMaker {
    static int [] denoms = null;
    static String [] denomsArray = null;
    static int target = 0;
    static int rows = 0;
    static int colums = 0;
    static Tuple[][] table = new Tuple[rows][colums];



/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid denominations
   *  @param String[] takes in and validates the inputs for coin values and the target number as well
   *  @throws  IllegalArgumentException if improper imput
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public  static void validateArgs(String args[]){
    denomsArray = args[0].split(",");
    denoms = new int[denomsArray.length];
    target = Integer.parseInt(args[1]);

    try{
        for(int i = 0; i < denomsArray.length; i++){
            denoms[i] = Integer.parseInt(denomsArray[i]);
        }

        if (args.length != 2 || target <= 0){
            throw new Exception(); 
        }

        for(int i = 0; i < denoms.length; i++){
            if (denoms[i] <= 0){
                throw new Exception();
            }
            for(int j = i + 1; j < denoms.length; j++){
                if(denoms[i] == denoms[j]){
                    throw new Exception();
                }

            }
        }
    }
    catch( Exception e){
        System.out.println("BAD DATA! Must input 2 arguments. Must be non-repeating, non-negative integers");
    }

}

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to calculate the optimal number of coins for the target value
   *  @param   int[]  int array of the denomination
   *  @param   int  target value of coins
   *  NOTE: may be changed to int[] or some other type based on requirements in code above
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public static Tuple makeChangeWithDynamicProgramming (int[] denom, int target){
    colums = target + 1;
    rows = denom.length; 
    table = new Tuple[rows][colums];
    Tuple answer = Tuple.IMPOSSIBLE;
    //table[row][column - currentDom]
    //table[row - 1][column] when not in zero



    return answer;
}


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public static void main (String args[]){
    DynamicChangeMaker.validateArgs(args); 

}

}