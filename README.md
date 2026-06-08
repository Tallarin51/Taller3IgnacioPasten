# Taller 3 - Sistema de Magos y Hechizos

## Descripción del proyecto

Este proyecto corresponde al Taller 3 de Programación Avanzada.

El sistema permite administrar magos y hechizos mediante una aplicación de consola desarrollada en Java. El programa trabaja con archivos `.txt`, desde donde carga la información inicial de magos y hechizos, y también guarda los cambios realizados desde el panel administrador.

El sistema cuenta con dos paneles principales:

- **Panel Administrador:** permite agregar, modificar y eliminar magos y hechizos.
- **Panel Analista:** permite consultar rankings, listados y puntuaciones de magos y hechizos.

Cada hechizo pertenece a un tipo elemental: Fuego, Tierra, Planta o Agua. Según su tipo, el hechizo posee propiedades específicas y una fórmula distinta para calcular su puntuación.

## Integrante

- **Nombre:** Ignacio Antonio Pastén Durán
- **RUT:** 22.067.577-7
- **Carrera:** Ingeniería Civil en Computación e Informática
- **Usuario GitHub:** Tallarin51

## Estructura del proyecto

```txt
Taller3IgnacioPasten
│
├── Taller
│   ├── dominio
│   │   ├── Hechizo.java
│   │   ├── HechizoFuego.java
│   │   ├── HechizoTierra.java
│   │   ├── HechizoPlanta.java
│   │   ├── HechizoAgua.java
│   │   └── Mago.java
│   │
│   └── logica
│       ├── App.java
│       ├── Sistema.java
│       └── SistemaImpl.java
│
├── Hechizos.txt
├── Magos.txt
├── DiagramaClases.pdf
├── ModeloDominio.pdf
└── README.md
```

## Paquetes y clases principales

### Paquete `dominio`

Contiene las clases que representan los elementos principales del problema.

- **Mago:** representa a un mago y la lista de hechizos que domina.
- **Hechizo:** clase abstracta que contiene los atributos comunes de todos los hechizos.
- **HechizoFuego:** representa hechizos de tipo Fuego.
- **HechizoTierra:** representa hechizos de tipo Tierra.
- **HechizoPlanta:** representa hechizos de tipo Planta.
- **HechizoAgua:** representa hechizos de tipo Agua.

### Paquete `logica`

Contiene las clases encargadas del funcionamiento del sistema.

- **App:** clase principal del programa. Lee archivos, muestra menús y solicita datos al usuario.
- **Sistema:** interfaz que define las operaciones principales del sistema.
- **SistemaImpl:** implementación de la interfaz `Sistema`. Administra listas, búsquedas, rankings, modificaciones y guardado de archivos.

## Archivos utilizados

### `Hechizos.txt`

Contiene los hechizos disponibles en el sistema, junto con su tipo, daño y propiedades específicas.

Formato general:

```txt
NombreHechizo;Tipo;Daño;Propiedades
```

Ejemplos:

```txt
Ascuas;Fuego;77;5
Tumba Rocas;Tierra;18;40
Polen Somnífero;Planta;81;3,10
Escaldar;Agua;110;45,360
```

### `Magos.txt`

Contiene los magos registrados y los hechizos que domina cada uno.

Formato general:

```txt
NombreMago;Hechizo1|Hechizo2|HechizoN
```

Ejemplo:

```txt
Pruno;Sofoco|Disparo Lodo|Pirotecnia|Vórtice Ígneo
```

## Funcionalidades

### Panel Administrador

Permite realizar las siguientes operaciones:

1. Agregar mago.
2. Modificar mago.
3. Eliminar mago.
4. Agregar hechizo.
5. Modificar hechizo.
6. Eliminar hechizo.

Cada cambio realizado se guarda en el archivo correspondiente.

### Panel Analista

Permite realizar las siguientes consultas:

1. Top 10 mejores hechizos.
2. Top 3 mejores magos.
3. Mostrar todos los hechizos.
4. Mostrar todos los magos.
5. Mostrar todos los hechizos junto a su puntuación.
6. Mostrar todos los magos junto a su puntuación.

## Cálculo de puntuación

Cada tipo de hechizo calcula su puntuación de forma distinta:

- **Fuego:** `Daño * DuracionQuemadura`
- **Tierra:** `(Daño * MejoraDefensa) / 2`
- **Planta:** `Daño + (DuracionStun * CantPlantas)`
- **Agua:** `(Daño + CantidadHeal + PresionAgua) * 2`

La puntuación de un mago se calcula sumando las puntuaciones de todos los hechizos que domina.

## Consideraciones

- El proyecto utiliza Programación Orientada a Objetos.
- Se utiliza herencia mediante la clase abstracta `Hechizo` y sus subclases.
- Se utiliza una interfaz llamada `Sistema`.
- La lógica principal se separa entre `App`, `Sistema` y `SistemaImpl`.
- Los datos se leen desde archivos `.txt`.
- Los cambios realizados desde el panel administrador se guardan en los archivos correspondientes.
- El proyecto incluye el modelo de dominio y el diagrama de clases en formato PDF.
