import java.awt.*;

/**
 * This class will inherit the MachineObject but set the object colour to be grey for the bumpers
 */
public class Bumper extends MachineObject
{
    public Bumper(int xPos, int yPos, double objectRadius, Machine machine)
    {
        super(xPos, yPos, Color.GRAY, objectRadius, machine); // Set colour to grey
    }
}