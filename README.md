## Taller de productividad basada en herramientas tecnológicas
## Descripción
Actualmente la empresa Aceros tiene un sistema de administración de viajes en el cual se registran los datos de viajes realizados, los gatos y consumos que el viaje genera. Es necesario generar notificaciones automáticas que ejecute los servicios rest que la empresa proveerá.
## Problema 
La empresa requiere que en base a una configuración en su sistema se envíen las notificaciones correspondientes, basados en una configuración que también el sistema ya maneja, actualmente tienen que entrar al sistema y dar un click en un botón para enviar la notificación, pero si un día no se ingresa al sistema ya sea porque el administrador no está o es fines de semana las notificaciones no se envían.

## Requerimientos
*	Configuración para rango de ejecuciones, por segundos, minutos u horas
*	Lectura de notificaciones
*	Procesamiento de notificaciones
#### Solución
*	Se diseño una aplicación java de consola en la que se configuran las periodicidades de ejecución y algunas configuraciones para ejecutar los servicios rest del cliente, el servicio obtiene las configuraciones desde el servicio rest con las credenciales compartidas con el usuario
*	El sistema procesa las notificaciones y ejecuta los métodos envío


#### Versión de java
*	La versión más actual de java
#### Paquetes adicionales
*	No se requiere ningún paquete, el proyecto tiene agregadas las referencias de json de los repositorios de Maven, para compilar por primera vez es necesario estar conectado a internet para que se actualizan

#### Base de datos
*	El sistema no usa base de datos ya que se usan servicios rest del cliente
#### Instalación
*	Clonar o descargar el proyecto del repositorio git
*	Para las ejecuciones manuales debemos correr el proyecto desde NetBeans, en el archivo de configuración del proyecto debemos configurar los datos del servicio rest y credenciales del cliente y la periodicidad de ejecución
*	Para correr el proyecto sin NetBeans debemos correr el archivo jar desde la consola del sistema operativo o con doble click, recordad que debemos tener java jdk en su última versión instalada
Como es un .jar el software no se instala, solo se ejecuta en el ambiente de JAVA por eso es importante que tengamos la última versión de java instalada en nuestro equipo.
## Configuración
* Debemos instalar la última versión de jdk de java
*	En el archivo de configuración del proyecto tomar y editar con block de notas configurar la periodicidad de ejecución en base al manual del archivo y las credenciales del cliente
*	Configurar el jar con un Schedule del sistema operativo que se ejecute cada vez que se inicie, es compatible con cualquier sistema operativo, el servidor donde se configure deberá estar conectado a internet si no, no funcionara el sistema ya que el servicio rest del cliente está en la nube
*	el código se entregará al cliente por lo cual no podrán usar el repositorio personal no se administrará sobre git
## Uso
El sistema funcional se entregará al cliente y también el código no será expuesto por git por la información confidencial, el versionamiento y administración se llevará por la empresa.
