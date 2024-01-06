package org.firstinspires.ftc.teamcode.Behaviors.Actions;

import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

public class OpenClaw extends Node{
    @Override
    public Node.State tick(QQOpMode opmode)
    {
        opmode.robot.claw.Open();
        return State.SUCCESS;
    }
}
