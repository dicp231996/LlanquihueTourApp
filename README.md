# LlanquihueTourApp
Versión 2.0.0 de la aplicación para Llanquihue tour, se añaden clase que permite leer datos desde archivos txt y procesarlos para crear instancias de las clases de entities
# LlanquihueTourApp2.0.0
# LlanquihueTour
 
## Descripción
Proyecto Java orientado a objetos para representar la compocision y herencia de clases
 
## Estructura
resourses/
|── base_datos_guias_turisticos.txt
|── base_datos_tours.txt
└──base_datos_turistas.txt
src/
|── data/
|   |── GestionFiltro.java
|   |── GestorInstancias.java
|   └── GestorDatos.java
├── model/
│   ├── core/
|   |   └── Persona.java
│   ├── entities/
|   |   |── GuiaTuristico.java
|   |   └──Turista.java
│   └── valueobjects/
|       |── Direccion.java
|       |── GrupoTuristico.java
|       └──Rut.java
|── util/
|   ├── core/
|   |   |── metadata/ 
|   |   |   |── Campo.java
|   |   |   |── TipoDato.java
|   |   |   └── TipoEntidad.java
|   |   |── rules/
|   |   |   └── GestorValidacion.java
|   |   └── ui/
|   |       └── GestorValidaciones.java
|   ├── queries/
|   |   └── MenuConsultas.java
|   ├── adddata/
|   |    └── AgregarRegistro.java
|   |── removedata/
|   |   └── QuitarRegistro.java
|   └── MenuPrincipal.java
└── app/
    └── LlanquihueTour.java
 
## Cómo ejecutar
Abrir el proyecto en IntelliJ IDEA y ejecutar la clase LlanquihueTour ubicada en el paquete app.

## En la clase LlanquihueTour esta la clase MenuPrincipal, la cual nos permite interactuar con el sistema dandonos la opcion de agregar registros, eliminar registros o filtrar las bases de datos
 
## Autor
Daniel Ignacio Campos Pérez.
