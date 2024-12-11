package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareConfig {
    public static DcMotorEx leftFrontMotor;
    public static DcMotorEx rightFrontMotor;
    public static DcMotorEx leftRearMotor;
    public static DcMotorEx rightRearMotor;
    public static DcMotorEx sweeperMotor;
    public static ServoPlus leftMiniExtendoServo;
    public static ServoPlus rightMiniExtendoServo;
    public static DcMotorEx extendoMotor;
    public static DcMotorEx elevatorMotor;
    public static ServoPlus leftArmServo;
    public static ServoPlus rightArmServo;
    public static ServoPlus clawServo;
    public static DistanceSensor clawProximitySensor;
    public static ServoPlus ptoServo;
    public static ServoPlus leftWheelieServo;
    public static ServoPlus rightWheelieServo;
    public static FastColorRangeSensor sweeperColorSensor;
    public static void init(HardwareMap hwMap) {
        leftFrontMotor = hwMap.get(DcMotorEx.class, "cM3");
        rightFrontMotor = hwMap.get(DcMotorEx.class, "eM1");
        leftRearMotor = hwMap.get(DcMotorEx.class, "cM2");
        rightRearMotor = hwMap.get(DcMotorEx.class, "eM2");
        sweeperMotor = hwMap.get(DcMotorEx.class, "eM3");
        leftMiniExtendoServo = hwMap.get(ServoPlus.class, "eS4");
        rightMiniExtendoServo = hwMap.get(ServoPlus.class, "eS2");
        extendoMotor = hwMap.get(DcMotorEx.class, "cM1");
        elevatorMotor = hwMap.get(DcMotorEx.class, "eM0");
        leftArmServo = hwMap.get(ServoPlus.class, "eS3");
        rightArmServo = hwMap.get(ServoPlus.class, "eS5");
        clawServo = hwMap.get(ServoPlus.class, "cS2");
        clawProximitySensor = hwMap.get(DistanceSensor.class, "Claw");
        ptoServo = hwMap.get(ServoPlus.class, "cS4");
        leftWheelieServo = hwMap.get(ServoPlus.class, "eS0");
        rightWheelieServo = hwMap.get(ServoPlus.class, "eS1");
        sweeperColorSensor = hwMap.get(FastColorRangeSensor.class, "Storage");
    }
}
