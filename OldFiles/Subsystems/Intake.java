package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Intake implements Mechanism {
    public CRServo _intake;

    public float intakePower = 0;

    public void init(HardwareMap HwMap) {
        _intake = HWMap.get(CRServo.class, "intake");
    }
    public void update(){
        _intake.setPower(intakePower)
    }

    public void toggleIntake() {
        if (intake != 0) {
            intakePower = 1;
        } else {intakePower = 0;}
    }
    public void intakeOn() {
        intakePower = 1;
        }
    public void intakeOff() {
        intakePower = 0;
    }
}