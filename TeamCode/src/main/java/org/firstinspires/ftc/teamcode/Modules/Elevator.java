package org.firstinspires.ftc.teamcode.Modules;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;
import org.firstinspires.ftc.teamcode.Utils.PIDController;

@Config
public class Elevator {

    public static PIDController pid=new PIDController(0.013,0,0.0005);
    public static double targetPos=0;
    public static boolean disabled=true;
    public static double currentPositionConfig = 0;
    public static boolean atTargetConfig = true;
    private static ElapsedTime timer=new ElapsedTime();
    public static void setTargetPosition(double pos){
        if(disabled){return;}
        targetPos=pos;
        pid.setTarget(pos);
        timer.reset();
    }
    public static double getCurrentPosition(){if(disabled){return currentPositionConfig;}return HardwareConfig.elevatorMotor.getCurrentPosition();}
    public static boolean atTarget(){if(disabled){return atTargetConfig;}return Math.abs(getCurrentPosition()-targetPos)<5 && timer.seconds()>0.5;}
    public static void update(){
        if(disabled){HardwareConfig.elevatorMotor.setPower(0);return;}
        double power=pid.update(getCurrentPosition());
        HardwareConfig.elevatorMotor.setPower(power);
    }
}
