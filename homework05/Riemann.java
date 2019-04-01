/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Riemann.java
 *  Purpose       :  The main program for the Riemann class
 *  @see
 *  @author       :  Salem Tesfu
 *  Date written  :  2019-03-21
 *  Description   :  Calculate the Riemann sum of polynomial and sin functions
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.ArrayList;


 public class Riemann {
    String[] functions = {"poly", "sin","cos","runtests"};
    ArrayList<Double> inputs = new ArrayList<Double>();
    double upperB = 0;
    double lowerB = 0;
    double percent = 1;
    double deltaX = 0;
    String functionType = "";


    public Riemann(){

    }

    public void handleInitialArguments(String args[]){


    }

    public boolean validateArgs(String[] args ){

        boolean validated = true;

        // if(functions.indexOf(args[0]) == -1 ){ //why doesnt work
        //     validated = false;
        // }

        functionType = args[0];
        

        if(args[args.length - 1].contains("%")){
            percent = Double.parseDouble(args[args.length - 1].replace("%", " ").trim());
            upperB = Double.parseDouble(args[args.length -2]);
            lowerB = Double.parseDouble(args[args.length -3]); //need to adjust when upperbound is smaller?
            for(int i = 1; i < (args.length - 3); i++ ){
                inputs.add(Double.parseDouble(args[i])); 
            }
        }
        // }else{ //percent is required 
        //     lowerB = Double.parseDouble(args[args.length -1]);
        //     upperB = Double.parseDouble(args[args.length -2]);
        //     for(int i = 1; i < (args.length - 2); i++ ){
        //         inputs.add(Double.parseDouble(args[i])); 
        //     }
        // }


        return validated; //if validated is false throw number format exception
    }

    public double solvePoly(double x){
        double solved = 0;
        for(int i=0; i < inputs.size(); i++){ //degree would be inputs.size - 1
            solved += (inputs.get(i) * (Math.pow(x, i)));
        }
        return solved;
    }

    public double solveSin(double radX){
        double solved = 0;
        solved = Math.sin(radX);
        return solved;
    }

    public double solveLog(double x){
        double solved = 0;
        solved = Math.log(x);
        return solved;
    }

    public double integrate(double upperB, double lowerB,double q){ 
        double integral = 0.0;
        deltaX = ((Math.abs(upperB - lowerB))/q); // absolute value... do i have to take things in to use them?
        switch(functionType){
            case "poly":
                for(double i = lowerB; i < upperB; i += deltaX){
                    integral += (solvePoly(i) * deltaX);
                }
                break;
            case "sin":
                for(double i = lowerB; i < upperB; i += deltaX){
                    integral += (solveSin(i) * deltaX);
                }
                break;
            case "log":
                for(double i = lowerB; i < upperB; i += deltaX){
                    integral += (solveLog(i) * deltaX);
                }
                break;
        }
        return integral;

    }

    public static void main(String args[]){
        Riemann sim = new Riemann();
        sim.validateArgs(args);
        // System.out.println("leggo!");
        // System.out.println(sim.inputs);
        // System.out.println("solved poly: " + sim.solvePoly(10));
        // System.out.println("integral: " + sim.integratePoly(sim.upperB, sim.lowerB, 1));
        // System.out.println("this b delta x: " + sim.deltaX);
        // System.out.println("the upper and lower bounds upper: " + sim.upperB + " lower: "+ sim.lowerB);
        // System.out.println( sim.integratePoly(sim.upperB, sim.lowerB, 1000));
        double previous = sim.integrate(sim.upperB, sim.lowerB, 1.0);
        System.out.println(previous);
        double q = 2.0;
        while(true){ 
            double current = sim.integrate(sim.upperB, sim.lowerB, q);
            if(Math.abs(1 - (previous/current)) <= (sim.percent/100.0)){
                System.out.println("the integral is: " + current);
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
