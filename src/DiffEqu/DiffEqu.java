/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiffEqu;

import PeriodicFunc.PeriodicFunc;

/**
 *
 * @author Char Aznable
 */
public class DiffEqu {
    double[] coefficient;
    PeriodicFunc func;
    
    public DiffEqu(double[] coefficient, double omega, double maxVal, double duty, String waveform){
        this.coefficient = new double[coefficient.length];
        for (int i=0; i<coefficient.length; i++) {
            this.coefficient[i] = coefficient[i];
        }
        func = new PeriodicFunc(omega, maxVal, duty, waveform);
    }
    
    public DiffEqu(double[] coefficient) {
        this(coefficient, 0, 0, 0, "");
    }
    
    public void setFunc(double omega, double maxVal, double duty, String waveform) {
        func.setPram(omega, maxVal, duty, waveform);
    }
    
    public String getEqu(){
        String equ = new String();
        for (int i=coefficient.length-1; i>=0; i--) {
            if (coefficient[i] != 0) {
                equ += coefficient[i]+"x";
                for (int j=0; j<i; j++) {
                    equ += "'";
                }
                if (i!=0) equ += "+";
            } else {
                equ += 0+"+";
            }
        }
        equ += "="+func.getFunc();
        return equ;
    }
    
    public double getCoefficient(int i) {
        return coefficient[i];
    }
    
    public int getOrder() {
        return coefficient.length-1;
    }
    
    public double calcFunc(double time) {
        return func.calcFunc(time);
    }
    
    public static void main(String[] args) {
        double[] coefficient = {5, 0, 10};
        double omega = 3.14;
        double maxVal = 1;
        double duty = 80;    //persent
        String waveform = "triangle";
        DiffEqu equ = new DiffEqu(coefficient, omega, maxVal, duty, waveform);
        System.out.println(equ.getEqu());
        System.out.println(equ.getOrder());
    }
}
