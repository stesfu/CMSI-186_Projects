/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Riemann.java
 *  Purpose       :  The main program for the Riemann class
 *  @see
 *  @author       :  Salem Tesfu
 *  Date written  :  2019-03-21
 *  Description   :  Calculate the Riemann sum of polynomial and sin functions
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


 public class Riemann {
    List <String> functions = Arrays.asList("runtests","poly", "sin","cos","runtests");
    ArrayList<Double> inputs = new ArrayList<Double>();
    double upperB = 0;
    double lowerB = 0;
    double percent = 1;
    double deltaX = 0;
    String functionType = "";


    public Riemann(){

    }

    public void handleInitialArguments(String args[]){

        if(args.length > 1){

            if(args[args.length - 1].contains("%")){
                percent = Double.parseDouble(args[args.length - 1].replace("%", " ").trim());
                upperB = Double.parseDouble(args[args.length -2]);
                lowerB = Double.parseDouble(args[args.length -3]); //need to adjust when upperbound is smaller?
                for(int i = 1; i < (args.length - 3); i++ ){
                    inputs.add(Double.parseDouble(args[i])); 
                }
            }else{ //percent is required 
                upperB = Double.parseDouble(args[args.length -1]);
                lowerB = Double.parseDouble(args[args.length -2]);
                for(int i = 1; i < (args.length - 2); i++ ){
                    inputs.add(Double.parseDouble(args[i])); 
                }
            }
            if (upperB < lowerB){
                // throw new NumberFormatException("Invalid input, Upper bound must be less than lower bound");
                System.out.println("Invalid input, Upper bound must be less than lower bound");
                System.exit(0);
            }

            if(percent < 0){
                System.out.println("Invalid input, Percent must be greater than 0");
                System.exit(0);
            }

         

        }


        functionType = args[0];



    }

    public boolean validateArgs(String[] args ){

        boolean validated = true;


        for(int i=0; i < args.length; i++){
            if((functions.contains(args[0]))== false){
                System.out.println("Invalid input, please use a valid function");
                System.exit(0);

            }
        }

        
        if(args.length < 4 && (args[0].contains("runtests") == false)){
                System.out.println(args.length);
                System.out.println(args[0]);
                System.out.println("Invalid input for len");
                validated = false;
                System.exit(0);
        }
        if(args.length == 0){
            System.out.println("Invalid input");
            validated = false;
            System.exit(0);
        }
        return validated; //if validated is false throw number format exception
    }

    public void validateArgsTest(){
        String [] myArgs = {"poly", "1", "2", "1", "1", "10", ".001%"};
        handleInitialArguments(myArgs); //returning true or
    }

    public void runMyTests(){
        validateArgsTest();

    }

    public double solvePoly(double x){ /// 4 3 2 lb ub percent sin(4 + 3x + 3x^2)
        double solved = 0;
        for(int i=0; i < inputs.size(); i++){ //degree would be inputs.size - 1
            solved += (inputs.get(i) * (Math.pow(x, i)));
        }
        return solved;
    }

    public double solveSin(double radX){
        double solved = 0.0; // if inputs.length == 0 
        double sinX = 0.0;

        if (inputs.size() == 0){
            solved = Math.sin(radX);
        }else{
            sinX = solvePoly(radX); //getting whatever that y value is?
            solved = Math.sin(sinX);
        }

        return solved;
    }

    // public double solveLog(double x){
    //     double solved = 0;
    //     solved = Math.log(x);
    //     return solved;
    // }

    public double integrate(double upperB, double lowerB,double q){ 
        double integral = 0.0;
        deltaX = ((upperB - lowerB)/q); // absolute value... do i have to take things in to use them?
        switch(functionType){
            case "runtests":
                runMyTests();
                break;
            case "poly":
                for(double i = lowerB; i < upperB; i += deltaX){
                    integral += (solvePoly(i) * deltaX);
                }
                if(inputs.size() == 1 && inputs.get(0) == 0 ){ // only at zero does this happen 
                    System.out.println("The LH Riemann Sum is: " + 0.0000);
                    System.out.println("The number of rectangle(s): " + 0);
                    System.exit(0);
                }
                break;
            case "sin":
                for(double i = lowerB; i < upperB; i += deltaX){
                    integral += (solveSin(i) * deltaX);
                    // System.out.println("this be the inputs" + inputs);
                }
                break;
            case "log":
                for(double i = lowerB; i < upperB; i += deltaX){
                    integral += (Math.log(i) * deltaX);
                }
                break;
        }
        return integral;

    }

    public static void main(String args[]){
        Riemann sim = new Riemann();
        sim.validateArgs(args);
        sim.handleInitialArguments(args);
        // System.out.println("leggo!");
        // System.out.println(sim.inputs);
        // System.out.println("solved poly: " + sim.solvePoly(10));
        // System.out.println("integral: " + sim.integratePoly(sim.upperB, sim.lowerB, 1));
        // System.out.println("this b delta x: " + sim.deltaX);
        // System.out.println("the upper and lower bounds upper: " + sim.upperB + " lower: "+ sim.lowerB);
        // System.out.println( sim.integratePoly(sim.upperB, sim.lowerB, 1000));
        double previous = sim.integrate(sim.upperB, sim.lowerB, 1.0);
        double q = 2.0;
        while(true){ 
            double current = sim.integrate(sim.upperB, sim.lowerB, q);
            if(Math.abs(1 - (previous/current)) <= (sim.percent/100.0)){
                System.out.println("The LH Riemann Sum is: " + current);
                System.out.println("The number of rectangle(s): " + q);
                break;
            }
            previous = current;
            q++;

        }
        
    }

}

// public double integrate (lb, ub, coeffs[],n){
//     Switch(args[0]){    //this goes into the main
//         case "runtests" : runMyTests();
//                                       break;
//         case "poly": integrate 
//                              break;
//         case "sin": sin integrate
//                             break; 
//     main(String [] args) --{
//         previous = integrate(lb, ub, coeffs,1);
//         q = 2;
//         while(--){ //percent thing
//             current = integrate( lb, ub, coeffd, q);
//              if( 1 - (current/pervious) <= percentage){
//                  break
//               }previous = current;
//              q++;
//     }
//     public void run myTests(){
//         testIntegrate();
//         testValidate();
//         testIntegrateSin();
//     //Inside test inegrate below
//     public void testIntegrate(){
//         String s = {"poly","0","8","-2","1","4","re-6"};
//         Double result = integrate(s); 
//         s[3] = "-3";
//         result = integrate(s); 
//        System.out.println("Expected 18, got" + result);
    
// 
