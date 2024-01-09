package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
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
    private DcMotor rightLiftMotor;
    private DcMotor leftLiftMotor;




    private double desiredPosition;
    private double sumOfErrors;
    private double lastError;
    static double K_P = 0.007;
    static double K_I = 0.0001;
    static double K_D = 0.02;
    public double motorPower;



    @Override
    public void init(HardwareMap hwMap) {
        rightLiftMotor = hwMap.get(DcMotor.class, "slideRight");
        rightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftLiftMotor = hwMap.get(DcMotor.class, "slideLeft");
        leftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftLiftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        setManipulatorPosition(LiftPositions.FLOOR_POSITION);
        desiredPosition = leftLiftMotor.getCurrentPosition();



    }
    public void setDesiredPosition(double newPosition){
        desiredPosition = newPosition;
        sumOfErrors = 0;
        lastError = 0;
    }

    public boolean areSlidesExtendedPastBoundary(){
        return currentPosition()> ARE_SLIDES_EXTENDED_BOUNDARY;

    }
    public void update(Telemetry telemetry, double slidePower){
//
//        double error;
//        error = getDesiredPosition() - currentPosition();
//        sumOfErrors = sumOfErrors + error;
//
////        motorPower = K_P * error + K_I * sumOfErrors + K_D * (error - lastError);
//        motorPower = K_P *error;
//        lastError = error;

        leftLiftMotor.setPower(slidePower);
         rightLiftMotor.setPower(-slidePower);
//        telemetry.addData("Desired power Lift", "" + String.format("%.2f", motorPower));
//        telemetry.addData("Current position Lift", "" + String.format("%.2f", currentPosition()));
//        telemetry.addData("Current position left Lift", "" + String.format("%d", leftLiftMotor.getCurrentPosition()));
//        telemetry.addData("Current position right Lift", "" + String.format("%d", rightLiftMotor.getCurrentPosition()));
//
//        telemetry.addData("Desired Position Lift", "" + String.format("%.2f", desiredPosition));
        telemetry.update();
    }
    public void update(Telemetry telemetry ){
//
//        double error;
//        error = getDesiredPosition() - currentPosition();
//        sumOfErrors = sumOfErrors + error;
//
////        motorPower = K_P * error + K_I * sumOfErrors + K_D * (error - lastError);
//        motorPower = K_P *error;
//        lastError = error;


//        telemetry.addData("Desired power Lift", "" + String.format("%.2f", motorPower));
//        telemetry.addData("Current position Lift", "" + String.format("%.2f", currentPosition()));
//        telemetry.addData("Current position left Lift", "" + String.format("%d", leftLiftMotor.getCurrentPosition()));
//        telemetry.addData("Current position right Lift", "" + String.format("%d", rightLiftMotor.getCurrentPosition()));
//
//        telemetry.addData("Desired Position Lift", "" + String.format("%.2f", desiredPosition));
        telemetry.update();
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
