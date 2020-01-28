// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.subsystems;


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.PIDOutput;
// import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Shooter extends Subsystem 
{

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // private PWMTalonSRX shooterCtr1;
    // private PWMTalonSRX shooterCtr2;
    // private SpeedControllerGroup shooterCtr;
    // private PWMTalonSRX inputShooterCtr;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        ç shooterCtr1 = new PWMTalonSRX(13);
        // addChild("ShooterCtr1",shooterCtr1);
        shooterCtr1.setInverted(false);
        
        PWMTalonSRX shooterCtr2 = new PWMTalonSRX(14);
        // addChild("ShooterCtr2",shooterCtr2);
        shooterCtr2.setInverted(false);
        
        shooterCtr = new SpeedControllerGroup(shooterCtr1, shooterCtr2  );
        addChild("ShooterCtr",shooterCtr);

        
        inputShooterCtr = new PWMTalonSRX(15);
        addChild("InputShooterCtr",inputShooterCtr);
        inputShooterCtr.setInverted(false);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    @Override
    public void initDefaultCommand() 
    {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() 
    {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.



    public void shoot() 
    {
        shooterCtr.set(ControlMode.PercentOutput, 1);
    }

    public void in()
    {
        inputShooterCtr.set(ControlMode.PercentOutput,1);
    }
 
    public void out()
    {
        shooterCtr.set(ControlMode.PercentOutput,1);
    }

    public void stop_all()
    {
        stop_shooterCtr;
        stop_inputShooterCtr;
    }

    public void stop_shooterCtr();
    {
        shooterCtr.set(ControlMode.PercentOutput,0);
    }

    public void stop_inputShooterCtr()
    {
        inputShooterCtr.set(ControlMode.PercentOutput,0);
    }
}