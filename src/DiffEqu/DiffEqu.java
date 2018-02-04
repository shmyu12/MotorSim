/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiffEqu;

/**
 *
 * @author Char Aznable
 */
public class DiffEqu {
    int[] coefficient;
    int omega;
    String waveform = new String();
    
    public DiffEqu(int[] coefficient, String waveform, int omega){
        this.coefficient = new int[coefficient.length];
        for (int i=0; i<coefficient.length; i++) {
            this.coefficient[i] = coefficient[i];
        }
        this.waveform = waveform;
        this. omega = omega;
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
        equ += "="+getFunc();
        return equ;
    }
    
    String getFunc() {
        return waveform+"("+ omega +")";
    }
    
    public String getOrder() {
        return (coefficient.length-1) + "階微分方程式";
    }
    
    public static void main(String[] args) {
        int[] coefficient = {5, 0, 10};
        DiffEqu equ = new DiffEqu(coefficient, "sin", 5);
        System.out.println(equ.getEqu());
        System.out.println(equ.getOrder());
    }
}
