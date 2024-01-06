package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.LiftControl;

abstract public class QQOpMode extends OpMode {
    public Robot robot = new Robot();
    public LiftControl liftControl = new LiftControl(robot);
    public void init(){
        robot.init(hardwareMap);
    }
}
