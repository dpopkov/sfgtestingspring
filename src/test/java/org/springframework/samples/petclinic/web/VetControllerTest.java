package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    ClinicService clinicService;
    @InjectMocks
    VetController controller;
    @Mock
    Map<String, Object> model;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Given
        given(clinicService.findVets()).willReturn(List.of(new Vet()));
        // Mock MVC Standalone Setup
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testControllerShowVetList() throws Exception {
        mockMvc.perform(get("/vets.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vets"))
                .andExpect(view().name("vets/vetList"));
    }

    @Test
    void showVetList() {
        // When
        String viewName = controller.showVetList(model);
        // Then
        then(clinicService).should().findVets();
        then(model).should().put(anyString(), any(Vets.class));
        assertThat(viewName).isEqualTo("vets/vetList");
    }

    @Test
    void showResourcesVetList() {
        // When
        Vets vets = controller.showResourcesVetList();
        // Then
        then(clinicService).should().findVets();
        assertThat(vets.getVetList()).hasSize(1);
    }
}
