package com.jbielak.petclinic.controllers;

import com.jbielak.petclinic.model.Vet;
import com.jbielak.petclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class VetControllerTest {

    @Mock
    VetService vetService;

    @InjectMocks
    VetController vetController;

    MockMvc mockMvc;
    Vet vet;

    @BeforeEach
    void setUp() {
        vet = Vet.builder().id(1L).firstName("John").lastName("Doe").build();

        mockMvc = MockMvcBuilders
                .standaloneSetup(vetController)
                .build();
    }

    @Test
    void findVets() throws Exception {
        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attributeExists("vets"));

        verify(vetService).findAll();
    }

    @Test
    void apiGetVets() throws Exception {
        when(vetService.findAll()).thenReturn(Stream.of(vet).collect(Collectors.toSet()));

        mockMvc.perform(get("/api/vets")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(vet.getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(vet.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(vet.getLastName())));

        verify(vetService).findAll();
    }
}
