package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class PIDController {
    public PIDController() { 
        static double K_P = 0.4;
        static double K_I = 0.0005;
        static double K_D = 0.005;

        public double desiredPosition;
        public double sumOfErrors;
        public double lastError;
        public double motorPower;
        public DcMotor motor;
    }
    public double Update() {
        double error;
        error = getDesiredPosition() - currentPosition();
        sumOfErrors = sumOfErrors + error;

        motorPower = K_P * error + K_I * sumOfErrors + K_D * (error - lastError);
        motorPower = K_P *error;
        motor.setPower(motorPower);
        lastError = error;
    }

    public void setDesiredPosition() {
        desiredPosition = newPosition;
        sumOfErrors = 0;
        lastError = 0;
    }

    public double getDesiredPosition() {
        return desiredPosition;
    }

    public void currentPosition() {
        return motor.getCurrentPosition;
    }

    public void setMotor(DcMotor _motor)
    {
        motor = _motor;
    }


}