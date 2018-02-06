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

    static double[] solve(DiffEqu equ, double[] init, double time, double interval){
        int order = equ.getOrder();
        if (order != init.length) return init;    //係数とinitの数が一致しなかったら変更なし
        double[][] k = new double[order][4];    //
        double[] x = new double[order]; //変数は階数の数だけある
        
        for (int j=0;j<4;j++) { //k0~k3
            
            double[] tmp = init.clone();    //initの更新
            for (int l=order-1;l>=0;l--) {  //変数ごと
                switch (j) {                //kごとに違う
                    case 0:
                        break;
                    case 1:
                        tmp[l] += k[l][j-1]/2;
                        break;
                    case 2:
                        tmp[l] += k[l][j-1]/2;
                        break;
                    case 3:
                        tmp[l] += k[l][j-1];
                        break;
                    default:
                }
            }
            
            for (int i=order-1;i>=0;i--) {  //変数xのforループ
                
                switch (j){
                    case 0:
                        if (i == order-1) {
                            k[i][j] = interval*rk4Pram(equ, init, time, 0);
                        } else {
                            k[i][j] = interval*init[i+1];
                        }
                        break;
                    case 1:
                        if (i == order-1) {
                            k[i][j] = interval*rk4Pram(equ, tmp, time, (interval/2));
                        } else {
                            k[i][j] = interval*(init[i+1] + k[i+1][0]/2);
                        }
                        break;
                    case 2:
                        if (i == order-1) {
                            k[i][j] = interval*rk4Pram(equ, tmp, time, (interval/2));
                        } else {
                            k[i][j] = interval*(init[i+1] + k[i+1][1]/2);
                        }
                        break;
                    case 3:
                        if (i == order-1) {
                            k[i][j] = interval*rk4Pram(equ, tmp, time, interval);
                        } else {
                            k[i][j] = interval*(init[i+1] + k[i+1][2]);
                        }
                        break;
                    default:
                }
            }
        }
        for (int i=order-1;i>=0;i--) {
            x[i] = init[i] + (k[i][0] + 2*k[i][1] + 2*k[i][2] + k[i][3])/6;
        }
        return x; 
    }
    
    static double rk4Pram(DiffEqu equ, double[] init, double time, double interval) {
        int order = equ.getOrder();
        double ret = equ.calcFunc(time+interval);
        for (int i=order-1;i>=0;i--) {
            ret -= equ.getCoefficient(i)*init[i];
        }
        ret /= equ.getCoefficient(order);
        return ret;
    }
    
    public static void main(String args[]) {
        double[] coefficient = {-9, 0, 34, -2};
        double omega = 3.14;
        double maxVal = 1;
        double duty = 80;
        String waveform = "";
        DiffEqu equ = new DiffEqu(coefficient, omega, maxVal, duty, waveform);
        
        System.out.println(equ.getEqu());
        
        double[] init = {0, 0, 1};
        double time = 0;
        double interval = 0.01;
        
        for (int i=0;i<200;i++) {
            //System.out.println(Arrays.toString(init));
            System.out.println(time+"\t"+init[0]);
            init = solve(equ, init, time, interval);
            time += interval;
        }
    }
}
