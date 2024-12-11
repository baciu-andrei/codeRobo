package org.firstinspires.ftc.teamcode.Utils;

public class Enums {
/*
    public enum SweeperState {
        IDLE,
        INTAKING,
        REVERSE,
        FULL
    }

    public enum MiniExtendoState {
        RETRACTED,
        EXTENDED
    }

    public enum ExtendoState {
        IDLE,
        MOVING_TO_POSITION
    }

    public enum ElevatorState {
        GROUND,
        LOW,
        HIGH,
        MOVING_TO_POSITION,
        MANUAL
    }

    public enum ArmState {
        INTAKE,
        HUMAN_PLAYER,
        WALL,
        HIGH_BASKET,
        HIGH_CHAMBER,
        LOW_BASKET,
        LOW_CHAMBER
    }

    public enum ClawState {
        OPEN,
        CLOSED
    }

    public enum PTOState {
        ENGAGED,
        DISENGAGED
    }

    public enum WheelieState {
        RETRACTED,
        EXTENDED
    }

    public enum IntakeState {
        IDLE,
        EXTENDED,
        INTAKING,
        RETRACTING
    }

    public enum OuttakeState {
        IDLE,
        ACTIVE
    }

    public enum EndgameState {
        IDLE,
        PREPARE_CLIMB,
        CLIMBING
    }
*/

    public enum SpeedMode {
        SLOW(0.5),
        FAST(1.0);
        public double multiplier;

        SpeedMode(double m) {
            multiplier = m;
        }
    }
}
