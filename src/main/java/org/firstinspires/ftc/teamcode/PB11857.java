package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 */
public class PB11857
{
    /* Public OpMode members. */
    public DcMotor  leftDrive   = null;
    public DcMotor  rightDrive  = null;
    public DcMotor  leftArm     = null;
    public Servo    left_claw    = null;
    public Servo    right_claw   = null;
    public TouchSensor sensor;

    public static final double MID_SERVO       =  0.0 ;
    public static final double ARM_UP_POWER    =  1 ;
    public static final double ARM_DOWN_POWER  = -1 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    private double leftClawPos = ARM_DOWN_POWER;
    private double rightClawPos = ARM_UP_POWER;

    /* Constructor */
    public PB11857(){
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        leftArm = hwMap.get(DcMotor.class, "left_arm");
        //sensor = hwMap.get(TouchSensor.class, "touch_sensor");
        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        leftArm.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        left_claw = hwMap.get(Servo.class, "left_claw");
        right_claw = hwMap.get(Servo.class, "right_claw");
        left_claw.setPosition(0);
        right_claw.setPosition(1);
    }

    private double clamp(double var, double min, double max) {
        return var > max ? max : var < min ? min : var;
    }
}

