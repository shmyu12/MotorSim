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
        drive.setMaxVal(drive.getMaxVal()*motor.torqueConst);
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
        time += interval;
        return init;
    }
    
    public static void main(String[] args) {
        double resistance = 1.0;
        double inductance = 0.5;
        double cemfConst = 0.3;
        double torqueConst = 0.3;
        double inertia = 2.0;
        double dampingRatio = 0.1;
        Motor motor = new Motor(resistance, inductance, cemfConst, torqueConst, inertia, dampingRatio);
        
        double omega = 10;
        double maxVal = 6.;
        double duty = 80;    //persent
        String waveform = "cos";
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
