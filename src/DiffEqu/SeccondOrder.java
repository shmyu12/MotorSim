/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiffEqu;

/**
 *
 * @author d14125
 */
public class SeccondOrder {
    public int a;
    public int b;
    public int omega;
    public String waveform;
    
    SeccondOrder(int a, int b, String waveform, int omega){
        this.a = a;
        this.b = b;
        this.waveform = waveform;
        this. omega = omega;
    }
    
    String printEqu(){
        return "x\"+" + a+"x\'+" +b+"x=" + getFunc();
    }
    
    String getFunc() {
        return waveform+"("+ omega +")";
    }
    
    public static void main(String[] args) {
        SeccondOrder equ = new SeccondOrder(2, 3, "sin", 5);
        System.out.print(equ.printEqu());
    }
}
