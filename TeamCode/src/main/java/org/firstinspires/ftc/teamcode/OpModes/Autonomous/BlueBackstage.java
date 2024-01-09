package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class BlueBackstage {
    public Robot robot = new Robot();

    TrajectorySequence untitled0 = robot.nav.trajectorySequenceBuilder(new Pose2d(-34.26, 61.49, Math.toRadians(-90.00)))
            .splineTo(new Vector2d(-26.99, 35.94), Math.toRadians(-7.52))
            .splineTo(new Vector2d(12.67, 35.20), Math.toRadians(0.26))
            .splineTo(new Vector2d(35.05, 56.10), Math.toRadians(26.15))
            .splineTo(new Vector2d(61.70, 59.23), Math.toRadians(0.00))
            .setReversed(true)
            .build();


}
