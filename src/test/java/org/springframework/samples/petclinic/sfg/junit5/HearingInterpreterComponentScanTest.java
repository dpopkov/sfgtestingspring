package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles({"component-scan", "yanny"})
@SpringJUnitConfig(classes = {HearingInterpreterComponentScanTest.TestConfig.class})
class HearingInterpreterComponentScanTest {

    @Profile("component-scan")
    @Configuration
    @ComponentScan(
            excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Nested.*"),
                    @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Laurel.*")
            },
            basePackages = "org.springframework.samples.petclinic.sfg")
    static class TestConfig {
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whaIHeard() {
        String word = hearingInterpreter.whaIHeard();
        assertEquals("Yanny", word);
    }
}
