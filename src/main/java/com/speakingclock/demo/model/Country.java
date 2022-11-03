package com.speakingclock.demo.model;

/**
 * Country names with time zone ids
 */
public enum Country {
    INDIA("IST"),AUSTRALIA("AET"),CHINA("CTT"), JAPAN("JST");

    private String countryCode;

    private Country(String countryCode){
        this.countryCode=countryCode;
    }
    public String getCountryCode(){
        return countryCode;
    }

}
