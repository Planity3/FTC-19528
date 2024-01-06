package org.firstinspires.ftc.teamcode.Behaviors.Actions;

import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

public class MakeNormalDrive extends Node {
    @Override
    public State tick(QQOpMode opmode)
    {
        opmode.robot.mecanumDrive.setNormalDrive();
        return Node.State.SUCCESS;
    }
}
