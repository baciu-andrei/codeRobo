package org.firstinspires.ftc.teamcode.Systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Modules.Claw;
import org.firstinspires.ftc.teamcode.Modules.DifferentialArm;
import org.firstinspires.ftc.teamcode.Modules.Elevator;
import org.firstinspires.ftc.teamcode.Modules.Extendo;
import org.firstinspires.ftc.teamcode.Utils.HardwareConfig;

@Config
public class OuttakeFSM {
    public enum State {
        INTAKING,WAITING_FOR_CLAW_CLOSE,TRAVEL,HUMAN,WALL,HIGH_BASKET,LOW_BASKET,HIGH_CHAMBER,LOW_CHAMBER,WAITING_AFTER_CLAW_OPEN
    }
    public static State currentState=State.INTAKING;
    static ElapsedTime stateTimer=new ElapsedTime();
    public static double WAIT_CLAW_CLOSE_DELAY=0.1,WAIT_AFTER_OPEN_DELAY=0.2;
    public static double IntakeArmAngle =21.3, IntakePivotAngle =0,IdleElevatorLevel=-100,SafeElevatorLevel=50;
    public static double HumanArmAngle =200, HumanPivotAngle =200;
    public static double ArmScoreSample=245,PivotScoreSample=200,ElevatorScoreSample=500;
    public static double ArmScoreSpecimen=110,PivotScoreSpecimen=0,ElevatorScoreSpecimen=330,ArmHumanSpecimen=10,ElevatorHumanSpecimen=300;
    public static double ArmWallSpecimen =320, PivotWallSpecimen =198, ElevatorWallSpecimen =60;
    public static double ElevatorLowSpecimen =100, ElevatorHighSpecimen =350, ElevatorLowSample =500, ElevatorHighSample =1075;
    public static double TransferArm=60,TransferPivot=0;
    
    public static void setState(State s){currentState=s;stateTimer.reset();}
    public static void update(){
        Elevator.update();
        Extendo.update();
        DifferentialArm.update();
        switch(currentState){
            case INTAKING:
                DifferentialArm.setTargetAngles(IntakeArmAngle, IntakePivotAngle);
                Elevator.setTargetPosition(SafeElevatorLevel);
                Claw.open();
                if(IntakeFSM.currentState==IntakeFSM.State.FULL&&Extendo.atTarget()){
                    setState(State.WAITING_FOR_CLAW_CLOSE);
                }
                break;
            case WAITING_FOR_CLAW_CLOSE:
                if(stateTimer.seconds()>WAIT_CLAW_CLOSE_DELAY){
                    Claw.close();
                    setState(State.TRAVEL);
                }
                break;
            case TRAVEL:
                Claw.close();
                Elevator.setTargetPosition(SafeElevatorLevel);
                DifferentialArm.setTargetAngles(TransferArm, TransferPivot);
                break;
            case HUMAN:
                Elevator.setTargetPosition(0);
                DifferentialArm.setTargetAngles(HumanArmAngle,HumanPivotAngle);
                if(Claw.isOpen())setState(State.TRAVEL);
                break;
            case WALL:
                Elevator.setTargetPosition(ElevatorWallSpecimen);
                DifferentialArm.setTargetAngles(ArmWallSpecimen, PivotWallSpecimen);
                Claw.open();
                double dist=HardwareConfig.clawProximitySensor.getDistance(DistanceUnit.MM);
                if(dist<40){
                    Claw.close();
                    setState(State.TRAVEL);
                }
                break;
            case HIGH_BASKET:
                Elevator.setTargetPosition(ElevatorHighSample);
                DifferentialArm.setTargetAngles(ArmScoreSample,PivotScoreSample);
                if(DifferentialArm.motionCompleted())
                    Claw.open();
                if(Claw.isOpen())setState(State.TRAVEL);
                break;
            case LOW_BASKET:
                Elevator.setTargetPosition(ElevatorLowSample);
                DifferentialArm.setTargetAngles(ArmScoreSample,PivotScoreSample);
                if(DifferentialArm.motionCompleted())
                    Claw.open();
                if(Claw.isOpen())setState(State.TRAVEL);
                break;
            case HIGH_CHAMBER:
                Elevator.setTargetPosition(ElevatorHighSpecimen);
                DifferentialArm.setTargetAngles(ArmScoreSpecimen,PivotScoreSpecimen);
                if(DifferentialArm.motionCompleted())
                    Claw.open();
                if(Claw.isOpen())setState(State.WAITING_AFTER_CLAW_OPEN);
                break;
            case LOW_CHAMBER:
                Elevator.setTargetPosition(ElevatorLowSpecimen);
                DifferentialArm.setTargetAngles(ArmScoreSpecimen,PivotScoreSpecimen);
                if(DifferentialArm.motionCompleted())
                    Claw.open();
                if(Claw.isOpen())setState(State.WAITING_AFTER_CLAW_OPEN);
                break;
            case WAITING_AFTER_CLAW_OPEN:
                if(stateTimer.seconds()>WAIT_AFTER_OPEN_DELAY){
                    setState(State.TRAVEL);
                }
                break;
        }
    }
}
