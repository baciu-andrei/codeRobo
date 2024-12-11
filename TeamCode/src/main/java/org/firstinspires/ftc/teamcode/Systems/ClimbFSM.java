package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Modules.PTO;
import org.firstinspires.ftc.teamcode.Modules.Wheelie;
import org.firstinspires.ftc.teamcode.Modules.Elevator;
import org.firstinspires.ftc.teamcode.Modules.Extendo;

public class ClimbFSM {
    public enum State {
        IDLE,PREPARING,CLIMBING,FINISHED
    }
    public static State currentState=State.IDLE;
    static ElapsedTime stateTimer=new ElapsedTime();
    static double BAR_POSITION=300,EXTENDO_MAX=600;
    public static void setState(State s){currentState=s;stateTimer.reset();}
    public static void startClimb(){
        if(currentState==State.IDLE)setState(State.PREPARING);
    }
    public static void update(){
        switch(currentState){
            case IDLE:
                break;
            case PREPARING:
                PTO.engage();
                Wheelie.raise();
                if(Wheelie.motionCompleted())setState(State.CLIMBING);
                break;
            case CLIMBING:
                Elevator.setTargetPosition(BAR_POSITION);
                if(Elevator.atTarget()){
                    Elevator.setTargetPosition(0);
                    if(Math.abs(Elevator.getCurrentPosition()-0)<5){
                        Elevator.setTargetPosition(BAR_POSITION);
                        Extendo.setTargetPosition(EXTENDO_MAX);
                        if(Extendo.atTarget()){
                            Extendo.setTargetPosition(0);
                            if(Extendo.atTarget()){
                                setState(State.FINISHED);
                            }
                        }
                    }
                }
                break;
            case FINISHED:
                break;
        }
    }
}
