package org.firstinspires.ftc.teamcode.Utils;

public class PIDController {
    double kP,kI,kD;
    double target=0;
    double integral=0;
    double lastError=0;
    long lastTime=0;
    public PIDController(double p,double i,double d) {
        kP=p;kI=i;kD=d;
        lastTime=System.currentTimeMillis();
    }
    public void setTarget(double t){
        target=t;
        integral=0;
        lastError=0;
        lastTime=System.currentTimeMillis();
    }
    public double update(double current){
        double error=target-current;
        double dt=(System.currentTimeMillis()-lastTime)/1000.0;
        lastTime=System.currentTimeMillis();
        integral+=error*dt;
        double deriv=(error-lastError)/dt;
        lastError=error;
        return kP*error+kI*integral+kD*deriv;
    }
}
