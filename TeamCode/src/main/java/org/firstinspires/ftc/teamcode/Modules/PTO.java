package org.firstinspires.ftc.teamcode.Modules;

import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;

public class PTO {
    public static double ENGAGED_ANGLE=232,DISENGAGED_ANGLE=180;
    public static void engage(){HardwareConfig.ptoServo.setAngle(ENGAGED_ANGLE);}
    public static void disengage(){HardwareConfig.ptoServo.setAngle(DISENGAGED_ANGLE);}
    public static boolean isEngaged(){return Math.abs(HardwareConfig.ptoServo.getAngle()-ENGAGED_ANGLE)<5;}
}
