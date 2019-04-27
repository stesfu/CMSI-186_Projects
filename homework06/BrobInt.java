/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson (Prototype)
 * Date       :  2017-04-04
 * @author    :  Salem Tesfu 
 * Date       :  2019-04-05
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   public  String internalValue = "";        // internal String representation of this BrobInt
   public  byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   int chunks = 0;
   int start = 0;
   int stop = 0;
   int[] chunksArr;
   DecimalFormat df = new DecimalFormat("000000000");

   private static BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
   private static final boolean DEBUG_ON = false;
   private static final boolean INFO_ON  = false;

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
  public BrobInt( String value ) {
   this.internalValue = value;
   if (value.charAt(0) == '-') {
     this.sign = 1;
     //value.charAt(0) = "0";
     //internalValue = internalValue.substring(1, internalValue.length() );
   }
   this.chunks = (this.internalValue.length() / 9); 
   if(value.length() % 9 != 0){
      this.chunks++;
   }
   if (this.sign == 1) {
     value = value.substring(1);
   }
   this.chunksArr = new int[this.chunks];
   this.stop = internalValue.length();
   this.start = this.stop - 9;
   if (this.chunks == 1) {
     this.start = 0;
   }

   for (int i = 0; i < chunks; i++) {
     this.chunksArr[i] = Integer.parseInt(internalValue.substring(this.start, this.stop));
     this.stop -= 9;
     if(i == this.chunksArr.length - 2) {
       this.start = 0;
     } else {
       this.start -= 9;
     }
     if( DEBUG_ON) { 
        System.out.println( "...start: " + this.start + ", stop: " + this.stop ); 
      }
   }

   if( DEBUG_ON) { 
      System.out.println("count: " + value.length() + ", numchunks: "+ this.chunks ); 
   }
}
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateArgs(){
      boolean valid = true;
      for (int i = 0; i < this.chunksArr.length; i++){
         for (int j = 0; j < 9; j++) {
            if (internalValue.length() == 0) {
               valid = false;
               throw new IllegalArgumentException("Input a number");
            }else if ((i != 0) && (!Character.isDigit( internalValue.charAt(i)))){
               valid = false;
               throw new IllegalArgumentException( "Number not valid" );
            }else if (i != this.chunksArr.length - 1) {
               if (j != 0 && (this.internalValue.charAt(j) == '-' || this.internalValue.charAt(j) == '+' )) {
                  valid = false;
                  throw new IllegalArgumentException( "Numbers not valid" );
               }
            }
         }
      }
      return valid;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  bint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt bint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobInt passed as argument to this BrobInt using byte array
   *  @param  bint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt bint ) {
      int carry = 0; 
      String brobStr = "";
      DecimalFormat df = new DecimalFormat("000000000");

      int [] addedBrobs = new int[Math.max(this.chunksArr.length,bint.chunksArr.length) + 1];
     
      int j = 0;
      

      for(int i=0; i < (Math.min(this.chunksArr.length,bint.chunksArr.length)); i++){
         addedBrobs[i] = this.chunksArr[i] + bint.chunksArr[i] + carry;
         if( addedBrobs[i] > 999999999){
             addedBrobs[i] -= 1000000000;
            carry = 1;
         }else{
            carry = 0;
         }
         j++; 
      }

      for(int i = j; i < Math.max(this.chunksArr.length,bint.chunksArr.length); i++){
         if(this.chunksArr.length > bint.chunksArr.length){
            addedBrobs[i] = this.chunksArr[i] + carry;
            if( addedBrobs[i] > 999999999){
                addedBrobs[i] -= 1000000000;
               carry = 1;
            }else{
               carry = 0;
            }
         }

         if(this.chunksArr.length < bint.chunksArr.length){
            addedBrobs[i] = bint.chunksArr[i] + carry;
            if( addedBrobs[i] > 999999999){
                addedBrobs[i] -= 1000000000;
               carry = 1;
            }else{
               carry = 0;
            }
            
         }
      }

      for(int i= addedBrobs.length -1; i >= 0; i--){ 
         if(i < addedBrobs.length - 2){
            brobStr += df.format((double)addedBrobs[i]);
         }else{
            brobStr += addedBrobs[i];
         }
        
      }

      for(int i = 0; i < brobStr.length(); i++){
         if(i != 0 && brobStr.charAt(i) == '-'){
            //System.out.println(brobStr);
            brobStr = brobStr.substring(i, brobStr.length());
         }
      }

      if(sign == 1 && brobStr.charAt(0) != '-'){
         brobStr = "-" + brobStr;
      } 


      BrobInt finalAdd = new BrobInt(brobStr);

      return finalAdd.removeLeadingZeros(finalAdd);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
   *  @param  bint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt bint ) {
      

      boolean bothPositive;
      boolean bothNegative; 
      boolean differentSigns;
      boolean specialCondition = false;
      boolean negativeResult = false;
      int max = Math.max(this.chunksArr.length, bint.chunksArr.length);
      int min = Math.min(this.chunksArr.length, bint.chunksArr.length);
      String brobStr = ""; 
      int k = 0;
      BrobInt specialSub = new BrobInt("0");
      int[] longArray;
      int[] shortArray;
      int[] result = new int[ max + 2 ];


      if(this.sign == 0 && bint.sign == 0){
         bothPositive = true;
         bothNegative = false;
      }else{
         bothPositive = false; 
         bothNegative = true;
      }

      if(this.sign != bint.sign){
         differentSigns = true;
      }else{
         differentSigns = false; 
      }

      if ((bothPositive) && (this.compareTo(bint) < 0)) {
         negativeResult = true;
      } 
      else if ((differentSigns) && (this.compareTo(bint) > 0)) {
         String becomeAdd = bint.internalValue.substring(1, bint.internalValue.length());
         BrobInt posBrobInt = new BrobInt(becomeAdd);
         specialCondition = true;
         negativeResult = false;
         specialSub = new BrobInt(String.valueOf(this.add(posBrobInt)));
  
      }
      else if ((differentSigns) && (this.compareTo(bint) < 0)) {
         String biggerNeg = this.internalValue.substring(1, this.internalValue.length());
         BrobInt negBrobInt = new BrobInt(biggerNeg);
         String negAnswer = "-" + bint.add(negBrobInt).toString();
         specialCondition = true;
         specialSub = new BrobInt(negAnswer);
      }
      else if ((bothNegative) && (this.compareTo(bint) > 0)) { //allows test 37 to work 
         negativeResult = true;
      }

      longArray = new int[max];
      shortArray = new int[min];

      if (this.internalValue.length() > bint.internalValue.length()) {
         for (int i = 0; i < max; i++) {
            longArray[i] = this.chunksArr[i];
         }
         for (int j = 0; j < min; j++) {
            shortArray[j] = bint.chunksArr[j];
         }
      }
      else {
         for (int i = 0; i < max; i++) {
            longArray[i] = bint.chunksArr[i];
         }
         for (int j = 0; j < min; j++) {
            shortArray[j] = this.chunksArr[j];
         }
      }
      
      for (int i = 0; i < min; i++) {
         if (longArray[i] < shortArray[i]) {
            if (i != shortArray.length - 1) {
               longArray[i + 1]--;
            }
         }
         result[i] = longArray[i] - shortArray[i];
         k++;
      }

      for(int i= result.length -1; i >= 0; i--){ 
         if(i < result.length - 3){
            brobStr += df.format((double)result[i]);
         }else{
            brobStr += result[i];
         }
      }

      for(int i = 0; i < brobStr.length(); i++){
         if(i != 0 && brobStr.charAt(i) == '-'){
            brobStr = brobStr.substring(i, brobStr.length());
            if(!negativeResult){
               brobStr = brobStr.substring(1, brobStr.length());
            }

         }
      }

      if(negativeResult && (brobStr.charAt(0) != '-')){
         brobStr = "-" + brobStr;
      }

      BrobInt finalSub = new BrobInt (brobStr);

      if (!specialCondition) { 
         finalSub = finalSub.removeLeadingZeros(finalSub);
      }
      else{
         finalSub = specialSub.removeLeadingZeros(specialSub);
      }

      return finalSub;

      
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to multiply this by
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt bint ) {

      
      String brobStr = "";
     
      
      int multSign = 0;
      int[] arrA;
      int[] arrB;
      long[] result;
      long carry = 0;
      int k = 0;
     
   
      if(bint.sign != this.sign){
         multSign = 1; 
      }

      if(this.internalValue.length() > bint.internalValue.length()){
         arrA = new int[this.chunks];
         for(int i = 0; i < this.chunks; i++){
            arrA[i] = this.chunksArr[i];
         }

         arrB = new int[bint.chunks];
         for(int j = 0; j < bint.chunks; j++){
            arrB[j] = bint.chunksArr[j];
         }
      }else{
         arrA = new int[bint.chunks];
         for(int i = 0; i < bint.chunks; i++){
            arrA[i] = bint.chunksArr[i];
         }

         arrB = new int[this.chunks];
         for(int j = 0; j < this.chunks; j++){
            arrB[j] = this.chunksArr[j];
         }
      }

      result = new long [arrA.length + arrB.length + 1]; 
      for (int i = 0; i < result.length; i++){
         result[i] = 0;
      }

      for(int i = 0; i < arrB.length; i++){
         k = i;
         for(int j = 0; j < arrA.length; j++){
            result[k] = ((long) arrA[j] * (long) arrB[i]) + carry;

            if(result[k] > 999999999){
               carry = result[k] / 1000000000;
               result[k] = result[k] % 1000000000;
            }else{
               carry = 0;
            }

            k++;
         }
         if (carry != 0){
            result[k - 1] += carry;
            carry = 0;
         }
    
      }

      for(int i= result.length -1; i >= 0; i--){ 
         
         if(i < result.length - 2){
            brobStr += df.format(result[i]);
         }else{
            brobStr += result[i];
         }
         
      }

      if(multSign == 1){
         brobStr = "-" + brobStr;
      }

      BrobInt finalMult = new BrobInt(brobStr);

      return finalMult.removeLeadingZeros(finalMult);

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  bint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt bint ) {
      //int lengthCount = 0;
      int currentDiv = 0;
      String remainStr = "";
      String extract = "";

      BrobInt quotient = new BrobInt("0");
      BrobInt remainBrob = new BrobInt("0");
     

      if(bint.equals(BrobInt.ZERO)){
         throw new IllegalArgumentException("ERROR!: Cannot divide by zero");
      
      }else if(bint.equals(this)){
         return BrobInt.ONE;

      }else if(this.compareTo(bint) < 0){
         return BrobInt.ZERO;

      }else{
         int lengthCount = bint.internalValue.length();
         //remainStr = this.toString().substring(0, lengthCount); 
         remainBrob = new BrobInt(this.toString().substring(0, lengthCount));
      

      if(this.compareTo(remainBrob) < 0){
         lengthCount++;
         remainBrob = new BrobInt(this.toString().substring(0, lengthCount)); 
      }

      while(lengthCount <= this.toString().length()){
         while(remainBrob.compareTo(bint) > 0){ //flipped operator
            if( allZeroDetect( remainBrob ) ) { //fixes problem when all 0's are twos when divide by 2
               break;
            }
            remainBrob = remainBrob.subtract(bint);
            quotient = quotient.add(BrobInt.ONE);
            //break;
         }

         if(remainBrob.equals(bint)){ //remain fix!!!!
            remainBrob = BrobInt.ZERO;
            quotient = quotient.add(BrobInt.ONE); 
         }

         lengthCount++;
         if(lengthCount > this.toString().length()){ 
            break; 
         }
         
         remainBrob = remainBrob.multiply(BrobInt.TEN);
         quotient = quotient.multiply(BrobInt.TEN);

         remainBrob = remainBrob.add(new BrobInt(this.toString().substring(lengthCount - 1, lengthCount)));
       
      }

      }
      return this.removeLeadingZeros(quotient);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  bint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt bint ) {
      //b1.divide(b2).multiply(b2) take this number and subtract frm b1. Then you get a brob int that
      //is the remainder 
      return this.subtract(this.divide(bint).multiply(bint));
      //throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

 /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  bint  BrobInt to compare to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public int compareTo( BrobInt bint ) {

   // remove any leading zeros because we will compare lengths
    String me  = removeLeadingZeros( this ).toString();
    String arg = removeLeadingZeros( bint ).toString();

   // handle the signs here
    if( 1 == sign && 0 == bint.sign ) {
       return -1;
    } else if( 0 == sign && 1 == bint.sign ) {
       return 1;
    } else if( 0 == sign && 0 == bint.sign ) {
      // the signs are the same at this point ~ both positive
      // check the length and return the appropriate value
      //   1 means this is longer than bint, hence larger positive
      //  -1 means bint is longer than this, hence larger positive
       if( me.length() != arg.length() ) {
          return (me.length() > arg.length()) ? 1 : -1;
       }
    } else {
      // the signs are the same at this point ~ both negative
       if( me.length() != arg.length() ) {
          return (me.length() > arg.length()) ? -1 : 1;
       }
    }

   // otherwise, they are the same length, so compare absolute values
    for( int i = 0; i < me.length(); i++ ) {
       Character a = Character.valueOf( me.charAt(i) );
       Character b = Character.valueOf( arg.charAt(i) );
       if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
          return 1;
       } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
          return (-1);
       }
    }
    return 0;
 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to check if a BrobInt passed as argument is equal to this BrobInt
 *  @param  bint     BrobInt to compare to this
 *  @return boolean  that is true if they are equal and false otherwise
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public boolean equals( BrobInt bint ) {
    return ( (sign == bint.sign) && (this.toString().equals( bint.toString() )) );
 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to return a BrobInt given a long value passed as argument
 *  @param  value    long type number to make into a BrobInt
 *  @return BrobInt  which is the BrobInt representation of the long
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public static BrobInt valueOf( long value ) throws NumberFormatException {
    BrobInt bi = null;
    try { bi = new BrobInt( Long.valueOf( value ).toString() ); }
    catch( NumberFormatException nfe ) { throw new NumberFormatException( "\n  Sorry, the value must be numeric of type long." ); }
    return bi;
 }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      // if (sign == 1){
      //    return "-" + internalValue;
      //    //return internalValue;
      // }
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to remove leading zeros from a BrobInt passed as argument
   *  @param  bint     BrobInt to remove zeros from
   *  @return BrobInt that is the argument BrobInt with leading zeros removed
   *  Note that the sign is preserved if it exists
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt removeLeadingZeros( BrobInt bint ) {
      Character sign = null;
      String returnString = bint.toString();
      int index = 0;

      if( allZeroDetect( bint ) ) {
         return bint;
      }

      if( ('-' == returnString.charAt( index )) || ('+' == returnString.charAt( index )) ) {
         sign = returnString.charAt( index );
         index++;
      }
      if( returnString.charAt( index ) != '0' ) {
         return bint;
      }

      while( returnString.charAt( index ) == '0' ) {
         index++;
      }
      returnString = bint.toString().substring( index, bint.toString().length() );
      if( sign != null ) {
         returnString = sign.toString() + returnString;
      }
      return new BrobInt( returnString );

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a boolean if a BrobInt is all zeros
   *  @param  bint     BrobInt to compare to this
   *  @return boolean  that is true if the BrobInt passed is all zeros, false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean allZeroDetect( BrobInt bint ) {
      for( int i = 0; i < bint.toString().length(); i++ ) {
         if( bint.toString().charAt(i) != '0' ) {
            return false;
         }
      }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  @param   d  byte array from which to display the contents
   *  NOTE: may be changed to int[] or some other type based on requirements in code above
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) { //changed to long from in t
      System.out.println( "Array contents: " + Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  @param   d  byte array from which to display the contents
   *  NOTE: may be changed to long[] or some other type based on requirements in code above
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public void toArrayLong( long[] d ) { //changed to long from in t
   System.out.println( "Array contents: " + Arrays.toString( d ) );
}
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display a prompt for the user to press 'ENTER' and wait for her to do so
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void pressEnter() {
      String inputLine = null;
      try {
         System.out.print( "      [Press 'ENTER' to continue]: >> " );
         inputLine = input.readLine();
      }
      catch( IOException ioe ) {
         System.out.println( "Caught IOException" );
      }

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  NOTE:  we don't really care about these, since we test the BrobInt class with the BrobIntTester
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      System.exit( 0 );

   }
}