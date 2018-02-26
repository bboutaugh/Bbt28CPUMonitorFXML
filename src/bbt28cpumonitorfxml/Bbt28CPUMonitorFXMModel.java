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
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Brad
 */
public class Bbt28CPUMonitorFXMModel 
{ 
    //Image dialImage;
    //ImageView dial;
    Image gaugeImage;
    ImageView gauge;
    ImageView image;
    int degrees;
    boolean isActivated;
    
    private double usage;
    Timeline timeline;
    KeyFrame keyframe;
    double secondsElapsed;
    double timeInSeconds;
    double angleDeltaPerSeconds;
    DecimalFormat twoDecimals;
    
      public Bbt28CPUMonitorFXMModel() 
      {
       // dialImage = new Image(getClass().getResourceAsStream("hand.png"));
        gaugeImage = new Image(getClass().getResourceAsStream("gauge.png"));
        this.secondsElapsed = 1.0;
        this.timeInSeconds = 1.0;
        setupDuration();
        this.angleDeltaPerSeconds = 6.0;
        isActivated = false;
        this.usage = 0.0;
        twoDecimals = new DecimalFormat("#.00");
        rotateDial(0 ,image);
      }
      
       public void update() 
      {
        secondsElapsed += timeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        //digitalDisplay.setText(String.valueOf(model.getCPUUsage()*100.00));
        //dial.setRotate(rotation);
      }
    
    //Getting CPU Usage//////////
    public double getCPUUsage() 
    {
     OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
     for(Method method : operatingSystemMXBean.getClass().getDeclaredMethods())
     {
         method.setAccessible(true);
         if(method.getName().startsWith("getSystemCpuLoad")      
                 && Modifier.isPublic(method.getModifiers()))
         {
             try
             {
                 //CPU usage assigned to usage
                 usage = (double)method.invoke(operatingSystemMXBean);
             }
             catch(Exception e)
             {
                 usage = 0;
             }
            return usage;
         }
     }
     return usage;
    }//End getCPUUsage method
    
    public void setupDuration() 
     {
        keyframe = new KeyFrame(Duration.millis(timeInSeconds * 1), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    public void rotateDial(int degrees, ImageView image)
    {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1),this.image);
        rotateTransition.setByAngle(this.degrees);
        rotateTransition.setCycleCount(this.degrees);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();  
    }
    
}//End Bbt28CPUMonitorFXMModel 
