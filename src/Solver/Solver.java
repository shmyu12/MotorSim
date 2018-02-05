/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solver;

import DiffEqu.DiffEqu;

/**
 *
 * @author Char Aznable
 */
public class Solver{
    Solver(){
    }

    double[] solve(DiffEqu equ, double[] init, double interval){
        int order = equ.getOrder();
        if (order+1 != init.length) return init;    //係数とinitの数が一致しなかったら変更なし
        double[][] k = new double[order][4];    //
        double[] x = new double[order]; //変数は階数の数だけある

        
        double rk4Pram = equ.calcFunc(init[order]); //init[]の最後はtime
        for (int i=order;i>=0;i--) {
            rk4Pram -= equ.getCoefficient(i)*init[i];
        }
        rk4Pram /= equ.getCoefficient(order+1);
        
        x[order-1] = 0;
        
        for (int i=order-1;i>=0;i--) {
            
            k[i][0] = init[i+1];
            k[i][1] = init[i+1] + (interval/2)*k[i+1][0];
            k[i][2] = init[i+1] + (interval/2)*k[i+1][1];
            k[i][3] = init[i+1] + interval*k[i+1][2];
            x[i] = init[i] + (interval/6)*(k[i][0] + 2*k[i][1] + 2*k[i][2] + k[i][3]);
        }
        return init; 
    }
    
    double rk4Pram(DiffEqu equ, double[] init, double interval) {
        int order = equ.getOrder();
        double ret = equ.calcFunc(init[order]); //init[]の最後はtime
        for (int i=order;i>=0;i--) {
            ret -= equ.getCoefficient(i)*init[i];
        }
        ret /= equ.getCoefficient(order+1);
        return ret;
    }
    
    public static void main(String args[]) {

    }
}
