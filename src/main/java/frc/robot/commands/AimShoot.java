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
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *
 */
public class AimShoot extends Command {

    double startTime;
    boolean newTimer=true;
    boolean shoot=false;
    boolean correct_angle=false;
    
    public AimShoot() {


        requires(Robot.shooter);
        requires(Robot.intake);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        double tx = Robot.shooter.Read_Limelight_tx();
        double tv = Robot.shooter.Read_Limelight_tv();
        double speed;
        if(tv == 1)
        {   
            speed = Robot.shooter.speed_from_distance();
            speed = 160;
            // TEST IF DISTANCE IS TOO FAR
            Robot.shooter.shootSpeed(speed);
            if (tx > 1 && correct_angle==false) Robot.shooter.rotate_right();
            if (tx < -1 && correct_angle==false) Robot.shooter.rotate_left();
            if ((tx < 1 && tx > -1) || correct_angle)
            {            
                correct_angle = true;
                Robot.shooter.stop_rotator();
                if (speed-RobotMap.SpeedError<Robot.shooter.Read_Speed_Shooter())
                    if(shoot || Robot.shooter.Read_Speed_Shooter()>speed-RobotMap.SpeedError)    
                        move_balls();
            }
        }
        else end();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.shooter.stop_all();
        Robot.intake.stop_transferup();
        Robot.intake.stop_roller();
        Robot.intake.stop_frontintake();
        newTimer = true;
        shoot = false;
        correct_angle = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }

    protected void move_balls() {
        if(newTimer == true) 
        {
            startTime = System.currentTimeMillis();
            newTimer = false;
            System.out.println("Timer start");
        }
        if(System.currentTimeMillis() - startTime > 200)
            Robot.intake.transferup_UP(RobotMap.MAX_SPEED_TRANSFERUP);
        if(System.currentTimeMillis() - startTime > 300)
            Robot.intake.roller_IN();
        if(System.currentTimeMillis() - startTime > 400)
            Robot.intake.frontintakeIN(); 
    }
}
