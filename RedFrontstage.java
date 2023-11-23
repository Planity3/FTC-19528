@Autonomous
import AutonomousInterface;
public class RedFrontstage extends LinearOpMode {

  private DcMotor backLeft;
  private DcMotor backRight;
  private DcMotor frontLeft;
  private DcMotor frontRight;

  @Override
  public void runOpMode() {

    backLeft = hardwareMap.get(DcMotor.class, "backLeft");
    backRight = hardwareMap.get(DcMotor.class, "backRight");
    frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
    frontRight = hardwareMap.get(DcMotor.class, "frontRight");

 frontRight.setDirection(DcMotor.Direction.REVERSE);
backRight.setDirection(DcMotor.Direction.REVERSE);
    waitForStart();
    if (opModeIsActive()) {
      ApplyPowers(DriveForwards(1.0));
      sleep(1);
      StopMotors();
     }

  public void ApplyPowers(double[] powers)
  {
    frontLeft.setPower(powers[0]);
    frontRight.setPower(powers[1]);
    backLeft.setPower(powers[2]);
    backRight.setPower(powers[3]);
  }
  public void StopMotors()
  {
    frontLeft.setPower(0);
    frontRight.setPower(0);
    backLeft.setPower(0);
    backRight.setPower(0);
  }
 }
}