package org.firstinspires.ftc.teamcode.Modules;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;
import org.firstinspires.ftc.teamcode.Utils.Colors.ColorType;

@Config
public class Sweeper {
    public static boolean disabled=true;
    public static boolean isFullConfig=false;
    public static double IN_POWER=1,OUT_POWER=-1;
    public static ColorType desiredColor=ColorType.RED;
    public static void intake(){if(disabled){HardwareConfig.sweeperMotor.setPower(0);return;}HardwareConfig.sweeperMotor.setPower(IN_POWER);}
    public static void outtake(){if(disabled){HardwareConfig.sweeperMotor.setPower(0);return;}HardwareConfig.sweeperMotor.setPower(OUT_POWER);}
    public static void stop(){HardwareConfig.sweeperMotor.setPower(0);}
    public static boolean isFull(){
        if(disabled){return isFullConfig;}
        ColorType c=HardwareConfig.sweeperColorSensor.getColorSeenBySensor();
        return c==desiredColor;
    }
}
