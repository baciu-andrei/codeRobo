package org.firstinspires.ftc.teamcode.Modules;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;
import org.firstinspires.ftc.teamcode.Utils.PIDController;

@Config
public class Extendo {
    public static PIDController pid = new PIDController(0.01,0,0.00045);
    public static int MaxExtendoExtension = 1300;
    public static double targetPosition = 0;
    public static boolean disabled = true;
    public static boolean atTargetConfig = false;

    public static void setTargetPosition(double pos){
        if(disabled){return;}
        if(pos > MaxExtendoExtension) pos = MaxExtendoExtension;
        if(pos < 0) pos = 0;
        targetPosition = pos;
        pid.setTarget(pos);
    }

    public static double getTargetPosition(){
        return targetPosition;
    }

    public static double currentPosition(){
        return HardwareConfig.extendoMotor.getCurrentPosition();
    }

    public static boolean atTarget(){
        if (disabled){return atTargetConfig;}
        return Math.abs(currentPosition()-targetPosition)<5;
    }

    public static void update(){
        if(disabled){
            HardwareConfig.extendoMotor.setPower(0);
            return;
        }
        double power = pid.update(currentPosition());
        HardwareConfig.extendoMotor.setPower(power);
    }
}
