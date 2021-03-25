/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.controller;

import co.com.ml.challenge.business.DeteccionMutanteBusiness;
import co.com.ml.challenge.business.exception.DeteccionMutantesException;
import co.com.ml.challenge.vo.DnaVO;
import co.com.ml.challenge.vo.EstadisticasVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author usuario
 */
@RestController
@Api(tags = "DeteccionMutante")
public class DeteccionMutanteController {

    @Autowired
    private DeteccionMutanteBusiness deteccionMutanteBusiness;

    @PostMapping(value = "/mutant", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Validacion de mutante segun su cadena de ADN", response = void.class, httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 200, message = "La cadena corresponde a la de un mutante"),
                   @ApiResponse(code = 403, message = "La cadena corresponde a la de un simple humano", response = DeteccionMutantesException.class),
                   @ApiResponse(code = 500, message = "Error de datos en la validacion", response = DeteccionMutantesException.class)})
    public void validarMutante(@Valid @RequestBody(required = true) DnaVO dna) {
        if (!deteccionMutanteBusiness.isMutant(dna.getDna())) {
            throw new DeteccionMutantesException(HttpStatus.FORBIDDEN, "No es mutante, es un simple humano");
        }
    }

    @GetMapping(value = "/stats", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Validacion de mutante segun su cadena de ADN", response = EstadisticasVO.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "La consulta de estadisticas se realizo satisfactoriamente"),                   
                   @ApiResponse(code = 500, message = "Se presento un error en la consulta de las estadisticas", response = DeteccionMutantesException.class)})
    public EstadisticasVO consultarEstadisticas() {
        return deteccionMutanteBusiness.obtieneEstadisticas();
    }
}
