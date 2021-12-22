package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {
        "classpath:spring/mvc-test-config.xml",
        "classpath:spring/mvc-core-config.xml"
})
class OwnerControllerTest {
    @Autowired
    OwnerController ownerController;
    @Autowired
    ClinicService clinicService;

    MockMvc mockMvc;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @AfterEach
    void tearDown() {
        reset(clinicService);
    }

    @Test
    void testFindByNameNotFound() throws Exception {
        mockMvc.perform(get("/owners")
                .param("lastName", "Dont find me"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void testFindByNameWhenOneResultFound() throws Exception {
        // Given
        final Owner singleOwner = new Owner();
        final Integer ownerId = 11;
        singleOwner.setId(ownerId);
        final String lastName = "Single";
        given(clinicService.findOwnerByLastName(lastName)).willReturn(List.of(singleOwner));
        // When
        mockMvc.perform(get("/owners")
                    .param("lastName", lastName))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + ownerId));
        // Then
        then(clinicService).should().findOwnerByLastName(stringCaptor.capture());
        assertThat(stringCaptor.getValue()).isEqualTo(lastName);
    }

    @Test
    void testFindByNameWhenMultipleFound() throws Exception {
        // Given
        given(clinicService.findOwnerByLastName("")).willReturn(List.of(new Owner(), new Owner()));
        // When
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("selections"))
                .andExpect(view().name("owners/ownersList"));
        // Then
        then(clinicService).should().findOwnerByLastName(stringCaptor.capture());
        assertThat(stringCaptor.getValue()).isEqualTo("");
    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }
}
