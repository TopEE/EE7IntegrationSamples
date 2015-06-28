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
public class Hotel {
    private String adress;
    private String name;

    public Hotel() {
    }
    
    

    public Hotel(String adress, String name) {
        this.adress = adress;
        this.name = name;
    }
    
    

    public String getAdress() {
        return adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

   
    
    
    
}
