# JAVA + SPRING
## ¿Que es un Framework?
-  Un framework es un conjunto de clases e interfaces que nos permiten
resolver uno o varios problemas específicos.

## ¿Que es Spring?
- Spring es un framework más popular de Java ya qué nos permite crear
aplicaciones de alto rendimiento, livianas y reutilizables.
- Spring es muy amplio y prácticamente tine una solución para cada 
problema que pueda surgir al crear una aplicación Java, desde
aplicaciones web, aplicaciones empresariales, aplicaciones en la
nube, etc.

## ¿Que es Spring Boot?
- Debido a la popularidad de Spring surgió la necesidad de simplificar
el uso de este framework.
- De esta idea surge Spring Boot con el objetivo de Simplificar,
estandarizar y unificar el uso de Spring Framework.
- Spring Boot hace muy sencillo empezar a trabajar con Spring.

## ¿Como crear un proyecto con Spring Boot?
- [x] Ingresamos a la pagina de Spring Initializr:
  - <a href="https://start.spring.io/">link Spring</a>
- [x] Selección de administración de librerías seleccionaremos:
  - Maven
- [x] En el tipo de lenguaje de desarrollo será:
  - Java
- [x] En la selección de version de Spring Boot la dejaremos en default:
  - V - 3.2.2
- [x] En ***Project Metadata*** registraremos la data sobre nuestro proyecto:
  - Group: com             
  - Artifact: zona_fit_GYM  
  - Name: Zona-Fit-GYM
  - Description: Sistema Zona Fit GYM
  - Package name: com.zona_fit_GYM
  - Packaging: Jar
  - Java: V - 21
- [x] Dependencies:
  - Spring Data JPS [SQL]
    - Esta dependencia nos permite establecer una conexión hacia
    la base de datos entre varias tareas más.
  - MySQL Driver [SQL]
    - Librería necesaria para la conexión a la base de datos en MyQSL.
  - Lombok [DEVELOPER TOOLS]
    - Esta librería nos permite reducir el código repetitivo en nuestras
    aplicaciones Java. Simplificar métodos como: Getter - Setter - Constructores -
    ToString - etc.
-[x] Para creacion del proyecto daremos clic en:
  - GENERATE
    - Descargará un archivo .zip para posteriormente ser descomprimido
    y puedas inicializarlos en tu IDE de preferencia.

## ARQUITECTURA SPRING
- [x] PRESENTACIÓN
  - ***@SpringBoot*** --> Application
- [x] SERVICIO
  - ***@Service***
- [x] DATOS
  - ***@Repository***
- [x] ENTIDAD
  - ***@Entity*** <-----> DB