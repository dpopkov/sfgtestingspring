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

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    ClinicService clinicService;
    @InjectMocks
    VetController controller;
    @Mock
    Map<String, Object> model;

    @BeforeEach
    void setUp() {
        // Given
        given(clinicService.findVets()).willReturn(List.of(new Vet()));
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
