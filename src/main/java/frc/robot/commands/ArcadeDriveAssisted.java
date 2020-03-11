
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.PixyTracker;


public class ArcadeDriveAssisted extends Command {

    public ArcadeDriveAssisted() {

        requires(Robot.drivingSystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double forward = Robot.oi.joyDriver.getRawAxis(RobotMap.AXIS_FORWARD);
        double turn = Robot.oi.joyDriver.getRawAxis(RobotMap.AXIS_ROTATION);
        double x;
        if(Robot.oi.joyDriver.getRawButton(RobotMap.ASSISTED_DRIVING))
        {
            PixyTracker.getBiggestBlock();
            if(Robot.pixyTracker.Read_Pixy_is_Ball())
            {
                Robot.intake.frontintakeIN();
                x = Robot.pixyTracker.Read_Pixy_x()-160;
                turn = 0;
                if(x>50) turn=RobotMap.ASSISTED_SPEED; 
                if(x<0) turn=-RobotMap.ASSISTED_SPEED;
            }
        }
        Robot.drivingSystem.arcadeDrive(forward, turn, RobotMap.DRIVER_SLOWDOWN);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
