// Golden Bots - FRC 6651 - Season 2020

package frc.robot;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {

Command autonomousCommand;
SendableChooser<Command> chooser = new SendableChooser<>();

public static OI oi;
public static Intake intake;
public static Shooter shooter;
public static Climber climber;
public static WheelOfFortune wheelOfFortune;
public static DrivingSystem drivingSystem;
public static PixyTracker pixyTracker;

    @Override
    public void robotInit() {

        intake = new Intake();
        shooter = new Shooter();
        climber = new Climber();
        // wheelOfFortune = new WheelOfFortune();
        drivingSystem = new DrivingSystem();
        pixyTracker = new PixyTracker();

        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_RobotBuilder);

        // Add commands to Autonomous Sendable Chooser
        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber("Set Speed",100);
        SmartDashboard.putNumber("Kp", 1);
        SmartDashboard.putNumber("Kd", 0);
        SmartDashboard.putNumber("PID", 0);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        update_smartboard();
    }

    public void update_smartboard(){
        //SmartDashboard.putBoolean("Rotator Right", rotator...);
        SmartDashboard.putBoolean("Transfer Up Ball", intake.is_ball_transferup());
        SmartDashboard.putBoolean("Left Rotator", shooter.is_left_rotator());
        SmartDashboard.putBoolean("Right Rotator", shooter.is_right_rotator());
        SmartDashboard.putNumber("Limelight TX", shooter.Read_Limelight_tx());
        SmartDashboard.putNumber("Limelight TY", shooter.Read_Limelight_ty());
        SmartDashboard.putNumber("Limelight TA", shooter.Read_Limelight_ta());
        SmartDashboard.putNumber("Limelight TV", shooter.Read_Limelight_tv());
        SmartDashboard.putNumber("Distance to wall",shooter.Distance_to_target());
        SmartDashboard.putNumber("Power Percent",shooter.percent_from_distance());
        SmartDashboard.putNumber("Power Speed",shooter.speed_from_distance());
        SmartDashboard.putBoolean("Pixy Ball",pixyTracker.Read_Pixy_is_Ball());
        SmartDashboard.putNumber("Speed Shooter",shooter.Read_Speed_Shooter());
    }
}
