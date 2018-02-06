/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plot;

import java.util.ArrayList;
import java.util.List;
import numericCalc.DiffEqu;
import numericCalc.PeriodicFunc;
import static numericCalc.Solver.solve;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 *
 * @author Char Aznable
 */

public class Plot {
    DiffEqu equ;
    PeriodicFunc func;
    
    public Plot (DiffEqu equ) {
        this.equ = equ;
    }
    
    public void go(double[] init, double time, double interval) throws Exception {
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();
        xData.add(time);
        yData.add(init[0]);
        
        final XYChart chart = QuickChart.getChart("Simulation", "Time[sec]", "x(t)", "responce", xData, yData);
        // Show it
        final SwingWrapper<XYChart> sw = new SwingWrapper<>(chart);
        sw.displayChart();
        
        while(true) {
            Thread.sleep(50);
            
            time += interval;
            init = solve(equ, init, time, interval);
            xData.add(time);
            yData.add(init[0]);
            if (xData.size() > 100) xData.remove(0);
            if (yData.size() > 100) yData.remove(0);
            
            chart.updateXYSeries("responce", xData, yData, null);
            sw.repaintChart();
        }
    }
    
    public static void main(String[] args) throws Exception {
        double[] coefficient = {-3, 2, 1};
        double omega = 1;
        double maxVal = 2;
        double duty = 80;
        String waveform = "cos";
        DiffEqu equ = new DiffEqu(coefficient, omega, maxVal, duty, waveform);
        
        double[] init = {0, 0};
        double time = 0;
        double interval = 0.01;
        
        Plot plot = new Plot(equ);
        plot.go(init, time, interval);
    }
}