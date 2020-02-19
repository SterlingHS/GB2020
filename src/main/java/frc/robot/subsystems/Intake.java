
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends Subsystem {

  WPI_VictorSPX frontintake = new WPI_VictorSPX(RobotMap.FRONTINTAKE_CAN_ID);

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
    
}


