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
        
    }
    
    @FXML
    private void startButtonAction(ActionEvent event)
    {
        if(model.isActivated)
        {
        startButton.setText("Start");
        recordButton.setText("Reset");
        //timeline.pause();
        model.getCPUUsage();
        model.isActivated = false;
        }
        else
        {
        startButton.setText("Stop");
        recordButton.setText("Record");
        //timeline.play();
        model.isActivated = true;
        }
    }
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
}//End FXMLDocumentController
