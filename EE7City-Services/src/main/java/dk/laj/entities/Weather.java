/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.laj.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lars
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {
    private int temperature;
    private String wather;

    public Weather() {
    }
    
    

    public Weather(int temperature, String wather) {
        this.temperature = temperature;
        this.wather = wather;
    }
    
    

    public int getTemperature() {
        return temperature;
    }

    public String getWather() {
        return wather;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setWather(String wather) {
        this.wather = wather;
    }
    
    
    
}
