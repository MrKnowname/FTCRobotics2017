package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="OpMode Manual", group="Manual")
public class OpModeManual extends LinearOpMode {

    static final double INCREMENT   = 0.01;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor leftArm = null;
    private Servo left_claw = null;
    private Servo right_claw = null;
    private TouchSensor sensor;

    double position = 0;
    double posLeft = 0;
    double posRight= 1;

    private PB11857 robot = new PB11857();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.init(hardwareMap);
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftArm = hardwareMap.get(DcMotor.class, "left_arm");
        left_claw = hardwareMap.get(Servo.class, "left_claw");
        right_claw = hardwareMap.get(Servo.class, "right_claw");
        //sensor = hardwareMap.get(TouchSensor.class, "touch_sensor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.leftDrive.setPower(-gamepad1.left_stick_y);
            robot.rightDrive.setPower(-gamepad1.right_stick_y);

            // If the 'a' button is pressed, close the claws
            if ((gamepad1.a || gamepad2.a) /*&& robot.leftArm.getCurrentPosition() >= 0 && robot.leftArm.getCurrentPosition() < 2500*/) {
                telemetry.addLine("A WAS PRESSED");
                robot.leftArm.setPower(0.3);
            } else if (gamepad1.b || gamepad2.b /* && robot.leftArm.getCurrentPosition() >= 0 && robot.leftArm.getCurrentPosition() < 2500*/)
                robot.leftArm.setPower(-0.3);
            else if (robot.leftArm.getCurrentPosition() < 0)
                robot.leftArm.setPower(0.1);
            else if (robot.leftArm.getCurrentPosition() > 2500)
                robot.leftArm.setPower(-0.1);
            else {
                    robot.leftArm.setPower(0);}

            if (gamepad1.x || gamepad2.x) {
                posLeft+=INCREMENT;
                posRight-=INCREMENT;
            }

            else if (gamepad1.y || gamepad2.y) {
                posLeft-=INCREMENT;
                posRight+=INCREMENT;
            }

            if(posLeft>0.8) posLeft = 0.8;
            if(posRight>1) posRight = 1;
            if(posLeft<0) posLeft = 0;
            if(posRight<0.2) posRight = 0.2;

            robot.left_claw.setPosition(posLeft);
            robot.right_claw.setPosition(posRight);
                //leftClaw.getPosition();
            telemetry.addLine(String.valueOf(right_claw.getPosition()) + " right servo");
            telemetry.addLine(String.valueOf(left_claw.getPosition()) + " left servo");
            telemetry.update();

            // Send calculated power to wheels
            //leftDrive.setPower(leftPower);
            //rightDrive.setPower(rightPower);

            /*if (sensor.isPressed()) {
                telemetry.addLine("Touch sensor pressed");
                telemetry.update();
            }*/

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
