import java.text.DecimalFormat;
import java.util.ArrayList;

public class SoccerSim{
    double timeSlice = 1.0; 
    boolean collision = true;
    public static boolean ballsMoving = true;
    public static boolean ballInBound = true;
    public  boolean simulationRunning = true;
    int ballsOutOfBounds = 0;
    int ballsStopped = 0;
    ArrayList<Ball> balls = new ArrayList<Ball>();
   

    public void SoccerSim(){}

    // public void outCount() {
    //     for (int i = 0; i < balls.size(); i++){
    //         if(balls.get(i).isInBounds() == false){
    //             ballsOutOfBounds++; 
    //         }
    //     }
    //   }

    //   public void stopCount() {
    //     for (int i = 0; i < balls.size(); i++){
    //         if(balls.get(i).isInMotion() == false){
    //             ballsStopped++; 
    //         }
    //     }
    //   }

    //   public boolean simulationRunning(){
    //       boolean value = true;
    //       if(ballsOutOfBounds + ballsStopped == balls.size()){
    //           value = false;
    //       }
    //       return value;
    //   }

   

    public void handleInitialArguments(String args[]){
        if (0 == args.length) {
         System.out.println("   Sorry you must enter at least one argument\n" +
          "   Usage: java SoccerSim <ball 1 xpos> <ball 1 ypos> < ball 1 xvel> <ball 1 yvel>  <ball 2 xpos> (repeat for as many balls as you want) [timeSlice] \n" +
          "   Please try again..........."); //y same thing then the timeSlice
         System.exit(1); //hoo r u?
        } 
        System.out.println("\n   Welcome to SoccerSim!\n\n");
        //idk maybe do a main here
        //int ballCount = (args.length / 4); 
        for(int i = 0; i < args.length; i += 4){
            Double locX = Double.parseDouble(args[i]);
            Double locY = Double.parseDouble(args[i+1]);
            Double velX = Double.parseDouble(args[i+2]);
            Double velY = Double.parseDouble(args[i+3]);
            balls.add(new Ball(locX, locY, velX, velY));
        }




    }

    public static double validateBallArgs(){
        System.out.println("yeet");
        return 0.0;
    }

    public static double validateTimeSlice(){
        return 0.0;
    } 

    // public void setBalls(Ball [] balls){
    //     this.balls = balls;
    // }

    // public void addBalls(double locX, double locY, double velX, double velY){
    //     balls.add(new Ball(locX, locY, velX, velY));
    // }

    public void moveAll(double timeslice) {
        for(int i=0; i < balls.size(); i++){
            balls.get(i).move(timeslice);
            balls.get(i).updateVel(); 
        }
        
    }

    public void runSimulation(double timeSlice){
        moveAll(timeSlice);
        stopBall();
        ballOutOfBounds();

    }

    public void stopBall(){
        for(int i=0; i< balls.size(); i++){
            if (balls.get(i).isInMotion() == false){
                ballsStopped++;
            }

        }
    }

    public void ballOutOfBounds(){
        for(int i=0; i< balls.size(); i++){
            if (balls.get(i).isInBounds() == false){
                balls.get(i).outOfBoundsVelocity();
            }

        }
    }

    public boolean allBallStop(){
        boolean ballStopValue = false;
        for(int i=0; i< balls.size(); i++){
            if (ballsStopped == balls.size()){ 
                ballStopValue = true;
            }
        }
        return ballStopValue;
    }

    // public boolean allBallLeft(){
    //     boolean ballStopValue = false;
    //     for(int i=0; i< balls.size(); i++){
    //         if (ballsOutOfBounds == balls.size()){ 
    //             ballStopValue = true;
    //         }
    //     }
    //     return ballStopValue;
    // }

    public String toString(){
        String result = ""; 
        for (int i = 0; i < balls.size(); i++ ){
            result += " Ball " + i + " " + balls.get(i);
            result += "\n";
        }
        return result;
    }

    public static void main(String args[]){

        SoccerSim sim = new SoccerSim();
        sim.handleInitialArguments(args);

        // System.out.println(sim);
        // int times = 4;
        // for (int i=0; i < times; i++){
        //     sim.ballReport(sim.timeSlice);
        //     System.out.println(sim);
        // }

        // while(sim.allBallStop() == true){
        //     sim.ballReport(sim.timeSlice);
        //     System.out.println(sim);

        // }

        while(sim.simulationRunning == true){
            // sim.outCount();
            sim.runSimulation(sim.timeSlice);
            System.out.println(sim);
            if(sim.allBallStop() == true){
                sim.simulationRunning = false;
                System.out.println("All the balls have stopped moving or left the bounds, no collision occured");
            }
        }


        // while(simulationRunning == true){
        //     // sim.outCount();
        //     sim.ballReport(sim.timeSlice);
        //     System.out.println(sim);
        //     if(ballsMoving == false){
        //         simulationRunning = false;
        //         System.out.println("All the balls have stopped moving, no collision occured");
        //     }
        //     if(sim.ballsOutOfBounds == sim.balls.size()){
        //         simulationRunning = false; 
        //         System.out.println("All the balls have gone out of bounds and no collision occured");
        //     }
        // }
        // while(ballsMoving == true){
        //     sim.ballReport(sim.timeSlice);
        //     System.out.println(sim);
        // }
      
    }

 

 


}
