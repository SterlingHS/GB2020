
package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class IntakeBallsToTransferup extends Command {
    private int number_of_balls;
    private boolean flag_ball_count;

    public IntakeBallsToTransferup() {

        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        number_of_balls = 0;
        flag_ball_count = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        count_ball();
        if (number_of_balls < 4)
            Robot.intake.frontintakeIN();
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

    public void count_ball() {
        if (flag_ball_count == false && Robot.intake.is_ball_in_intake())
        {
            flag_ball_count = true;
            number_of_balls += 1;
        }
        if (flag_ball_count == true && Robot.intake.is_ball_in_intake() == false) 
        {
            flag_ball_count = false;
        }
    }
}
