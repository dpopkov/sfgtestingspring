package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.samples.petclinic.sfg.LaurelWordProducer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("nested-class")
@SpringJUnitConfig(classes = HearingInterpreterNestedClassTest.TestConfig.class)
class HearingInterpreterNestedClassTest {

    @Profile("nested-class")
    @Configuration
    static class TestConfig {
        @Bean
        HearingInterpreter hearingInterpreter() {
            return new HearingInterpreter(new LaurelWordProducer());
        }
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whaIHeard() {
        String word = hearingInterpreter.whaIHeard();
        assertEquals("Laurel", word);
    }
}
