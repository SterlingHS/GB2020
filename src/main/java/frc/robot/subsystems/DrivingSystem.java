
package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



/**
 *
 */
public class DrivingSystem extends Subsystem {

    private final WPI_TalonSRX speedController1;
    private final WPI_TalonSRX speedController2;
    private final SpeedControllerGroup speedController_Leftside;
    private final WPI_TalonSRX speedController3;
    private final WPI_TalonSRX speedController4;
    private final SpeedControllerGroup speedController_Rightside;
    private final DifferentialDrive differentialDrive1;

    public DrivingSystem() {
        speedController1 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_1);
        addChild("Speed Controller 1", speedController1);
        speedController1.setInverted(false);

        speedController2 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_2);
        addChild("Speed Controller 2", speedController2);
        speedController2.setInverted(false);

        speedController_Leftside = new SpeedControllerGroup(speedController1, speedController2);
        addChild("Speed Controller- Left side", speedController_Leftside);

        speedController3 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_1);
        addChild("Speed Controller 3", speedController3);
        speedController3.setInverted(false);

        speedController4 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_2);
        addChild("Speed Controller 4", speedController4);
        speedController4.setInverted(false);

        speedController_Rightside = new SpeedControllerGroup(speedController3, speedController4);
        addChild("Speed Controller - Right side", speedController_Rightside);

        differentialDrive1 = new DifferentialDrive(speedController_Leftside, speedController_Rightside);
        addChild("Differential Drive 1", differentialDrive1);
        differentialDrive1.setSafetyEnabled(true);
        differentialDrive1.setExpiration(0.1);
        differentialDrive1.setMaxOutput(1.0);

    }

    @Override
    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

        setDefaultCommand(new ArcadeDrive());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void arcadeDrive(double forward, double turn, double slowdown_factor) 
    {
        if(slowdown_factor < 1 && slowdown_factor > 0)
        {
            forward*=slowdown_factor;
            turn*=slowdown_factor;
        }
        differentialDrive1.arcadeDrive(0.5, turn, true); 
        // true = squaredInputs - If set, decreases the input sensitivity at low speeds.
    }

}

