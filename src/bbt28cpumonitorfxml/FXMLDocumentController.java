/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt28cpumonitorfxml;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Brad
 */
public class FXMLDocumentController implements Initializable 
{
    Bbt28CPUMonitorFXMModel model = new Bbt28CPUMonitorFXMModel();
    Timeline timeline;
    KeyFrame keyframe;
    double secondsElapsed;
    double timeInSeconds;
    double angleDeltaPerSeconds;
    boolean recordCheck1 = true;
    boolean recordCheck2 = false;
    boolean recordCheck3 = false;

    
    @FXML
    private VBox rootContainer;
    
    //Recordings Elements
    @FXML
    private VBox recordingsVBox;
    @FXML
    private Label recording1;
    @FXML 
    private Label recording2;
    @FXML
    private Label recording3;
    
    //Image Elements
    @FXML
    private StackPane imagePane;
    @FXML
    private ImageView gauge;
    //gauge.setImage(gaugeImage);
    
    @FXML
    private ImageView dial;
    
    //Display Elements
    @FXML
    private VBox displayVBox;
    @FXML
    private Label digitalDisplay;
    
    //Buttons//////////
    @FXML
    private HBox buttonHBox;
    @FXML
    private Button recordButton;
    @FXML
    private Button startButton;
    
    @FXML
    private void recordButtonAction(ActionEvent event)
    {
        if(model.isActivated)
        {
        //Record Action
        if(recordCheck1)
        {
        recording1.setText(String.valueOf("Recording 1: " + 
                model.twoDecimals.format(model.getCPUUsage()*100.00))+ "% " 
                + LocalDateTime.now());
        recordCheck1 = false;
        recordCheck2 = true;
        }
        else if(recordCheck2)
        {
            recording2.setText(String.valueOf("Recording 2: " + 
                    model.twoDecimals.format(model.getCPUUsage()*100.00))+ "% " 
                    + LocalDateTime.now());
            recordCheck2 = false;
            recordCheck3 = true;
        }
        else if(recordCheck3)
        {
            recording3.setText(String.valueOf("Recording 3: " + 
                    model.twoDecimals.format(model.getCPUUsage()*100.00))+ "% " 
                    + LocalDateTime.now());
            recordCheck3 = false;
            recordCheck1 = true;
           
        }
      
        }//End isActivated if else statement
        else
        {
        //Reset Action
        recording1.setText("--.--");
        recording2.setText("--.--");
        recording3.setText("--.--");
        digitalDisplay.setText("--.--");
        }
       
    }//End Record Button Action Event
    
    @FXML
    private void startButtonAction(ActionEvent event)
    {
        if(model.isActivated)
        {
        //Stop Action
        startButton.setText("Start");
        recordButton.setText("Reset");
        model.timeline.pause();
        model.isActivated = false;
        }
        else
        {
        //Start Action
        startButton.setText("Stop");
        recordButton.setText("Record");
        //model.setupDuration();
        model.timeline.play();
        //model.getCPUUsage();
        digitalDisplay.setText(String.valueOf(model.twoDecimals.format(model.getCPUUsage()*100.00))+ "%");
        model.isActivated = true;
        }
    }//End Start Button Action Event
    

 
        
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
}//End FXMLDocumentController
