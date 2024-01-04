package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class ControlHub implements Mechanism{
    public IMU gyro;
    public VoltageSensor batteryVoltageSensor;
    public void init(HardwareMap hwMap) {
        gyro = hwMap.get(IMU.class, "imu");
        batteryVoltageSensor = hwMap.voltageSensor.iterator().next();
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        // Now initialize the IMU with this mounting orientation
        // Note: if you choose two conflicting directions, this initialization will cause a code exception.
        gyro.initialize(new IMU.Parameters(orientationOnRobot));

    }
    public double getHeading(AngleUnit angleUnit) {
        YawPitchRollAngles angles = gyro.getRobotYawPitchRollAngles();
        return angles.getYaw(angleUnit);
    }

}
