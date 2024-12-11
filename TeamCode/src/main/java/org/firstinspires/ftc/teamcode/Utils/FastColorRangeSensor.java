package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchSimple;
import com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties;
import com.qualcomm.robotcore.hardware.configuration.annotations.I2cDeviceType;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@I2cDeviceType
@DeviceProperties(
        xmlTag = "FastColorRangeSensor",
        name = "Fast ColorRangeSensor - REV V3"
)
public class FastColorRangeSensor extends RevColorSensorV3 implements HardwareDevice {

    public class ColorRangeSensorPacket {
        public double R,G,B,A;
        public double D;
        public ColorRangeSensorPacket() {
            R=G=B=0;D=0;
        }
    }

    private long timeDistance=0,timeRGB=0;
    private double freq=20;
    private double lowPassFilter=0.6;
    public ColorRangeSensorPacket p=new ColorRangeSensorPacket();
    public ColorRangeSensorPacket RGB=new ColorRangeSensorPacket();

    public FastColorRangeSensor(I2cDeviceSynchSimple deviceClient, boolean deviceClientIsOwned){
        super(deviceClient, deviceClientIsOwned);
        timeDistance=System.currentTimeMillis();
        timeRGB=System.currentTimeMillis();
        setFreqToUpdate(50);
    }

    public void setFreqToUpdate(double x){freq=x;}
    public void setLowPassFilterCoefficient(double k){this.lowPassFilter=k;}

    @Override
    public double getDistance(DistanceUnit unit){
        if(System.currentTimeMillis()-timeDistance>freq){
            p.D=unit.fromUnit(DistanceUnit.INCH,this.inFromOptical(this.rawOptical()));
            timeDistance=System.currentTimeMillis();
        }
        return p.D;
    }

    public Colors.ColorType getColorSeenBySensor(){
        if(System.currentTimeMillis()-timeRGB>freq){
            p.G=(int)(this.green()*this.lowPassFilter+p.G*(1 - lowPassFilter));
            p.R=(int)(this.red()*this.lowPassFilter+p.R*(1 - lowPassFilter));
            p.B=(int)(this.blue()*this.lowPassFilter+p.B*(1 - lowPassFilter));
            p.A=Math.max(p.G,Math.max(p.R,p.B));

            double rr= Range.clip((p.R/p.A)*255,0,255);
            double gg= Range.clip((p.G/p.A)*255,0,255);
            double bb= Range.clip((p.B/p.A)*255,0,255);

            RGB.R=rr;RGB.G=gg;RGB.B=bb;

            timeRGB=System.currentTimeMillis();
        }
        return Colors.getColorFromRGB(new Colors.Color(RGB.R, RGB.G, RGB.B));
    }
}
