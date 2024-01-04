package org.firstinspires.ftc.teamcode;
import androidx.lifecycle.Lifecycle;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.Arm;
import org.firstinspires.ftc.teamcode.Hardware.Claw;
import org.firstinspires.ftc.teamcode.Hardware.ControlHub;
import org.firstinspires.ftc.teamcode.Hardware.Drone;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Hardware.Mechanism;
import org.firstinspires.ftc.teamcode.Hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.Navigation;

import java.util.Arrays;
import java.util.List;

public class Robot {
    List<Mechanism> mechanisms;
    public MecanumDrive mecanumDrive;
    public ControlHub controlHub;
    public Claw claw;
    public Lift lift;
    public Arm arm;
    public Drone drone;
    public Navigation nav;


     public Robot() {
        mecanumDrive = new MecanumDrive();
        controlHub = new ControlHub();
        claw = new Claw();
        lift = new Lift();
        arm = new Arm();
        drone = new Drone();
        nav = new Navigation(controlHub, mecanumDrive);

         mechanisms = Arrays.asList(
                 mecanumDrive,
                 controlHub,
                 claw,
                 lift,
                 arm,
                 drone
         );
     }

     public void makeDriveOnly()
     {
         mechanisms = Arrays.asList(
                 mecanumDrive,
                 controlHub
         );
     }
    public void init(HardwareMap hwMap) {
        for (Mechanism mechanism : mechanisms) {
            mechanism.init(hwMap);
        }
    }

    public List<Mechanism> getMechanismList() {
        return mechanisms;
    }
}