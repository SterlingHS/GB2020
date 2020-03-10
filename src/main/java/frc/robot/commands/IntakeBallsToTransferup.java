
package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class IntakeBallsToTransferup extends Command {
    public IntakeBallsToTransferup() {

        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (Robot.intake.number_of_balls() < 4)
            Robot.intake.frontintakeIN();
        else Robot.intake.stop_frontintake();
        Robot.intake.roller_to_sensor();
        Robot.intake.transferup_to_sensor();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.intake.stop_frontintake();
        Robot.intake.stop_roller();
        Robot.intake.stop_transferup();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }

}
