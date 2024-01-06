package org.firstinspires.ftc.teamcode.Behaviors;

import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

import java.util.Arrays;
import java.util.List;
public class Sequence extends Node{
    List<Node> children;

    public Sequence(Node ... a) {
        this.children = Arrays.asList(a);
    }

    @Override
    public State tick(QQOpMode opmode) {
        for (Node child : children) {
            State state = child.tick(opmode);
            if (state == State.FAILURE) {
                return State.FAILURE;
            } else if (state == State.RUNNING) {
                return State.RUNNING;
            }
        }
        return State.SUCCESS;
    }
}
