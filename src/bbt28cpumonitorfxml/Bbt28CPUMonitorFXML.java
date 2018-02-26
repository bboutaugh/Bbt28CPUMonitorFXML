/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt28cpumonitorfxml;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Brad
 */
public class Bbt28CPUMonitorFXML extends Application 
{
    Bbt28CPUMonitorFXMModel model = new Bbt28CPUMonitorFXMModel();
    @Override
    public void start(Stage stage) throws Exception 
    {
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), (ActionEvent) -> {
        model.getCPUUsage();
        }));
        
        timeline.setCycleCount(100);
        timeline.play();
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
