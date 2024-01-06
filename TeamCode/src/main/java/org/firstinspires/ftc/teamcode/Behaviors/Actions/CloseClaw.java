package org.firstinspires.ftc.teamcode.Behaviors.Actions;

import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

public class CloseClaw extends Node {
    @Override
    public Node.State tick(QQOpMode opmode)
    {
        opmode.robot.claw.Close();
        return Node.State.SUCCESS;
    }
}
