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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    
    @FXML private ComboBox preset;
    
    MotorEqu equ;
    Motor motor;
    PeriodicFunc drive;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preset.getItems().add("none");
        preset.getItems().add("RE20");
    }
    
    @FXML
    public void onPramInput(ActionEvent event) {
    }
    
    @FXML
    public void onPresetChoosed(ActionEvent event) {
        switch (preset.getValue().toString()) {
            case "none" :
                resistance.setText("");
                inductance.setText("");
                cemfConst.setText("");
                torqueConst.setText("");
                inertia.setText("");
                dampingRatio.setText("");
                break;
            case "RE20" :
                resistance.setText("0.952");
                inductance.setText("0.000088");
                cemfConst.setText("0.0143");
                torqueConst.setText("0.0143");
                inertia.setText("0.00000104");
                dampingRatio.setText("0");
                break;
            default :
                break;
        }
        
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

                pw.println("time\tomega");

                double tmp_time;
                double tmp_omega;
                //ファイルに書き込む
                for (int i=0;i<Integer.parseInt(steps.getText());i++) {
                    tmp_time = equ.getTime();
                    tmp_omega = equ.solve()[0];
                    pw.println(tmp_time+"\t"+tmp_omega);
                }
                //ファイルを閉じる
                pw.close();
            } catch (IOException e) {
            }
        }
    }
    
}
