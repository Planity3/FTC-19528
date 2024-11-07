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
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");

        Servo clawLeft  = hardwareMap.get(Servo.class, "clawLeft");
        Servo clawRight = hardwareMap.get(Servo.class, "clawRight");
        DcMotor clawPitch = hardwareMap.dcMotor.get("clawPitch");

        // Servo planeServo = hardwareMap.get(Servo.class, "planeServo");


        DcMotor slideLeft = hardwareMap.dcMotor.get("slideLeft");
        DcMotor slideRight = hardwareMap.dcMotor.get("slideRight");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        clawRight.setDirection(Servo.Direction.REVERSE);

        //makes the motor stop fast as opposed to slowly fading off "jerk stop"
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawPitch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

// variables used to store the state of the claw for use in the code later
        boolean planeToggle = false;
        boolean clawPosition = false;
        boolean clawHold = false;
        int clawPos = 0;
        int slidePos = 0;
        //false = closed
//reset the  law position
        waitForStart();
        clawLeft.setPosition(0.35);
        clawRight.setPosition(0.2); 
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
            
            int powerMultiplier = 1;
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

            //plane controls
            // if(gamepad2.y && !planeToggle)
            // {
            //     planeToggle == true;
            // }
            // if(planeToggle && gamepad2.x)
            // {
            //     planeServo.setPosition(0);
            // }
            
            //drive controls for claw
            if(gamepad2.left_bumper)
            {
                while(gamepad2.left_bumper)
                {
                    
                }
                clawPosition = !clawPosition;
                if(clawPosition)
                {
                     //open
                   clawLeft.setPosition(0.123);
                   clawRight.setPosition(0.45);
                 }
                 else
                 {
                     //close
                   clawLeft.setPosition(0.35);
                   clawRight.setPosition(0.2);   
                 }
            }
            //claw pitch
            
            
            double clawPitchPos = gamepad2.right_stick_x;
            clawPitch.setPower(clawPitchPos / 0.75);
            
            // Slide Controls
            // double slidePower = gamepad2.left_stick_y;
            // slideLeft.setPower(slidePower);
            // slideRight.setPower(-slidePower);
            
            if(gamepad2.dpad_up)
            {
                slidePos += 1;
            }
            if(gamepad2.dpad_down)
            {
                slidePos -= 1;
            }
            slideLeft.setTargetPosition(slidePos);
            slideRight.setTargerPosition(slidePos);
            //outputs the motors to the screen so we can debug what is going on
            telemetry.addData("Front_Left tgt pwr", "pwr: " + String.format("%.2f", v1));
            telemetry.addData("Front_Right tgt pwr", "pwr: " + String.format("%.2f", v2));
            telemetry.addData("Back_Left tgt pwr", "pwr: " + String.format("%.2f", v3));
            telemetry.addData("Back_Right tgt pwr", "pwr: " + String.format("%.2f", v4));
            telemetry.addData("Slide tgt pwr", "pwr: " + String.format("%.2f", slidePower));
            telemetry.addData("Claw Rotation tgt pwr", "pwr: " + String.format("%.2f", clawPitchPos));
            telemetry.update();
        }
    }
}

public class PIDController {
    public PIDController() { 
        static double K_P = 0.4;
        static double K_I = 0.0005;
        static double K_D = 0.005;
    }
}