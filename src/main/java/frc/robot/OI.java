
package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;


public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    
    public JoystickButton intakeBt;
    public JoystickButton intake_to_transferupBt;
    public JoystickButton shootBt;
    public JoystickButton shoulderUpBt;
    public JoystickButton shoulderDownBt;

    public Joystick joyDriver;
    public Joystick joyCo;

    

    public OI() {
        

        joyCo = new Joystick(RobotMap.JOYCO_USB_PORT);
        joyDriver = new Joystick(RobotMap.JOYDRIVER_USB_PORT);

        intakeBt = new JoystickButton(joyDriver, RobotMap.driverButtonIntakeBt);
        intakeBt.whileHeld(new IntakeBalls());
        intake_to_transferupBt = new JoystickButton(joyDriver, RobotMap.driverButtonIntake_to_transferupBt);
        intake_to_transferupBt.whileHeld(new IntakeBallsToTransferup());

        shootBt = new JoystickButton(joyDriver,RobotMap.driverButtonActiveShooter);
        // shootBt.whileHeld(new AimShoot());
        shootBt.whileHeld(new ActivateShooter());

        shoulderUpBt = new JoystickButton(joyDriver,RobotMap.driverButtonShoulderUp);
        shoulderUpBt.whileHeld(new ShoulderUP());

        shoulderDownBt = new JoystickButton(joyDriver,RobotMap.driverButtonShoulderDown);
        shoulderDownBt.whileHeld(new ShoulderDOWN());

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("IntakeBalls", new IntakeBalls());
        SmartDashboard.putData("ActivateShooter", new ActivateShooter());
    
    }
        
    public Joystick getJoyDriver() {
        return joyDriver;
    }

    public Joystick getJoyCo() {
        return joyCo;
    }
}

