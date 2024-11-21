package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration

        PIDController vSlidePID = new PIDController();
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");

        CRServo intake = hardwareMap.get(CRServo.class, "intakeServo");
        Servo outtake = hardwareMap.get(Servo.class, "outtakeServo");

        DcMotor horizontalSlide = hardwareMap.dcMotor.get("hSlide");
        DcMotor verticalSlide = hardwareMap.dcMotor.get("vSlide");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //makes the motor stop fast as opposed to slowly fading off "jerk stop"
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double servoPower = 0;
        boolean intakeStatus = false;

        vSlidePID.setMotor(verticalSlide);

        waitForStart();
        if (isStopRequested()) return;
// main program running loop
        while (opModeIsActive()) {
            
            // this is the code that makes the controler drive the robot
            double r = Math.hypot(-gamepad1.left_stick_y, gamepad1.left_stick_x);
            double robotAngle = Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            rightX *= 2;
            double v1 = r * Math.cos(robotAngle) + rightX;
            double v2 = r * Math.sin(robotAngle) - rightX;
            double v3 = r * Math.sin(robotAngle) + rightX;
            double v4 = r * Math.cos(robotAngle) - rightX;

            //hard code for each type of turn because the code above doesn't work properly
            if(rightX > 0)
            {
                v1 = Math.abs(v1);
                v3 = -Math.abs(v3);
                
                v2 = Math.abs(v2);
                v4 = -Math.abs(v4);
            }
            else if(rightX < 0)
            {
                v1 = -Math.abs(v1);
                v3 = Math.abs(v3);
                
                v2 = -Math.abs(v2);
                v4 = Math.abs(v4);
            }

            //setting power to the motors for speed level
            frontLeftMotor.setPower(v1 * powerMultiplier);
            frontRightMotor.setPower(v2 * powerMultiplier);
            backLeftMotor.setPower(v3 * powerMultiplier);
            backRightMotor.setPower(v4 * powerMultiplier);

            
            //intake
            if(gamepad2.left_bumper)
            {
                while(gamepad2.left_bumper)
                {
                    
                }
                intakeStatus = !intakeStatus;
                if(intakeStatus)
                {
                    intakePower = 1;
                 }
                 else
                 {
                   intakePower = 0;
                 }
            }

            //horizontal slide
            double hslidePower = gamepad2.left_stick_x;
            horizontalSlide.setPower(hslidePower);

            //vertical slide
            double vslidePower = gamepad2.right_stick_y;
            verticalSlide.setPower(vslidePower);

            

            //outputs the motors to the screen so we can debug what is going on

            telemetry.addData("Front_Left tgt pwr", "pwr: " + String.format("%.2f", v1));
            telemetry.addData("Front_Right tgt pwr", "pwr: " + String.format("%.2f", v2));
            telemetry.addData("Back_Left tgt pwr", "pwr: " + String.format("%.2f", v3));
            telemetry.addData("Back_Right tgt pwr", "pwr: " + String.format("%.2f", v4));
            telemetry.addData("Vertical Slide tgt pwr", "pwr: " + String.format("%.2f", vslidePower));
            telemetry.addData("Horizontal Slide tgt pwr", "pwr: " + String.format("%.2f", hslidePower));
            telemetry.update();
        }
    }
}

