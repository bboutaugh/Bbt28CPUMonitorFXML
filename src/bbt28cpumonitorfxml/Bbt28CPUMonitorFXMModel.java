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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

/**
 *
 * @author Brad
 */
public class Bbt28CPUMonitorFXMModel 
{ 
    Image dialImage;
    Image gaugeImage;
    boolean isActivated;
    private double usage;
    Timeline timeline;
    KeyFrame keyframe;
    DecimalFormat twoDecimals = new DecimalFormat("#.00");
    
      public Bbt28CPUMonitorFXMModel() 
      {
        //timeline = new timeline();
        //keyframe = new keyframe();
        dialImage = new Image(getClass().getResourceAsStream("hand.png"));
        gaugeImage = new Image(getClass().getResourceAsStream("gauge.png"));
        isActivated = false;
        this.usage = 0.0;
      }
    
    //Getting CPU Usage//////////
    public void getCPUUsage() 
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
            // return usage;
         }
     }
      System.out.println(usage);
    }//End getCPUUsage method
    
}//End Bbt28CPUMonitorFXMModel 
