package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.Behaviors.Trees.TeleopTree;
import org.firstinspires.ftc.teamcode.Hardware.Lift;

@TeleOp
public class MainTeleopTest extends  QQOpMode{

    Node root = TeleopTree.root();

    boolean done;


    @Override
    public void loop() {
            Node.State state = root.tick(this);

        robot.arm.update(telemetry);
        if(gamepad2.a)
        {
            robot.arm.goToBackdropPosition();
        }
        if(gamepad2.b)
        {
            robot.arm.goToGroundPosition();
        }

        robot.lift.update(telemetry, gamepad2.left_stick_y);

        if(gamepad2.left_bumper)
        {
                robot.claw.Open();
        } else if (gamepad2.right_bumper) {
            robot.claw.Close();
        }

    }



}