package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Hardware.ControlHub;
import org.firstinspires.ftc.teamcode.Hardware.MecanumDrive;

public class Navigation {
    ControlHub controlHub;
    MecanumDrive mecanumDrive;
    public Navigation(ControlHub controlHub, MecanumDrive mecanumDrive) {
        this.controlHub = controlHub;
        this.mecanumDrive = mecanumDrive;
    }
    public void fieldRelative(double forward, double right, double rotate) {
        double heading = controlHub.getHeading(AngleUnit.RADIANS);

        Polar drive = new Polar(right, forward);
        drive.rotate(-heading, AngleUnit.RADIANS);

        mecanumDrive.move(drive.getY(), drive.getX(), rotate);
    }
}
