package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

        robot.lift.update(telemetry);
        if(gamepad2.a){
            robot.lift.setDesiredPosition(100);
        }

    }



}