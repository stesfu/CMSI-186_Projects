import java.text.DecimalFormat;

public class SoccerSim{
    double fieldSize = 500.0;
    public static double timeSlice = 1.0; 
    boolean collision = true;
    Ball [] balls;
   

    public SoccerSim(){

    }

    public void handleInitialArguments(String args[]){
        if (0 == args.length) {
         System.out.println("   Sorry you must enter at least one argument\n" +
          "   Usage: java SoccerSim <ball 1 xpos> <ball 1 ypos> < ball 1 xvel> <ball 1 yvel>  <ball 2 xpos> (repeat for as many balls as you want) [timeSlice] \n" +
          "   Please try again..........."); //y same thing then the timeSlice
         System.exit(1); //hoo r u?
        } 



    }

    public static double validateBallArgs(){
        System.out.println("yeet");
        return 0.0;
    }

    public static double validateTimeSlice(){
        return 0.0;
    } 

    public void setBalls(Ball [] balls){
        this.balls = balls;
    }

    public void ballReport() {
        for(int i=0; i < balls.length; i++){
            balls[i].move(timeSlice);
            balls[i].updateVel(); 
        }
        
    }

    public String toString(){
        String result = ""; 
        for (int i = 0; i < balls.length; i++ ){
            result += " Ball " + i + " " + balls[i];
            result += "\n";
        }
        return result;
    }

    public boolean isInBounds(){
        return true; 

    }



    public static void main(String args[]){

        System.out.println("\n   Welcome to SoccerSim!\n\n");
        //idk maybe do a main here
        int ballCount = (args.length / 4); 
        Ball [] balls = new Ball[ballCount];
        int j = 0;
        for(int i = 0; i < args.length; i += 4){
            Double locX = Double.parseDouble(args[i]);
            Double locY = Double.parseDouble(args[i+1]);
            Double velX = Double.parseDouble(args[i+2]);
            Double velY = Double.parseDouble(args[i+3]);
            balls[j] = new Ball(locX, locY, velX, velY);
            j++; 
        }

        SoccerSim sim = new SoccerSim();
        sim.setBalls(balls);

        System.out.println(sim);
        int times = 4;
        for (int i=0; i < times; i++){
            sim.ballReport();
            System.out.println(sim);
        }
    }

 

 


}
