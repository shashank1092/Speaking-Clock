package com.speakingclock.demo.service;

import com.speakingclock.demo.exception.InvalidCountryCodeException;
import com.speakingclock.demo.model.Country;
import org.springframework.stereotype.Component;

@Component
public interface ClockService {

    /**
     * This method returns the current time in India in String format
     * @return current time in India in words
     */
    public String getCurrentTimeInIndia();

    /**
     * This method returns the current time in given country in String format
     * @param country
     * @return current time in given country in words
     */
    public String getCurrentTimeForCountry(Country country);

    /**
     * This method returns the time for the provided country code
     * @param countryCode
     * @return current time in provided country code in words, if country code is invalid throws InvalidCountryCodeException
     * @throws InvalidCountryCodeException
     */
    public String getTimeForCountry(String countryCode) throws InvalidCountryCodeException;
}
