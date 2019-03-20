

public class Ball {
    double locX = 0.0;
    double locY = 0.0;
    double velX = 0.0;
    double velY = 0.0;
    double fieldSize = 500.0;
    double BALL_RADIUS = 4.45;
    double BALL_DISTANCE = 2 * (BALL_RADIUS);   

    public Ball(double locX, double locY, double velX, double velY){
        this.locX = locX;
        this.locY = locY;
        this.velX = velX;
        this.velY = velY; 
    }

    public void move(double timeSlice){
        this.locX += this.velX * timeSlice;
        this.locY += this.velY * timeSlice; 
    }

    public void updateVel(){
        this.velX = this.velX * 0.99; 
        this.velY = this.velY * 0.99;
    }

    public void stopVelocity(){
        this.velX = 0.0;
        this.velY = 0.0;
    }

    public boolean isInMotion(){
        boolean ballsMoving = true;
        if(Math.abs(this.velX) <= 0.083 && Math.abs(this.velY) <= 0.083){
            ballsMoving = false;
        }
        return ballsMoving;

    }

    public boolean isInBounds(){
        boolean inBounds = true;
        if(Math.abs(this.locX) >= fieldSize || Math.abs(this.locY) >= fieldSize){
            inBounds = false;
        }
        return inBounds; 

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
        String ballString = "X-Location: " + this.locX + " Y-Location: " + this.locY + " X-Velocity: " + this.velX + " Y-Velocity: " + this.velY;
        return ballString;
    }

    public static void main(String args[]){
        Ball b1 = new Ball(1,2,3,4);
        System.out.println(b1);

    }

}
