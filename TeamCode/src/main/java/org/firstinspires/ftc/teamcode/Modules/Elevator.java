package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;
import org.firstinspires.ftc.teamcode.Utils.PIDController;

public class Elevator {
    private static PIDController pid=new PIDController(0.013,0,0.0005);
    private static double targetPos=0;
    private static boolean disabled=false;
    private static ElapsedTime timer=new ElapsedTime();
    public static void setTargetPosition(double pos){
        targetPos=pos;
        pid.setTarget(pos);
        timer.reset();
    }
    public static double getCurrentPosition(){return HardwareConfig.elevatorMotor.getCurrentPosition();}
    public static boolean atTarget(){return Math.abs(getCurrentPosition()-targetPos)<5 && timer.seconds()>0.5;}
    public static void update(){
        if(disabled){HardwareConfig.elevatorMotor.setPower(0);return;}
        double power=pid.update(getCurrentPosition());
        HardwareConfig.elevatorMotor.setPower(power);
    }
}
