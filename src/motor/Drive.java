/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor;

import numericCalc.PeriodicFunc;

/**
 *
 * @author Char Aznable
 */
public class Drive extends PeriodicFunc {
    
    public double maxVolt;
    
    public Drive(double omega, double maxVolt, double duty, String waveform) {
        super(omega, 0, duty, waveform);
        this.maxVolt = maxVolt;
    }
}
