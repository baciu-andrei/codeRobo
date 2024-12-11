package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Modules.Extendo;
import org.firstinspires.ftc.teamcode.Modules.Sweeper;
import org.firstinspires.ftc.teamcode.Systems.ClimbFSM;
import org.firstinspires.ftc.teamcode.Systems.Controls;
import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.IntakeFSM;
import org.firstinspires.ftc.teamcode.Systems.OuttakeFSM;
import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;

@TeleOp(name="MainOp",group="Main")
public class MainOp extends LinearOpMode {
    Drivetrain drive;
    Controls controls=new Controls();
    int loopCounter=0;
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareConfig.init(hardwareMap);
        drive=new Drivetrain(telemetry);
        Controls.Initialize(gamepad1,gamepad2);
        waitForStart();
        while(opModeIsActive()){
            Controls.Update();
            if(Controls.gamepad2.gamepad.right_trigger>0.1){IntakeFSM.startIntake();} else if(Controls.gamepad2.gamepad.left_trigger>0.1){Sweeper.outtake();} else {Sweeper.stop();}
            if(Controls.gamepad2.wasPressedDL)OuttakeFSM.setState(OuttakeFSM.State.INTAKING);
            if(Controls.gamepad2.wasPressedDU)OuttakeFSM.setState(OuttakeFSM.State.HUMAN);
            if(Controls.gamepad2.wasPressedDD)OuttakeFSM.setState(OuttakeFSM.State.WALL);
            if(Controls.gamepad2.gamepad.left_bumper&&Controls.gamepad2.gamepad.y)OuttakeFSM.setState(OuttakeFSM.State.LOW_BASKET);
            if(Controls.gamepad2.gamepad.right_bumper&&Controls.gamepad2.gamepad.y)OuttakeFSM.setState(OuttakeFSM.State.LOW_CHAMBER);
            if(Controls.gamepad2.gamepad.left_bumper&&!Controls.gamepad2.gamepad.y)OuttakeFSM.setState(OuttakeFSM.State.HIGH_BASKET);
            if(Controls.gamepad2.gamepad.right_bumper&&!Controls.gamepad2.gamepad.y)OuttakeFSM.setState(OuttakeFSM.State.HIGH_CHAMBER);

            if(IntakeFSM.currentState == IntakeFSM.State.FULL) Extendo.setTargetPosition(0);
            else {
                double extInput = Controls.gamepad2.gamepad.right_stick_y;
                if(Math.abs(extInput) > 0.1) {
                    double desired = Extendo.getTargetPosition() + (extInput * 5);
                    Extendo.setTargetPosition(desired);
                }
            }
            if(Controls.gamepad2.gamepad.x){IntakeFSM.currentState=IntakeFSM.State.INTAKING;Extendo.setTargetPosition(Extendo.MaxExtendoExtension);}
            if(Controls.gamepad2.gamepad.b){Extendo.setTargetPosition(0);IntakeFSM.stopIntake();}



            if(Controls.gamepad1.wasPressedRB)ClimbFSM.startClimb();
            IntakeFSM.update();
            OuttakeFSM.update();
            ClimbFSM.update();

            loopCounter++;
            if(loopCounter >= 10){
                Extendo.update();
                loopCounter=0;
            }

            drive.loop(controls);
            telemetry.addData("Outtake State",OuttakeFSM.currentState);
            telemetry.addData("Intake State",IntakeFSM.currentState);
            telemetry.addData("Climb State",ClimbFSM.currentState);
            telemetry.update();
        }
    }
}
