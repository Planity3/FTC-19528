package org.firstinspires.ftc.teamcode.Behaviors;

import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

abstract public class Node {

    public enum State {
        SUCCESS,
        FAILURE,
        RUNNING
    }

    public abstract State tick(QQOpMode opMode);
}
