package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Drone implements Mechanism{
    public double raisePosition;
    public double launchPosition;
    private Servo raiseServo;
    private Servo launchServo;
    @Override
    public void init(HardwareMap hwMap)
    {
//        raiseServo = hwMap.get(Servo.class, "liftServo");
//        launchServo = hwMap.get(Servo.class, "launchServo");

    }
    public void RaiseLauncher()
    {
        raiseServo.setPosition(raisePosition);
    }
    public void LaunchPlane()
    {
        launchServo.setPosition(launchPosition);
    }
}
