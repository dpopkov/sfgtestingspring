package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.sfg.BaseConfig;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.samples.petclinic.sfg.YannyConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {BaseConfig.class, YannyConfig.class})
class HearingInterpreterYannyTest {
    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whaIHeard() {
        final String word = hearingInterpreter.whaIHeard();
        assertEquals("Yanny", word);
    }
}