package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Hardware.ControlHub;
import org.firstinspires.ftc.teamcode.Hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

import static org.firstinspires.ftc.teamcode.Hardware.MecanumDrive.MAX_ANGULAR_ACCELERATION;
import static org.firstinspires.ftc.teamcode.Hardware.MecanumDrive.MAX_ANGULAR_VELOCITY;


import java.util.Arrays;

public class Navigation {
    ControlHub controlHub;
    MecanumDrive mecanumDrive;
    public TrajectoryVelocityConstraint velocityConstraint = new MinVelocityConstraint(Arrays.asList(
            new AngularVelocityConstraint(MAX_ANGULAR_VELOCITY),
            new TranslationalVelocityConstraint(MecanumDrive.MAX_VELOCITY)
    ));
    public TrajectoryAccelerationConstraint accelConstraint = new ProfileAccelerationConstraint(MecanumDrive.MAX_ACCELERATION);
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
    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, Boolean reversed) {
        return new TrajectoryBuilder(startPose, reversed, velocityConstraint, accelConstraint);
    }
    public TrajectorySequenceBuilder trajectorySequenceBuilder(Pose2d startPose) {
        return new TrajectorySequenceBuilder(
                startPose,
                velocityConstraint, accelConstraint,
                MAX_ANGULAR_VELOCITY, MAX_ANGULAR_ACCELERATION
        );
    }
}
