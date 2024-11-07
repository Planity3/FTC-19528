package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MecanumDrive implements Mechanism {

    public static final double FAST_DRIVE_MULTIPLIER = 1.0;
    public final double SLOW_DRIVE_MULTIPLIER= 0.4;
    public final double REGULAR_DRIVE_MULTIPLIER = 0.6;

    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;

    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    public boolean doSlowDrive=false;

    //multiplied into our speed
    private double speedMultiplier = 1.0;

    public void init(HardwareMap HwMap) {
        backLeftMotor = HwMap.get(DcMotor.class, "backLeft");
        backRightMotor = HwMap.get(DcMotor.class, "backRight");
        frontRightMotor = HwMap.get(DcMotor.class, "frontRight");
        frontLeftMotor = HwMap.get(DcMotor.class, "frontLeft");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        List<DcMotor> motors = Arrays.asList(backLeftMotor, backRightMotor, frontRightMotor, frontLeftMotor);
        //loop through all motors and change settings
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public void setSlowDrive(){
        speedMultiplier = SLOW_DRIVE_MULTIPLIER;
    }
    public void setFastDrive(){
        speedMultiplier = FAST_DRIVE_MULTIPLIER;
    }
    public void setNormalDrive(){
        speedMultiplier = REGULAR_DRIVE_MULTIPLIER;
    }

    public void move(float leftX, float leftY, float rightX, float rightY) {
        double rotate = Math.hypot(-leftY, leftX);
        double robotAngle = Math.atan2(leftX, -leftY) - Math.PI / 4;
        double right = rightX;

        frontLeftPower = rotate * Math.cos(robotAngle) + right;
        frontRightPower =  rotate * Math.sin(robotAngle) - right;
        backLeftPower = rotate * Math.sin(robotAngle) + right;
        backRightPower = rotate * Math.cos(robotAngle) - right;
    }

    public void setPowers(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {

        frontLeftPower *= speedMultiplier;
        frontRightPower *= speedMultiplier;
        backLeftPower *= speedMultiplier;
        backRightPower *= speedMultiplier;

        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);

    }
    public double getAverageDrivetrainPower(){
        return (frontLeftPower+frontRightPower+backLeftPower+backRightPower)/4;
    }
}