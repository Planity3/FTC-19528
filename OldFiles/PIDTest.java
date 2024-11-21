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

        //DcMotor horizontalSlide = hardwareMap.dcMotor.get("hSlide");
        DcMotor verticalSlide = hardwareMap.dcMotor.get("vSlide");

        //horizontalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        vSlidePID.setMotor(verticalSlide);

        waitForStart();
        if (isStopRequested()) return;
            while (opModeIsActive()) {
            
            /*
            //horizontal slide
            double hslidePower = gamepad2.left_stick_x;
            horizontalSlide.setPower(hslidePower);

            //vertical slide
            double vslidePower = gamepad2.right_stick_y;
            verticalSlide.setPower(vslidePower);
            */
            

            //outputs the motors to the screen so we can debug what is going on

            telemetry.addData("Vertical Slide tgt pwr", "pwr: " + String.format("%.2f", vslidePower));
            //telemetry.addData("Horizontal Slide tgt pwr", "pwr: " + String.format("%.2f", hslidePower));
            telemetry.addData("Vertical slide current position", "pos: " + String.format("%.2f", verticalSlide.getCurrentPosition()));
            telemetry.addData("Vertical slide target position", "pos: " + String.format("%.2f", vSlidePID.getDesiredPosition()));
            telemetry.update();
        }
    }
}

