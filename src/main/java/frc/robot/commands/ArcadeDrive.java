

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class ArcadeDrive extends Command {

    public ArcadeDrive() {

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
        double rotation = Robot.oi.joyDriver.getRawAxis(RobotMap.AXIS_ROTATION);
	
        Robot.drivingSystem.arcadeDrive(forward, rotation, RobotMap.DRIVER_SLOWDOWN);
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
