@Autonomous
import AutonomousInterface;
public class RedBackstage extends LinearOpMode {

  private DcMotor backLeft;
  private DcMotor backRight;
  private DcMotor frontLeft;
  private DcMotor frontRight;
    
  @Override
  public void runOpMode() {

    backLeft = hardwareMap.get(DcMotor.class, "RightDrive");
    backRight = hardwareMap.get(DcMotor.class, "LeftDrive");
    frontLeft = hardwareMap.get(DcMotor.class, "Arm");
    frontRight = hardwareMap.get(DcMotor.class, "Int");
    LeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    
    waitForStart();
    if (opModeIsActive()) {
      ApplyPowers(DriveForwards(1));
      sleep(1);
      StopMotors();
     }

public void ApplyPowers(double[] powers)
{
fl.setPower(powers[0]);
fr.setPower(powers[1]);
bl.setPower(powers[2]);
br.setPower(powers[3]);
}
public void StopMotors()
{
fl.setPower(0);
fr.setPower(0);
bl.setPower(0);
br.setPower(0);
}
 }