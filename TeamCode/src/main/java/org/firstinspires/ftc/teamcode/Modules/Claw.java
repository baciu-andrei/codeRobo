package org.firstinspires.ftc.teamcode.Modules;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;

@Config
public class Claw {
    public static boolean disabled=true;
    public static boolean isClawOpenConfig = true;
    public static double OPEN_POS=100,CLOSE_POS=255;
    public static void open(){if(disabled){return;}HardwareConfig.clawServo.setAngle(OPEN_POS);}
    public static void close(){if(disabled){return;}HardwareConfig.clawServo.setAngle(CLOSE_POS);}
    public static boolean isOpen(){if(disabled){return isClawOpenConfig;}return Math.abs(HardwareConfig.clawServo.getAngle()-OPEN_POS)<5;}
    public static boolean isClosed(){return Math.abs(HardwareConfig.clawServo.getAngle()-CLOSE_POS)<5;}
}
