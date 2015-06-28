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
public class WhatToSee {
    private String name;

    public WhatToSee() {
    }
   
    

    public WhatToSee(String name) {
        this.name = name;
  
    }
    
    

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

   
    
    
    
}
