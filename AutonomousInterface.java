package org.firstinspires.ftc.teamcode;


public class AutonomousInterface {

	// FL, FR, BL, BR
	public double[] DriveForwards(float power)
	{
		return [Math.abs(power), Math.abs(power), Math.abs(power), Math.abs(power)];
	}

	public double[] DriveBackwards(float power)
	{
		return[-Math.abs(power), -Math.abs(power), -Math.abs(power), -Math.abs(power)];
	}

public double[] StrafeLeft(float power)
	{
		return[-Math.abs(power), Math.abs(power),Math.abs(power), -Math.abs(power)];
	}
	public double[] StrafeRight(float power)
	{
		return[Math.abs(power), -Math.abs(power),-Math.abs(power), Math.abs(power)];
	}

public double[] DriveNE(float power)
{
return[Math.abs(power), 0.0, 0.0, Math.abs(power)];
}
public double[] DriveSE(float power)
{
return[0, -Math.abs(power), -Math.abs(power), 0.0];
}
public double[] DriveSW(float power)
{
return[-Math.abs(power), 0.0, 0.0, -Math.abs(power)];
}
public double[] DriveNW(float power)
{
return[0.0, Math.abs(power), Math.abs(power), 0.0];
}
public double[] RotateCW(float power)
{
return[Math.abs(power), -Math.abs(power), Math.abs(power), -Math.abs(power)];
}
public double[] RotateCCW(float power)
{
return[-Math.abs(power), Math.abs(power), -Math.abs(power), Math.abs(power)];
}
}
