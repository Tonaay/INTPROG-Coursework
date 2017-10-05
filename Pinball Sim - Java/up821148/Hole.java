import java.awt.*;

/**
 * This class is inherited from Machine object which will create a hole for the machine
 * The hole colour is set to black.
 */

public class Hole extends MachineObject
{
    public Hole(int xPos, int yPos, double objectRadius, Machine machine)
    {
        super(xPos, yPos, Color.BLACK, objectRadius, machine); // Set colour to black
    }
}