package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.Gamepad;

public class AutoGamepad {
    public Gamepad gamepad;
    boolean prevA,prevB,prevX,prevY,prevLB,prevRB,prevDU,prevDD,prevDL,prevDR;
    public boolean isAHeld,wasPressedA,wasPressedB,wasPressedX,wasPressedY,wasPressedLB,wasPressedRB,wasPressedDU,wasPressedDD,wasPressedDL,wasPressedDR;
    public AutoGamepad(Gamepad g){
        gamepad=g;
    }
    public void update(){
        isAHeld=gamepad.a;
        wasPressedA=gamepad.a&&!prevA;
        wasPressedB=gamepad.b&&!prevB;
        wasPressedX=gamepad.x&&!prevX;
        wasPressedY=gamepad.y&&!prevY;
        wasPressedLB=gamepad.left_bumper&&!prevLB;
        wasPressedRB=gamepad.right_bumper&&!prevRB;
        wasPressedDU=gamepad.dpad_up&&!prevDU;
        wasPressedDD=gamepad.dpad_down&&!prevDD;
        wasPressedDL=gamepad.dpad_left&&!prevDL;
        wasPressedDR=gamepad.dpad_right&&!prevDR;
        prevA=gamepad.a;prevB=gamepad.b;prevX=gamepad.x;prevY=gamepad.y;prevLB=gamepad.left_bumper;prevRB=gamepad.right_bumper;prevDU=gamepad.dpad_up;prevDD=gamepad.dpad_down;prevDL=gamepad.dpad_left;prevDR=gamepad.dpad_right;
    }
}
