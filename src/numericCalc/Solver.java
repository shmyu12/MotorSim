/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numericCalc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author Char Aznable
 */
public class Solver{
    Solver(){
    }

    public static double[] solve(DiffEqu equ, double[] init, double time, double interval){
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
                        tmp[l] += k[l][j-1]/2.0;
                        break;
                    case 2:
                        tmp[l] += k[l][j-1]/2.0;
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
                            k[i][j] = interval*rk4Pram(equ, tmp, time, (interval/2.0));
                        } else {
                            k[i][j] = interval*(init[i+1] + k[i+1][1]/2.0);
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
            x[i] = init[i] + (k[i][0] + 2.0*k[i][1] + 2.0*k[i][2] + k[i][3])/6.0;
        }
        return x; 
    }
    
    static double rk4Pram(DiffEqu equ, double[] init, double time, double interval) {
        int order = equ.getOrder();
        double ret = equ.calcFunc(time+interval);
        for (int i=order-1;i>=0;i--) {
            ret -= equ.getCoefficient(i)*init[i];
        }
        if (equ.getCoefficient(order) == 0) return ret;
        ret /= equ.getCoefficient(order);
        return ret;
    }
    
    public static void main(String args[]) {
        double[] coefficient = {-3, 2, 1};
        double omega = 1;
        double maxVal = 2;
        double duty = 80;
        String waveform = "cos";
        DiffEqu equ = new DiffEqu(coefficient, omega, maxVal, duty, waveform);

        System.out.println(equ.getEqu());
        
        double[] init = {0, 0};
        double time = 0;
        double interval = 0.01;
        
        double tv = 0;
        try {
            // FileWriterクラスのオブジェクトを生成する
            FileWriter file = new FileWriter("test.txt");
            // PrintWriterクラスのオブジェクトを生成する
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
            
            //ファイルに書き込む
            for (int i=0;i<200000;i++) {
                //System.out.println(Arrays.toString(init));
                pw.println(time+"\t"+init[0]+"\t"+tv);
                time += interval;
                init = solve(equ, init, time, interval);
                //tv = Math.sin(time);
                tv = (3.0/20.0)*Math.exp(-3.0*time) + (1.0/4.0)*Math.exp(time)
                            + (1.0/5.0)*Math.sin(time) - (2.0/5.0)*Math.cos(time);
            }
            //ファイルを閉じる
            pw.close();
        } catch (IOException e) {
        }
    }
}
