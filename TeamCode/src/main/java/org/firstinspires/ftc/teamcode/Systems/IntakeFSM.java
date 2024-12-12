package org.firstinspires.ftc.teamcode.Systems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Modules.DropDown;
import org.firstinspires.ftc.teamcode.Modules.Sweeper;

@Config
public class IntakeFSM {
    public enum State {
        IDLE,INTAKING,FULL
    }
    public static State currentState=State.IDLE;
    public static void startIntake(){
        if(currentState==State.IDLE||currentState==State.FULL){
            currentState=State.INTAKING;
            DropDown.down();
            Sweeper.intake();
        }
    }
    public static void stopIntake(){
        currentState=State.IDLE;
        DropDown.up();
        Sweeper.stop();
    }
    public static void update(){
        if (currentState == State.INTAKING) {
            if (Sweeper.isFull()) {
                DropDown.up();
                Sweeper.stop();
                currentState = State.FULL;
            }
        }

        //alte states?
        //pot sa pun pt full daca cateva loop-uri isFull e false but e ok si asa ca nu prea conteaza cand e IDLE tbh
    }
}
