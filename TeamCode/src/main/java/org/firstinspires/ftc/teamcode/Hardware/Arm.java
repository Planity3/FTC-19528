package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm implements Mechanism{
    private double desiredPosition;
    private double backdropPosition;
    private double sumOfErrors;
    private double lastError;
    static double K_P = 0.5;
    static double K_I = 0.005;
    static double K_D = 0.01;
    private double startPos;
    public double motorPower;
    private DcMotor clawPitch;


    @Override
    public void init(HardwareMap hwMap)
    {
        clawPitch = hwMap.dcMotor.get("clawPitch");
        clawPitch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawPitch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawPitch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        desiredPosition = clawPitch.getCurrentPosition();
        startPos = desiredPosition;
    }

    public void update()
    {
        double error;
        error = getDesiredPosition() - currentPosition();
        sumOfErrors = sumOfErrors + error;

        motorPower = K_P * error;
        lastError = error;

        clawPitch.setPower(motorPower);
    }

    public void goToBackdropPosition()
    {
        desiredPosition = backdropPosition;
    }
    public void goToGroundPosition()
    {
        desiredPosition = startPos;
    }
    public double getDesiredPosition(){
        return desiredPosition;
    }
    public double currentPosition(){
        return clawPitch.getCurrentPosition();
    }
}
