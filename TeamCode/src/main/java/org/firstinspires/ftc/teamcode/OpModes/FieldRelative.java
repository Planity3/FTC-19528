package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class FieldRelative extends QQOpMode {

    @Override
    public void init() {
        robot.makeDriveOnly();
        super.init();
    }

    @Override
    public void loop() {
        robot.nav.fieldRelative(-gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x);
    }
}