import java.awt.*;

/**
 * Class which draws out a type of object on the machine.
 * The bumpers, holes and pinball will in inherit this class.
 */
public class MachineObject
{
    private int currentXLocation;
    private int currentYLocation;
    private Color colour;
    private double radius;
    private Machine machine;

    /**
     * Set the properties of the machine object
     * @param xPos The x coordinate of the object
     * @param yPos The x coordinate of the object
     * @param objectColor The colour of the object
     * @param objectRadius The radius of the object
     * @param machine What machine the object is located at
     */
    public MachineObject(int xPos, int yPos, Color objectColor, double objectRadius, Machine machine)
    {
        currentXLocation = xPos;
        currentYLocation = yPos;
        colour = objectColor;
        radius = objectRadius;
        this.machine = machine;
    }

    /**
     * Retrieve the current machine
     * @return machine The current machine
     */
    public Machine getMachine()
    {
        return machine;
    }

    /**
     * return the horizontal position of this object
     * @return currentXLocation The x coordinate of the object
     */
    public int getXPosition()
    {
        return currentXLocation;
    }

    /**
     * return the vertical position of this object
     * @return currentXLocation The y coordinate of the object
     */
    public int getYPosition()
    {
        return currentYLocation;
    }

    /**
     * Sets the new x coordinate of this object
     * @param newX The new x coordinate
     */
    public void setXPosition(int newX)
    {
        currentXLocation = newX;
    }

    /**
     * Sets the new y coordinate of this object
     * @param newY The new y coordinate
     */
    public void setYPosition(int newY)
    {
        currentYLocation = newY;
    }

    /**
     * Sets the radius of the object
     * @param newRadius The new radius to replace the current radius
     */
    public void setRadius(double newRadius)
    {
        radius = newRadius;
    }

    /**
     * Changes the colour of the object
     * @param newColour The new colour which will replace the current colour
     */
    public void setColor(Color newColour)
    {
        colour = newColour;
    }

    /**
     * return the radius of this object
     * @return radius The radius of the object
     */
    public double getRadius()
    {
        return radius;
    }

    /**
     * Retrieves an integer version of the radius
     * @return r The radius rounded to an integer
     */
    public int getIntRadius()
    {
        int r = (int) Math.round(radius);
        return r;
    }

    /**
     * return the diameter of this object
     * @return The radius doubled
     */
    public int getDiameter()
    {
        return 2 * getIntRadius();
    }
    
    /**
     * return the colour of this object
     * @return colour The colour of the object
     */
    public Color getColor()
    {
        return colour;
    }
}
