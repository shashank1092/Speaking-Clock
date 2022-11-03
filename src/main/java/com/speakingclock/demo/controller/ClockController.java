package com.speakingclock.demo.controller;

import com.speakingclock.demo.exception.InvalidCountryCodeException;
import com.speakingclock.demo.model.Country;
import com.speakingclock.demo.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/clock")
public class ClockController {

    @Autowired
    ClockService clockService;

    @GetMapping("/currentTimeInIndia")
    public String getCurrentTimeInIndia() {
        return clockService.getCurrentTimeInIndia();
    }

    @GetMapping("/currentTimeInWorld")
    public String getCurrentTimeInWorld(@PathParam("country") Country country) {
        return clockService.getCurrentTimeForCountry(country);
    }

    @GetMapping("/currentTime")
    public String getCurrentTime(@PathParam("countryCode") @NotNull String countryCode ) {
        try {
            return clockService.getTimeForCountry(countryCode);
        } catch (InvalidCountryCodeException e) {
           return  e.getErrorMsg();
        }
    }

}
