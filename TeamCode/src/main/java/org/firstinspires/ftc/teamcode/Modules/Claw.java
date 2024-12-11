package org.firstinspires.ftc.teamcode.Modules;

import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;

public class Claw {
    public static double OPEN_POS=100,CLOSE_POS=255;
    public static void open(){HardwareConfig.clawServo.setAngle(OPEN_POS);}
    public static void close(){HardwareConfig.clawServo.setAngle(CLOSE_POS);}
    public static boolean isOpen(){return Math.abs(HardwareConfig.clawServo.getAngle()-OPEN_POS)<5;}
    public static boolean isClosed(){return Math.abs(HardwareConfig.clawServo.getAngle()-CLOSE_POS)<5;}
}
