package org.firstinspires.ftc.teamcode;


public class AutonomousInterface {

	// todo: write your code here
	public void DriveForwards()
	{
	  motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
      motorRight = hardwareMap.get(DcMotor.class, "motorRight");
      frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
      frontRight = hardwareMap.get(DcMotor.class, "frontRight");
      color1 = hardwareMap.get(ColorSensor.class, "color1");
      distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
      imu = hardwareMap.get(BNO055IMU.class, "imu");
      // Put initialization blocks here
      motorLeft.setDirection(DcMotor.Direction.REVERSE);
      waitForStart();
      // Put run blocks here
      motorLeft.setPower(1);
      motorRight.setPower(1);
      while (opModeIsActive()) {
        // Put loop blocks here
      }
	}
}
