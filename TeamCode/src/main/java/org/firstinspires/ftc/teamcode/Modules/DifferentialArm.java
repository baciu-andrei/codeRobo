package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;
import org.firstinspires.ftc.teamcode.Utils.PIDController;

public class DifferentialArm {
    public static double s1Offset=0,s2Offset=0;
    private static double targetArm=21.3,targetPivot=0;
    private static PIDController armPID=new PIDController(0.01,0,0.0005);
    private static PIDController pivotPID=new PIDController(0.01,0,0.0005);
    private static ElapsedTime timer=new ElapsedTime();
    public static void setAngles(double arm,double pivot){
        targetArm=arm;
        targetPivot=pivot;
        armPID.setTarget(arm);
        pivotPID.setTarget(pivot);
        timer.reset();
    }
    private static double getCurrentArmAngle(){
        double right=HardwareConfig.rightArmServo.getAngle();
        double left=HardwareConfig.leftArmServo.getAngle();
        double avg=(right+left)/2.0;
        return avg;
    }
    private static double getCurrentPivotAngle(){
        double right=HardwareConfig.rightArmServo.getAngle();
        double left=HardwareConfig.leftArmServo.getAngle();
        double pivot=(left - right);
        return pivot;
    }
    public static void update(){
        double currentArm=getCurrentArmAngle();
        double currentPivot=getCurrentPivotAngle();
        double armPower=armPID.update(currentArm);
        double pivotPower=pivotPID.update(currentPivot);
        double servoLeftAngle=(currentArm+armPower)+(currentPivot+pivotPower)/2.0+s1Offset;
        double servoRightAngle=(currentArm+armPower)-(currentPivot+pivotPower)/2.0+s2Offset;
        if(servoLeftAngle<0)servoLeftAngle=0;if(servoLeftAngle>355)servoLeftAngle=355;
        if(servoRightAngle<0)servoRightAngle=0;if(servoRightAngle>355)servoRightAngle=355;//idk if it's needed
        HardwareConfig.leftArmServo.setAngle(servoLeftAngle);
        HardwareConfig.rightArmServo.setAngle(servoRightAngle);
    }
    public static boolean motionCompleted(){
        return timer.seconds()>0.5 && Math.abs(getCurrentArmAngle()-targetArm)<5 && Math.abs(getCurrentPivotAngle()-targetPivot)<5;
    }//poate un fel de motion profile?
    public static void setTargetAngles(double arm,double pivot){
        setAngles(arm,pivot);
    }
}
