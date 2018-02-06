/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorSim;

import motor.Motor;
import plot.Plot;

/**
 *
 * @author Char Aznable
 */
public class motorSim {
    
    public static void main(String args[]) throws Exception {
        double resistance;      //ohm
        double inductance;      //henry
        double efc;             //volt/rad
        double inertia;         //kgm^2
        double dampingRatio;    //kg/s
        
        Motor motor = new Motor();
        
        double[] init = {0, 0};
        double time = 0;
        double interval = 0.01;
        Plot plot = new Plot(motor.equ);
        plot.go(init, time, interval);
    }
}
