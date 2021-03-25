/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.business;

import co.com.ml.challenge.vo.EstadisticasVO;

/**
 *
 * @author usuario
 */
public interface DeteccionMutanteBusiness {

    public boolean isMutant(String[] datos);

    public EstadisticasVO obtieneEstadisticas();

}
