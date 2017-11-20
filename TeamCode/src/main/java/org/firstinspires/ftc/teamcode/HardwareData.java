package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by William Garland on 11/19/2017.
 */

public class HardwareData {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor liftArm;
    private DcMotor extensionArm;
    private Servo leftClaw;
    private Servo rightClaw;
    private IrSeekerSensor irSensor;
    private ColorSensor colorSensor;

    public HardwareData(HardwareMap hardware) {
        this.leftDrive = hardware.get(DcMotor.class, "left_drive");
        this.rightDrive = hardware.get(DcMotor.class, "right_drive");
        this.liftArm = hardware.get(DcMotor.class, "lift_arm");
        this.extensionArm = hardware.get(DcMotor.class, "extension_arm");
        this.leftClaw = hardware.get(Servo.class, "left_claw");
        this.rightClaw = hardware.get(Servo.class, "right_claw");
        this.irSensor = hardware.get(IrSeekerSensor.class, "ir_sensor");
        this.colorSensor = hardware.get(ColorSensor.class, "color_sensor");

        this.leftDrive.setDirection(DcMotor.Direction.FORWARD);
        this.rightDrive.setDirection(DcMotor.Direction.REVERSE);
        this.liftArm.setDirection(DcMotor.Direction.FORWARD);
        this.extensionArm.setDirection(DcMotor.Direction.FORWARD);

        this.leftDrive.setPower(0);
        this.rightDrive.setPower(0);
        this.liftArm.setPower(0);
        this.extensionArm.setPower(0);

        this.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.liftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.extensionArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public DcMotor getLeftDrive() {
        return leftDrive;
    }

    public DcMotor getRightDrive() {
        return rightDrive;
    }

    public DcMotor getLiftArm() {
        return liftArm;
    }

    public DcMotor getExtensionArm() {
        return extensionArm;
    }

    public Servo getLeftClaw() {
        return leftClaw;
    }

    public Servo getRightClaw() {
        return rightClaw;
    }

    public IrSeekerSensor getIrSensor() {
        return irSensor;
    }

    public ColorSensor getColorSensor() {
        return colorSensor;
    }

}
