/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author usuario
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EstadisticasValidacion")
@NamedQuery(name = "EstadisticasValidacion.findAll", query = "SELECT E FROM EstadisticasValidacion E")
@NamedQuery(name = "EstadisticasValidacion.findByCadenaAdn", query = "SELECT E FROM EstadisticasValidacion E where E.cadenaAdn = :cadenaAdn")
@NamedQuery(name = "EstadisticasValidacion.findHumanoCount", query = "SELECT COUNT(E) FROM EstadisticasValidacion E where E.esMutante = false")
@NamedQuery(name = "EstadisticasValidacion.findMutanteCount", query = "SELECT COUNT(E) FROM EstadisticasValidacion E where E.esMutante = true")
public class EstadisticasValidacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cadena_adn", nullable = false)
    private String cadenaAdn;

    @Column(name = "es_mutante", nullable = false)
    private Boolean esMutante;

}
