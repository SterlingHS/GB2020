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

  // Talons for hoppers
  WPI_TalonSRX hopper1 = new WPI_TalonSRX(RobotMap.HOPPER1_CAN_ID);
  WPI_TalonSRX hopper2 = new WPI_TalonSRX(RobotMap.HOPPER2_CAN_ID);

  // Switch for hoppers (Detect if a ball is in the correct place)
  DigitalInput hopper1_switch = new DigitalInput(RobotMap.HOPPER1_DIO_ID);
  DigitalInput hopper2_switch = new DigitalInput(RobotMap.HOPPER2_DIO_ID);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void stop_all() 
  {
    stop_hopper1();
    stop_hopper2();
  }

  public void stop_hopper1() 
  {
    hopper1.set(ControlMode.PercentOutput, 0);
  }

  public void stop_hopper2() 
  {
    hopper2.set(ControlMode.PercentOutput, 0);
  }

  public void in_hopper1() 
  {
      hopper1.set(ControlMode.PercentOutput, RobotMap.HOPPER1_SPEED);
  }

  public void out_hopper1() 
  {
      hopper1.set(ControlMode.PercentOutput, -RobotMap.HOPPER1_SPEED);
  }

  public void in_hopper2() 
  {
    hopper1.set(ControlMode.PercentOutput, RobotMap.HOPPER2_SPEED);
  }

  public void out_hopper2() 
  {
    hopper1.set(ControlMode.PercentOutput, -RobotMap.HOPPER2_SPEED);
  }

  public boolean is_ball_in_hopper1()
  {
    return hopper1_switch.get();
  }

  public boolean is_ball_in_hopper2()
  {
    return hopper2_switch.get();
  }
}
