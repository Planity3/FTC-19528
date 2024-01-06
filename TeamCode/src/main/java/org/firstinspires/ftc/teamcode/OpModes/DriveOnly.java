package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name="DriveOnly")
public class DriveOnly extends QQOpMode{
    @Override
    public void init() {
        robot.makeDriveOnly();
        super.init();
    }

    @Override
    public void loop() {
        robot.mecanumDrive.move(Math.hypot(-gamepad1.left_stick_y, gamepad1.left_stick_x),
                Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y) - Math.PI / 4,
                gamepad1.right_stick_x);
    }
}
