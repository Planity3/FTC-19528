package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class Claw implements Mechanism{
    boolean clawOpen;
    private Servo clawLeft;
    private Servo clawRight;

    @Override
    public void init(HardwareMap hwMap) {
        clawLeft  = hwMap.get(Servo.class, "clawLeft");
        clawRight = hwMap.get(Servo.class, "clawRight");
        clawRight.setDirection(Servo.Direction.REVERSE);
        clawLeft.setPosition(0.35);
        clawRight.setPosition(0.2);
        clawOpen = false;
    }

    public void Open()
    {
        clawLeft.setPosition(0.123);
        clawRight.setPosition(0.45);
    }
    public void Close()
    {
        clawLeft.setPosition(0.35);
        clawRight.setPosition(0.2);
    }

}
