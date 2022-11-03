package com.speakingclock.demo.controller;

import com.speakingclock.demo.exception.InvalidCountryCodeException;
import com.speakingclock.demo.model.Country;
import com.speakingclock.demo.service.ClockService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClockController.class)
class ClockControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClockService clockService;

    @Test
    void getCurrentTimeInIndia() throws Exception {
        given(clockService.getCurrentTimeInIndia()).willReturn("It's three thirty four");

        mvc.perform(MockMvcRequestBuilders.get("/clock/currentTimeInIndia")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .equals("It's three thirty four");
    }

    @Test
    void getCurrentTimeInWorld() throws Exception {

        given(clockService.getCurrentTimeForCountry(Country.AUSTRALIA)).willReturn("It's two thirty");

        mvc.perform(MockMvcRequestBuilders.get("/clock/currentTimeInWorld")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .equals("It's two thirty");
    }

    @Test
    void getCurrentTime() throws Exception {

        given(clockService.getTimeForCountry("abcd")).willReturn("error");

        mvc.perform(MockMvcRequestBuilders.get("/clock/currentTime")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .equals("error");;
    }
}