/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.business.impl;

import co.com.ml.challenge.business.DeteccionMutanteBusiness;
import co.com.ml.challenge.business.exception.DeteccionMutantesException;
import co.com.ml.challenge.constantes.DeteccionMutanteConstantesEnum;
import co.com.ml.challenge.constantes.ErroresValidacionEnum;
import co.com.ml.challenge.constantes.MetodoAgrupamientoEnum;
import co.com.ml.challenge.dao.EstadisticasValidacion;
import co.com.ml.challenge.repository.EstadisticasValidacionRepository;
import co.com.ml.challenge.vo.EstadisticasVO;
import co.com.ml.challenge.vo.PosicionMatrizVO;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.http.HttpStatus;
import static java.util.stream.Collectors.toList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Slf4j
@Service
public class DeteccionMutanteBusinessImpl implements DeteccionMutanteBusiness {

    private int tamanioMatriz;

    @Autowired
    private EstadisticasValidacionRepository estadisticasValidacionRepository;

    /**
     * Metodo principal que recepciona la validacion de una cadena para
     * verificar si es mutante o humano
     *
     * @param datos
     * @return boolean TRUE en caso que sea mutante, FALSE en caso que sea
     * humano
     */
    @Override
    public boolean isMutant(String[] datos) {
        // Validacion de datos antes de verificar si el ADN corresponde a un mutante
        validaDatosADN(datos);

        // Transformo el dato en un objeto de tipo PosicionMatrizVO y lo agrego a una coleccion de tipo LinkedList 
        List<PosicionMatrizVO> matrizADN = new LinkedList<>();
        tamanioMatriz = (datos.length - 1);
        for (int fila = 0; fila <= tamanioMatriz; fila++) {
            List<String> datosFila = Arrays.asList(datos[fila].trim().split(""));
            int columna = 0;
            for (String dato : datosFila) {
                PosicionMatrizVO posicionMatrizVO = new PosicionMatrizVO();
                posicionMatrizVO.setFila(fila);
                posicionMatrizVO.setColumna(columna);
                posicionMatrizVO.setValor(dato.toUpperCase());
                matrizADN.add(posicionMatrizVO);
                columna++;
            }
        }
        // Se inicia la validacion con las reglas indicadas:
        boolean esMutante = false;
        // Iteramos por los objetos posicionados
        for (int index = 0; index <= tamanioMatriz; index++) {
            esMutante
                    = validacionMutante(matrizADN, index, 0, MetodoAgrupamientoEnum.HORIZONTAL)
                    || validacionMutante(matrizADN, 0, index, MetodoAgrupamientoEnum.VERTICAL)
                    || validacionMutante(matrizADN, index, 0, MetodoAgrupamientoEnum.DIAGONAL_PRINCIPAL_INFERIOR)
                    || validacionMutante(matrizADN, 0, index, MetodoAgrupamientoEnum.DIAGONAL_PRINCIPAL_SUPERIOR)
                    || validacionMutante(matrizADN, 0, (tamanioMatriz - index), MetodoAgrupamientoEnum.DIAGONAL_SECUNDARIA_SUPERIOR)
                    || validacionMutante(matrizADN, index, tamanioMatriz, MetodoAgrupamientoEnum.DIAGONAL_SECUNDARIA_INFERIOR);
            // Si cumple una condicion de mutante, no se sigue validando
            if (esMutante) {
                break;
            }
        }
        log.info("Resultado de validacion: ¿Es mutante?: " + esMutante);
        insertaRegistroEstadistico(Arrays.toString(datos), esMutante);
        return esMutante;
    }

    /**
     * Metodo principal que permite validar una matriz de datos para evaluar si
     * es correcta para su procesamiento Se realizan validaciones de estructura
     * de datos de la matriz, si tiene el tamaño minimo de comparacion, si es
     * cuadrada y si todos sus caracteres son validos
     *
     * @param datos
     */
    private void validaDatosADN(String[] datos) {
        // Calculo el tamaño del parametro, si no es el adecuado se retorna error por datos insuficientes:
        if (datos.length < Integer.parseInt(DeteccionMutanteConstantesEnum.TAMANIO_MINIMO_COMPARACION.valor)) {
            log.error(ErroresValidacionEnum.MENSAJE_TAMANIO_MATRIZ_INVALIDO.mensaje);
            throw new DeteccionMutantesException(HttpStatus.INTERNAL_SERVER_ERROR, ErroresValidacionEnum.MENSAJE_TAMANIO_MATRIZ_INVALIDO.mensaje);
        }
        List<String> datosList = Arrays.asList(datos);
        datosList.stream().map((dato) -> Arrays.asList(dato.split(""))).forEachOrdered((letras) -> {
            // Verifico si la matriz es cuadrada, si no es cuadrada se retorna error por estructura invalida
            if (Integer.compare(letras.size(), datos.length) != 0) {
                log.error(String.format(ErroresValidacionEnum.MENSAJE_NO_MATRIZ_CUADRADA.mensaje, datos.length, letras.size()));
                throw new DeteccionMutantesException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(ErroresValidacionEnum.MENSAJE_NO_MATRIZ_CUADRADA.mensaje, datos.length, letras.size()));
            }
            // Valido si los caracteres incluidos en cada posicion de la matriz estan dentro del rango de letras valido
            letras.forEach((letra) -> {
                if (!Arrays.asList(DeteccionMutanteConstantesEnum.CARACTERES_MATRIZ_VALIDOS.valor.split(DeteccionMutanteConstantesEnum.SEPARADOR_VALORES.valor)).contains(letra.toUpperCase())) {
                    log.error(String.format(ErroresValidacionEnum.MENSAJE_CARACTER_INVALIDO.mensaje, letra.toUpperCase()));
                    throw new DeteccionMutantesException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(ErroresValidacionEnum.MENSAJE_CARACTER_INVALIDO.mensaje, letra.toUpperCase()));
                }
            });
        });
        // Si cumple todas las condiciones, se continua con la validacion
    }

    /**
     * Metodo que permite validar la recurrencia de un caracter en un
     * subconjunto de caracteres representados en una cadena de ADN. Los tipos
     * de validacion son, HORIZONTAL: De arriba hacia abajo, VERTICAL: De
     * izquierda a derecha y OBLICUO: Diagonales principales y secundarias. La
     * validacion se ejecuta desde un punto inicial y se calcula el subconjunto
     * desde esa cordenada especifica.
     *
     * @param matrizADN
     * @param fila
     * @param columna
     * @param caso
     * @return boolean TRUE en caso que sea mutante, FALSE en caso que sea
     * humano
     */
    private boolean validacionMutante(List<PosicionMatrizVO> matrizADN, int fila, int columna, MetodoAgrupamientoEnum caso) {
        boolean retorno = false;
        // Se obtiene el subgrupo de datos para validación según el mecanismo (HORIZONTAL, VERTICAL U OBLICUO)
        List<PosicionMatrizVO> sublista = new LinkedList<>();
        if (MetodoAgrupamientoEnum.HORIZONTAL.equals(caso)) {
            sublista.addAll(matrizADN.stream().filter(prdct -> prdct.getFila() == fila).collect(toList()));
        } else if (MetodoAgrupamientoEnum.VERTICAL.equals(caso)) {
            sublista.addAll(matrizADN.stream().filter(prdct -> prdct.getColumna() == columna).collect(toList()));
        } else if (MetodoAgrupamientoEnum.GRUPO_OBLICUO.contains(caso)) {
            sublista.addAll(obtieneSubListaDiagonales(fila, columna, matrizADN, caso));
        }

        /* Si existen datos validos para comparar, se inicia la evaluacion de caracteres consecutivos */
        if (!sublista.isEmpty()) {
            String valorInicial = sublista.get(0).getValor();
            int contadorRecurrencia = 0;
            for (PosicionMatrizVO posicionMatrizVO : sublista) {
                if (valorInicial.equals(posicionMatrizVO.getValor())) {
                    contadorRecurrencia++;
                } else {
                    /* Si es mayor o igual a 4, significa que es un mutante y rompe la iteracion de validacion */
                    if (contadorRecurrencia >= Integer.parseInt(DeteccionMutanteConstantesEnum.TAMANIO_MINIMO_COMPARACION.valor)) {
                        retorno = true;
                        break;
                    }
                    valorInicial = posicionMatrizVO.getValor();
                    contadorRecurrencia = 0;
                }
            }
        }
        return retorno;
    }

    /**
     * Metodo que permite obtener los datos y posicion para ubicaciones oblicuas
     * dentro de la matriz de ADN. Segun el mecanismo de validacion se recorren
     * desde la diagonal principal (Desde arriba a la izquierda hasta abajo a la
     * derecha) o Diagonal secundaria (Desde arriba a la derecha hasta abajo a
     * la izquierda) y sus complementos superior e inferior.
     *
     * @param fila
     * @param columna
     * @param matriz
     * @param caso
     * @return List<PosicionMatrizVO> Colleccion de atributos correspondientes a
     * la diagonal evaluada.
     */
    private List<PosicionMatrizVO> obtieneSubListaDiagonales(int fila, int columna, List<PosicionMatrizVO> matriz, MetodoAgrupamientoEnum caso) {
        List<PosicionMatrizVO> sublista = new LinkedList<>();
        for (PosicionMatrizVO posicionMatrizVO : matriz) {
            if (posicionMatrizVO.getFila() == fila && posicionMatrizVO.getColumna() == columna) {
                sublista.add(posicionMatrizVO);
                if (caso.equals(MetodoAgrupamientoEnum.DIAGONAL_PRINCIPAL_INFERIOR) || caso.equals(MetodoAgrupamientoEnum.DIAGONAL_PRINCIPAL_SUPERIOR)) {
                    fila++;
                    columna++;
                } else if (caso.equals(MetodoAgrupamientoEnum.DIAGONAL_SECUNDARIA_INFERIOR) || caso.equals(MetodoAgrupamientoEnum.DIAGONAL_SECUNDARIA_SUPERIOR)) {
                    fila++;
                    columna--;
                }
            }
        }
        return sublista;
    }

    /**
     * Metodo que permite insertar un registro de validacion unico sea para
     * mutante como para humano.ç En caso que ya se encuentre en el repositorio,
     * se omite la inserciòn
     *
     * @param cadenaADN: Cadena de ADN que se valido en formato original
     * @param resultado: Booleano con el resultado de la validacion. TRUE en
     * caso que sea mutante, FALSE en caso que sea humano
     */
    private void insertaRegistroEstadistico(String cadenaADN, Boolean resultado) {
        EstadisticasValidacion estadisticasValidacion = new EstadisticasValidacion();
        estadisticasValidacion.setCadenaAdn(cadenaADN);
        estadisticasValidacion.setEsMutante(resultado);
        if (estadisticasValidacionRepository.findByCadenaAdn(cadenaADN) == null) {
            estadisticasValidacionRepository.saveAndFlush(estadisticasValidacion);
            log.info("Se guarda la cadena de ADN {} en el repositorio con resultado {}", cadenaADN, resultado);
        } else {
            log.info("La cadena de ADN {} ya se encuentra en el repositorio", cadenaADN);
        }
    }

    /**
     * Metodo que permite obtener los stats de validacion de mutantes y humanos,
     * asi como el ratio de mutantes
     *
     * @return EstadisticasVO Objeto que tiene la estructura de retorn con
     * cantidad de mutantes, humanos y ratio
     */
    @Override
    public EstadisticasVO obtieneEstadisticas() {
        Integer contadorHumanos = estadisticasValidacionRepository.findHumanoCount();
        Integer contadorMutantes = estadisticasValidacionRepository.findMutanteCount();
        Double ratio = new Double(contadorMutantes) / new Double(contadorHumanos);
        log.info("Cantidad de humanos: {}, Cantidad de mutantes: {}, Ratio: {}", contadorHumanos, contadorMutantes, ratio);
        EstadisticasVO estadisticasVO = new EstadisticasVO(contadorMutantes, contadorHumanos, ratio);
        return estadisticasVO;
    }

}
