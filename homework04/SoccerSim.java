import java.text.DecimalFormat;

public class SoccerSim{
    double fieldSize = 500.0;
    int ballCount = 0;
    double timeSlice = 1.0; 
   

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

    public static double validateArgs(){
        System.out.println("yeet");
        return 0.0;
    }

    public static void main(String args[]){
        SoccerSim sim = new SoccerSim();

        System.out.println("\n   Welcome to SoccerSim!\n\n");
        //idk maybe do a main here
        sim.ballCount = (int) (args.length / 4); 
        Ball [] balls = new Ball[sim.ballCount];
        int j = 0;
        for(int i = 0; i < args.length; i += 4){
            balls[j] = newBall(args[0], args[i+1], args[i+2], args[i+3]);
            j++; 
        }
    }

}
