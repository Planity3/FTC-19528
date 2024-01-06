package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.Behaviors.Trees.TeleopTree;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
@TeleOp
public class MainTeleop extends  QQOpMode{

    Node root = TeleopTree.root();

    boolean done;


    @Override
    public void loop() {
            Node.State state = root.tick(this);

        robot.lift.update(telemetry);
    }



}