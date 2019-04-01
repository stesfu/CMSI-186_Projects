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


    public Riemann(){

    }

    public void handleInitialArguments(String args[]){


    }

    public boolean validateArgs(String[] args ){

        boolean validated = true;

        // if(functions.indexOf(args[0]) == -1 ){ //why doesnt work
        //     validated = false;
        // }
        

        if(args[args.length - 1].contains("%")){
            percent = Double.parseDouble(args[args.length -1].substring(0,(args[args.length - 1].length()-2)));
            lowerB = Double.parseDouble(args[args.length -2]); //need to adjust wheb upperbound is smaller?
            upperB = Double.parseDouble(args[args.length -3]);
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

    public double integratePoly(double upperB, double lowerB,double q){ 
        double integral = 0;
        double deltaX = 0;
        deltaX = ((Math.abs(upperB - lowerB))/q); // absolute value... do i have to take things in to use them?
        for(double i = lowerB; i < upperB; i = (i + deltaX)){
            integral += (solvePoly(i) * deltaX);
        }
        return integral;

    }

    public static void main(String args[]){
        Riemann sim = new Riemann();
        System.out.println("leggo!");
        double previous = sim.integratePoly(sim.upperB, sim.lowerB, 1.0);
        System.out.println(previous);
        double q = 2;
        double polyArea = 0; 
        while(polyArea == 0){ 
            double current = sim.integratePoly(sim.upperB, sim.lowerB, q);
            if(1 - (current/previous) <= sim.percent){
                System.out.println("the integral is" + current);
                polyArea = 1;
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
