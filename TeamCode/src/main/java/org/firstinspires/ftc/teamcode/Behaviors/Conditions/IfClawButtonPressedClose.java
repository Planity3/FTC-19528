package org.firstinspires.ftc.teamcode.Behaviors.Conditions;

import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

public class IfClawButtonPressedClose extends Node{
    @Override
    public State tick(QQOpMode opmode)
    {
        if(opmode.gamepad2.left_bumper)
        {
            if(opmode.robot.claw.clawOpen)
            {
                return State.FAILURE;
            }
            return State.SUCCESS;
        }
        return State.FAILURE;
    }
}
