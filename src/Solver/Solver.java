/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solver;

import Motor.Motor;

/**
 *
 * @author Char Aznable
 */
public class Solver {
    
    public Solver(){
            
    }
        
    public static void main(String args[]) {
        double resistance = 1.0;
        double inductance = 0.5;
        double efc = 0.01;
        double inertia = 0.01;
        double dampingRatio = 0.1;
        Motor motor = new Motor(resistance, inductance, efc, inertia, dampingRatio);
        System.out.println(motor.equ.getEqu());
    }
}
