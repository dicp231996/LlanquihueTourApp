# LlanquihueTourApp
Versión 1.1.0 de la aplicación para Llanquihue tour, se añaden clase que permite leer datos desde archivos txt y procesarlos para crear instancias de las clases de entities
# LlanquihueTourApp1.3.0
# LlanquihueTour
 
## Descripción
Proyecto Java orientado a objetos para representar la compocision y herencia de clases
 
## Estructura
resourse/
|── base_datos_guias_turisticos.txt
|── base_datos_tours.txt
└──base_datos_turistas.txt
src/
|── data/
|   |── GestionFiltro.java
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
|   |   |── TipoEntidad.java
|   |   |── TipoDato.java
|   |   |── Campo.java
|   |   |── GestorValidaciones.java
|   |   └── EscritorBaseDatos.java   
|   ├── adddata/
|   |    └── AgregarRegistro.java
|   └── removedata/
|       └── QuitarRegistro.java
└── ui/
    └── LlanquihueTour.java
 
## Cómo ejecutar
Abrir el proyecto en IntelliJ IDEA y ejecutar la clase LlanquihueTour ubicada en el paquete ui.

## En la clase existe un filtro dinamico, para proposito didactico esta configurado para filtrar los grupos turisticos que sean de dificultad avanzada, almacenando los objectos en un ArrayList que sera impreso en la consola
 
## Autor
Daniel Ignacio Campos Pérez.
