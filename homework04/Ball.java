

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

    public void move(){

    }

    public double updateVel(){

        return 0.0;
    }

    public boolean isInMotion(){
        return true;
    }

    public double [] getLoc(){ //put the x and y location into an array
        double[] location = new double[2];
        location[0] = locX;
        location[1] = locY;
        return location;
    }

    public double getSpeed(){
        return 0;

    }

    public String toString(){
        return ("yeEt");

    }

    public boolean isInBounds(){
        return true; 

    }

    public static void main(String args[]){
        //Reference clock for set up

    }

}
