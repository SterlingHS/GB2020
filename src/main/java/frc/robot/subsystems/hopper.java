/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.Counter;
// import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class hopper extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Talon for intake
  WPI_TalonSRX shoulder = new WPI_TalonSRX(RobotMap.SHOULDER_CAN_ID);
  WPI_TalonSRX intake = new WPI_TalonSRX(RobotMap.INTAKE_CAN_ID);

  // Counter on shoulder
  // Counter shoulder_counter;
  // double position;

  // Switch for ball
  DigitalInput ball_switch = new DigitalInput(RobotMap.BALL_SWITCH_DIO_ID);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // reset_counter();
  }

  public void stop_all() {
    stop_intake();
    stop_shoulder();
  }

  public void stop_intake() {
    intake.set(ControlMode.PercentOutput, 0);
  }

  public void stop_shoulder() {
    shoulder.set(ControlMode.PercentOutput, 0);
  }

    public void in() {
      intake.set(ControlMode.PercentOutput, RobotMap.MAX_SPEED_INTAKE);
  }

  public void out() {
    intake.set(ControlMode.PercentOutput, -RobotMap.MAX_SPEED_INTAKE);
  }

  public void up() {
    shoulder.set(ControlMode.PercentOutput, RobotMap.MAX_SPEED_SHOULDER);
    // position += shoulder_counter.get();
    // shoulder_counter.reset();
  }

  public void down() {
    shoulder.set(ControlMode.PercentOutput, -RobotMap.MAX_SPEED_SHOULDER);
    // position -= shoulder_counter.get();
    // shoulder_counter.reset();
  }

  public boolean is_ball_in()
  {
    return ball_switch.get();
  }

  /* public void init_counter()
  {
    shoulder_counter = new Counter(new DigitalInput(RobotMap.CounterIO));
    reset_counter();
  }

  public void reset_counter()
  {
    position = 0;
    shoulder_counter.reset();
  }

  public double read_counter()
  {
    return position;
  } */


}
