import java.text.DecimalFormat;
import java.util.ArrayList;

//What's not working: Out of Bounds (some cases works for: 10 20 20 20 40 40 20 20 but not 10 10)

public class SoccerSim{
    double timeSlice = 1.0; 
    double BALL_RADIUS = (4.45/12.0); //allows to collide in proper time
    //double BALL_RADIUS = 4.45; //allows to collide with pole 
    boolean collision = true;
    double time = 0; 
    public static boolean ballsMoving = true;
    public static boolean ballInBound = true;
    public  boolean simulationRunning = true;
    double hours = 0.0;
    double seconds = 0.0;
    double minutes = 0.0;
    int ballsOutOfBounds = 0;
    int ballsStopped = 0;
    String collideItems = "The items collided have the following attributes: "; 
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
        int argumentChecker = 0;
        if (0 == args.length || (4 > args.length)) {
         System.out.println("   Sorry you must enter at least 4 arguments\n" +
          "   Usage: java SoccerSim <ball 1 xpos> <ball 1 ypos> < ball 1 xvel> <ball 1 yvel>  <ball 2 xpos> (repeat for as many balls as you want) [timeSlice] \n" +
          "   Please try again..........."); //y same thing then the timeSlice
         System.exit(1); 
        } 

        if((args.length % 4) == 0){ 
            timeSlice = 1.0;
           argumentChecker = args.length;
        } if((args.length % 4) == 1){ 
            double timeSliceInput = Double.parseDouble(args[args.length - 1]);
            timeSlice = Ball.validateTimeSlice(timeSliceInput);
            argumentChecker = (args.length - 1);
        }if((args.length % 4) > 1){ //QUESTION do i need to make it less than zero
            throw new NumberFormatException("Invalid Input");
        }
        

        for(int i = 0; i < argumentChecker; i += 4){ //was args.length
            Double locX = Double.parseDouble(args[i]);
            Double locY = Double.parseDouble(args[i+1]);
            Double velX = Double.parseDouble(args[i+2]);
            Double velY = Double.parseDouble(args[i+3]);
            balls.add(new Ball(locX, locY, velX, velY));
        }

        
        System.out.println("\n   Welcome to SoccerSim!\n\n");
        System.out.println("\n" + "There is a pole at ( 25,10 )" + "\n");
        //idk maybe do a main here
        //int ballCount = (args.length / 4); 
        // for(int i = 0; i < args.length; i += 4){ //original parsing
        //     Double locX = Double.parseDouble(args[i]);
        //     Double locY = Double.parseDouble(args[i+1]);
        //     Double velX = Double.parseDouble(args[i+2]);
        //     Double velY = Double.parseDouble(args[i+3]);
        //     balls.add(new Ball(locX, locY, velX, velY));
        // }




    }

    public static double validateBallArgs(){
        System.out.println("yeet");
        return 0.0;
    }

    // public static double validateTimeSlice(){
    //     double tempTimeSlice = Double.parseDouble(args[args.length - 1]);
    //         if(tempTimeSlice > 0 || tempTimeSlice <= 1800){
    //             timeSlice = tempTimeSlice;
    //         }
    //     return 0.0;
    // } 

    // public void setBalls(Ball [] balls){
    //     this.balls = balls;
    // }

    // public void addBalls(double locX, double locY, double velX, double velY){
    //     balls.add(new Ball(locX, locY, velX, velY));
    // }

    public void moveAll(double timeslice) {
        for(int i=0; i < balls.size(); i++){
            // balls.get(i).updateVel();
            balls.get(i).move(timeslice); //this.timeSlice? but that doesnt compile
        }
        this.time += this.timeSlice;
        
    }

    public void runSimulation(double timeSlice){
        moveAll(timeSlice);
        stopBall();
        // ballOutOfBounds();

    }

    public void stopBall(){
        for(int i=0; i< balls.size(); i++){
            if (balls.get(i).isInMotion() == false || balls.get(i).isInBounds() == false){
                balls.get(i).stopVelocity();
                balls.remove(i);
                ballsStopped++;
            }

        }
    }

    // public void removeBall(){
    //     for(int i=0; i< balls.size(); i++){
    //         this.balls.removeIf(ball -> this.balls.get(i).isInBounds() == false);
    //         }

    //     }
    

    // public void ballOutOfBounds(){
    //     for(int i=0; i< balls.size(); i++){
    //         if (balls.get(i).isInBounds() == false){
    //             balls.get(i).stopVelocity();
    //             ballsStopped++;
    //         }

    //     }
    // }

    public boolean allBallStop(){
        return balls.size() == 0;
    //     boolean ballStopValue = false;
    //     for(int i=0; i< balls.size(); i++){
    //         if (ballsStopped >= (balls.size() + 1)){ // +1 fixes the time problem but runs infinite loop
    //             ballStopValue = true;
    //         }
    //     }
    //     return ballStopValue;
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

    public boolean ballsCollision(){
        boolean ballCollide = false;
        // if(balls.size() != 1){

            for (int i = 0; i < balls.size(); i++ ){
                if (i+1 < balls.size()){
                    double xElement = Math.abs(balls.get(i).locX - (balls.get(i + 1).locX));
                    double yElement = Math.abs(balls.get(i).locY - (balls.get(i + 1).locY));
                    double distance = Math.hypot(xElement, yElement);
        
                    if(distance <= (2 * BALL_RADIUS) ){
                        ballCollide = true;
                        collideItems += "\n" + "Time: " + this.toStringTime() + "\n" + "Ball at " + (balls.get(i).toString() + "\n" + "Ball at " +  balls.get(i + 1).toString()) + "\n";
                        
                    }
                }
            }
        // }

            // for (int i = 0; i < balls.size(); i++ ){
            //    for (int j = 1;  j < balls.size(); j++ ){
            //     double xElement = Math.abs(balls.get(i).locX - (balls.get(j).locX));
            //     double yElement = Math.abs(balls.get(i).locY - (balls.get(j).locY));
            //     double distance = Math.hypot(xElement, yElement);
    
            //     if(distance <= (2 * BALL_RADIUS) ){
            //         ballCollide = true;
            //         collideItems += "\n" + "Ball at " + (balls.get(i).toString() + "\n" + "Ball at " +  balls.get(j).toString()) + "\n";
                    
            //     }

            //    }
           // }

            

        
        for (int i = 0; i < balls.size(); i++ ){
            // System.out.println("BALLS X LOCATION: " + Double.toString(balls.get(i).locX));
            // System.out.println("BALLS Y LOCATION: " + Double.toString(balls.get(i).locY));
            double xElement = Math.abs(balls.get(i).locX - 25.0);
            double yElement = Math.abs(balls.get(i).locY - 10.0);
            double distance = Math.hypot(xElement, yElement);

            if(distance <= (2 * BALL_RADIUS) ){
                ballCollide = true;
                collideItems += "\n" + "Time: " + this.toStringTime() + "\n" + "Ball at " + balls.get(i).toString() + "\n" + "Pole at X-Location: 25 Y-Location: 10" + "\n";
            }
        }

        return ballCollide;
    }

    // public boolean poleCollision(){  made in ballCollision
    //     boolean poleCollide = false;
    //     for (int i = 0; i < balls.size() - 1; i++ ){
    //         double xElement = Math.abs(balls.get(i).locX - 25);
    //         double yElement = Math.abs(balls.get(i).locY - 10);
    //         double distance = Math.hypot(xElement, yElement);

    //         if(distance <= (2 * BALL_RADIUS) ){
    //             poleCollide = true;
    //             collideItems += "\n" + "Ball at " + balls.get(i).toString() + "\n" + "Pole at X-Location: 25 Y-Location: 10" + "\n";
    //         }
    //     }
    //     return poleCollide;
    // }

    public String toStringTime(){
        DecimalFormat secFormatter = new DecimalFormat("00.00");
        DecimalFormat hourFormatter = new DecimalFormat("00");
        DecimalFormat minFormatter = new DecimalFormat("00");

        double roughHours = this.time / 3600;
        hours = Math.floor(roughHours);
 
        double roughMinutes = ((roughHours - hours) * 3600) / 60;
        minutes = Math.floor(roughMinutes);
        seconds = ((roughMinutes - minutes) * 60);

        String strHours = String.valueOf(hourFormatter.format(hours));
        String strMin = String.valueOf(minFormatter.format(minutes));
        String strSec = String.valueOf(secFormatter.format(seconds));
        return strHours + ":" + strMin + ":" + strSec;

    }

    public String toString(){
        String result = ""; 
        result += "Time: " + this.toStringTime() + "\n"; //problem area! What's here throws an infinite loop
        for (int i = 0; i < balls.size(); i++ ){
            result += " Ball " + i + " " + balls.get(i);
            result += "\n";
        }
        return result;
    }

    public static void main(String args[]){

        SoccerSim sim = new SoccerSim();
        sim.handleInitialArguments(args);
        System.out.println(sim.toString());

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

        while(sim.simulationRunning){
            // sim.outCount();
            // sim.removeBall();
            System.out.println(sim);
            if(sim.allBallStop()){
                sim.simulationRunning = false;
                System.out.println("All the balls have stopped moving or left the bounds, no collision occured");
                break;
            }
            if(sim.ballsCollision()){
                sim.simulationRunning = false;
                System.out.println("COLLISION DETECTED");
                System.out.println(sim.collideItems);
            }
            // if(sim.poleCollision() == true){
            //     sim.simulationRunning = false;
            //     System.out.println("COLLISION DETECTED");
            //     System.out.println(sim.collideItems);
            // }
            sim.runSimulation(sim.timeSlice);
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
