package dk.laj.entities;


import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public class CityInfo {

        Hotel hotel;
        Weather weather;
        List<WhatToSee> whatToSee;

        public CityInfo() {
        }

        public void setHotel(Hotel hotel) {
            this.hotel = hotel;
        }

        public void setWeather(Weather weather) {
            this.weather = weather;
        }

        public void setWhatToSee(List<WhatToSee> whatToSee) {
            this.whatToSee = whatToSee;
        }
    }