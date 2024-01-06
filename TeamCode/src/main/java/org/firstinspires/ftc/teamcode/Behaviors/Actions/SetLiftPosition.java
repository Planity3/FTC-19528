package org.firstinspires.ftc.teamcode.Behaviors.Actions;

import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.OpModes.QQOpMode;

public class SetLiftPosition extends Node {
    @Override
    public State tick(QQOpMode opmode) {
        if(opmode.gamepad2.a){
            opmode.liftControl.setManipulatorPosition(Lift.LiftPositions.LOW_POSITION);
        } else if(opmode.gamepad2.x) {
            opmode.liftControl.setManipulatorPosition(Lift.LiftPositions.MIDDLE_POSITION);

        } else if(opmode.gamepad2.y){
            opmode.liftControl.setManipulatorPosition(Lift.LiftPositions.TOP_POSITION);
        } else if (opmode.gamepad2.b){
            opmode.liftControl.setManipulatorPosition(Lift.LiftPositions.FLOOR_POSITION);
        }

        return State.RUNNING;
    }
}
