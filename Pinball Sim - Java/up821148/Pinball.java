import java.awt.*;
import java.util.*;

/**
 * An object that exists in the pinball. 
 * 
 * Movement can be initiated by repeated calls to the "move" method.
 * 
 * @author 821148
 * @version 23/03/2017
 * 
 */

public class Pinball extends MachineObject
{
    private double speedXTravel;
    private double speedYTravel;
    
    private final int leftWallPosition;
    private final int rightWallPosition;
    private final int topWallPosition;
    private final int bottomWallPosition;
    private int points;
    
    private Random random;

    /**
     * Initiate the values of the Pinball - Inherit the Machine object but add velocity
     * @param xPos The x coordinate of the pinball
     * @param yPos The y coordinate of the pinball
     * @param xVel The horizonal velocity of the pinball
     * @param yVel The vertical velocity pinball
     * @param objectColor The colour of the pinball
     * @param objectRadius The radius of the pinball
     * @param machine The machine the pinball is located at
     */
    public Pinball(int xPos, int yPos, double xVel, double yVel, Color objectColor, double objectRadius, Machine machine)
    {
        super(xPos, yPos, objectColor, objectRadius, machine);
        speedXTravel = xVel;
        speedYTravel = yVel;
        
        leftWallPosition = machine.getLeftWall();
        rightWallPosition = machine.getRightWall();
        topWallPosition = machine.getTopWall();
        bottomWallPosition = machine.getBottomWall();
     
        points = 0;
        
        random = new Random();

    }

    /**
     * Retrives the amount of points the pinball has
     * @return points The points of the pinball
     */
    public int getPoints(){
        return points;
    }

    /**
     * Adds an amount of points to the pinball score
     * @param amount The amount to points to add onto the pinball
     */
    public void addPoints(int amount) {
        points += amount;
    }

    /**
     * Resets the pinball points back to 0
     */
    public void resetPoints()
    {
        points = 0;
    }

    /**
     * Returns a random double number
     * @param min The minimum number in range
     * @param max The maximum number in range
     * @return randomNum A random double
     */
    public double randDouble(double min, double max) {
        
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        double randomNum = min + (max - min) * random.nextDouble();
    
        return randomNum;
    }

    /**
     * Returns a random boolean
     * @return Returns true or false randomly
     */
    public boolean getBoolean()
    {
     return random.nextBoolean();
    }

    /**
     * Retrieve the x Velocity of the pinball
     * @return speedXTravel The x Veocity
     */
    public double getSpeedX()
    {
        return speedXTravel;
    }

    /**
     * Retrieve the y Velocity of the pinball
     * @return speedYTravel The y Velocity
     */
    public double getSpeedY()
    {
        return speedYTravel;
    }

    /**
     * Convert x velocity into an integer and retrieve it
     * @return x The x Velocity in integer form.
     */
    public int getIntSpeedX()
    {
        int x = (int) Math.round(speedXTravel);
        return x;
    }

    /**
     * Convert y velocity into an integer and retrieve it
     * @return y The y Velocity in integer form.
     */
    public int getIntSpeedY()
    {
        int y = (int) Math.round(speedYTravel);
        return y;
    }

    /**
     * Set a new velocity for x coordinate
     * @param newSpeed The new x velocity
     */
    public void setSpeedX(double newSpeed)
    {
        speedXTravel = newSpeed;
    }

    /**
     * Set a new velocity for y coordinate
     * @param newSpeed The new y velocity
     */
    public void setSpeedY(double newSpeed)
    {
        speedYTravel = newSpeed;
    }

    /**
     * Move this object according to its position and speed and redraw.
     **/
    public void move()
    {
         // remove from universe at the current position
        getMachine().erasePinball(this);
        
        // compute new position
        setXPosition((getXPosition() + getIntSpeedX()));
        setYPosition((getYPosition() + getIntSpeedY()));

        // check if it has hit the walls
        if(getXPosition() <= (leftWallPosition + getIntRadius())) 
        {
            setXPosition((leftWallPosition + getIntRadius()));
            speedXTravel = speedXTravel * -1;
            wallCollision();
            points ++;
            addPoints(1);
        }
        else if(getXPosition() >= (rightWallPosition - getIntRadius()))
        {
            setXPosition((rightWallPosition - getIntRadius()));
            speedXTravel = speedXTravel * -1;
            wallCollision();
            points ++;
        }
        else if(getYPosition() <= (topWallPosition + getIntRadius()))
        {
            setYPosition((topWallPosition + getIntRadius()));
            speedYTravel = speedYTravel * -1;
            wallCollision();
            points ++;

        }
        else if(getYPosition() >= (bottomWallPosition - getIntRadius()))
        {
            if((getXPosition() + getRadius() <= 250 || getXPosition() - getRadius() <= 250) || (getXPosition() + getRadius() >= 350 || getXPosition() - getRadius() >= 350))
            {
                setYPosition((bottomWallPosition - getIntRadius()));
                speedYTravel = speedYTravel * -1;
                wallCollision();
                points ++;

            }
        }
      
        // draw again at new position
        getMachine().draw(this);
    }

    /**
     * Method for the pinball when it collides with the wall
     */
    public void wallCollision()
    {
        System.out.println("Pinball has collided with wall");
    }

    /**
     * Method for the pinball with it collides with another pinball
     */
    public void collide()
    {
       speedXTravel = speedXTravel * -1;
       speedYTravel = speedYTravel * -1;
    }

    /**
     * Method for the pinball when it collides with a bumper
     */
    public void objectCollide()
    {
       speedXTravel *= -1;
       speedYTravel *= -1;
    }
}   