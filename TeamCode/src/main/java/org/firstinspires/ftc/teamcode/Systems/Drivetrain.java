package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utils.Enums.SpeedMode;
import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;

import java.util.ArrayList;

public class Drivetrain {
    public static boolean DISABLED=false;
    private DcMotorEx mfl,mfr,mbl,mbr;
    private Telemetry telemetry;
    private SpeedMode speedMode=SpeedMode.SLOW;
    public Drivetrain(Telemetry t){
        telemetry=t;
        mfl=HardwareConfig.leftFrontMotor;
        mfr=HardwareConfig.rightFrontMotor;
        mbl=HardwareConfig.leftRearMotor;
        mbr=HardwareConfig.rightRearMotor;
        mfl.setDirection(DcMotorSimple.Direction.FORWARD);
        mfr.setDirection(DcMotorSimple.Direction.REVERSE);
        mbl.setDirection(DcMotorSimple.Direction.FORWARD);
        mbr.setDirection(DcMotorSimple.Direction.REVERSE);
        ArrayList<DcMotorEx> motors=new ArrayList<>();
        motors.add(mfl);motors.add(mfr);motors.add(mbl);motors.add(mbr);
        for(DcMotorEx motor: motors){
            motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            MotorConfigurationType mt=motor.getMotorType().clone();
            mt.setAchieveableMaxRPMFraction(1.0);
            motor.setMotorType(mt);
        }
    }
    public void switchSpeed(SpeedMode s){speedMode=s;}
    public void drive(double forward,double right,double turn){
        double denom=Math.max(1.0,Math.abs(forward)+Math.abs(right)+Math.abs(turn));
        forward/=denom;right/=denom;turn/=denom;
        double mflP=forward+right+turn;
        double mfrP=forward-right-turn;
        double mblP=forward-right+turn;
        double mbrP=forward+right-turn;
        mfl.setPower(mflP*speedMode.multiplier);
        mfr.setPower(mfrP*speedMode.multiplier);
        mbl.setPower(mblP*speedMode.multiplier);
        mbr.setPower(mbrP*speedMode.multiplier);
    }
    public void loop(Controls c){
        if(DISABLED)return;
        switchSpeed(c.getSpeedMode());
        double f=c.getForward();
        double r=c.getStrafe();
        double t=c.getTurn();
        drive(f,r,t);
    }
}
