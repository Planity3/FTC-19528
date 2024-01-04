package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;
abstract public class QQOpMode extends OpMode {
    public Robot robot = new Robot();

    public void init(){
        robot.init(hardwareMap);
    }
}
