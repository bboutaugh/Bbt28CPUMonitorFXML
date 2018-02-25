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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Brad
 */
public class FXMLDocumentController implements Initializable 
{
    Bbt28CPUMonitorFXMModel model = new Bbt28CPUMonitorFXMModel();
    
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
        if((recording2.getText() == "--.--") && (recording3.getText() == "--.--") 
                &&(recording1.getText() == "--.--"))
        {
        recording1.setText(String.valueOf(model.twoDecimals.format(model.getCPUUsage()*100.00))+ "%");
        }
        else if((recording1.getText()) != "--.--" && (recording2.getText() == "--.--"))
        {
            recording2.setText(String.valueOf(model.twoDecimals.format(model.getCPUUsage()*100.00))+ "%");
        }
        else if((recording1.getText() != "--.--") && (recording2.getText() != "--.--"))
        {
            recording3.setText(String.valueOf(model.twoDecimals.format(model.getCPUUsage()*100.00))+ "%");
        }
      
        }
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
        model.timeline.play();
        model.setupDuration();
        model.getCPUUsage();
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
