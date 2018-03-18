/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor;
import java.util.Arrays;
import numericCalc.DiffEqu;
/**
 *
 * @author Char Aznable
 */
public class Motor {
    
    double resistance;      //ohm
    double inductance;      //henry
    double cemfConst;             //volt/rad
    double torqueConst;        //Nm/A
    double inertia;         //kgm^2
    double dampingRatio;    //kg/s
    
    public Motor (double resistance, double inductance, double cemfConst, double torqueConst, double inertia, double dampingRatio) {
        this.resistance = resistance;
        this.inductance = inductance;
        this.cemfConst = cemfConst;
        this.torqueConst = torqueConst;
        this.inertia = inertia;
        this.dampingRatio = dampingRatio;
    }
    
    public void setPrams (double resistance, double inductance, double cemfConst, double torqueConst, double inertia, double dampingRatio) {
        this.resistance = resistance;
        this.inductance = inductance;
        this.cemfConst = cemfConst;
        this.torqueConst = torqueConst;
        this.inertia = inertia;
        this.dampingRatio = dampingRatio;
    }
    
    double[] calcCoefficient() {
        double[] coefficient = new double[3];
        coefficient [0] = (resistance*dampingRatio)/torqueConst+cemfConst;
        coefficient [1] = (inductance*dampingRatio+resistance*inertia)/torqueConst;
        coefficient [2] = (inductance*inertia)/torqueConst;
        return coefficient;
    }
    
    public static void main(String args[]) {
        double resistance = 0.952;
        double inductance = 0.000088;
        double cemfConst = 0.0143;
        double torqueConst = 0.0143;
        double inertia = 0.00000104;
        double dampingRatio = 0;
        Motor motor = new Motor(resistance, inductance, cemfConst, torqueConst, inertia, dampingRatio);
        DiffEqu equ = new DiffEqu(motor.calcCoefficient());
        
        System.out.println(Arrays.toString(motor.calcCoefficient()));
        System.out.println(equ.getEqu());
    }
}
