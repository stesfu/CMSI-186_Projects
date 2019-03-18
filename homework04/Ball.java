

public class Ball {
    double locX = 0.0;
    double locY = 0.0;
    double velX = 0.0;
    double velY = 0.0;
    double BALL_RADIUS = 4.45;
    double BALL_DISTANCE = 2 * (BALL_RADIUS);   

    public Ball(double locX, double locY, double velX, double velY){
        this.locX = locX;
        this.locY = locY;
        this.velX = velX;
        this.velY = velY; 
    }

    public void move(double timeSlice){
        locX = velX * timeSlice;
        locY = velY * timeSlice; 
    }

    public void updateVel(){
        velX = velX * 0.99; 
        velY = velY * 0.99;
    }

    public boolean isInMotion(){
        
        if (velX == 0 && velY == 0 ){
            return false;

        }else{
            return true;

        }
       
    }

    // public double [] getLoc(){ //put the x and y location into an array
    //     double[] location = new double[2];
    //     location[0] = locX;
    //     location[1] = locY;
    //     return location;
    // }

    // public double getSpeed(){
    //     return 0;

    // }

    public String toString(){
        String ballString = "X-Location: " + locX + " Y-Location: " + locY + " X-Velocity: " + velX + " Y-Velocity: " + velY;
        return ballString;
    }

    public static void main(String args[]){
        Ball b1 = new Ball(1,2,3,4);
        System.out.println(b1);

    }

}
