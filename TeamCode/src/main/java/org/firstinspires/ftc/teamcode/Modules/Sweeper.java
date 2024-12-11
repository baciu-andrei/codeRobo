package org.firstinspires.ftc.teamcode.Modules;

import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;
import org.firstinspires.ftc.teamcode.Utils.Colors.ColorType;

public class Sweeper {
    public static boolean DISABLED=false;
    public static double IN_POWER=1,OUT_POWER=-1;
    public static ColorType desiredColor=ColorType.RED;
    public static void intake(){if(DISABLED){HardwareConfig.sweeperMotor.setPower(0);return;}HardwareConfig.sweeperMotor.setPower(IN_POWER);}
    public static void outtake(){if(DISABLED){HardwareConfig.sweeperMotor.setPower(0);return;}HardwareConfig.sweeperMotor.setPower(OUT_POWER);}
    public static void stop(){HardwareConfig.sweeperMotor.setPower(0);}
    public static boolean isFull(){
        ColorType c=HardwareConfig.sweeperColorSensor.getColorSeenBySensor();
        return c==desiredColor;
    }
}
