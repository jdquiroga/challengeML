/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author usuario
 */
@ApiModel
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticasVO {

    private Integer count_mutant_dna;
    private Integer count_human_dna;
    private Double ratio;

}