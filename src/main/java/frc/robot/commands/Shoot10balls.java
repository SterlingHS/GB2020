// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 *
 */
public class Shoot10balls extends Command {

    private double balls_shot;
    private boolean ball_in_transferup;
    public Shoot10balls() {


        requires(Robot.shooter);

        
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        ball_in_transferup = Robot.intake.is_ball_transferup();
        if (ball_in_transferup == true) balls_shot=1;
        else balls_shot=0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //Robot.shooter.shootSpeed(-Robot.shooter.speed_from_distance());
        double speed;
        speed=SmartDashboard.getNumber("Set Speed",0.5);
        Robot.shooter.shootSpeed(speed);
        Robot.intake.transferup_UP(.7);
        Robot.intake.roller_IN();
        Robot.intake.frontintakeIN();
        if(Robot.intake.is_ball_transferup() && ball_in_transferup==false)
        {
            ball_in_transferup = true;
            balls_shot += 1;
        }
        if(Robot.intake.is_ball_transferup()==false && ball_in_transferup==true)
            ball_in_transferup=false;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return balls_shot==11;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.shooter.stop_shooter();
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