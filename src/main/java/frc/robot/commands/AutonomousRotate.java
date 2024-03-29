

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class AutonomousRotate extends Command {

    double starting_angle;
    double final_angle;
    double turn;
    public AutonomousRotate(double angle) {
        requires(Robot.drivingSystem);
        final_angle=angle;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        starting_angle = Robot.drivingSystem.getAngle();
        if(final_angle>starting_angle) turn = 0.5;
        else turn = -.5;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.drivingSystem.arcadeDrive(0, turn, RobotMap.DRIVER_SLOWDOWN);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if(Math.abs(starting_angle-final_angle)<5) return true;
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
