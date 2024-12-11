package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utils.Enums.SpeedMode;

public class Controls {
    public static AutoGamepad gamepad1,gamepad2;
    private static SpeedMode currentSpeed=SpeedMode.SLOW;
    public static void Initialize(Gamepad g1, Gamepad g2){
        gamepad1=new AutoGamepad(g1);
        gamepad2=new AutoGamepad(g2);
    }
    public static void Update(){
        gamepad1.update();
        gamepad2.update();
        if(gamepad1.isAHeld){
            currentSpeed=SpeedMode.FAST;
        }else currentSpeed=SpeedMode.SLOW;
    }
    public SpeedMode getSpeedMode(){return currentSpeed;}
    public double getForward(){return -gamepad1.gamepad.left_stick_y;}
    public double getStrafe(){return gamepad1.gamepad.left_stick_x;}
    public double getTurn(){return (gamepad1.gamepad.right_trigger - gamepad1.gamepad.left_trigger);}
}
