package org.firstinspires.ftc.teamcode.Behaviors.Actions;

import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

public class MakeSlowDrive extends Node {
    @Override
    public State tick(QQOpMode opmode)
    {
        opmode.robot.mecanumDrive.setSlowDrive();
        return State.SUCCESS;
    }
}
