/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor;

import java.util.Arrays;
import numericCalc.DiffEqu;
import numericCalc.PeriodicFunc;
import numericCalc.Solver;

/**
 *
 * @author Char Aznable
 */
public class MotorEqu {
    
    Motor motor;
    PeriodicFunc drive;
    DiffEqu equ;
    double[] init;
    double time;
    double interval;
    
    public MotorEqu(Motor motor, PeriodicFunc drive, double[] init, double time, double interval) {
        this.motor = motor;
        this.drive = drive;
        //drive.setMaxVal(drive.getMaxVal()*motor.torqueConst);
        equ = new DiffEqu(motor.calcCoefficient(), drive);
        this.init = init;
        this.time = time;
        this.interval = interval;
    }
    
    public MotorEqu(Motor motor, PeriodicFunc drive, double interval) {
        this(motor, drive, new double[]{0, 0}, 0, interval);
    }
    
    public double[] solve() {
        init = Solver.solve(equ, init, time, interval);
        time += interval;   //init and time are auto-update
        return init;        //return answer
    }
    
    public double getTime() {
        return time;
    }
    
    public String getEqu() {
        return equ.getEqu();
    }
    
    public static void main(String[] args) {
        double resistance = 0.952;
        double inductance = 0.000088;
        double cemfConst = 0.0143;
        double torqueConst = 0.0143;
        double inertia = 0.00000104;
        double dampingRatio = 0;
        Motor motor = new Motor(resistance, inductance, cemfConst, torqueConst, inertia, dampingRatio);
        
        double omega = 10;
        double maxVal = 15;
        double duty = 80;    //persent
        String waveform = "step";
        PeriodicFunc drive = new PeriodicFunc(omega, maxVal, duty, waveform);
        
        double[] init = {0, 0};
        double time = 0;
        double interval = 0.01;
        MotorEqu equ = new MotorEqu(motor, drive, init, time, interval);
        
        System.out.println(Arrays.toString(motor.calcCoefficient()));
        System.out.println(equ.equ.getEqu());
        
        for (int i=0; i<100; i++) {
            System.out.println(Arrays.toString(equ.solve()));
        }
    }
}
