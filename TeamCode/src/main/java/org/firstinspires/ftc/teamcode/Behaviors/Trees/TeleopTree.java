package org.firstinspires.ftc.teamcode.Behaviors.Trees;

import org.firstinspires.ftc.teamcode.Behaviors.Actions.CloseClaw;
import org.firstinspires.ftc.teamcode.Behaviors.Actions.DriveFieldRelative;
import org.firstinspires.ftc.teamcode.Behaviors.Actions.MakeFastDrive;
import org.firstinspires.ftc.teamcode.Behaviors.Actions.MakeNormalDrive;
import org.firstinspires.ftc.teamcode.Behaviors.Actions.MakeSlowDrive;
import org.firstinspires.ftc.teamcode.Behaviors.Actions.OpenClaw;
import org.firstinspires.ftc.teamcode.Behaviors.Actions.SetLiftPosition;
import org.firstinspires.ftc.teamcode.Behaviors.Conditions.AreNotSlidesExtended;
import org.firstinspires.ftc.teamcode.Behaviors.Conditions.AreSlidesExtended;
import org.firstinspires.ftc.teamcode.Behaviors.Conditions.IfClawButtonPressedClose;
import org.firstinspires.ftc.teamcode.Behaviors.Conditions.IfClawButtonPressedOpen;
import org.firstinspires.ftc.teamcode.Behaviors.Conditions.IfLeftTriggerPressed;
import org.firstinspires.ftc.teamcode.Behaviors.Conditions.IsControllerDriving;
import org.firstinspires.ftc.teamcode.Behaviors.Failover;
import org.firstinspires.ftc.teamcode.Behaviors.Node;
import org.firstinspires.ftc.teamcode.Behaviors.Parallel;
import org.firstinspires.ftc.teamcode.Behaviors.Sequence;

public class TeleopTree {
    public static Node root(){
        return new Parallel(4,
                /*
                new Sequence(

                    new IsEndgame(),

                    new Parallel(2,
                            new Sequence(
                                    new Failover(
                                            new IsLastThreeSeconds(),
                                            new Sequence(
                                                new IsDroneLocation(),
                                                new IsDroneButtonPressed()
                                            )
                                    ),
                                    new HaveNotLaunchedDrone(),
                                    new LaunchDrone()
                            ),

                            new Sequence(
                                    new IsClimbLocation(),
                                    new IsClimbButtonPressed(),
                                    new Climb()
                            )
                    )
                ),

                 */
                new DriveFieldRelative(),
                new Failover(
                        new Sequence(
                                //new HasLessThan2Pixels(),
                                new IfClawButtonPressedClose(),
                                new CloseClaw()
                        ),
                        new Sequence(
                                //new HasMoreThan2Pixels(), // having more than 2 pixels is impossible
                                new IfClawButtonPressedOpen(),
                                new OpenClaw() // X not using eject
                        )
                ),
                new Failover(
                        new Sequence(
                                new SetLiftPosition()
                        )
                ),
                new Parallel(2,
                        new Sequence(
                                //new Has1or2Pixels(),
                                new Failover(
                                        new Sequence(
                                                new AreSlidesExtended()
                                        )
                                )
                        ),
                        new Failover(
                                new AreSlidesExtended(),
                                new IsControllerDriving(),
                                new MakeSlowDrive()
                        ),
                        new Sequence(
                                new AreNotSlidesExtended(),
                                new Sequence(
                                        new IsControllerDriving(),
                                        new Failover(
                                                new MakeNormalDrive(),
                                                new Sequence(
                                                        new IfLeftTriggerPressed(),
                                                        new MakeFastDrive()

                                                )


                                        )

                                )
                        )

                )
        );
    }


}