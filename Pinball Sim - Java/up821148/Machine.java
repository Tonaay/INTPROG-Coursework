import java.awt.*;
import java.util.*;
/**
 * A pinball machine which holds various objects such as holes, bumpers and pinballs.
 * The machine will draw the canvas of the simulation and check collisions with each object.
 * 
 * @author 821148 
 * @version 23/03/2017
 */
public class Machine
{
    private Canvas machine;
    private int topEdge = 0;
    private int leftEdge = 0;
    private int bottomEdge;
    private int rightEdge;
    private int lengthToGap;        // the distance between the edge of the machine and the start of the gap
    private int gapWidth = 50;
    private ArrayList<Pinball> pinballs; // List to store all pinballs
    private ArrayList<MachineObject> objects; // List to store bumpers and holes
    private int totalPoints;
    private boolean gameIsRunning; // Continues running the game if true
    
    /**
     * Create a machine with default name and size
    */
    public Machine()
    {
        machine = new Canvas("Pinball Demo", 600, 500);
        rightEdge = 600;
        bottomEdge = 500;
        lengthToGap = (rightEdge / 2) - gapWidth;
        drawMachine();  
        pinballs = new ArrayList<Pinball>();
        objects = new ArrayList<MachineObject>();
        totalPoints = 0;
        gameIsRunning = true;
    }
    
    /**
     *  Create a machine with given name and size
     *  @param name The name to give the machine
     *  @param rightEdge The maximum x coordinate
     *  @param bottomEdge The maximum y coordinate
     */
     public Machine(String name, int rightEdge, int bottomEdge)
    {
        machine = new Canvas(name, rightEdge, bottomEdge);
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        lengthToGap = (rightEdge / 2) - gapWidth;
        drawMachine();
        pinballs = new ArrayList<Pinball>();
        objects = new ArrayList<MachineObject>();
        totalPoints = 0;
    }
    
    /**
     * Runs the simulation checking all collisions before moving all pinballs in the array list
     * If the game has ended, the score will be printed.
     */
    public void startMachine()
    {
       while(gameIsRunning)
       {
            pauseMachine();           // small delay
            if(pinballs.isEmpty())
            {
                break;
            }
            else
            {
                checkCollisions();
                movePinballs();
            }
       }
       drawString("S C O R E",new Font("Arial", Font.BOLD, 40), 200, 250);
       drawString(Integer.toString(getTotalPoints()),new Font("Arial", Font.BOLD, 40), 280, 300);
    }
        
    /**
     * Retrieves the total points by adding all the pinball's points together through the array list
     * @return totalPoints Returns the total points of the machine
     */
    public int getTotalPoints()
    {
        Iterator<Pinball> iteratePinball = pinballs.iterator();
        while(iteratePinball.hasNext())
        {
            Pinball currentPinball = iteratePinball.next();
            totalPoints += currentPinball.getPoints(); // Adds pinball points to the total
            System.out.println("Points :" + currentPinball.getPoints());
        }
        return totalPoints;
    } 
    
    /**
     * Add a pinball to the array list
     * @param newPinball The selected pinball to be added to the array list
     */
    public void addPinball(Pinball newPinball)
    {
        pinballs.add(newPinball);
    }
    
    /**
     * Add an object to the array list
     * @param newObject Adds an object to the array list.
     */
    public void addObject(MachineObject newObject)
    {
        objects.add(newObject);
    }
    
    /**
     * Moves all pinballs in the array list
     */
    public void movePinballs()
    {   
        for(Pinball pinball : pinballs)
        {
            {
                pinball.move();
            }
        }
    }
    
    /**
     * Calculates the difference between the positions of two objects
     * @param firstObject The current Pinball being checked for collision
     * @param secondObject The object that is being checked against the current Pinball
     * @return Returns the differenc between the positions of two objects using their x/y coordinates
     */
    public double positionDifference(MachineObject firstObject, MachineObject secondObject)
    {
        //call function to compare distance (x2-x1)^2 + (y2-y2)^2 
        return Math.pow(secondObject.getXPosition() - firstObject.getXPosition(),2) + Math.pow(secondObject.getYPosition() - firstObject.getYPosition(),2);
    }

    /**
     * Square the sum of the radius of two objects and returns the value
     * @param firstObject The current Pinball being checked for collision
     * @param secondObject The object that is being checked against the current Pinball
     * @return Squared sum of radiuses of both pinballs
     */
    public double radiusDifference(MachineObject firstObject, MachineObject secondObject)
    {
        //call function to compare distance (r1+r2)^2
        return Math.pow(secondObject.getIntRadius() + firstObject.getIntRadius(),2);
    }
    
    /**
     * Iterates through the array list of pinballs and checks if it has collided with all objects on the machine
     */
    public void checkCollisions()
    {
        Iterator<Pinball> iteratePinball = pinballs.iterator();
        while(iteratePinball.hasNext())
        {
            Pinball currentPinball = iteratePinball.next();
            // Checks whether pinball has touched the gap
            if(currentPinball.getYPosition() - currentPinball.getRadius() > bottomEdge)
            {
                iteratePinball.remove(); // Remove pinball from array list
                gameIsRunning = false; // Stops the whole simulation
            }
            else
            {
                //Check collision against all other objects (holes / bumpers)
                for(MachineObject object : objects)
                {
                    // Checks if pinball has touched an object through it's radius
                    if(positionDifference(currentPinball,object) <= radiusDifference(currentPinball,object))
                    {
                        //Collisions against holes
                        if(object.getColor() == Color.BLACK)
                        {
                            //If the pinball is smaller than the hole and collides
                            if(currentPinball.getRadius() <= object.getRadius())
                            {
                                erasePinball(currentPinball); // Undraws pinball from the machine
                                iteratePinball.remove(); // Remove pinball from array list
                   
                                break;
                            }
                            else
                            {
                                currentPinball.resetPoints(); // Reset the points of the pinball
                            }
                        }
                        // Collisions against bumpers
                        else if (object.getColor() == Color.GRAY)
                        {
                            currentPinball.objectCollide();
                            currentPinball.addPoints(2);
                        }
                    }
                }
                
                for(Pinball otherPinball : pinballs)
                {
                    // Makes sure pinball doesn't collide by itself
                    if(currentPinball.getXPosition() != otherPinball.getXPosition() && currentPinball.getYPosition() != otherPinball.getYPosition())
                    {
                        // Checks if pinballs have touched each other.
                        if(positionDifference(currentPinball,otherPinball) <= radiusDifference(currentPinball, otherPinball))
                        {   
                            currentPinball.collide();
                            currentPinball.addPoints(5); 
                        }
                    }
                }
            }
        }
    }
  
    /**
    * Erase a PinballObject from the view of the pinball machine
    * @param pinballObj The object to be erased
    */
    public void erasePinball(Pinball pinballObj)
    {
        machine.eraseCircle(pinballObj.getXPosition() - pinballObj.getIntRadius(), pinballObj.getYPosition()- pinballObj.getIntRadius(), pinballObj.getDiameter());
    
        drawMachine(); // Redraws walls on machine to stop the pinball from rubbing out the walls
        
        // Draw all holes and bumpers on the machine to prevent them from being rubbed out
         for(MachineObject object : objects)
        {
            drawObject(object);
        }
    }

    /**
    * Draw an PinballObject at its current position onto the view of the pinball machine
    * @param pinballObj The object to be drawn
    */
    public void draw(Pinball pinballObj)
    {
        machine.setForegroundColor(pinballObj.getColor()); //Sets colour of the pinball
        machine.fillCircle(pinballObj.getXPosition() - pinballObj.getIntRadius(), pinballObj.getYPosition() - pinballObj.getIntRadius(), pinballObj.getDiameter());
        machine.setForegroundColor(Color.BLACK); //Sets score colour to black
        // Place score in the centre of the pinball
        machine.drawString(Integer.toString(pinballObj.getPoints()), pinballObj.getXPosition() - 7, pinballObj.getYPosition() + 5);
    }
    
    /**
    * Draw an MachineObject at its current position onto the view of the pinball machine
    * @param newObject The object to be drawn
    */
    public void drawObject(MachineObject newObject)
    {
        machine.setForegroundColor(newObject.getColor()); // Sets colour of the object
        machine.fillCircle(newObject.getXPosition() - newObject.getIntRadius(), newObject.getYPosition() - newObject.getIntRadius(), newObject.getDiameter());
    }
    
    /**
     * Draws a string and allows a custom font to be set.
     * @param string The text which will be drawn
     * @param font The type of font which will be used
     * @param x The x coordinate of the text
     * @param y The y coordinate of the text
     */
    public void drawString(String string, Font font, int x, int y)
    {
        machine.setFont(font);
        machine.drawString(string, x, y);
    }

    /**
    * Draws all of the walls onto the machine.
    */
    public void drawMachine()
    {
    
        machine.setForegroundColor(Color.DARK_GRAY);
        
        machine.fillRectangle(0, 0, rightEdge, 10);  // top edge
        machine.fillRectangle(0, 0, 10, bottomEdge); // left edge
        machine.fillRectangle(rightEdge - 10, 0, 10, bottomEdge); // right edge
        
        machine.fillRectangle(0, bottomEdge - 10, lengthToGap, 10); // left-hand side of bottom edge
        machine.fillRectangle(rightEdge - lengthToGap, bottomEdge - 10, rightEdge, 10);     // right-hand side of bottom edge
        
        //Length of gap
        //machine.fillRectangle(250,370,100,10);
        
    }
    
    /**
     * Return the edge of the left-hand wall
     * @return  The left-hand wall
     */
    public int getLeftWall()
    {
        return leftEdge + 10;
    }
    
    /**
     * Return the edge of the right-hand wall
     * @return  The right-hand wall
     */
    public int getRightWall()
    {
        return rightEdge - 10;
    }
    
    /**
     * Return the edge of the top-hand wall
     * @return The top-hand wall
     */
    public int getTopWall()
    {
        return topEdge + 10;
    }
    
    /**
     * Returns the edge of the bottom-hand wall
     * @return the bottom-hand wall
     */
    public int getBottomWall()
    {
        return bottomEdge - 10;
    }
   
    
    /**
     * Introduces a small delay in ball movement, for smooth running
     */
    
    public void pauseMachine()
    {
        machine.wait(20);
    }
    
    /**
     * Resets the machine back to initial view, with no pinballs
     */
    public void resetMachine()
    {
        machine.erase();
        drawMachine();
    }
}
