package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by William Garland on 11/19/2017.
 */

@TeleOp(name = "ManualMode", group = "Manual")
public class ManualMode extends LinearOpMode {

    private static final double MOTOR_OFF = 0;
    private static final double EXTENSION_ARM_SPEED = 0.3;
    private static final double LIFT_ARM_SPEED = 0.3;
    private static final double CLAW_INCREMENT = 0.01;
    private static final double CLAW_POS_MIN = 0;
    private static final double CLAW_POS_MAX = 1;

    private HardwareData data;
    private double clawPos;

    @Override
    public void runOpMode() throws InterruptedException {
        this.data = new HardwareData(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            updateDriveMotors();
            updateLiftArm();
            updateExtensionArm();
            updateClaws();

            idle();
        }
    }

    private void updateDriveMotors() {
        // These are reversed because for some reason the controller or robot needed them to be switched
        data.getLeftDrive().setPower(gamepad1.right_stick_y);
        data.getRightDrive().setPower(gamepad1.left_stick_y);
    }

    private void updateLiftArm() {
        if (gamepad1.dpad_up || gamepad2.dpad_up)
            raiseArm();
        else if (gamepad1.dpad_down || gamepad2.dpad_down)
            lowerArm();
        else
            data.getLiftArm().setPower(MOTOR_OFF);
    }

    private void raiseArm() {
        data.getLiftArm().setPower(LIFT_ARM_SPEED);
    }

    private void lowerArm() {
        data.getLiftArm().setPower(-LIFT_ARM_SPEED);
    }

    private void updateExtensionArm() {
        if (gamepad1.y || gamepad2.y)
            extendArm();
        else if (gamepad1.a || gamepad2.a)
            retractArm();
        else
            data.getExtensionArm().setPower(MOTOR_OFF);
    }

    private void extendArm() {
        data.getExtensionArm().setPower(EXTENSION_ARM_SPEED);
    }

    private void retractArm() {
        data.getExtensionArm().setPower(-EXTENSION_ARM_SPEED);
    }

    private void updateClaws() {
        boolean rightTrigger = gamepad1.right_trigger > 0 || gamepad2.right_trigger > 0;
        if (rightTrigger)
            closeClaws();
        else if (gamepad1.right_bumper || gamepad2.right_bumper)
            openClaws();
    }

    private void openClaws() {
        increaseClawPos();
        updateClawPosition();
    }

    private void increaseClawPos() {
        clawPos = clamp(clawPos + CLAW_INCREMENT, CLAW_POS_MIN, CLAW_POS_MAX);
    }

    private void closeClaws() {
        decreaseClawPos();
        updateClawPosition();
    }

    private void decreaseClawPos() {
        clawPos = clamp(clawPos - CLAW_INCREMENT, CLAW_POS_MIN, CLAW_POS_MAX);
    }

    private void updateClawPosition() {
        data.getLeftClaw().setPosition(clawPos);
        data.getRightClaw().setPosition(1 - clawPos);
    }

    private double clamp(double var, double min, double max) {
        return var > max ? max : var < min ? min : var;
    }

}