/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor;

import numericCalc.DiffEqu;

/**
 *
 * @author Char Aznable
 */
public class MotorEqu {
    
    Motor motor;
    Drive drive;
    DiffEqu equ;
    
    public MotorEqu(Motor motor, Drive drive) {
        this.motor = motor;
        this.drive = drive;
        drive.setMaxVal(calcMaxVal());
        equ = new DiffEqu(motor.calcCoefficient(), drive);
    }
    
    static double calcMaxVal() {
        return 0;
    }
    
}
