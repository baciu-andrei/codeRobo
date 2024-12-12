package org.firstinspires.ftc.teamcode.Modules;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;

@Config
public class DropDown {
    public static boolean disabled=true;
    public static double UP_ANGLE=275,DOWN_ANGLE=130;
    public static void down(){
        if(disabled){return;}
        HardwareConfig.leftMiniExtendoServo.setAngle(DOWN_ANGLE);
        HardwareConfig.rightMiniExtendoServo.setAngle(DOWN_ANGLE);
    }
    public static void up(){
        if(disabled){return;}
        HardwareConfig.leftMiniExtendoServo.setAngle(UP_ANGLE);
        HardwareConfig.rightMiniExtendoServo.setAngle(UP_ANGLE);
    }
}
