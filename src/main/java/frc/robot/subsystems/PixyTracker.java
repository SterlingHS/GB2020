/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.SPILink;

/**
 * Add your docs here.
 */
public class PixyTracker extends Subsystem {

  private static Pixy2 pixy = null;
  private static double x , y;
  private static boolean ball;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    pixy = Pixy2.createInstance(new SPILink()); // Creates a new Pixy2 camera using SPILink
		pixy.init(); // Initializes the camera and prepares to send/receive data
		pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		pixy.setLED(255, 255, 255); // Sets the RGB LED to full white
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static Pixy2 getPixyCamera1() {
		return pixy;
  }
  
  public static void getBiggestBlock() 
  {
		// Gets the number of "blocks", identified targets, that match signature 1 on the Pixy2,
		// does not wait for new data if none is available,
		// and limits the number of returned blocks to 25, for a slight increase in efficiency
		int blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
		System.out.println("Found " + blockCount + " blocks!"); // Reports number of blocks found
		if (blockCount >= 0)
		{
			ArrayList<Block> blocks = pixy.getCCC().getBlocks(); // Gets a list of all blocks found by the Pixy2
			Block largestBlock = null;
			for (Block block : blocks) 
			{ // Loops through all blocks and finds the widest one
				if (largestBlock == null) {
					largestBlock = block;
				} else if (block.getWidth() > largestBlock.getWidth()) {
					largestBlock = block;
				}
			
			x = largestBlock.getX();
			y = largestBlock.getY();
			ball = true;
			}
		}
		else
		{
			x = 0;
			y = 0;
			ball = false;
		}


	}
	public double Read_Pixy_x()
	{
		return x;
	}
	public double Read_Pixy_y()
	{
		return y;
	}
	public boolean Read_Pixy_is_Ball()
	{
		getBiggestBlock();
		return ball;
    }
}

