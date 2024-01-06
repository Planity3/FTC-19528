package org.firstinspires.ftc.teamcode.Behaviors.Conditions;

import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

public class AreSlidesExtended extends Node{

    @Override
    public State tick(QQOpMode opmode)
    {
        if (opmode.robot.lift.areSlidesExtendedPastBoundary())
        {
            return State.SUCCESS;
        }
        return State.FAILURE;
    }
}
