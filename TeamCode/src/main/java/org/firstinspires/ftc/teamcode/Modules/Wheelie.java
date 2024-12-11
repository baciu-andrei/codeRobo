package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;

public class Wheelie {
    public static double DOWN_1=135,DOWN_2=290,RAISE_1=315,RAISE_2=100;
    private static ElapsedTime timer=new ElapsedTime();
    private static boolean moving=false;
    public static void raise(){
        HardwareConfig.leftWheelieServo.setAngle(RAISE_1);
        HardwareConfig.rightWheelieServo.setAngle(RAISE_2);
        timer.reset();
        moving=true;
    }
    public static void putDown(){
        HardwareConfig.leftWheelieServo.setAngle(DOWN_1);
        HardwareConfig.rightWheelieServo.setAngle(DOWN_2);
        timer.reset();
        moving=true;
    }
    public static boolean motionCompleted(){return !moving||timer.seconds()>0.5;}
}
