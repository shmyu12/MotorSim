/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PeriodicFunc;

import static java.lang.Math.*;

/**
 *
 * @author Char Aznable
 */
public class PeriodicFunc {
    double omega;
    double maxVal;
    double duty;    //persent
    String waveform = new String();
    
    public PeriodicFunc(double omega, double maxVal, double duty, String waveform) {
        this.omega = omega;
        this.maxVal = maxVal;
        this.duty = duty;
        this.waveform = waveform;
    }
    
    public void setPram(double omega, double maxVal, double duty, String waveform) {
        this.omega = omega;
        this.maxVal = maxVal;
        this.duty = duty;
        this.waveform = waveform;
    }
    
    public double calcFunc(double time) {
        switch (waveform) { 
            case "sin":
                return maxVal*sin(omega*time);
            case "cos":
                return maxVal*cos(omega*time);
            case "rect":
                return maxVal*rectangleWave(time);
            case "triangle":
                return maxVal*triangulerWave(time);
            default:
                return 0;
        }
    }
    
    double calcPeriod() {
        return (2*PI)/omega;
    }
    
    double rectangleWave(double time) {
        double period = calcPeriod();
        while (time > period) {
            time -= period;
        }
        if (period * (duty/100) >= time ) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
    
    double triangulerWave(double time) {
        double period = calcPeriod();
        while (time > period) {
            time -= period;
        }
        
        return time/period;
    }
    
    public String getFunc() {
        return waveform+"("+omega+"t:"+duty+"%)";
    }
    
    public static void main (String[] args) {
        double omega = 2*PI;
        double maxVal = 1;
        double duty = 80;    //persent
        String waveform = "triangle";
        PeriodicFunc func = new PeriodicFunc(omega, maxVal, duty, waveform);
        
        for (double t=0; t<10; t+=0.1) {
            System.out.println(t +","+ func.calcFunc(t));
        }
    }
}
