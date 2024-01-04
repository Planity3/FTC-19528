package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Lift implements Mechanism{
    public LiftPositions getManipulatorPosition() {
        return manipulatorPosition;
    }

    public void setManipulatorPosition(LiftPositions manipulatorPosition) {
        this.manipulatorPosition = manipulatorPosition;
    }





    public enum LiftPositions{
        FLOOR_POSITION,
        LOW_POSITION,
        MIDDLE_POSITION,
        TOP_POSITION

    }
    public static final int ARE_SLIDES_EXTENDED_BOUNDARY = 100; //TODO tune value

    private LiftPositions manipulatorPosition;
    private static final int LIFT_POSITION_SAFETY_BOTTOM = -50;
    private static final int LIFT_POSITION_SAFETY_TOP = 343455; //TODO need to fix

    private static final int LOW_POSITION = 700;
    private static final int MIDDLE_POSITION = 1600;
    private static final int TOP_POSITION = 2400;
    private static final int FLOOR_POSITION =0  ;
    private static final int PIXEL_HEIGHT = 271;
    private final int MANUAL_CHANGE = 50;
    private DcMotorEx rightLiftMotor;
    private DcMotorEx leftLiftMotor;




    private double desiredPosition;
    private double sumOfErrors;
    private double lastError;
    static double K_P = 0.007;
    static double K_I = 0.0001;
    static double K_D = 0.2;
    public double motorPower;



    @Override
    public void init(HardwareMap hwMap) {
        rightLiftMotor = hwMap.get(DcMotorEx.class, "rightSlide");
        rightLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftLiftMotor = hwMap.get(DcMotorEx.class, "leftSlide");
        leftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setManipulatorPosition(LiftPositions.FLOOR_POSITION);
        desiredPosition = leftLiftMotor.getCurrentPosition();



    }
    private void setDesiredPosition(double newPosition){
        desiredPosition = newPosition;
        sumOfErrors = 0;
        lastError = 0;
    }

    public boolean areSlidesExtendedPastBoundary(){
        return currentPosition()> ARE_SLIDES_EXTENDED_BOUNDARY;

    }
    public void update(){

        double error;
        error = getDesiredPosition() - currentPosition();
        sumOfErrors = sumOfErrors + error;

        //motorPower =K_P * error + K_I * sumOfErrors + K_D * (error - lastError);
        motorPower = K_P *error;
        lastError = error;

        rightLiftMotor.setPower(motorPower);
        leftLiftMotor.setPower(motorPower);
    }



    public void manualLiftUp(){
        setDesiredPosition(currentPosition() + MANUAL_CHANGE);
        if(getDesiredPosition() > LIFT_POSITION_SAFETY_TOP){
            desiredPosition = LIFT_POSITION_SAFETY_TOP;
        }
    }public void manualLiftDown(){
        setDesiredPosition(currentPosition()-MANUAL_CHANGE);
        if(getDesiredPosition() < LIFT_POSITION_SAFETY_BOTTOM){
            setDesiredPosition(LIFT_POSITION_SAFETY_BOTTOM);

        }
    }
    public double getPower(){
        return motorPower;
    }
    public double getDesiredPosition(){
        return desiredPosition;
    }


    public void goToLow(){
        setDesiredPosition(LOW_POSITION);
    }
    public void goToMiddle(){
        setDesiredPosition(MIDDLE_POSITION);
    }
    public void goToTop(){
        setDesiredPosition(TOP_POSITION);
    }
    public void goToFloor(){
        setDesiredPosition(FLOOR_POSITION);
    }
    public void upOnePixel(){
        setDesiredPosition(currentPosition() + PIXEL_HEIGHT);
    }
    public void downOnePixel(){
        setDesiredPosition(currentPosition() - PIXEL_HEIGHT);
    }
    public double currentPosition(){
        return ((rightLiftMotor.getCurrentPosition() + leftLiftMotor.getCurrentPosition())/2.0);
    }

}
