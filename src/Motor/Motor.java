/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Motor;
import DiffEqu.DiffEqu;
/**
 *
 * @author Char Aznable
 */
public class Motor {
    public DiffEqu equ;            //related to omega
    
    double resistance;      //ohm
    double inductance;      //henry
    double efc;             //volt/rad
    double inertia;         //kgm^2
    double dampingRatio;    //kg/s
    
    public Motor (double resistance, double inductance, double efc, double inertia, double dampingRatio) {
        this.resistance = resistance;
        this. inductance = inductance;
        this.efc = efc;
        this.inertia = inertia;
        this.dampingRatio = dampingRatio;
        equ = new DiffEqu(calcPram(), "", 0);
    }
    
    private int[] calcPram() {
        int[] coefficient = new int[3];
        coefficient [0] = 1;
        coefficient [1] = 0;
        coefficient [2] = 3;
        return coefficient;
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