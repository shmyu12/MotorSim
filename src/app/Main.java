/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Char Aznable
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        VBox root;
        
        try {
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("MotorSim");
            stage.show();
        } catch (IOException e) {
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
