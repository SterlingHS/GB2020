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

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;

public class Shooter extends Subsystem {
    private WPI_VictorSPX shooterCtr1 = new WPI_VictorSPX(RobotMap.SHOOTER1_CAN_ID);
    private WPI_VictorSPX shooterCtr2 = new WPI_VictorSPX(RobotMap.SHOOTER2_CAN_ID);
    private SpeedControllerGroup shooterCtr = new SpeedControllerGroup(shooterCtr1, shooterCtr2);
    private WPI_VictorSPX rotateShooterCtr = new WPI_VictorSPX(RobotMap.ROTATOR_CAN_ID);

    private DigitalInput left_switch_rotator = new DigitalInput(RobotMap.LEFT_SWITCH_DIO);
    private DigitalInput right_switch_rotator = new DigitalInput(RobotMap.RIGHT_SWITCH_DIO);
    private Encoder shooter_encoder = new Encoder(RobotMap.SHOOTER_ENCODER1, RobotMap.SHOOTER_ENCODER2);
    
    // PID Shooter spped
    private double kDt = 0.02;
    private final TrapezoidProfile.Constraints m_constraints =
    new TrapezoidProfile.Constraints(1.75, 0.75);
    private final ProfiledPIDController shooter_PID =
      new ProfiledPIDController(SmartDashboard.getNumber("Kp", 1), 0.0, SmartDashboard.getNumber("Kd", 0), m_constraints, kDt);

    /*public void Shooter() 
    {
        percent = 1;
    }*/
    
    @Override
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() 
    {
        // Put code here to be run every loop

    }

    // Methods for shooter
    public void shootSpeed(double desired_speed) 
    {
        double percent_from_max=desired_speed/RobotMap.MAX_SHOOTER_SPEED;
        double current_speed = Read_Speed_Shooter();
        double error=desired_speed-current_speed;
        SmartDashboard.putNumber("Error", error);
        shooterCtr.set(-desired_speed/RobotMap.MAX_SHOOTER_SPEED);
        // Run controller and update motor output
        /*shooter_PID.setGoal(desired_speed);
        double PID_calculate = shooter_PID.calculate(current_speed);
        shooterCtr.set(percent_from_max+PID_calculate);
        SmartDashboard.putNumber("PID",PID_calculate);*/

        // Brute force speed
        shooterCtr.set(-desired_speed/RobotMap.MAX_SHOOTER_SPEED);
    }

    public void shootPercent(double speed) 
    {
        shooterCtr.set(-speed);
    }

    public void clean_shoot() // shoot inward to "clean" the shooter
    {
        shooterCtr.set(RobotMap.inputShooter_Speed);
    }

    public void stop_shooter()
    {
        shooterCtr.stopMotor();
    }

    // Methods for shooter
    public void stop_rotator()
    {
        rotateShooterCtr.stopMotor();
    }

    public void rotate_right()
    {
        if(is_right_rotator() == false)
            rotateShooterCtr.set(RobotMap.rotateShooter_Speed);
        else stop_rotator();
    }

    public void rotate_left()
    {
        if(is_left_rotator() == false)
            rotateShooterCtr.set(-RobotMap.rotateShooter_Speed);
        else stop_rotator();
    }

    public void stop_all()
    {
        stop_shooter();
        stop_rotator();
    }

    // Sensors on rotator
    public boolean is_right_rotator(){
        return right_switch_rotator.get();
    }

    public boolean is_left_rotator(){
        return left_switch_rotator.get();
    }

    // Limelight readers
    public double Read_Limelight_tx()
	{
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);	
    }

    public double Read_Limelight_ty()
	{
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
	}
    
    public double Read_Limelight_ta()
	{
		return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    }
    
    public double Read_Limelight_tv()
	{
		return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    }

    public double Distance_to_target()
    {
        double angle_of_elevation = Read_Limelight_ty()+27.56417632;
        double distance=5/Math.atan(angle_of_elevation*Math.PI/180);
        return distance;
    }
    
    // Encoder
    public double Read_Speed_Shooter()
    {
        return shooter_encoder.getRate()/1000;
    }

    public double Read_Distance_Shooter()
    {
        return shooter_encoder.getDistance();
    }

    public void Reset_Encoder_Shooter()
    {
        shooter_encoder.reset();
    }

    // Limelight-Speed calculation depending on distance
    public double percent_from_distance()
    {   
        if(Read_Limelight_tv()==1)
        {
            double distance = Distance_to_target();
            // linear approximation
            double slope=(.9-.4)/(20-10);
            double percent = slope*(distance-10)+.4;

            return percent;
        }
        else return -1;
    }
    public double speed_from_distance()
    {   
        if(Read_Limelight_tv()==1)
        {
            double distance = Distance_to_target();
            // linear approximation
            //double slope=(.9-.4)/(20-10);
            //double percent = slope*(distance-10)+.4;
            // Exponential Approximation
            double percent=((20*Math.pow(Math.E, .0754*distance))/100);
            double speed = percent*200/0.9;
            return speed;
        }
        else return -1;
    }
}