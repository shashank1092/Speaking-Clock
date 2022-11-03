package com.speakingclock.demo.service;

import com.speakingclock.demo.exception.InvalidCountryCodeException;
import com.speakingclock.demo.model.Country;
import com.speakingclock.demo.model.NumberMapping;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

@Service
public class ClockServiceImpl implements ClockService{

    @Override
    public String getCurrentTimeInIndia() {
        return getCurrentTimeForCountry(Country.INDIA);
    }

    @Override
    public String getCurrentTimeForCountry(Country country) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(country.getCountryCode()));
        return getCurrentCountryTime(cal);
    }

    private String getCurrentCountryTime(Calendar cal) {
        int hour = cal.get(Calendar.HOUR);
        StringBuilder time = new StringBuilder("It's ");
        //if hour == 0  means it's either midday or midnight
        if(hour != 0){
            time.append(NumberMapping.numberMap.get(hour));
        }else{
            int amOrPm = cal.get(Calendar.AM_PM);
            if(amOrPm == 0){
                time.append("Midnight");
            }else{
                time.append("Midday");
            }
            return time.toString();
        }

        //Get minutes
        int minutes = cal.get(Calendar.MINUTE);
        time.append(" " +NumberMapping.numberMap.get(minutes));
        return time.toString();
    }

    @Override
    public String getTimeForCountry(String countryCode) throws InvalidCountryCodeException {
        if(ZoneId.SHORT_IDS.containsKey(countryCode.toUpperCase())){
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(countryCode));
            return getCurrentCountryTime(cal);
        }else{
            throw new InvalidCountryCodeException("Invalid Country code");
        }
    }
}
