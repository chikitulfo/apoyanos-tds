![Apóyanos Logo](https://raw.githubusercontent.com/chikitulfo/apoyanos-tds/master/src/main/java/recursos/apoyanos.png)

# Apóyanos

Proyecto de construcción de una aplicación de crowdfunding para la asignatura TDS de la Universidad de Murcia. Con el fin de interiorizar y hacer uso de patrones de diseño y orientación a objetos, el objetivo de este proyecto es implementar una aplicación de escritorio inspirada en las numerosas plataformas de micro-mecenazgo (crowdfunding) que han surgido en los últimos años.

Realizado por Juanjo Andreu Blázquez (Chikitulfo) y Francisco Herrera Zapata (Herrera13).

##### Índice
  - [Descripción de la funcionalidad](#funcionalidad)  
  - [Diseño de la aplicación](#diseno)  
  - [Diagrama de clases](#clases)  
  - [Diagramas de secuencia](#secuencia)  

 

<a name="funcionalidad"/>
## Descripción de la funcionalidad

Cualquier usuario registrado puede crear un proyecto indicando, entre otras, las siguientes informaciones: una
descripción, la financiación necesaria, el plazo para conseguir esta financiación y las recompensas. Un proyecto
puede establecer varias recompensas, cada una de las cuales es definida por una cantidad de dinero y una
descripción de qué obtendría el usuario si apoya el proyecto con esa cantidad. A partir de su creación los
proyectos entran en la fase de votación en la que pueden ser votados por los usuarios (mecenas) y si consiguen
un número determinado de votos (100 en el caso de lanzanos.com) entonces pasan a la fase de financiación. En
esta fase los usuarios pueden financiar (apoyar) un proyecto eligiendo una recompensa. Una vez acabado el plazo
establecido para la financiación, los usuarios reciben una notificación del sistema que les indica si el proyecto ha
conseguido o no la financiación necesaria. Si se ha alcanzado dicha financiación se carga a los mecenas el pago
de la cantidad con la que apoyaron. La realización de los pagos, que pueden realizarse a través de Paypal o
tarjeta bancaria no será considerada en la aplicación. La entrega de las recompensas queda fuera del ámbito de
este tipo de plataformas y no debe ser considerada en el desarrollo de ¡Apóyanos!

El **documento de requisitos** completo [se puede encontrar aquí.](../../raw/master/docs/TDS-Practica-Crowdfunding.pdf) 
<a name="diseno"/>
# Diseño de la aplicación

En [este enlace](../../raw/master/docs/ApoyanosMemoria-AndreuBlazquez-HerreraZapata.pdf)  hay un **pdf completo con el diseño y solución implementadas**, en esta sección del readme se hace un breve resumen de dicho documento.

La aplicación de crowdfunding o financiación colectiva Apóyanos   ha   sido   diseñada   utilizando  
una   arquitectura   de   modelo-vista-controlador (MVC),   de   forma   que   se   ha   separado   en   diversos  
paquetes   independientes   el   modelo,   la   vista   y   el   controlador.   El   modelo   sigue   la   estructura  
vista   en   el diagrama de   clases   del   dominio.   El   controlador   es   el   punto   de   entrada   que  
permite   a   la   vista   llevar   a   cabo   operaciones   sobre   el   modelo,   y   garantiza   que   se   satisfagan  
ciertas   restricciones.   La   vista   realiza   las   operaciones   sobre   el   modelo   a   través   del   controlador,   y  
no   interactúa   directamente   con   él.   De   este   modo,   el   modelo   es   independiente   del   controlador   y  
de   la   vista,   y   podría   funcionar   con   distintas   implementaciones   de   vista   haciendo   solicitudes   al  
controlador.

<a name="clases"/>
##Diagrama de clases del modelo

El modelo de la aplicación se estructura de la siguiente manera.
![Diagrama de clases](https://raw.githubusercontent.com/chikitulfo/apoyanos-tds/master/docs/Diagrama_clases.png)

<a name="secuencia"/>
## Diagramas de secuencia.

Se han incluido también los diagramas de secuencia para votar a un proyecto, así como para apoyarlo con una cantidad y recompensa específicas.

![Diagrama de secuencia de voto a un proyecto](https://raw.githubusercontent.com/chikitulfo/apoyanos-tds/master/docs/VotarProyecto.png)

![Diagrama de secuencia de apoyo a un proyecto](https://raw.githubusercontent.com/chikitulfo/apoyanos-tds/master/docs/ApoyarProyecto.png)

##

