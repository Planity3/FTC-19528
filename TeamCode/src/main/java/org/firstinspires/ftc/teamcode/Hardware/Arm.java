package org.firstinspires.ftc.teamcode.Hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm implements Mechanism{
    private double desiredPosition;
    private double backdropPosition = -1515;
    private double sumOfErrors;
    private double lastError;
    static double K_P = 0.4;
    static double K_I = 0.005;
    static double K_D = 0.005;
    private double startPos;
    public double motorPower;
    private DcMotor clawPitch;

    private boolean toggle = false;
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

    public void update(Telemetry telemetry){
        double error;
        error = getDesiredPosition() - currentPosition();
        sumOfErrors = sumOfErrors + error;

//        motorPower = K_P * error;
        motorPower = K_P * error + K_I * sumOfErrors + K_D * (error - lastError);

        lastError = error;

        if(toggle && clawPitch.getCurrentPosition() > -150) {
            motorPower = 0;
        }
        if(!toggle && clawPitch.getCurrentPosition() < -1350) {
            motorPower = 0;
        }
        clawPitch.setPower(motorPower);
        telemetry.addData("Desired Position Lift", "" + String.format("%d", clawPitch.getCurrentPosition()));
        telemetry.update();
    }

    public void goToBackdropPosition()
    {
        desiredPosition = backdropPosition;
        toggle = false;
    }
    public void goToGroundPosition()
    {
        desiredPosition = startPos;
        toggle = true;
    }
    public double getDesiredPosition(){
        return desiredPosition;
    }
    public double currentPosition(){
        return clawPitch.getCurrentPosition();
    }
}
