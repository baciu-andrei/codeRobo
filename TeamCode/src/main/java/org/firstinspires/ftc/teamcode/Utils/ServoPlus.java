package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.configuration.ServoFlavor;
import com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties;
import com.qualcomm.robotcore.hardware.configuration.annotations.ServoType;

@ServoType(flavor = ServoFlavor.CUSTOM)
@DeviceProperties(name = "ServoPlus", xmlTag = "servoPlus")
public class ServoPlus extends ServoImpl {
    private volatile double maxAngle = 355;
    private double currentAngle = 0;
    public ServoPlus(ServoController controller, int portNumber, Direction direction) {
        super(controller, portNumber, direction);
    }
    public ServoPlus(ServoController controller, int portNumber) {
        super(controller, portNumber);
    }
    public synchronized void setMaxAngle(double angle) {
        maxAngle = angle;
    }
    public synchronized void setAngle(double angle) {
        currentAngle = angle;
        double position = angle / maxAngle;
        setPosition(position);
    }
    public double getAngle() {
        return currentAngle;
    }
    public boolean isEqualToAngle(double angle) {
        return Math.abs(angle - getAngle()) < 0.1;
    }
}
