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
    List <String> functions = Arrays.asList("runtests","poly", "sin","cos", "tan", "sqrt", "exp", "log","runtests");
    ArrayList<Double> inputs = new ArrayList<Double>();
    double upperB = 0;
    double lowerB = 0;
    double percent = 1;
    double deltaX = 0;
    String functionType = "";

    public Riemann(){}

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
        if((args.length < 3 && (args[0].contains("runtests") == false)) || (args.length < 4 && (args[0].contains("poly") ))){
                System.out.println("Invalid input for length for particular function");
                validated = false;
                System.exit(0);
        }
        if(args.length == 0){
            System.out.println("Invalid input");
            validated = false;
            System.exit(0);
        }
        return validated; 
    }

    public void validateArgsTest(){
        String [] myArgs = {"poly", "1", "2", "1", "1", "10", ".001%"};
        handleInitialArguments(myArgs); 
    }

    public void runMyTests(){
        validateArgsTest();

    }

    public double solvePoly(double x){ 
        double solved = 0;
        for(int i=0; i < inputs.size(); i++){ 
            solved += (inputs.get(i) * (Math.pow(x, i)));
        }
        return solved;
    }

    public double solveOther(double radX){
        double solved = 0.0; 
        double functionX = 0.0;
        switch(functionType){
            case "sin":
                if (inputs.size() == 0){
                    solved = Math.sin(radX);
                }else{
                    functionX = solvePoly(radX); 
                    solved = Math.sin(functionX);
                }
                break;
            case "cos":
                if (inputs.size() == 0){
                    solved = Math.cos(radX);
                }else{
                    functionX = solvePoly(radX); 
                    solved = Math.cos(functionX);
                }
                break;  
            case "tan":
                if (inputs.size() == 0){
                    solved = Math.tan(radX);
                }else{
                    functionX = solvePoly(radX); 
                    solved = Math.tan(functionX);
                }
                break;
            case "log":
                if (inputs.size() == 0){
                    solved = Math.log(radX);
                }else{
                    functionX = solvePoly(radX); 
                    solved = Math.log(functionX);
                }
                break;
            case "exp":
                if (inputs.size() == 0){
                    solved = Math.exp(radX);
                }else{
                    functionX = solvePoly(radX); 
                    solved = Math.exp(functionX);
                }
                break;
            case "sqrt":
                if(lowerB < 0){
                    System.out.println("inputs out of bounds for sqrt function");
                    System.exit(0);
                }
                if (inputs.size() == 0){
                    solved = Math.sqrt(radX);
                }else{
                    functionX = solvePoly(radX); 
                    solved = Math.sqrt(functionX);
                }
                if(Double.isNaN(solved)){
                    System.out.println("inputs out of bounds for sqrt function");
                    System.exit(0);
                }
                break;
        }
        return solved;
    }

    public double integrate(double upperB, double lowerB,double q){ 
        double integral = 0.0;
        deltaX = ((upperB - lowerB)/q); 
        switch(functionType){
            case "runtests":
                runMyTests();
                break;
            case "poly":
                for(double i = lowerB; i < upperB; i += deltaX){
                    integral += (solvePoly(i) * deltaX);
                }
                if(inputs.size() == 1 && inputs.get(0) == 0 ){ 
                    System.out.println("The LH Riemann Sum is: " + 0.0000);
                    System.out.println("The number of rectangle(s): " + 0);
                    System.exit(0);
                }
                break;
            default:
                for(double i = lowerB; i < upperB; i += deltaX){
                    integral += (solveOther(i) * deltaX);
                }
        }
        return integral;
    }

    public static void main(String args[]){
        Riemann sim = new Riemann();
        sim.validateArgs(args);
        sim.handleInitialArguments(args);
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