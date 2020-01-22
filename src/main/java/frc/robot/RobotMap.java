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
        
    
    // Joystick configuration
    public static final int DRIVER_USB_PORT_0 = 0;
    public static final int ACTUATOR_USB_PORT_1 = 1;
    
    public static final int AXIS_FORWARD = 1;
    public static final int AXIS_ROTATION = 2;

    // Hopper configuration
    public static final int HOPPER1_CAN_ID = 20;
    public static final int HOPPER2_CAN_ID = 21;

    public static final int HOPPER1_DIO_ID = 0;
    public static final int HOPPER2_DIO_ID = 1;

    public static final double HOPPER1_SPEED = 0.5;
    public static final double HOPPER2_SPEED = 0.5;
}	
