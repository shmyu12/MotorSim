package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import motor.Motor;
import motor.MotorEqu;
import numericCalc.PeriodicFunc;

/**
 * FXML Controller class
 *
 * @author Char Aznable
 */
public class MainController implements Initializable {

    @FXML private Label formula;
    
    @FXML private TextField resistance;
    @FXML private TextField inductance;
    @FXML private TextField cemfConst;
    @FXML private TextField torqueConst;
    @FXML private TextField inertia;
    @FXML private TextField dampingRatio;
    
    @FXML private TextField waveform;
    @FXML private TextField duty;    
    @FXML private TextField maxVal;
    @FXML private TextField omega;
    @FXML private TextField interval;
    @FXML private TextField steps;
    
    MotorEqu equ;
    Motor motor;
    PeriodicFunc drive;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    public void onPramInput(ActionEvent event) {
    }
    
    @FXML
    public void onSolveButtonClicked(ActionEvent event) {
        if (resistance.getText().isEmpty() || inductance.getText().isEmpty() ||
            cemfConst.getText().isEmpty() || torqueConst.getText().isEmpty() ||
            inertia.getText().isEmpty() || dampingRatio.getText().isEmpty() ||
            waveform.getText().isEmpty() || maxVal.getText().isEmpty() ||
            omega.getText().isEmpty() || interval.getText().isEmpty() ||
            steps.getText().isEmpty() || duty.getText().isEmpty()) {
            formula.setText("Error");
        } else {
            
            motor = new Motor(Double.parseDouble(resistance.getText()), Double.parseDouble(inductance.getText()), 
                    Double.parseDouble(cemfConst.getText()), Double.parseDouble(torqueConst.getText()),
                    Double.parseDouble(inertia.getText()), Double.parseDouble(dampingRatio.getText()));
            
            drive = new PeriodicFunc(Double.parseDouble(omega.getText()), Double.parseDouble(maxVal.getText()),
                    Double.parseDouble(duty.getText()), waveform.getText());
            
            equ = new MotorEqu(motor, drive, Double.parseDouble(interval.getText()));
            
            formula.setText(equ.getEqu());
            
            try {
                FileWriter file = new FileWriter("test.tsv");
                PrintWriter pw = new PrintWriter(new BufferedWriter(file));

                pw.println("time\tomega\tomega_dot");

                //ファイルに書き込む
                for (int i=0;i<Integer.parseInt(steps.getText());i++) {
                    pw.println(equ.getTime()+"\t"+Arrays.toString(equ.solve()));
                }
                //ファイルを閉じる
                pw.close();
            } catch (IOException e) {
            }
        }
    }
    
}
