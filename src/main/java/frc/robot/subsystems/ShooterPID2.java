/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// https://github.com/wpilibsuite/allwpilib/blob/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/frisbeebot/subsystems/ShooterSubsystem.java

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ShooterPID2 extends PIDSubsystem {
  
  
  private final SimpleMotorFeedforward shooterFeedforward =
      new SimpleMotorFeedforward(RobotMap.kSVolts,
                                 RobotMap.kVVoltSecondsPerRotation);
  
  
  /**
   * Creates a new ShooterPID2.
   */
  public ShooterPID2() {
    super(
        // The PIDController used by the subsystem
        new PIDController(RobotMap.SHOOTER_PID_KP, 
                          RobotMap.SHOOTER_PID_KI, 
                          RobotMap.SHOOTER_PID_KD));
    setSetpoint(Robot.shooter.speed_from_distance());
        

  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  private final SimpleMotorFeedforward shooterFeedforward =
  Robot.shooter.shootPercent(output+shooterFeedforward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return Robot.shooter.Read_Speed_Shooter();
  }
}
