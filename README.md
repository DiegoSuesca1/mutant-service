
# Mutants ADN

Este repositorio contiene el código de los servicios rest que análisan secuencias de ADN para identificar si es un ADN mutante o humano.

##1. Análisis ADN
Servicio que retorna HTTP 200-OK en caso de que el adn corresponda a un mutante ó
403-Forbidden en caso de que el adn sea humano.
#####Servicio rest POST /mutant
`https://peaceful-depths-02275.herokuapp.com/mutant/`

#####Json body:
Representa el adn a analizar, debe contener una matriz "dna" de texto N * N con las letras A,T,G,C, si existen dos secuencias de 4 letras iguales ya sea horizontal, vertical u oblicua, se considera que es un mutante.
######Ejemplo 1: Humano
```
{
	"dna":["AATTTTA",
				 "CAGTGCT",
				 "CAAGGGC",
				 "ACCTTGC",
				 "ACTAGAT",
				 "CCTCTGT",
				 "CTTCTGT"]
}
```
######Ejemplo 2: Mutante
```
{
	"dna":["AATTTTA",
				 "CAGTGCT",
				 "CAAGGGC",
				 "ACCTTGC",
				 "ACTAGAT",
				 "CCTCTGT",
				 "CCTCTGT"]
}
```


##2. Estadísticas
Servicio que retorna objeto Json con información estadística de los análisis ejecutados.
#####Servicio rest GET /starts
`https://peaceful-depths-02275.herokuapp.com/stats`

#####Resultado
Datos de retorno:
- **countMutantDna**: Cantidad de mutantes detectados
- **countHumanDna**: Cantidad de humanos detectados
- **ratio**: Proporción de resultados
######Ejemplo
```
{
	"countMutantDna": 6,
	"countHumanDna": 8,
	"ratio": 0.75
}
```
