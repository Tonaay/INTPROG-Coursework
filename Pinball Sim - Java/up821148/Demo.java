import java.awt.*;
/**
 * Class to demonstrate functionality of the Pinball machine
 * 
 * @author 821148
 * @version 1
 */
public class Demo
{
    private Machine machine;
    /**
     * Constructor for objects of class Demo
     */
    public Demo()
    {
       
    }

    /**
     * Sample demo of pinball simulation with all objects drawn onto the machine
     */
    public void sampleDemo()
    {
        // sample demo
        machine = new Machine();
        
        // Creates all the pinballs
        Ball8 obj1 = new Ball8(50, 200, -5, -10, Color.RED, 50, machine);
        Ball8 obj2 = new Ball8(124, 300, 4, 3, Color.BLUE, 40, machine);
        Ball8 obj3 = new Ball8(300, 300, 6, -4, Color.GREEN, 25, machine);
        Ball4 obj4 = new Ball4(450, 200, 5, 7, Color.YELLOW, 25, machine);
        Ball4 obj5 = new Ball4(300, 400, 4, -4, Color.MAGENTA, 25, machine);
        Ball4 obj6 = new Ball4(300, 200, 4, -6, Color.CYAN, 20, machine);
        //(200, 200, 11, -7, Color.CYAN, 30, machine);

        // Adds all pinballs to the array list
        machine.addPinball(obj1);
        machine.addPinball(obj2);
        machine.addPinball(obj3);
        machine.addPinball(obj4);
        machine.addPinball(obj5);
        machine.addPinball(obj6);

        // Creates the hole objects
        Hole hole = new Hole(60, 80, 25, machine);
        Hole hole2 = new Hole(400, 130, 40, machine);

        // Creates the bumpers
        Bumper bumper = new Bumper(150, 130, 20, machine);
        Bumper bumper2 = new Bumper(570, 470, 20, machine);

        // Add holes and bumpers to the MachineObject array list
        machine.addObject(bumper);
        machine.addObject(bumper2);
        machine.addObject(hole);
        machine.addObject(hole2);

        // Draws objects onto the machine
        machine.drawObject(hole);
        machine.drawObject(hole2);
 
        machine.drawObject(bumper);
        machine.drawObject(bumper2);

        // Start the simulation
        machine.startMachine();
    }
    
    /**
     * Method to test pinball 8's collisions with each other
     */
    public void testBall8()
    {
         // Reset machine
        machine = new Machine();

        // Creates all the pinballs
        Ball8 obj1 = new Ball8(50, 200, -2, -7, Color.RED, 50, machine);
        Ball8 obj2 = new Ball8(100, 350, 1, 8, Color.BLUE, 25, machine);
        Ball8 obj3 = new Ball8(300, 300, -6, -4, Color.GREEN, 30, machine);
        Ball8 obj4 = new Ball8(200, 100, 1, -10, Color.RED, 25, machine);
        
        Hole hole = new Hole(60, 80, 25, machine);
        machine.drawObject(hole);
        machine.addObject(hole);

        // Adds all pinballs to the array list
        machine.addPinball(obj1);
        machine.addPinball(obj2);
        machine.addPinball(obj3);
        machine.addPinball(obj4);
        
        // Start the simulation
        machine.startMachine();
    }
    
     /**
     * Method to test pinball 4's collisions with each other
     */
    public void testBall4()
    {
         // Reset machine
        machine = new Machine();

        // Creates all the pinballs
        Ball4 obj1 = new Ball4(50, 200, 8, -4, Color.YELLOW, 25, machine);
        Ball4 obj2 = new Ball4(100, 350, 5, 6, Color.MAGENTA, 25, machine);
        Ball4 obj3 = new Ball4(300, 300, 3, -4, Color.CYAN, 25, machine);
        Ball4 obj4 = new Ball4(200, 100, 4, -12, Color.MAGENTA, 25, machine);
        
        Hole hole = new Hole(60, 80, 25, machine);
        machine.drawObject(hole);
        machine.addObject(hole);
        
         // Creates the bumpers
        Bumper bumper = new Bumper(40, 380, 20, machine);
        Bumper bumper2 = new Bumper(570, 470, 20, machine);
        machine.drawObject(bumper);
        machine.drawObject(bumper2);
        machine.addObject(bumper);
        machine.addObject(bumper2);

        // Adds all pinballs to the array list
        machine.addPinball(obj1);
        machine.addPinball(obj2);
        machine.addPinball(obj3);
        machine.addPinball(obj4);
        
        // Start the simulation
        machine.startMachine();
    }
    
    /**
     * Method to test whether pinballs collide to bumpers correctly
     */
    public void testBumpers()
    {
             // Reset machine
        machine = new Machine();

        // Creates all the pinballs
        Ball8 obj1 = new Ball8(50, 200, -7, -4, Color.RED, 50, machine);
        Ball8 obj2 = new Ball8(300, 200, 6, 4, Color.GREEN, 20, machine);
        Ball4 obj3 = new Ball4(450, 200, 1, 4, Color.YELLOW, 30, machine);
        Ball4 obj4 = new Ball4(300, 400, 3, 3, Color.MAGENTA, 25, machine);
        Ball8 obj5 = new Ball8(300, 300, 0, -2, Color.BLUE, 30, machine);
        
        Bumper bumper = new Bumper(300, 130, 15, machine);
        Bumper bumper2 = new Bumper(400, 250, 15, machine);
        
        machine.addObject(bumper);
        machine.addObject(bumper2);
        
        machine.drawObject(bumper);
        machine.drawObject(bumper2);
        
        // Adds all pinballs to the array list
        machine.addPinball(obj1);
        machine.addPinball(obj2);
        machine.addPinball(obj3);
        machine.addPinball(obj4);
        machine.addPinball(obj5);
        
        machine.startMachine();
    }
    
    /**
     * Method to test whether pinballs collide with holes correctly
     */
    public void testHoles()
    {
            // Reset machine
        machine = new Machine();

        // Creates all the pinballs
        Ball8 obj1 = new Ball8(50, 200, -4, -4, Color.RED, 50, machine);
        Ball8 obj2 = new Ball8(300, 200, 6, 4, Color.GREEN, 20, machine);
        Ball4 obj3 = new Ball4(450, 200, 5, 7, Color.YELLOW, 30, machine);
        Ball4 obj4 = new Ball4(100, 400, 6, 5, Color.MAGENTA, 25, machine);
        
       
        // Creates the hole objects
        Hole hole = new Hole(60, 80, 25, machine);
        Hole hole2 = new Hole(400, 130, 40, machine);

          
        machine.addObject(hole);
        machine.addObject(hole2);
        
        machine.drawObject(hole);
        machine.drawObject(hole2);
        
        // Adds all pinballs to the array list
        machine.addPinball(obj1);
        machine.addPinball(obj2);
        machine.addPinball(obj3);
        machine.addPinball(obj4);
      
        machine.startMachine();
    }
    
    /**
     * Test whether the simulation ends when the pinball falls through the gap
     */
    public void testGap()
    {
        // Reset machine
        machine = new Machine();
        
        Ball8 obj1 = new Ball8(300, 300, 0, -6, Color.RED, 30, machine);
        Ball8 obj2 = new Ball8(100, 200, 3, 7, Color.GREEN, 20, machine);
        
        machine.addPinball(obj1);
        machine.addPinball(obj2);
        
        machine.startMachine();
    }
    
    public static void main(String[] args) {
        new Demo().testHoles();
    }
}
