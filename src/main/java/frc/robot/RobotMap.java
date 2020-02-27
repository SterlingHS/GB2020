package frc.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    
    // Joysitck configuration
    public static int JOYDRIVER_USB_PORT = 0;
    public static int JOYCO_USB_PORT = 1;
    public static int driverButtonIntakeBt = 1;
    public static int driverButtonIntake_to_transferupBt = 2;
    public static final int AXIS_FORWARD = 1;
    public static final int AXIS_ROTATION = 2;

    // CAN IDs
    public static int DRIVETRAIN_LEFT_1 = 11;
    public static int DRIVETRAIN_LEFT_2 = 13;
    public static int DRIVETRAIN_RIGHT_1 = 12;
    public static int DRIVETRAIN_RIGHT_2 = 14;
    public static int FRONTINTAKE_CAN_ID = 19;
    public static int ROLLER_CAN_ID = 21;
    public static int TRANSFERUP_CAN_ID = 20;

    // Driver configuration
    public static double DRIVER_SLOWDOWN = .6; 

    // Intake
    public static double MAX_SPEED_FRONTINTAKE = 0.5;
    public static double MAX_SPEED_ROLLER = 0.5; 
    public static double MAX_SPEED_TRANSFERUP = 0.6;
    public static double STOCK_SPEED_TRANSFERUP = 0.4;
    public static int TRANSFERUP_DIO_ID = 7;
    
    // Variables for Shooter
    public static final double Shooter_Speed = 0.6;
    public static final double inputShooter_Speed = 0.5;
    public static final double rotateShooter_Speed = 0.2;
    public static double ball_counter = 0;
}	
