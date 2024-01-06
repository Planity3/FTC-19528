package org.firstinspires.ftc.teamcode.Behaviors.Conditions;

import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

public class IfLeftTriggerPressed extends Node {
    public static final double TRIGGER_THRESHOLD = 0.1;

    @Override
    public State tick(QQOpMode opmode) {
        if (opmode.gamepad1.left_trigger> TRIGGER_THRESHOLD){
            return State.SUCCESS;
        }
        return  State.FAILURE;
    }
}
