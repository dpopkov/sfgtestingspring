package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.Profiles;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(Profiles.EXTERNALIZED)
//@TestPropertySource(locations = {"/word.properties"})
@TestPropertySource("classpath:word.properties")
@SpringJUnitConfig(classes = {HearingInterpreterPropertiesTest.TestConfig.class})
class HearingInterpreterPropertiesTest {

    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg")
    static class TestConfig {
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whaIHeard() {
        String word = hearingInterpreter.whaIHeard();
        assertEquals("External_Yanny", word);
    }
}
