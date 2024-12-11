package org.firstinspires.ftc.teamcode.Modules;

import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;

public class DropDown {
    public static double UP_ANGLE=275,DOWN_ANGLE=130;
    public static void down(){
        HardwareConfig.leftMiniExtendoServo.setAngle(DOWN_ANGLE);
        HardwareConfig.rightMiniExtendoServo.setAngle(DOWN_ANGLE);
    }
    public static void up(){
        HardwareConfig.leftMiniExtendoServo.setAngle(UP_ANGLE);
        HardwareConfig.rightMiniExtendoServo.setAngle(UP_ANGLE);
    }
}
