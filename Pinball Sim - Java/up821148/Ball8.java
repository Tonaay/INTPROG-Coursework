import java.awt.*;
import java.util.*;

/**
 * This class inherits the Pinball.
 * This class is used to draw a specific type of pinball.
 * Pinball 8 changes size when colliding with a wall
 * Pinball 8 changes speed when colliding with another pinball
 */
public class Ball8 extends Pinball
{

    /**
     * Initiate the values of the Pinball - Inherit the Pinball class.
     *@param xPos The x coordinate of the pinball
     * @param yPos The y coordinate of the pinball
     * @param xVel The horizonal velocity of the pinball
     * @param yVel The vertical velocity pinball
     * @param objectColor The colour of the pinball
     * @param objectRadius The radius of the pinball
     * @param machine The machine the pinball is located at
     */
    public Ball8(int xPos, int yPos, double xVel, double yVel, Color objectColor, double objectRadius, Machine theMachine)
    {
     super(xPos, yPos, xVel, yVel, objectColor, objectRadius, theMachine);
    }

    /**
     * Change size when colliding with a wall / Increase or decrease by 10%
     */
    public void wallCollision()
    {
       // Make sure that size does not go too small
       if(getRadius() <= 20)
       {
           setRadius((getRadius() + (getRadius() * 0.1)));
       }
       
       // Makes sure that size does not become too big
       else if(getRadius() >= 50)
       {
           setRadius((getRadius() - (getRadius() * 0.1)));
       }
       
       else
       {
           if(getBoolean())
           {
               // Increase by 10%
               setRadius((getRadius() + (getRadius() * 0.1)));
           }
           else
           {
               // Decrease by 10%
               setRadius((getRadius() - (getRadius() * 0.1)));
           }
        }
    }

    /**
     * Change speed when colliding with a pinball
     */
    public void collide()
    {
       double speedMultiplier = randDouble(0.9,1.1);
       setSpeedX(getSpeedX() * (-speedMultiplier));
       setSpeedY(getSpeedY() * (-speedMultiplier)); 
    }
}