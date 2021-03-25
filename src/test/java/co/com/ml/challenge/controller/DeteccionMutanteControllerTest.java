/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.controller;

import co.com.ml.challenge.business.DeteccionMutanteBusiness;
import co.com.ml.challenge.vo.EstadisticasVO;
import static org.junit.Assert.assertTrue;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author usuario
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeteccionMutanteControllerTest {

    @InjectMocks
    private DeteccionMutanteController deteccionMutanteController;

    @Mock
    private DeteccionMutanteBusiness deteccionMutanteBusiness;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidarHumano() throws Exception {
        String requestJson = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGCCA\",\"TCACCG\"]}";
        mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().isForbidden());
    }

    @Test
    public void testValidarMutante() throws Exception {
        String requestJson = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";
        mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().isOk());
    }

    @Test
    public void obtieneEstadisticas() throws Exception {
        mockMvc.perform(get("/stats").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    public void testValidaCadenaExistenteEnRepositorio() throws Exception {
        String requestJson = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";
        mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().isOk());
        mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().isOk());
    }

    @Test
    public void testValidarErrorMatrizNoCuadrada() throws Exception {
        String requestJson = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\"]}";
        mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().is5xxServerError());
    }

    @Test
    public void testValidarErrorCaracterInvalido() throws Exception {
        String requestJson = "{\"dna\":[\"ATYCGA\",\"CAGTGC\",\"TTATGT\",\"AGZAGG\",\"CCCCTA\",\"TCACTG\"]}";
        mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().is5xxServerError());
    }

    @Test
    public void testValidarErrorMatrizInsuficiente() throws Exception {
        String requestJson = "{\"dna\":[\"AT\",\"CA\"]}";
        mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().is5xxServerError());
    }

    @Test
    public void testConsultarEstadisticas() {
        EstadisticasVO estadisticasVO = new EstadisticasVO(0, 0, Double.NaN);
        Mockito.when(deteccionMutanteBusiness.obtieneEstadisticas()).thenReturn(estadisticasVO);
        EstadisticasVO respuestaListaAgenteDTO = deteccionMutanteController.consultarEstadisticas();
        assertTrue(estadisticasVO.equals(respuestaListaAgenteDTO));
    }

}
