import java.awt.*;
import java.util.*;


/**
 * This class inherits the Pinball.
 * This class is used to draw a specific type of pinball.
 * Pinball 4 changes colour when colliding with a wall
 * Pinball 4 changes direction when colliding with another pinball
 */
public class Ball4 extends Pinball
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
    public Ball4(int xPos, int yPos, double xVel, double yVel, Color objectColor, double objectRadius, Machine theMachine)
    {
        super(xPos, yPos, xVel, yVel, objectColor, objectRadius, theMachine);
    }

    /**
     * Change colour when colliding with a wall
     */
    public void wallCollision()
    {
        getRandomColour();
    }
    
    /**
     * Calculate the magnitude of the pinball by using the pythagorean theorem
     * @return Rerieve the maginute 
     */
    public double getMagnitude()
    {
        //Work out hypotenuse by square rooting x^2 + y^2
        return Math.sqrt(Math.pow(Math.abs(getSpeedY()), 2) + Math.pow(Math.abs(getSpeedX()), 2));
    }
    
    /**
     * Collision when pinball hits another pinball. - Changes direction
     */
    public void collide()
    {
        super.collide();
        
        // Find the angle of movement
        double angle = Math.tan(Math.abs(getSpeedY()) / Math.abs(getSpeedX()));
        
        //Set new X/Y values and make sure magnitude is constantly set.
        double newX = getMagnitude() * Math.cos(angle);
        double newY = getMagnitude() * Math.sin(angle);
        
        
        setSpeedX(newX);
        setSpeedY(newY);
    }
    
    /**
     * Generates a random colour
     */
    public void getRandomColour() {
        Color colours[] = {Color.CYAN, Color.MAGENTA, Color.YELLOW}; // List of available colours
        boolean currentColour = true;
        while(currentColour) // Carry on if current colour is found
          {
              // Pick a random colour
              Color nextColour = colours[new Random().nextInt(colours.length)];

              // Makes sure that colour is not the current colour of the pinball
              if(getColor() != nextColour)
              {
                  // Set the new colour
                  setColor(nextColour);
                  currentColour = false; // End the loop
              }
          }
    }
}