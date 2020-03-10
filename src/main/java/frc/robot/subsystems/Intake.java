
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends Subsystem {

  WPI_VictorSPX frontintake = new WPI_VictorSPX(RobotMap.FRONTINTAKE_CAN_ID);
  WPI_VictorSPX roller = new WPI_VictorSPX(RobotMap.ROLLER_CAN_ID);
  WPI_VictorSPX transferup = new WPI_VictorSPX(RobotMap.TRANSFERUP_CAN_ID);
  DigitalInput ball_sensor_transferup = new DigitalInput(RobotMap.TRANSFERUP_DIO_ID);
  DigitalInput ball_sensor_inner = new DigitalInput(RobotMap.INNER_SWITCH_DIO_ID);
  DigitalInput ball_sensor_right = new DigitalInput(RobotMap.RIGHT_SWITCH_DIO_ID);
  DigitalInput ball_sensor_left = new DigitalInput(RobotMap.LEFT_SWITCH_DIO_ID);
  public int number_of_balls = RobotMap.NUMBER_OF_BALLS_INIT;
  public boolean ball_flag_in = false;
  public boolean ball_flag_out = false;

  public void initDefaultCommand()
  {
  }
  
  public void stop_frontintake() {
    frontintake.set(ControlMode.PercentOutput, 0);
  }
  
  
  public void frontintakeIN() {
    frontintake.set(ControlMode.PercentOutput, RobotMap.MAX_SPEED_FRONTINTAKE);
  }
    
  public void frontintakeOUT() {
    frontintake.set(ControlMode.PercentOutput, -RobotMap.MAX_SPEED_FRONTINTAKE);
  }
  
    
  public void stop_roller() {
    roller.set(0);
  }

  public void roller_IN() {
    roller.set(ControlMode.PercentOutput, RobotMap.MAX_SPEED_ROLLER);
  }

  public void roller_OUT() {
    roller.set(ControlMode.PercentOutput, -RobotMap.MAX_SPEED_ROLLER);
  }

  public void stop_transferup() {
    transferup.set(0);
  }

  public void transferup_UP(double speed) {
    transferup.set(ControlMode.PercentOutput, speed);
  }

  public void transferup_DOWN() {
    transferup.set(ControlMode.PercentOutput, -RobotMap.MAX_SPEED_TRANSFERUP);
  }

  public boolean is_ball_transferup() {
    return ball_sensor_transferup.get();
  }

  public boolean is_ball_inner() {
    return ball_sensor_inner.get();
  }

  public boolean is_ball_in_intake() {
    return ball_sensor_left.get() || ball_sensor_right.get();
  }

  public void transferup_to_sensor() {
    if (is_ball_transferup()) stop_transferup();
    else transferup_UP(RobotMap.STOCK_SPEED_TRANSFERUP);
  }

  public void roller_to_sensor() {
    if (is_ball_inner()) stop_roller();
    else roller_IN();
  }

  public void reset_balls(){
    number_of_balls=0;
  }

  public void add_ball(){
    number_of_balls+=1;
  }

  public void remove_ball(){
    number_of_balls-=1;
  }

  public int number_of_balls(){
    return number_of_balls;
  }

  public void count_balls() {
    // Counts balls going into the intake
    if(is_ball_in_intake() && ball_flag_in==false)
    {
      ball_flag_in=true;
      if(number_of_balls<4)
        number_of_balls+=1;
    }
    if(is_ball_in_intake()!=true && ball_flag_in)
    {
      ball_flag_in=false;
    }

    // Counts balls coming out of the transfer up
    if(is_ball_transferup() && ball_flag_out==false)
    {
      ball_flag_out=true;
    }
    if(is_ball_transferup()==false && ball_flag_out)
    {
      ball_flag_out=false;
      if(number_of_balls>0) number_of_balls-=1;
    }
  }
}



