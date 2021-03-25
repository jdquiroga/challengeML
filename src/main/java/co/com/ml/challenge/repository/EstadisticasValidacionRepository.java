/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.repository;

import co.com.ml.challenge.dao.EstadisticasValidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author usuario
 */
public interface EstadisticasValidacionRepository extends JpaRepository<EstadisticasValidacion, Long> {

    public EstadisticasValidacion findByCadenaAdn(@Param("cadenaAdn") String cadenaAdn);

    public Integer findHumanoCount();

    public Integer findMutanteCount();

}
