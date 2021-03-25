# Evaluación ADN Mutante ML
Challenge de Mercado Libre.

Este proyecto permite evaluar una cadena de ADN a partir de un arreglo de caracteres.

## Ejecución 🚀

El proyecto tiene dos API's en la nube de [Google App Engine](https://cloud.google.com/appengine).

1. Mutant

API que permite verificar si una cadena de ADN corresponde a un mutante o a un humano.<br/>
https://deteccion-mutante-challenge.uc.r.appspot.com/mutant<br/>
Tipo: **HTTP:POST**<br/>
@Param: {dna}: Arreglo de strings con la cadena a evaluar<br/>

* Ejemplo @Param mutante:
  ```
  {"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
  ```

* Ejemplo @Param humano:
  ```
  {"dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]}
  ```

2. Stats

API que permite verificar las estadisticas de validación de cadenas de ADN y el ratio entre mutantes y humanos.<br/>
https://deteccion-mutante-challenge.uc.r.appspot.com/stats<br/>
Tipo: **HTTP:GET**<br/>
@Param: Ninguno<br/>

Puedes consultar el [swagger](https://deteccion-mutante-challenge.uc.r.appspot.com/swagger-ui/#/) documental de las APIs.


## Compilación 📦

Para compilar el proyecto se debe ejecutar el siguiente comando sobre el root del proyecto en un cmd o sh:

```
mvn clean install
```

Para ejecutar el reporte de covertura de pruebas, se debe ejecutar el siguiente comando sobre el root del proyecto en un cmd o sh:

```
mvn test jacoco:report
```

Muchas gracias a Mercado Libre por la oportunidad 🤓

Desarrollado por [Jorge David Quiroga Rojas](https://github.com/jdquiroga) 😊








