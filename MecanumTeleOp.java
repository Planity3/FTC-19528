package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            
            
            double r = Math.hypot(-gamepad1.left_stick_y, gamepad1.left_stick_x);
            double robotAngle = Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            rightX *= 2;
            double v1 = r * Math.cos(robotAngle) + rightX;
            double v2 = r * Math.sin(robotAngle) - rightX;
            double v3 = r * Math.sin(robotAngle) + rightX;
            double v4 = r * Math.cos(robotAngle) - rightX;
            
            int powerMultiplier = 1;
            
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
            
            frontLeftMotor.setPower(v1 * powerMultiplier);
            frontRightMotor.setPower(v2 * powerMultiplier);
            backLeftMotor.setPower(v3 * powerMultiplier);
            backRightMotor.setPower(v4 * powerMultiplier);

            //2nd controler section

            //plane controls
            boolean planeToggle = false;

            if(gamepad2.y && !planeToggle)
            {
                planeToggle == true;
            }
            if(planeToggle && gamepad2.x)
            {
                planeServo.setPosition(0);
            }



        }
    }
}
