/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.constantes;

import java.util.EnumSet;

/**
 *
 * @author usuario
 */
public enum MetodoAgrupamientoEnum {

    HORIZONTAL,
    VERTICAL,
    DIAGONAL_PRINCIPAL_INFERIOR,
    DIAGONAL_PRINCIPAL_SUPERIOR,
    DIAGONAL_SECUNDARIA_INFERIOR,
    DIAGONAL_SECUNDARIA_SUPERIOR;

    public static EnumSet<MetodoAgrupamientoEnum> GRUPO_OBLICUO = EnumSet.of(DIAGONAL_PRINCIPAL_INFERIOR, DIAGONAL_PRINCIPAL_SUPERIOR, DIAGONAL_SECUNDARIA_INFERIOR, DIAGONAL_SECUNDARIA_SUPERIOR);
}
