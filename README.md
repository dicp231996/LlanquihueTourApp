# LlanquihueTourApp
Versión 2.0.0 de la aplicación para Llanquihue tour, se añaden clase que permite leer datos desde archivos txt y procesarlos para crear instancias de las clases de entities
# LlanquihueTourApp2.0.0
# LlanquihueTour
 
## Descripción
Proyecto Java orientado a objetos para representar la compocision y herencia de clases
 
## Estructura
├── resources/
│   ├── base_datos_avistamiento.txt
│   ├── base_datos_guias_turisticos.txt
│   ├── base_datos_paseos_lacustres.txt
│   ├── base_datos_ruta_patrimonial.txt
│   ├── base_datos_tours.txt
│   ├── base_datos_trekking.txt
│   └── base_datos_turistas.txt
└── src/
├── app/
│   └── LlanquihueTour.java
├── data/
│   ├── GestionFiltro.java
│   ├── GestorDatos.java
│   └── GestorInstancias.java
├── model/
│   ├── core/
│   │   ├── Persona.java
│   │   └── ServicioTuristico.java
│   ├── entities/
│   │   ├── actors/
│   │   │   ├── GuiaTuristico.java
│   │   │   └── Turista.java
│   │   └── services/
│   │       ├── AvistamientoHumedales.java
│   │       ├── PaseoLacustre.java
│   │       ├── RutaPatrimonial.java
│   │       └── TrekkingAltaMontania.java
│   └── valueobjects/
│       ├── Direccion.java
│       ├── GrupoTuristico.java
│       └── Rut.java
└── util/
├── adddata/
│   └── AgregarRegistros.java
├── core/
│   ├── metadata/
│   │   ├── Campo.java
│   │   ├── TipoDato.java
│   │   └── TipoEntidad.java
│   ├── rules/
│   │   └── GestorValidaciones.java
│   └── ui/
│       └── SelectorBaseDatos.java
├── queries/
│   └── MenuConsultas.java
├── removedata/
│   └── QuitarRegistro.java
└── MenuPrincipal.java
 
## Cómo ejecutar
Abrir el proyecto en IntelliJ IDEA y ejecutar la clase LlanquihueTour ubicada en el paquete app.
Se abrira un menu interactivo por consola en donde se pueden agregar registros, quitarlos de la de datos elegida o filtrar todas las instancias por medio de queries.

## En la clase LlanquihueTour esta la clase MenuPrincipal, la cual nos permite interactuar con el sistema dandonos la opcion de agregar registros, eliminar registros o filtrar las bases de datos
 
## Autor
Daniel Ignacio Campos Pérez.
