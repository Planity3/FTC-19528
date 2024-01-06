package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Robot;

public class LiftControl {
    Lift.LiftPositions manipulatorPosition;
    Lift lift;

    public LiftControl(Robot robot)
    {
        lift = robot.lift;
    }
    public boolean atPosition(Lift.LiftPositions liftPosition){
        return (manipulatorPosition==liftPosition);
    }
    public void manualLiftUp(){
        lift.manualLiftUp();
    }public void manualLiftDown(){
        lift.manualLiftDown();
    }

    public void goToLow(){
        lift.goToLow();
    }
    public void goToMiddle(){
        lift.goToMiddle();
    }
    public void goToTop(){
        goToTop();
    }
    public void goToFloor(){
        lift.goToFloor();
    }
    public void upOnePixel(){
        lift.upOnePixel();
    }
    public void downOnePixel(){
        lift.downOnePixel();
    }
    public void setManipulatorPosition(Lift.LiftPositions liftPosition){
        manipulatorPosition = liftPosition;

    }
}
